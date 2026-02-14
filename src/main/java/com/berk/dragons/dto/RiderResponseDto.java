package com.berk.dragons.dto;

import com.berk.dragons.model.Rider;

public class RiderResponseDto {

    private final int id;
    private final String name;
    private final int skillLevel;
    private final String rank;

    public RiderResponseDto(Rider rider) {
        this.id = rider.getId();
        this.name = rider.getName();
        this.skillLevel = rider.getSkillLevel();
        this.rank = calculateRank(rider.getSkillLevel());
    }

    private String calculateRank(int level) {
        if (level >= 9) return "MASTER";
        if (level >= 7) return "ELITE";
        if (level >= 4) return "TRAINED";
        return "NOVICE";
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getSkillLevel() { return skillLevel; }
    public String getRank() { return rank; }
}