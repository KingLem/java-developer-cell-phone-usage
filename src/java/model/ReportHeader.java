package java.model;

public class ReportHeader {
  private String reportRunDate;
  private String numberOfPhones;
  private String totalMinutes;
  private String totalData;
  private String averageMinutes;
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
