package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Floor;
import junction.al.nova_spring.repository.FloorRepo;
import junction.al.nova_spring.service.FloorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {
    private final FloorRepo floorRepo;

    public FloorServiceImpl(FloorRepo floorRepo) {
        this.floorRepo = floorRepo;
    }

    @Override
    public List<Floor> floorPerBuilding(String buildingId) {
        return this.floorRepo.floorByBuildingIdIs(buildingId);
    }
}
