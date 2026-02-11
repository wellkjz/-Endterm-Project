package com.berk.dragons.dto;

public class RentalRequestDto {
    private int dragonId;
    private String riderName;
    private int riderLevel;

    public RentalRequestDto(int dragonId, String riderName, int riderLevel) {
        this.dragonId = dragonId;
        this.riderName = riderName;
        this.riderLevel = riderLevel;
    }

    public int getDragonId() {
        return dragonId;
    }

    public String getRiderName() {
        return riderName;
    }

    public int getRiderLevel() {
        return riderLevel;
    }
}