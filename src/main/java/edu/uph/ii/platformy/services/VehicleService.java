package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.Room;
import edu.uph.ii.platformy.models.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {

    List<Accessory> getAllAccessories();

    List<RoomType> getAllTypes();

    Page<Room> getAllVehicles(VehicleFilter filter, Pageable pageable);

    List<Room> getAllRooms();

    Room getVehicle(Long id);

    void deleteVehicle(Long id);

    void saveVehicle(Room room);

}
