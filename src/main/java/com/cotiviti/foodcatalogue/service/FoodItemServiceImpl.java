package com.cotiviti.foodcatalogue.service;

import com.cotiviti.foodcatalogue.dto.FoodCataloguePage;
import com.cotiviti.foodcatalogue.dto.FoodItemDTO;
import com.cotiviti.foodcatalogue.dto.Restaurant;
import com.cotiviti.foodcatalogue.entity.FoodItem;
import com.cotiviti.foodcatalogue.mapper.FoodItemMapper;
import com.cotiviti.foodcatalogue.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService{

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItem);
    }


    @Override
    public FoodCataloguePage fetchFoodByRestaurant(int restaurantId) {
        List<FoodItem> foodItemList = findAllFoodItem(restaurantId);
        Restaurant restaurant = findRestaurantById(restaurantId);
        return fetchFoodAndRestaurant(foodItemList, restaurant);

    }

    private FoodCataloguePage fetchFoodAndRestaurant(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;

    }

    private Restaurant findRestaurantById(int restaurantId) {
        return  restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/getById/"+restaurantId, Restaurant.class);
    }

    private List<FoodItem> findAllFoodItem(int restaurantId) {
        return foodItemRepository.findByRestaurantId(restaurantId);
    }
}
