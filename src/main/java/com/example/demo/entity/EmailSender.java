package com.example.demo.entity;

import javax.mail.MessagingException;

public interface EmailSender {
  void send(String to, String email) throws MessagingException;

}
