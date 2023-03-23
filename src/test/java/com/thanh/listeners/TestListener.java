package com.thanh.listeners;

import com.aventstack.extentreports.Status;
import com.thanh.helpers.CaptureHelpers;
import com.thanh.helpers.PropertiesHelpers;
import com.thanh.reports.AllureManager;
import com.thanh.reports.ExtentReportManager;
import com.thanh.reports.ExtentTestManager;
import com.thanh.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("Starting Suite: " + result.getStartDate());
//        if (PropertiesHelpers.getValue("record_video").equals("yes")) {
//            CaptureHelpers.startRecord(result.getName());
//        }
        PropertiesHelpers.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("Finish Suite: " + result.getEndDate());
//        if (PropertiesHelpers.getValue("record_video").equals("yes")) {
//            CaptureHelpers.stopRecord();
//        }
        ExtentReportManager.getExtentReports().flush(); //Kết thúc và thực thi xuất report ra file
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info(result.getName() + " is pass.");
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //System.out.println(result.getName() + " is fail.");

//        if (PropertiesHelpers.getValue("screenshot_fail").equals("yes")) {
        CaptureHelpers.takeScreenshot(result); //Chụp màn hình khi Fail
//        }
        LogUtils.error(result.getName() + " is fail.");

        //Extent Report
        ExtentTestManager.addScreenShot(result.getName());
        //ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());

        //Allure Report
        AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn(result.getName() + " is skipped.");
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}
