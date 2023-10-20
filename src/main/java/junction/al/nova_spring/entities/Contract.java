package junction.al.nova_spring.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document("contract")
@Getter
@Setter
public class Contract {
    @Id
    private String id;
    private String userId;
    private String contractNumber;
    private String pdfPath;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
}
