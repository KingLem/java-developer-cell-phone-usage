package main.model;

public class CellPhoneUsageByMonth {
  private int emplyeeId;
  private String date;
  private int totalMinutes;
  private float totalData;

  public int getEmplyeeId() {
    return emplyeeId;
  }

  public void setEmplyeeId(int emplyeeId) {
    this.emplyeeId = emplyeeId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getTotalMinutes() {
    return totalMinutes;
  }

  public void setTotalMinutes(int totalMinutes) {
    this.totalMinutes = totalMinutes;
  }

  public float getTotalData() {
    return totalData;
  }

  public void setTotalData(float totalData) {
    this.totalData = totalData;
  }
}
