package java.model;

import com.opencsv.bean.CsvBindByName;

public class CellPhone {
   @CsvBindByName
   private
   int employeeId;

   @CsvBindByName
   private
   String employeeName;

   @CsvBindByName
   private
   String purchaseDate;

   @CsvBindByName
   private
   String model;

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(String purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }
}
