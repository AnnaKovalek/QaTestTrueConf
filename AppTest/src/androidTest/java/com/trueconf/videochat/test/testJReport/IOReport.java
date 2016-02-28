package com.trueconf.videochat.test.testJReport;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IOReport extends ResourcesReport {
    private File fileDestination;
    private StringBuilder stringBuilder;
    private String format;

    public IOReport() {
        this.stringBuilder = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy_hh:mm");
        format = format1.format(date);
        this.fileDestination = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/report_" + format);
        fileDestination.mkdirs();
        this.fileDestination = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/report_" + format + "/img");
        fileDestination.mkdirs();

        this.fileDestination = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/report_" + format + "/index.html");
        try {
            fileDestination.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void destroy(Test test, String errorMesage) {
        this.stringBuilder.append(index_01);
        this.stringBuilder.append("  <b> <a href=\"index.html\" class=\"list-group-item\">" + test.getName() + "</a> </b>");
        this.stringBuilder.append(index_02);
        this.stringBuilder.append("<h3>Test:  " + test.getName() + "</h3> <h4>Data:");
        this.stringBuilder.append(format + "</h4>");
        this.stringBuilder.append(" <p>  " + test.getTitle() + " </p> <p>Этапы теста</p> <ol>");
        List<String> stagesOfTest = test.getStagesOfTest();
        for (String iter : stagesOfTest) {
            this.stringBuilder.append("<li>" + iter + "</li>");
        }
        this.stringBuilder.append("</ol> </div> <div class=\"col-xs-8\">");
        List<TestCase> testCases = test.getTestCases();
        for (TestCase iter : testCases) {

            this.stringBuilder.append("<div class=\"thumbnail\">  <div class=\"caption\">");
            this.stringBuilder.append("<h4>" + iter.getId() + " " + iter.getTitle() + "</h4>");
            this.stringBuilder.append("<img class=\"img-thumbnail\" height=\"400\" width=\"250\"  src=\"img/" + iter.getImage() + ".jpg" + "\" alt=\"\">");


            this.stringBuilder.append("<h5><b>Error Message : " + iter.getErrorMessage() + " </b></h5>");
            this.stringBuilder.append("<h5><b>Successfully : " + iter.getSuccessfully() + " </b></h5>");
            this.stringBuilder.append(" <h5><b>Time : 3c </b></h5>");
            this.stringBuilder.append("</div> </div>");
        }

        if (errorMesage != null) {
            this.stringBuilder.append(index_error_begin);
            this.stringBuilder.append("<span class=\"colortext\"> <h4> Exception: </h4></span>");
            this.stringBuilder.append("<span class=\"colortext\"><b>" + errorMesage + "</b></span>");
            this.stringBuilder.append(index_error_end);

        }

        this.stringBuilder.append(index_03);

        try {
            PrintWriter out = new PrintWriter(fileDestination.getAbsoluteFile());
            try {
                out.print(this.stringBuilder.toString());
            } finally {
                if (out!=null)
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (TestCase iter : testCases) {
            copy(iter.getImage());
        }
        deleteImage();

    }

    private void copy(String image) {
        try {
            File source = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/" + image + ".jpg");
            File dest = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/report_" + format + "/img/" + image + ".jpg");
            try {
                fileDestination.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileChannel sourceChannel = new FileInputStream(source).getChannel();
            try {
                FileChannel destChannel = new FileOutputStream(dest).getChannel();
                try {
                    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
                } finally {
                    destChannel.close();
                }
            } finally {
                sourceChannel.close();
                source.delete();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteImage() {
        try {
            File source = new File(Environment.getExternalStorageDirectory() + "/Robotium-Screenshots/init.jpg");
            source.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
