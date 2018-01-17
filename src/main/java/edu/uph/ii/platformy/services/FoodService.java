package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.FoodFilter;
import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.Food;
import edu.uph.ii.platformy.models.Room;
import edu.uph.ii.platformy.models.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;

public interface FoodService {
    //Page<Food> getAllFood( Pageable pageable);
    //public Page<Room> getAllVehicles(VehicleFilter search, Pageable pageable)
     Page<Food> getAllFood(FoodFilter filter, Pageable pageable);
    Food getFood(Long id);
    void saveFood(Food food);
    void deleteFood(Long id);



}
