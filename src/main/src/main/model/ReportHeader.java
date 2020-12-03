package main.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class ReportHeader {
  @CsvBindByName(column = "Report Run Date")
  @CsvBindByPosition(position = 0)
  private String reportRunDate;

  @CsvBindByName(column = "Number of Phones")
  @CsvBindByPosition(position = 1)
  private String numberOfPhones;

  @CsvBindByName(column = "Total Minutes")
  @CsvBindByPosition(position = 2)
  private String totalMinutes;

  @CsvBindByName(column = "Total Data")
  @CsvBindByPosition(position = 3)
  private String totalData;

  @CsvBindByName(column = "Average Minutes")
  @CsvBindByPosition(position = 4)
  private String averageMinutes;

  @CsvBindByName(column = "Average Data")
  @CsvBindByPosition(position = 5)
  private String averageData;

  public String getReportRunDate() {
    return reportRunDate;
  }

  public void setReportRunDate(String reportRunDate) {
    this.reportRunDate = reportRunDate;
  }

  public String getNumberOfPhones() {
    return numberOfPhones;
  }

  public void setNumberOfPhones(String numberOfPhones) {
    this.numberOfPhones = numberOfPhones;
  }

  public String getTotalMinutes() {
    return totalMinutes;
  }

  public void setTotalMinutes(String totalMinutes) {
    this.totalMinutes = totalMinutes;
  }

  public String getTotalData() {
    return totalData;
  }

  public void setTotalData(String totalData) {
    this.totalData = totalData;
  }

  public String getAverageMinutes() {
    return averageMinutes;
  }

  public void setAverageMinutes(String averageMinutes) {
    this.averageMinutes = averageMinutes;
  }

  public String getAverageData() {
    return averageData;
  }

  public void setAverageData(String averageData) {
    this.averageData = averageData;
  }
}
