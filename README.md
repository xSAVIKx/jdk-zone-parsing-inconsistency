JDK `ZoneId` parsing inconsistency
------------

This repository contains a reproducible example of the Java `time` API usage inconsistency
across different JDK versions.

# The problem

The `UTC` time zone specified in a String with date and time is parsed to a different `ZoneId` 
in Java11+ rather than in Java8.

Consider the following pattern `MM/dd/yyyy hh:mm a z` used to create a `DateTimeFormatter` with 
the US locale.

When a date string such as e.g. `05/17/2021 09:23 PM UTC` is parsed to a `ZonedDateTime` 
using the formatter, the `ZoneId` value of the `ZonedDateTime` is different when different 
JDK versions are used.

For JDK 8-10 and 12-14 the value of the `ZoneId` is `UTC`. But for JDK 11 and JDK 15 
the value is equal to `Etc/UTC`.

## Environments

Windows Server 2019, macOS 10.15, Ubuntu 18.04.

Java 11, Java 15.

## Steps to reproduce

The easiest way to reproduce the issue is to clone the repository and run `./gradlew build`.

Otherwise, you may follow the steps below.

1. Create `DateTimeFormatter` with the `MM/dd/yyyy hh:mm a z` pattern:

    ```
    DateTimeFormatter ZONED_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a z", Locale.US);
    ```

2. Parse date-time string `05/17/2021 09:23 PM UTC` to a `ZonedDateTime` object:

    ```
    ZonedDateTime result = ZONED_FORMATTER.parse(dateString, ZonedDateTime::from);
    ```

3. Check `ZoneId` value of the result:

    ```
    assert result.getZone().equals(ZoneId.of("UTC"));
    ```

Here's a full compilable example that fails in Java 11 and Java 15 environments.

```java
final class ZonedParser {

    public static void main(String[] args) {
        DateTimeFormatter ZONED_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a z", Locale.US);
        ZonedDateTime result = ZONED_FORMATTER.parse("05/17/2021 09:23 PM UTC", ZonedDateTime::from);
        if (!result.getZone().equals(ZoneId.of("UTC"))) {
            throw new IllegalStateException("Zone ID must be equal to `UTC`.");
        }
    }
}
```
