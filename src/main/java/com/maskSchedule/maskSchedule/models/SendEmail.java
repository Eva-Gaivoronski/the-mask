package com.maskSchedule.maskSchedule.models;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class SendEmail {
    public static void SendSchedule(String[] args) throws IOException {
        Email from = new Email("antsilva93@gmail.com");
        String subject = "You have been scheduled; Login to see new schedule.";
        Email to = new Email("antsilva93@gmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}