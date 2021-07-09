package com.pexapark.ElectricityFarm.repository;

import com.pexapark.ElectricityFarm.model.ElectricityFarm;
import com.pexapark.ElectricityFarm.model.HourlyProductionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HourlyProductionDataRepository extends JpaRepository<HourlyProductionData, Long> {

  List<HourlyProductionData> findHourlyProductionDataByElectricityFarmAndTimestampBetween(
      ElectricityFarm electricityFarm, LocalDateTime from, LocalDateTime to);
}
