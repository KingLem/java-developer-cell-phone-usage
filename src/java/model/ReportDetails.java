package java.model;

public class ReportDetails {
  private String employeeId;
  private String employeeName;
  private String model;
  private String purchaseDate;
  private String[] minutesUsage;
  private String[] dataUsage;

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

  public String[] getMinutesUsage() {
    return minutesUsage;
  }

  public void setMinutesUsage(String[] minutesUsage) {
    this.minutesUsage = minutesUsage;
  }

  public String[] getDataUsage() {
    return dataUsage;
  }

  public void setDataUsage(String[] dataUsage) {
    this.dataUsage = dataUsage;
  }
}
