package com.boaretriever.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class Utils {
    public static final String BOA_URL_PROPERTY = "https://www.boa.aragon.es/cgi-bin/EBOA/BRSCGI?CMD=VERLST&BASE=BZHT&DOCS=1-200&SEC=OPENDATABOAJSON&OUTPUTMODE=JSON&SORT=-PUBL&SEPARADOR=&TITU=&SECC-C=I%2BO%2BII%2BO%2BIII%2BO%2BIV%2BO%2BV&SECC=&SUBS-C=&MATE-C=&NUMB=&RANG-C=&OLEY-C=&ALEY-C=&ORGA-C=&TEXT-C=&@PUBL-GE={fecha}&@PUBL-LE={fecha}&FDIS-C=";

    private Utils() {
        // Prevent instantiation
    }

    public static String getTodayAsYYYYMMDD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().format(formatter);
    }

    public static String replaceFechaPlaceholderForDate(String baseURL, String date) {
        return baseURL.replace("{fecha}", date);
    }

    public static String transformDateToDDMMYYYY(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")).format(formatter);
    }

    public static void exportToFile(List<String> data, String searchedWord, String outputDirectory) {
        String fileName = "BOA_" + searchedWord + "_" + getTodayAsYYYYMMDD() + ".txt";
        String resolvedDirectory = expandPath(outputDirectory == null ? "../exportedDocs" : outputDirectory);
        Path outputDir = Paths.get(resolvedDirectory);
        try {
            Files.createDirectories(outputDir);
            Path outputPath = outputDir.resolve(fileName);
            try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                for (String item : data) {
                    writer.write("Nueva publicación BOA que puede interesarte: " + item + System.lineSeparator());
                }
            }
            System.out.println("Data exported to file: " + outputPath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error exporting data to file: " + e.getMessage());
        }
    }

    private static String expandPath(String path) {
        if (path == null || path.isBlank()) {
            return path;
        }
        String resolved = path;
        if (resolved.startsWith("~")) {
            resolved = System.getProperty("user.home") + resolved.substring(1);
        }
        return resolved.replace("${user.home}", System.getProperty("user.home"));
    }
}
