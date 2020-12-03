package main.service;

import com.google.common.collect.Lists;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import main.model.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

  /**
   * Generates report from two identified files. We'll be hardcoding CellPhone.csv and CellPhoneUsageByMonth.csv in the
   * calling class.
   */
  public void generateReport(String inputEntities, String inputData) {
    String[] mainHeaders = new String[]{"Report Run Date", "Number of Phones", "Total Minutes", "Total Data", "Average Minutes", "Average Data",};
    List<String[]> headerList = new ArrayList<String[]>();
    headerList.add(mainHeaders);
    List<String[]> detailHeaderList = new ArrayList<String[]>();
    List<CellPhone> cellPhoneEntityList = new ArrayList<>();
    List<CellPhoneUsageByMonth> cellPhoneDataList = new ArrayList<>();

    try(// create and open output file, attach writer, auto-closes due to try-with-resource
      Writer writer = new FileWriter("yourfile.csv")
    ){
      // get files matching input strings, throw exception if no file found
      cellPhoneEntityList = new CsvToBeanBuilder(new FileReader(inputEntities))
          .withType(CellPhone.class).build().parse();
      cellPhoneDataList = new CsvToBeanBuilder(new FileReader(inputData))
          .withType(CellPhoneUsageByMonth.class).build().parse();

      ReportHeader reportHeader = new ReportHeader();
      List<ReportDetails> reportDetailsList = new ArrayList<>();
      Set<String> dateSet = new HashSet<>();

      int totalMinutes = 0;
      float totalData = 0.0f;
      int phones = cellPhoneEntityList.size();

      for (CellPhoneUsageByMonth cellPhoneUsageByMonth : cellPhoneDataList) {
        totalData += cellPhoneUsageByMonth.getTotalData();
        totalMinutes += cellPhoneUsageByMonth.getTotalMinutes();
        dateSet.add(cellPhoneUsageByMonth.getDate());
      }

      // generate ReportHeader record List
      reportHeader.setAverageData(Float.toString(totalData / phones));
      reportHeader.setAverageMinutes(Float.toString((totalMinutes * 1.0f) / phones));
      reportHeader.setNumberOfPhones(Integer.toString(phones));
      reportHeader.setReportRunDate(Timestamp.from(Instant.now()).toString());
      reportHeader.setTotalData(Float.toString(totalData));
      reportHeader.setTotalMinutes(Integer.toString(totalMinutes));

      // generate ReportDetails record List
      for(CellPhone cellPhone : cellPhoneEntityList){
        ReportDetails reportDetails = new ReportDetails();

        List<CellPhoneUsageByMonth> dataList = cellPhoneDataList.stream().filter(
            it -> it.getEmplyeeId() == cellPhone.getEmployeeId()
        ).collect(Collectors.toList());

        if (dataList.size() == 0) {
          throw new Exception("Employee Data missing");
        }

        reportDetails.setEmployeeId(Integer.toString(cellPhone.getEmployeeId()));
        reportDetails.setEmployeeName(cellPhone.getEmployeeName());
        reportDetails.setModel(cellPhone.getModel());
        reportDetails.setPurchaseDate(cellPhone.getPurchaseDate());

        dataList.forEach( dataItem -> {
          dateSet.add(dataItem.getDate());
          reportDetails.getDataMap().put(dataItem.getDate(), dataItem.getTotalData());
          reportDetails.getMinutesMap().put(dataItem.getDate(), dataItem.getTotalMinutes());
        });

        reportDetailsList.add(reportDetails);
      }

      List<String> dateList = Lists.newArrayList(dateSet);
      dateList.sort(new Comparator<String>() {
        final DateFormat f = new SimpleDateFormat("MM/dd/yyyy");

        @Override
        public int compare(String o1, String o2) {
          try {
            return f.parse(o1).compareTo(f.parse(o2));
          } catch (ParseException e) {
            throw new IllegalArgumentException(e);
          }
        }
      });

      // output ReportHeader records
      // output ReportDetails records
      CSVWriter csvWriter = new CSVWriter(writer);
      csvWriter.writeAll(headerList);

      StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
      beanToCsv.write(reportHeader);

      List<String> detailHeaders = Lists.newArrayList("Employee Id", "Employee Name", "Model", "Purchase Date");

      dateList.forEach(date -> {
        detailHeaders.add("Minutes Usage " + date);
      });

      dateList.forEach(date -> {
        detailHeaders.add("Data Usage " + date);
      });

      String[] headerArray = detailHeaders.toArray(new String[detailHeaders.size()]);
      detailHeaderList.add(headerArray);
      csvWriter.writeAll(detailHeaderList);

      List<String[]> detailLines = new ArrayList<>();

      reportDetailsList.forEach(
          item -> {
            try {
              List<String> detailLine = Lists.newArrayList(item.getEmployeeId(), item.getEmployeeName(), item.getModel(), item.getPurchaseDate());
              for (String s : dateSet) {
                if(item.getMinutesMap().get(s) != null) {
                  detailLine.add(Integer.toString(item.getMinutesMap().get(s)));
                } else {
                  detailLine.add("");
                }
              }
              for (String s : dateSet) {
                if(item.getMinutesMap().get(s) != null) {
                  detailLine.add(Float.toString(item.getDataMap().get(s)));
                } else {
                  detailLine.add("");
                }
              }
              detailLines.add(detailLine.toArray(new String[detailLine.size()]));
            } catch (Exception e){
              e.printStackTrace();
            }
          }
      );

      csvWriter.writeAll(detailLines);

    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
