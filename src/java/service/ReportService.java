package java.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.model.CellPhone;
import java.model.CellPhoneUsageByMonth;
import java.model.ReportDetails;
import java.model.ReportHeader;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ReportService {

  /**
   * Generates report from two identified files. We'll be hardcoding CellPhone.csv and CellPhoneUsageByMonth.csv in the
   * calling class.
   */
  public void generateReport(String inputEntities, String inputData) throws Exception {
    String[] mainHeaders = new String[]{"Report Run Date", "Number of Phones", "Total Minutes", "Total Data", "Average Minutes", "Average Data"};
    String[] detailHeaders = new String[]{"Employee Id", "Employee Name", "Model", "Purchase Date", "Minutes Usage", "Data Usage"};
    List<String[]> headerList = new ArrayList<String[]>();
    headerList.add(mainHeaders);
    List<String[]> detailHeaderList = new ArrayList<String[]>();
    detailHeaderList.add(detailHeaders);

    // get files matching input strings, throw exception if no file found
    List<CellPhone> cellPhoneEntityList = new CsvToBeanBuilder(new FileReader(inputEntities))
        .withType(CellPhone.class).build().parse();
    List<CellPhoneUsageByMonth> cellPhoneDataList = new CsvToBeanBuilder(new FileReader(inputData))
        .withType(CellPhoneUsageByMonth.class).build().parse();

    ReportHeader reportHeader = new ReportHeader();
    List<ReportDetails> reportDetailsList = new ArrayList<>();

    AtomicInteger totalMinutes = new AtomicInteger();
    float totalData = 0.0f;
    int phones = cellPhoneEntityList.size();

    for (CellPhoneUsageByMonth cellPhoneUsageByMonth : cellPhoneDataList) {
      cellPhoneUsageByMonth.getTotalMinutes().forEach(it -> totalMinutes.addAndGet(it));

      for(Float data : cellPhoneUsageByMonth.getTotalData()){
        totalData += data;
      }
    }

    // generate ReportHeader record List
    reportHeader.setAverageData(Float.toString(totalData/phones));
    reportHeader.setAverageMinutes(Float.toString((totalMinutes.get() *1.0f)/phones));
    reportHeader.setNumberOfPhones(Integer.toString(phones));
    reportHeader.setReportRunDate(Timestamp.from(Instant.now()).toString());
    reportHeader.setTotalData(Float.toString(totalData));
    reportHeader.setTotalMinutes(Integer.toString(totalMinutes.get()));

    // generate ReportDetails record List
    for (CellPhoneUsageByMonth cellPhoneData : cellPhoneDataList) {
      ReportDetails reportDetails = new ReportDetails();
      float cellData = 0f;
      int cellMinutes = 0;
      List<CellPhone> employeeData = cellPhoneEntityList.stream().filter(it
          -> it.getEmployeeId() == cellPhoneData.getEmplyeeId()).collect(Collectors.toList());

      if(employeeData.size() == 0){
        throw new Exception("Employee Data missing");
      }

      List<CellPhoneUsageByMonth> relevantData = cellPhoneDataList.stream().filter(it ->
          it.getEmplyeeId() == employeeData.get(0).getEmployeeId()).collect(Collectors.toList());

      reportDetails.setEmployeeId(Integer.toString(cellPhoneData.getEmplyeeId()));
      reportDetails.setEmployeeName(employeeData.get(0).getEmployeeName());
      reportDetails.setModel(employeeData.get(0).getModel());
      reportDetails.setPurchaseDate(employeeData.get(0).getPurchaseDate());
      reportDetails.setDataUsage((String[]) cellPhoneData.getTotalData().toArray());
      reportDetails.setMinutesUsage((String[]) cellPhoneData.getTotalMinutes().toArray());
    }

    // create and open output file, attach writer
    Writer writer = new FileWriter("yourfile.csv");
    // output ReportHeader records
    // output ReportDetails records
    CSVWriter csvWriter = new CSVWriter(writer);
    csvWriter.writeAll(headerList);

    StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
    beanToCsv.write(reportHeader);
    writer.close();

    // close file
  }
}
