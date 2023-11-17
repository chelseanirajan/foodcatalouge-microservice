package com.cotiviti.foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {
    private int id;
    private String itemName;
    private String itemDescription;
    private Number price;
    private int restaurantId;
    private boolean isVeg;
    private int quantity;

}
