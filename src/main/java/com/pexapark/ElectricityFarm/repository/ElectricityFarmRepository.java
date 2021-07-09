package com.pexapark.ElectricityFarm.repository;

import com.pexapark.ElectricityFarm.model.ElectricityFarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricityFarmRepository extends JpaRepository<ElectricityFarm, Long> {}
