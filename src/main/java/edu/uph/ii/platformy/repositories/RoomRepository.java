package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long>{

    //nazwa metody jest jednocześnie zapytaniem
    Page<Room> findByNameContaining(String phrase, Pageable pageable);
    

    //nad klasą Room znajduje się definicja zapytania (@NamedQuery) powiązana z tą metodą
  //  Page<Room> findAllVehiclesUsingNamedQuery(String phrase, Pageable pageable);

    @Query("SELECT v FROM Room v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(v.name) LIKE upper(:phrase) OR " +
            "upper(v.roomType.name) LIKE upper(:phrase)  " +
          //  "upper(v.vehicleType.name) LIKE upper(:phrase)" +
            ") " +
            "AND " +
            "(:min is null OR :min <= v.price) " +
            "AND (:max is null OR :max >= v.price)")
    Page<Room> findAllVehiclesUsingFilter(@Param("phrase") String p, @Param("min") Float priceMin, @Param("max") Float priceMax, Pageable pageable);
}