package java.model;

import java.util.List;

public class CellPhoneUsageByMonth {
  private int emplyeeId;
  private String date;
  private List<Integer> totalMinutes;
  private List<Float> totalData;

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

  public List<Integer> getTotalMinutes() {
    return totalMinutes;
  }

  public void setTotalMinutes(List<Integer> totalMinutes) {
    this.totalMinutes = totalMinutes;
  }

  public List<Float> getTotalData() {
    return totalData;
  }

  public void setTotalData(List<Float> totalData) {
    this.totalData = totalData;
  }
}
