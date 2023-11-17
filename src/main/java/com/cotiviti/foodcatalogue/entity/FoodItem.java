package com.cotiviti.foodcatalogue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String itemName;
    private String itemDescription;
    private Number price;
    private int restaurantId;
    private boolean isVeg;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int quantity;
}
