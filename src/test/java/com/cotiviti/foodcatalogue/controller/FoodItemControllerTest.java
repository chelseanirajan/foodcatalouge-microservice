package com.cotiviti.foodcatalogue.controller;

import com.cotiviti.foodcatalogue.dto.FoodCataloguePage;
import com.cotiviti.foodcatalogue.dto.FoodItemDTO;
import com.cotiviti.foodcatalogue.dto.Restaurant;
import com.cotiviti.foodcatalogue.entity.FoodItem;
import com.cotiviti.foodcatalogue.service.FoodItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class FoodItemControllerTest {

    @InjectMocks
    FoodItemController foodItemController;

    @Mock
    FoodItemServiceImpl foodItemService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFoodItem(){
        FoodItemDTO foodItemDTO = new FoodItemDTO(1, "Fry Chicken", "testy", 12, 1, false, 2);
        when(foodItemService.addFoodItem(foodItemDTO)).thenReturn(foodItemDTO);

        ResponseEntity<FoodItemDTO> response = foodItemController.addFoodItem(foodItemDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(foodItemDTO, response.getBody());

        verify(foodItemService, times(1)).addFoodItem(foodItemDTO);
    }

    @Test
    public void testFetchFoodByRestaurant(){
        int restaurantId = 1;
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();

        List<FoodItem> foodItemList = Arrays.asList(
                new FoodItem(1, "Dry Chicken", "testy", 12, 1, false, 2),
                new FoodItem(2, "Fry Chicken", "testy1", 13, 2, false, 4)

        );

        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(new Restaurant(1, "Restaurant 1", "rest1", "Roanoke 1", "texas"));
        when(foodItemService.fetchFoodByRestaurant(restaurantId)).thenReturn(foodCataloguePage);

        ResponseEntity<FoodCataloguePage> response = foodItemController.fetchFoodByRestaurant(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodCataloguePage, response.getBody());

        verify(foodItemService, times(1)).fetchFoodByRestaurant(restaurantId);

    }
}
