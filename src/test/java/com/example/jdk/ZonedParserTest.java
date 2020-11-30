package com.example.jdk;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.google.common.truth.Truth.assertThat;

@DisplayName("`ZonedParser` should")
final class ZonedParserTest {

    /**
     * Verifies that {@code DateTimeFormatter} parses {@code UTC} time zone
     * to the same {@code ZoneId}.
     */
    @Test
    @DisplayName("parse UTC date string to a `ZonedDateTime` instance with UTC `ZoneId`")
    void parse_jdk8() {
        LocalDate localDate = LocalDate.of(2021, 05, 17);
        LocalTime localTime = LocalTime.of(21, 23);
        ZonedDateTime expected = ZonedDateTime.of(localDate, localTime, ZoneId.of("UTC"));
        String dateString = "05/17/2021 09:23 PM UTC";

        ZonedDateTime actual = ZonedParser.parse(dateString);

        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Verifies that {@code DateTimeFormatter} parses {@code UTC} time zone
     * to Etc/UTC {@code ZoneId}.
     */
    @Test
    @DisplayName("parse UTC date string to a `ZonedDateTime` instance with Etc/UTC `ZoneId`")
    @Disabled("We want to test Java11+ backward compatibility.")
    void parse_jdk11() {
        LocalDate localDate = LocalDate.of(2021, 05, 17);
        LocalTime localTime = LocalTime.of(21, 23);
        ZonedDateTime expected = ZonedDateTime.of(localDate, localTime, ZoneId.of("Etc/UTC"));
        String dateString = "05/17/2021 09:23 PM UTC";

        ZonedDateTime actual = ZonedParser.parse(dateString);

        assertThat(actual).isEqualTo(expected);
    }
}
