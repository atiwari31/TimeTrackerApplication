package com.dcardprocessing.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public CurrentTime(){
    }

    public String currentTime(){
        return dtf.format(now);
    }
}
