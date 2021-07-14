package com.pexapark.ElectricityFarm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "electricity_farm")
@NoArgsConstructor
@Getter
@Setter
public class ElectricityFarm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "farm_name")
  private String farmName;

  @Enumerated(EnumType.STRING)
  @Column(name = "farm_type")
  private FarmType farmType;

  @Column(name = "farm_capacity_mw")
  private BigDecimal farmCapacityMW;

  @Column(name = "time_zone")
  private String timeZone;
}
