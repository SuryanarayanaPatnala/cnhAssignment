package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    ExtentReports extentReports;
    ExtentSparkReporter extentSparkReporter;
    ExtentTest extentTest;
    public ExtentReport(){
        extentReports=new ExtentReports();
        extentSparkReporter=new ExtentSparkReporter("HTMLReport/ExtentReport.html");
        extentReports.attachReporter(extentSparkReporter);
    }
    public ExtentTest createTest(String testName){
        extentTest=extentReports.createTest(testName);
        return extentTest;
    }
    public void pass(String text){
        extentTest.log(Status.PASS,text);
    }
    public void fail(String text){
        extentTest.log(Status.FAIL,text);
    }
    public void info(String text){
        extentTest.log(Status.INFO,text);
    }
    public void flush(){
        extentReports.flush();
    }
    }

