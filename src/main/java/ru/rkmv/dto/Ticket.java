package ru.rkmv.dto;
import java.time.LocalDateTime;


public class Ticket {
    private Source Origin;
    private Source Destination;
    private LocalDateTime DepartureDateTime;
    private LocalDateTime ArrivalDateTime;
    private String Carrier;
    private Integer Stops;
    private Integer Price;

    public Source getOrigin() {
        return Origin;
    }

    public Source getDestination() {
        return Destination;
    }

    public void setDestination(Source destination) {
        Destination = destination;
    }

    public LocalDateTime getDepartureDateTime() {
        return DepartureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        DepartureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return ArrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        ArrivalDateTime = arrivalDateTime;
    }

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String carrier) {
        Carrier = carrier;
    }

    public Integer getStops() {
        return Stops;
    }

    public void setStops(Integer stops) {
        Stops = stops;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public void setOrigin(Source origin) {
        Origin = origin;
    }
}
