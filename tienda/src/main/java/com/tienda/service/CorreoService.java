package com.tienda.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    private final JavaMailSender mailSender;
    private final String remitente;

    public CorreoService(
            JavaMailSender mailSender,
            @Value("${spring.mail.username}") String remitente) {
        this.mailSender = mailSender;
        this.remitente = remitente;
    }

    public void enviarCorreoHtml(String para,
            String asunto,
            String contenido) throws MessagingException {

        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper correo = new MimeMessageHelper(mensaje, true);

        correo.setFrom(remitente);
        correo.setTo(para);
        correo.setSubject(asunto);
        correo.setText(contenido, true);
        mailSender.send(mensaje);
    }
}
