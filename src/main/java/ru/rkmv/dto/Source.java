package ru.rkmv.dto;




public enum Source {
    VVO ("Владивосток", "+10"),
    TLV ("Тель-Авив", "+3");

    private String name;
    private String timezone;

    Source(String name, String timezone){
        this.name = name;
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
