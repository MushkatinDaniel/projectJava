package ru.netology;

import java.time.LocalDateTime;

public class SmartLogger implements Logger{
    private int count = 0;
    @Override
    public void log(String msg) {
        count++;
        String logInfo = "INFO";
        if(msg.toLowerCase().contains("error")) {
            logInfo = "ERROR";
        }
        System.out.println(logInfo + "#" + count + " [" + LocalDateTime.now() + "] <" + msg + ">");

    }
}
