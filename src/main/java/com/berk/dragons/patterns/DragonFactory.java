package com.berk.dragons.patterns;

import com.berk.dragons.model.*;

public class DragonFactory {
    public static DragonBase createDragon(String type, int id, String name, int stamina, double price) {
        if ("Lightning".equalsIgnoreCase(type)) {
            return new LightningStriker(id, name, stamina, price);
        } else if ("Fire".equalsIgnoreCase(type)) {
            return new FireBreather(id, name, stamina, price);
        } else {
            return new FireBreather(id, name, stamina, price);
        }
    }
}