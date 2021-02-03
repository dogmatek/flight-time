package ru.rkmv;

import ru.rkmv.dto.Ticket;
import ru.rkmv.services.Calculate;
import ru.rkmv.services.TicketService;

import java.util.ArrayList;
import java.util.List;

public class AppFlight {
    public static final String UTF8_BOM = "\uFEFF";

    public static void main(String[] args) {

        List<Ticket> tickets = new ArrayList<>(
                TicketService.getTicketsJson("tickets.json"));
//        System.out.println(tickets);

        long averagTimeFlight = Calculate.averageTimeOfFlight(tickets);
        System.out.println("Среднее время полета: " + averagTimeFlight / 60 / 60 + " часов " + (averagTimeFlight / 60) % 60 + " минут");

        long procentil = Calculate.PercentileTimeOfFlight(tickets, 0.90);
        System.out.println( "90-й процентиль времени полета между городами  Владивосток и Тель-Авив = "
                + procentil / 60 / 60 + " часов " + (procentil / 60) % 60 + " минут");
    }
}

