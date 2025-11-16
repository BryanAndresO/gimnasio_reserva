package com.gimansioreserva.gimnasioreserva_spring.util;

import java.util.regex.Pattern;

public class StringUtil {

    // Patrón para validar email
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    // Patrón para validar solo letras y espacios
    private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile(
            "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"
    );

    /**
     * Verifica si un String es nulo o vacío
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Verifica si un String NO es nulo ni vacío
     */
    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    /**
     * Elimina espacios en blanco al inicio y final, devuelve null si es null
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * Convierte un String a mayúsculas, devuelve null si es null
     */
    public static String toUpperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    /**
     * Convierte un String a minúsculas, devuelve null si es null
     */
    public static String toLowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    /**
     * Capitaliza la primera letra de un String
     */
    public static String capitalize(String str) {
        if (isNullOrEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Capitaliza la primera letra de cada palabra
     */
    public static String capitalizeWords(String str) {
        if (isNullOrEmpty(str)) return str;
        String[] words = str.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (result.length() > 0) result.append(" ");
            result.append(capitalize(word));
        }
        return result.toString();
    }

    /**
     * Valida formato de email
     */
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Verifica si contiene solo letras y espacios
     */
    public static boolean containsOnlyLetters(String str) {
        if (isNullOrEmpty(str)) return false;
        return LETTERS_ONLY_PATTERN.matcher(str).matches();
    }

    /**
     * Obtiene las primeras N letras de un String
     */
    public static String truncate(String str, int maxLength) {
        if (str == null) return null;
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength) + "...";
    }

    /**
     * Enmascara un email (ej: usu***@gmail.com)
     */
    public static String maskEmail(String email) {
        if (isNullOrEmpty(email) || !email.contains("@")) return email;
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];
        if (username.length() <= 2) {
            return username.charAt(0) + "***@" + domain;
        }
        return username.substring(0, 2) + "***@" + domain;
    }

    /**
     * Enmascara una contraseña
     */
    public static String maskPassword(String password) {
        if (isNullOrEmpty(password)) return "";
        return "*".repeat(Math.min(password.length(), 8));
    }

    /**
     * Sanitiza un String eliminando caracteres especiales peligrosos
     */
    public static String sanitize(String str) {
        if (str == null) return null;
        return str.replaceAll("[<>\"'&]", "");
    }

    /**
     * Normaliza espacios múltiples a uno solo
     */
    public static String normalizeSpaces(String str) {
        if (str == null) return null;
        return str.trim().replaceAll("\\s+", " ");
    }

    /**
     * Verifica si un String tiene longitud entre min y max
     */
    public static boolean hasLengthBetween(String str, int min, int max) {
        if (str == null) return false;
        int length = str.length();
        return length >= min && length <= max;
    }

    /**
     * Extrae números de un String
     */
    public static String extractNumbers(String str) {
        if (str == null) return "";
        return str.replaceAll("[^0-9]", "");
    }

    /**
     * Verifica si contiene al menos un número
     */
    public static boolean containsNumber(String str) {
        if (str == null) return false;
        return str.matches(".*\\d+.*");
    }

    /**
     * Verifica si contiene al menos una letra mayúscula
     */
    public static boolean containsUpperCase(String str) {
        if (str == null) return false;
        return str.matches(".*[A-Z].*");
    }

    /**
     * Verifica si contiene al menos una letra minúscula
     */
    public static boolean containsLowerCase(String str) {
        if (str == null) return false;
        return str.matches(".*[a-z].*");
    }

    /**
     * Verifica si contiene al menos un carácter especial
     */
    public static boolean containsSpecialChar(String str) {
        if (str == null) return false;
        return str.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }
}

