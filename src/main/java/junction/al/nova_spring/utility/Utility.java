package junction.al.nova_spring.utility;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

    public static ZonedDateTime convertTextToZonedDateTime(String dateText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        return ZonedDateTime.parse(dateText + "T00:00:00.000Z", formatter);
    }
}
