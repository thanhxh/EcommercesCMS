package com.thanh.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.thanh.constants.ConstantGlobal;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(ConstantGlobal.EXTENT_REPORT_PATH);
        reporter.config().setReportName("Extent Report | Thanh Dang");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Thanh Dang Tester");
        extentReports.setSystemInfo("Author", ConstantGlobal.AUTHOR);
        return extentReports;
    }

}
