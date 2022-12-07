package br.com.alura;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Datas {

    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();
        System.out.println(hoje);
        
        LocalDate olimpiadasRio = LocalDate.of(2016, Month.JUNE, 5);
        int anos = olimpiadasRio.getYear();
        System.out.println(anos);
        
        Period periodo = Period.between(hoje, olimpiadasRio);
        System.out.println(periodo);
        
        LocalDate proximasOlimpiadas = olimpiadasRio.plusYears(4);
        
        System.out.println(proximasOlimpiadas);
        
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Dia e ano tem que ser minúsculo
        
        String valorFormatado = proximasOlimpiadas.format(formatador);
        System.out.println(valorFormatado);
        
        DateTimeFormatter formatadorComHoras = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        
        LocalDateTime agora = LocalDateTime.now();
        System.out.println(agora.format(formatadorComHoras));
        
        LocalTime intervalo = LocalTime.of(15, 30);
        System.out.println(intervalo);
        
        ZonedDateTime zonaDeTempo = ZonedDateTime.now();
        System.out.println(zonaDeTempo);
        
        LocalDate futuro = LocalDate.of(2099, 01, 25);
        System.out.println(futuro);
        
        Period periodo2 = Period.between(hoje, futuro);
        System.out.println(periodo2);
        
        DateTimeFormatter hojeFormatado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(hojeFormatado.format(hoje));
    }

}
