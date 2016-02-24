package com.trueconf.videochat.test.testJReport.modelJMail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEmail {

    private CreateEmail() {
        // Create zip
        JZip zip = new JZip();
        String zipSource = zip.init();

        //Отправляем емайл
        //  JMail mail = new JMail(); // поумолчанию отправка
        JMail mail = new JMail(message());    // или со своим сообшением
        mail.addAttachment(zipSource);
        mail.send();

        //Удаляем архив с отчетами
        zip.deleteZip(zipSource);
    }

    public static CreateEmail instance() {
        return new CreateEmail();
    }

    private String message() {
        SimpleDateFormat format1 = new SimpleDateFormat("dd_MM_yyyy");
        String format = format1.format(new Date());
        return "Report TrueCOnf QA " + format + " android client ver. 1.3.0";
    }
}
