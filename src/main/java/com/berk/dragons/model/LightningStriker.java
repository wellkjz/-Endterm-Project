package com.berk.dragons.model;

public class LightningStriker extends DragonBase {
    public LightningStriker(int id, String name, int stamina, double basePrice) {
        super(id, name, stamina, basePrice);
    }

    @Override
    public String getSpeciesType() {
        return "Strike Class";
    }

    @Override
    public String getSpecialAbility() {
        return "Plasma Blast";
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice() * 1.5; // Наценка 50% (Полиморфизм)
    }
}