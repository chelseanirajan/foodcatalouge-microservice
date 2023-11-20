package com.cotiviti.foodcatalogue.service;

import com.cotiviti.foodcatalogue.dto.FoodCataloguePage;
import com.cotiviti.foodcatalogue.dto.FoodItemDTO;
import com.cotiviti.foodcatalogue.dto.Restaurant;
import com.cotiviti.foodcatalogue.entity.FoodItem;
import com.cotiviti.foodcatalogue.mapper.FoodItemMapper;
import com.cotiviti.foodcatalogue.repository.FoodItemRepository;
import com.google.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class FoodItemServiceImplTest {

    @InjectMocks
    FoodItemServiceImpl foodItemService;

    @Mock
    FoodItemRepository foodItemRepository;
    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFoodItem(){
        FoodItemDTO foodItemDTO = new FoodItemDTO(1, "Fry Chicken", "testy", 12, 1, false, 2);

        FoodItem foodItem = FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO);

        when(foodItemRepository.save(foodItem)).thenReturn(foodItem);

        FoodItemDTO foodItemDTO1 = foodItemService.addFoodItem(foodItemDTO);
        assertEquals(foodItemDTO, foodItemDTO1);
        verify(foodItemRepository, times(1)).save(foodItem);
    }
    @Test
    public void testFindAllFoodItem(){
        int restaurantId = 1;
        List<FoodItem> foodItemList = Arrays.asList(
                new FoodItem(1, "Dry Chicken", "testy", 12, 1, false, 2),
                new FoodItem(2, "Fry Chicken", "testy1", 13, 2, false, 4)
        );
        Restaurant restaurant = new Restaurant();
        when(foodItemRepository.findByRestaurantId(restaurantId)).thenReturn(foodItemList);
        when(restTemplate.getForObject(anyString(), eq(Restaurant.class))).thenReturn(restaurant);
        FoodCataloguePage foodCataloguePage = foodItemService.fetchFoodByRestaurant(restaurantId);

        verify(foodItemRepository, times(1)).findByRestaurantId(restaurantId);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Restaurant.class));

        assertEquals(foodItemList, foodCataloguePage.getFoodItemList());
        assertEquals(restaurant, foodCataloguePage.getRestaurant());


    }

}
