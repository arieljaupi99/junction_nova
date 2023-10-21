package junction.al.nova_spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractRequest {

    private String residentId;
    private String startDate;
    private String endDate;
    private String base64String;
    private String type;
}
