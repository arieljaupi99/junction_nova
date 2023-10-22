package junction.al.nova_spring.entities;

import junction.al.nova_spring.model.RoomRequestForUpdateResidentsAndContract;
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
    private String roomId;
    private String contractNumber;
    private String pdfPath;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;


    public static Contract generateContractFromRequest(RoomRequestForUpdateResidentsAndContract request,String pdfPath) {
        return Contract.builder()
                .pdfPath(pdfPath)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .roomId(request.getRoomId())
                .build();
    }
}
