package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class EmailServiceDevTest {

//    private final PrintStream systemOut = System.out;



    @Test
    void deveriaMandarEmailDeTeste() throws IOException {
        //ARRANGE
        String to = "Teste to";
        String subject = "Teste subject";
        String message = "Teste message";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        //ACT
        EmailServiceDev emailServiceDev = new EmailServiceDev();
        emailServiceDev.enviarEmail(to, subject, message);
        String[] prints = baos.toString().split(System.lineSeparator());

        //ASSERT
        Assertions.assertEquals("Enviando email fake", prints[0]);
        Assertions.assertEquals("Destinatario: " + to, prints[1]);
        Assertions.assertEquals("Assunto: " + subject, prints[2]);
        Assertions.assertEquals("Mensagem: " + message, prints[3]);
    }
}