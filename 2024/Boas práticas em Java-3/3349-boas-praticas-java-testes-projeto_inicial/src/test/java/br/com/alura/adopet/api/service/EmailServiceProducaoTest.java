package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceProducaoTest {

    @InjectMocks
    private EmailServiceProducao emailServiceProducao;

    @Mock
    private JavaMailSender emailSender;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> mailMessageCaptor;

    @Test
    void deveEnviarEmailReal(){
        //ARRANGE
        String to = "Fulano de tal";
        String subject = "Assunto de tal";
        String message = "Mensagem de tal";

        //ACT
        emailServiceProducao.enviarEmail(to, subject, message);

        //ASSERT
        BDDMockito.then(emailSender).should().send(mailMessageCaptor.capture());
        SimpleMailMessage email = mailMessageCaptor.getValue();
        Assertions.assertEquals(to, Objects.requireNonNull(email.getTo())[0]);
        Assertions.assertEquals(subject, email.getSubject());
        Assertions.assertEquals(message, email.getText());
    }
}