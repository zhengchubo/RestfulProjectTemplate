package com.justin4u.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;

public class NewFeaturesInJava8 {
    public static void main(String[] args) throws Exception {
        dateTime();
        System.out.println("");
        nashorn();
        System.out.println();
        base64();
    }

    private static void dateTime() {
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(date);
        System.out.println(dateFromClock);

        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(time);
        System.out.println(timeFromClock);

        final LocalDateTime dateTime = LocalDateTime.now();
        final LocalDateTime dateTime1 = LocalDateTime.now(clock);
        System.out.println(dateTime);
        System.out.println(dateTime1);
        System.out.println("---");

        final ZonedDateTime zdt = ZonedDateTime.now();
        final ZonedDateTime zdt1 = ZonedDateTime.now(clock);
        final ZonedDateTime zdt2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);
        System.out.println(zdt1);
        System.out.println(zdt2);
        System.out.println("---");

        final LocalDateTime from = LocalDateTime.of(2018, Month.APRIL, 24, 19, 59, 30);
        final LocalDateTime to = LocalDateTime.of(2018, Month.APRIL, 25, 0, 0, 0);
        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in seconds: " + duration.toMillis() / 1000);
    }

    private static void nashorn() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.getClass().getName());
        System.out.println("Result: " + engine.eval("function f() { return 1;}; f() + 1;"));
    }

    private static void base64() {
        final String text = "Base64 in Java 8";
        final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        final String decoded = new String(Base64.getDecoder().decode(encoded.getBytes(StandardCharsets.UTF_8)));
        System.out.println(encoded);
        System.out.println(decoded);
    }

}
