package com.berk.dragons.dto;

import com.berk.dragons.model.DragonBase;

public class DragonResponseDto {
    private final int id;
    private final String name;
    private final String type;
    private final double price;
    private final String status;

    public DragonResponseDto(DragonBase dragon) {
        this.id = dragon.getId();
        this.name = dragon.getName();
        this.type = dragon.getSpeciesType();
        this.price = dragon.calculateFinalPrice();

        if (dragon.getCurrentRider() != null) {
            this.status = "RENTED BY " + dragon.getCurrentRider().getName();
        } else {
            this.status = "AVAILABLE";
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("[%d] %-15s | Type: %-10s | Price: %.2f | %s",
                id, name, type, price, status);
    }
}