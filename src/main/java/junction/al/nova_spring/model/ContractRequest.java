package junction.al.nova_spring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ContractRequest {
    public static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";

    private String residentId;
    @JsonFormat(pattern = ZONED_DATE_TIME_FORMAT, timezone = "Europe/Tirane")
    private ZonedDateTime startDate;
    @JsonFormat(pattern = ZONED_DATE_TIME_FORMAT, timezone = "Europe/Tirane")

    private ZonedDateTime endDate;
    private String base64String;
    private String type;
}
