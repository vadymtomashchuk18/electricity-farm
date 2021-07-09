package com.pexapark.ElectricityFarm.model;

public enum FarmType {
    wind,
    solar,
    hydro,
    geothermal,
    biomass;

    public static FarmType of(String name) {
        for (FarmType v : values()) {
            String aType = name.replaceAll("(\\.|\\s|-)", "_").toLowerCase();
            if (aType.contains(v.name())) return v;
        }
        return null;
    }
}
