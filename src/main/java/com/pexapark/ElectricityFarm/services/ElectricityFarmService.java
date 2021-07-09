package com.pexapark.ElectricityFarm.services;

import com.pexapark.ElectricityFarm.exception.DataNotFoundException;
import com.pexapark.ElectricityFarm.model.ElectricityFarm;
import com.pexapark.ElectricityFarm.repository.ElectricityFarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectricityFarmService {

  private final ElectricityFarmRepository electricityFarmRepository;

  @Autowired
  public ElectricityFarmService(ElectricityFarmRepository electricityFarmRepository) {
    this.electricityFarmRepository = electricityFarmRepository;
  }

  public ElectricityFarm getDefaultElectricityFarm(long farmId) {
    ElectricityFarm farm =
        electricityFarmRepository
            .findById(farmId)
            .orElseThrow(
                () ->
                    new DataNotFoundException(
                        String.format("Cannot find data for request id:%s", farmId)));
    return farm;
  }
}
