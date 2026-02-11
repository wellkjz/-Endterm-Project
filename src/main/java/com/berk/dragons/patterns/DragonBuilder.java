package com.berk.dragons.patterns;

import com.berk.dragons.model.DragonBase;

public class DragonBuilder {
    private String name;
    private String type;
    private int stamina = 100;
    private double price;

    public DragonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DragonBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public DragonBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public DragonBase build() {
        return DragonFactory.createDragon(type, 0, name, stamina, price);
    }
}