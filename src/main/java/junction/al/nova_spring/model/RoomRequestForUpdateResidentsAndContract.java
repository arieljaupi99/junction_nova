package junction.al.nova_spring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import junction.al.nova_spring.utility.Utility;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class RoomRequestForUpdateResidentsAndContract {
    public static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";

    private String roomId;
    @JsonFormat(pattern = ZONED_DATE_TIME_FORMAT, timezone = "Europe/Rome")
    private ZonedDateTime startDate;
    @JsonFormat(pattern = ZONED_DATE_TIME_FORMAT, timezone = "Europe/Rome")
    private ZonedDateTime endDate;
    private String base64String;
    private String type;
}
