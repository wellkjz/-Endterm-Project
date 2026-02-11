package com.berk.dragons.model;

public class Rider {
    private int id;
    private String name;
    private int skillLevel;

    public Rider(int id, String name, int skillLevel) {
        this.id = id;
        this.name = name;
        this.skillLevel = skillLevel;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getSkillLevel() { return skillLevel; }
}