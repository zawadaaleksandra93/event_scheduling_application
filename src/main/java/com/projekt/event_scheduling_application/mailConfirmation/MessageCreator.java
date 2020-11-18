package com.projekt.event_scheduling_application.mailConfirmation;

public interface MessageCreator {

    void sendEmail(String to, String subject,String content);
}
