package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Food;
import edu.uph.ii.platformy.models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Page<Food> findByNameContaining(String phrase, Pageable pageable);


    //nad klasą Room znajduje się definicja zapytania (@NamedQuery) powiązana z tą metodą
    Page<Food> findAllFoodUsingNamedQuery(String phrase, Pageable pageable);

    @Query("SELECT v FROM Food v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(v.name) LIKE upper(:phrase)" +

            ") " +
            "AND " +
            "(:min is null OR :min <= v.price) " +
            "AND (:max is null OR :max >= v.price)")
    Page<Food> findAllFoodUsingFilter(@Param("phrase") String p, @Param("min") Float priceMin, @Param("max") Float priceMax, Pageable pageable);

}
