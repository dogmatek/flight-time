package ru.rkmv.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.rkmv.dto.Source;
import ru.rkmv.dto.Ticket;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

    // Парсер Json
    public static List<Ticket> getTicketsJson(String filename) {
        List<Ticket> tickets = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(filename)) {

            // Удаляем символы до '{'.  Решение проблемы с BOM символом
            StringBuffer buffer = new StringBuffer();
            int c;
            boolean fl = false;
            while ((c = reader.read()) != -1) {
                if (fl) {
                    buffer.append((char) c);
                } else if (c == '{') {
                    fl = true;
                    buffer.append((char) c);
                }
            }
            JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(buffer));
            JSONArray jsonTickets = (JSONArray) jsonObject.get("tickets");

            for (Object o : jsonTickets) {
                JSONObject jsonTicket = (JSONObject) o;
                Ticket ticket = new Ticket();
                ticket.setOrigin(Source.valueOf(jsonTicket.get("origin").toString()));
                ticket.setDestination(Source.valueOf(jsonTicket.get("destination").toString()));
                ticket.setDepartureDateTime(
                        LocalDateTime.parse(
                                jsonTicket.get("departure_date") + " " + jsonTicket.get("departure_time"), DateTimeFormatter.ofPattern("d.M.y H:m")
                        ).plusYears(2000)
                );
                ticket.setArrivalDateTime(
                        LocalDateTime.parse(
                                jsonTicket.get("arrival_date") + " " + jsonTicket.get("arrival_time"), DateTimeFormatter.ofPattern("d.M.y H:m")
                        ).plusYears(2000)
                );
                ticket.setCarrier(jsonTicket.get("carrier").toString());
                ticket.setStops(Integer.parseInt(jsonTicket.get("stops").toString()));
                ticket.setPrice(Integer.parseInt(jsonTicket.get("price").toString()));
                tickets.add(ticket);
            }
            return tickets;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

}
