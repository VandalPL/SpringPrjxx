package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.FoodFilter;
import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.exceptions.VehicleNotFoundException;
import edu.uph.ii.platformy.models.Food;
import edu.uph.ii.platformy.repositories.AccessoryRepository;
import edu.uph.ii.platformy.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service("FoodService")
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public Page<Food> getAllFood(FoodFilter filter, Pageable pageable) {
        Page page;
        if(filter.isEmpty()){
            page = foodRepository.findAll(pageable);
        }else{
            page = foodRepository.findAllFoodUsingFilter(filter.getPhraseLIKE(), filter.getMinPrice(), filter.getMaxPrice(), pageable);
        }

        return page;


    }

//    @Override
//    public void getAllFood(Food food) {
//        food = foodRepository.findAll();
//    }

//    @Override
//    public Page<Food> getAllFood(VehicleFilter filter, Pageable pageable) {
//        Page page;
//
//            page = foodRepository.findAll(pageable);
//
//
//        return page;
//
//    }

    @Override
    public Food getFood(Long id) {
        Optional<Food> optionalFood = foodRepository.findById(id);
        Food food = optionalFood.orElseThrow( () -> new VehicleNotFoundException(id) );
       // food.getAccessories().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return food;
    }

    @Override
    public void saveFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long id) {
        if(foodRepository.existsById(id) == true){
            foodRepository.deleteById(id);
        }else{
            throw new VehicleNotFoundException(id);
        }
    }


}
