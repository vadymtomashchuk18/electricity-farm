package com.pexapark.ElectricityFarm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hourly_production_data")
@NoArgsConstructor
@Getter
@Setter
public class HourlyProductionData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "electricity_farm_id")
  private ElectricityFarm electricityFarm;

  @Column private LocalDateTime timestamp;

  @Column(name = "electricity_produced_mwh")
  private Double electricityProducedMWh;
}
