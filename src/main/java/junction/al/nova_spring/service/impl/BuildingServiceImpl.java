package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.entities.Building;
import junction.al.nova_spring.repository.BuildingRepo;
import junction.al.nova_spring.service.BuildingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepo buildingRepo;

    public BuildingServiceImpl(BuildingRepo buildingRepo) {
        this.buildingRepo = buildingRepo;
    }

    @Override
    public List<Building> getAll() {
        return this.buildingRepo.findAll();
    }
}
