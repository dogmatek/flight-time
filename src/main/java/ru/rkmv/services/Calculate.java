package ru.rkmv.services;

import ru.rkmv.dto.Ticket;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class Calculate {
    /*
        Расчет времени в пути
     */
    public static long getTimeOfFlight(Ticket ticket) {

        long time;
        time = ticket.getArrivalDateTime().toEpochSecond(ZoneOffset.of(ticket.getDestination().getTimezone())) -
                ticket.getDepartureDateTime().toEpochSecond(ZoneOffset.of(ticket.getOrigin().getTimezone()));
        if (time < 0)
            throw new RuntimeException("Время полета не может быть отрицательным");
//        System.out.println(time);
//        System.out.println(time / 60 / 60 + ":" + (time / 60) % 60);
        return time;
    }


    /*
        Расчет среднего значения времени в пути
     */
    public static long averageTimeOfFlight(List<Ticket> tickets) {
        long average = 0;
        for (Ticket ticket : tickets) {
            average += getTimeOfFlight(ticket) / tickets.size();
        }
        return average;
    }


    /*
        Расчет процентиля времени полета
     */
    public static long PercentileTimeOfFlight(List<Ticket> tickets, double k) {
        List<Long> times = new ArrayList<>();
        for (Ticket ticket : tickets) {
            times.add(getTimeOfFlight(ticket));
        }
        times.sort(Long::compareTo);


        int countTimes = 0;
        double procenil = 0.0;
        for (long tm : times) {

            double newProcetal = 1.0 * (++countTimes) / times.size();
            if(k <= newProcetal) {
                return tm;
            }
        }
        return 0;
    }


}






