package com.berk.dragons.model;

public class FireBreather extends DragonBase {
    public FireBreather(int id, String name, int stamina, double basePrice) {
        super(id, name, stamina, basePrice);
    }

    @Override
    public String getSpeciesType() {
        return "Stoker Class";
    }

    @Override
    public String getSpecialAbility() {
        return "Fire Stream";
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice(); // Стандартная цена
    }
}