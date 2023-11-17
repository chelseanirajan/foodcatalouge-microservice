package com.cotiviti.foodcatalogue.controller;

import com.cotiviti.foodcatalogue.dto.FoodCataloguePage;
import com.cotiviti.foodcatalogue.dto.FoodItemDTO;
import com.cotiviti.foodcatalogue.dto.Restaurant;
import com.cotiviti.foodcatalogue.service.FoodItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food-item")
@CrossOrigin
public class FoodItemController {

    @Autowired
    private FoodItemServiceImpl foodItemService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO foodItemDTO1 = foodItemService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(foodItemDTO1, HttpStatus.CREATED);
    }
//    @GetMapping("/getById/{restaurantId}")
//    public ResponseEntity<Restaurant> getFoodByRestaurant(@PathVariable int restaurantId){
//        Restaurant byRestaurantId = foodItemService.findByRestaurantId(restaurantId);
//        return new ResponseEntity<>(byRestaurantId, HttpStatus.OK);
//    }

    @GetMapping("/fetch-food-by-restaurant/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchFoodByRestaurant(@PathVariable int restaurantId){
        FoodCataloguePage foodCataloguePage = foodItemService.fetchFoodByRestaurant(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
