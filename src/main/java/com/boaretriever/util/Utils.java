package com.boaretriever.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Utils {
    public static final String BOA_URL_PROPERTY = "https://www.boa.aragon.es/cgi-bin/EBOA/BRSCGI?CMD=VERLST&BASE=BZHT&DOCS=1-200&SEC=OPENDATABOAJSON&OUTPUTMODE=JSON&SORT=-PUBL&SEPARADOR=&TITU=&SECC-C=I%2BO%2BII%2BO%2BIII%2BO%2BIV%2BO%2BV&SECC=&SUBS-C=&MATE-C=&NUMB=&RANG-C=&OLEY-C=&ALEY-C=&ORGA-C=&TEXT-C=&@PUBL-GE={fecha}&@PUBL-LE={fecha}&FDIS-C=";

    private Utils() {
        // Prevent instantiation
    }

    public static String getTodayAsYYYYMMDD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().format(formatter);
    }

    public static String replaceFechaPlaceholderForToday(String baseURL) {
        return baseURL.replace("{fecha}", getTodayAsYYYYMMDD());
    }
}
