package com.berk.dragons.dto;

public class RiderRequestDto {
    private String name;
    private int skillLevel;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSkillLevel() { return skillLevel; }
    public void setSkillLevel(int skillLevel) { this.skillLevel = skillLevel; }
}