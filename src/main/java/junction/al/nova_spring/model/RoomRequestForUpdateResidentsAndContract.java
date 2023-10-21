package junction.al.nova_spring.model;

import junction.al.nova_spring.utility.Utility;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class RoomRequestForUpdateResidentsAndContract {

    private String roomId;
    private List<String> residentIdList;
    private String startDate;
    private String endDate;
    private String base64String;
    private String type;

    public ZonedDateTime convertStartDate() {
        return Utility.convertTextToZonedDateTime(this.startDate);
    }

    public ZonedDateTime convertEndDate() {
        return Utility.convertTextToZonedDateTime(this.endDate);
    }
}
