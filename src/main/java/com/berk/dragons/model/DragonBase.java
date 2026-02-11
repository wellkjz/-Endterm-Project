package com.berk.dragons.model;

public abstract class DragonBase {
    private int id;
    private String name;
    private int stamina;
    private double basePrice;

    private Rider currentRider;

    public DragonBase(int id, String name, int stamina, double basePrice) {
        this.id = id;
        this.name = name;
        this.stamina = stamina;
        this.basePrice = basePrice;
    }

    public abstract String getSpeciesType();

    public abstract String getSpecialAbility();
    public abstract double calculateFinalPrice();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getStamina() { return stamina; }
    public void setStamina(int stamina) { this.stamina = stamina; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public Rider getCurrentRider() { return currentRider; }
    public void setCurrentRider(Rider currentRider) { this.currentRider = currentRider; }
}