package org.Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("TestCases Started and Details are: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("TestCases Success and Details are: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("TestCases Failed and Details are: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("TestCases Skipped and Details are: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext result) {

    }

    @Override
    public void onFinish(ITestContext result) {

    }
}
