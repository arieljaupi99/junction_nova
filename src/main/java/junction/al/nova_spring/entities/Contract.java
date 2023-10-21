package junction.al.nova_spring.entities;

import junction.al.nova_spring.model.ContractRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document("contract")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {
    @Id
    private String id;
    private String residentId;
    private String contractNumber;
    private String pdfPath;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    public static Contract generateForResident(ContractRequest contractRequest) {
        return Contract.builder()
                .residentId(contractRequest.getResidentId())
                .build();
    }
}
