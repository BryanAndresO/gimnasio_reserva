package com.gimansioreserva.gimnasioreserva_spring.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Formatea una fecha en formato String
     */
    public static String formatDate(LocalDate date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Formatea una hora en formato String
     */
    public static String formatTime(LocalTime time) {
        if (time == null) return null;
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Formatea una fecha y hora en formato String
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    /**
     * Convierte un String a LocalDate
     */
    public static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) return null;
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Convierte un String a LocalTime
     */
    public static LocalTime parseTime(String timeString) {
        if (timeString == null || timeString.isEmpty()) return null;
        return LocalTime.parse(timeString, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Convierte un String a LocalDateTime
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.isEmpty()) return null;
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    /**
     * Obtiene la fecha y hora actual
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * Obtiene la fecha actual
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /**
     * Verifica si una fecha está en el pasado
     */
    public static boolean isPast(LocalDateTime dateTime) {
        return dateTime != null && dateTime.isBefore(LocalDateTime.now());
    }

    /**
     * Verifica si una fecha está en el futuro
     */
    public static boolean isFuture(LocalDateTime dateTime) {
        return dateTime != null && dateTime.isAfter(LocalDateTime.now());
    }

    /**
     * Agrega días a una fecha
     */
    public static LocalDateTime addDays(LocalDateTime dateTime, long days) {
        return dateTime != null ? dateTime.plusDays(days) : null;
    }

    /**
     * Agrega horas a una fecha y hora
     */
    public static LocalDateTime addHours(LocalDateTime dateTime, long hours) {
        return dateTime != null ? dateTime.plusHours(hours) : null;
    }

    /**
     * Agrega minutos a una fecha y hora
     */
    public static LocalDateTime addMinutes(LocalDateTime dateTime, long minutes) {
        return dateTime != null ? dateTime.plusMinutes(minutes) : null;
    }

    /**
     * Calcula la diferencia en días entre dos fechas
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) return 0;
        return Math.abs(java.time.temporal.ChronoUnit.DAYS.between(start, end));
    }

    /**
     * Calcula la diferencia en horas entre dos fechas y horas
     */
    public static long hoursBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) return 0;
        return Math.abs(java.time.temporal.ChronoUnit.HOURS.between(start, end));
    }

    /**
     * Calcula la diferencia en minutos entre dos fechas y horas
     */
    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) return 0;
        return Math.abs(java.time.temporal.ChronoUnit.MINUTES.between(start, end));
    }

    /**
     * Convierte Date a LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return LocalDateTime.of(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND)
        );
    }
}

