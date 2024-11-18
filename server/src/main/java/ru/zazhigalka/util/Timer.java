package ru.zazhigalka.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer {
    private LocalDateTime lastTime;

    public void start() {
        lastTime = LocalDateTime.now();
    }

    public double getTime() {
        if (lastTime == null) {
            return 0;
        }
        LocalDateTime currentTime = LocalDateTime.now();
        return (double) Duration.between(lastTime, currentTime).getNano() / 1000000;
    }
    public String getServerTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
        return currentTime.format(formatter);
    }



}
