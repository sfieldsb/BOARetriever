package com.boaretriever.service;

import java.util.List;
import java.util.Properties;

import com.boaretriever.util.ConfigManager;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {
    public void sendBOAUpdate(List<String> data, String searchedWord) throws Exception {
        ConfigManager config = ConfigManager.getInstance();

        String host = config.getProperty("boa.email.smtp.host");
        String port = config.getProperty("boa.email.smtp.port", "587");
        String username = config.getProperty("boa.email.smtp.username");
        String password = config.getProperty("boa.email.smtp.password");
        String from = config.getProperty("boa.email.from", username);
        String to = config.getProperty("boa.email.to");
        String subject = config.getProperty("boa.email.subject", "BOA Retriever update for " + searchedWord);

        if (host == null || host.isBlank()) {
            throw new IllegalStateException("Missing email SMTP host in configuration");
        }
        if (to == null || to.isBlank()) {
            throw new IllegalStateException("Missing email recipient address in configuration");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalStateException("Missing email SMTP username in configuration");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalStateException("Missing email SMTP password in configuration");
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", config.getProperty("boa.email.smtp.auth", "true"));
        props.put("mail.smtp.starttls.enable", config.getProperty("boa.email.smtp.starttls", "true"));
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(buildBody(data, searchedWord));

        Transport.send(message);
        System.out.println("Email sent to " + to);
    }

    private String buildBody(List<String> data, String searchedWord) {
        StringBuilder body = new StringBuilder();
        body.append("Resultados BOA para: ").append(searchedWord).append("\n\n");
        for (String item : data) {
            body.append("- ").append(item).append("\n");
        }
        body.append("\nGenerado por BOARetriever.");
        return body.toString();
    }
}
