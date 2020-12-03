package main.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.util.HashMap;

public class ReportDetails {
  @CsvBindByName(column = "Employee Id")
  @CsvBindByPosition(position = 0)
  private String employeeId;

  @CsvBindByName(column = "Employee Name")
  @CsvBindByPosition(position = 1)
  private String employeeName;

  @CsvBindByName(column = "Model")
  @CsvBindByPosition(position = 2)
  private String model;

  @CsvBindByName(column = "Purchase Date")
  @CsvBindByPosition(position = 3)
  private String purchaseDate;

  private HashMap<String, Integer> minutesMap = new HashMap<>();

  private HashMap<String, Float> dataMap = new HashMap<>();

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(String purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public HashMap<String, Integer> getMinutesMap() {
    return minutesMap;
  }

  public void setMinutesMap(HashMap<String, Integer> minutesMap) {
    this.minutesMap = minutesMap;
  }

  public HashMap<String, Float> getDataMap() {
    return dataMap;
  }

  public void setDataMap(HashMap<String, Float> dataMap) {
    this.dataMap = dataMap;
  }
}
