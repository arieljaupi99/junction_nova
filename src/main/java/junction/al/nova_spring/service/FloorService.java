package junction.al.nova_spring.service;


import junction.al.nova_spring.entities.Floor;

import java.util.List;

public interface FloorService {
    List<Floor> floorPerBuilding(String buildingId);
}
