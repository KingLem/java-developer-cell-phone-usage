package main;

import main.service.ReportService;

public class App {
    static ReportService service;

    public static void main(String[] args) {
        service = new ReportService();

        try {
            service.generateReport("CellPhone.csv", "CellPhoneUsageByMonth.csv");
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
