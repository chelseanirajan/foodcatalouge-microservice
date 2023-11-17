package com.cotiviti.foodcatalogue.service;

import com.cotiviti.foodcatalogue.dto.FoodCataloguePage;
import com.cotiviti.foodcatalogue.dto.FoodItemDTO;
import com.cotiviti.foodcatalogue.dto.Restaurant;
import org.springframework.http.ResponseEntity;

public interface FoodItemService {
    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO);


    FoodCataloguePage fetchFoodByRestaurant(int restaurantId);
}
