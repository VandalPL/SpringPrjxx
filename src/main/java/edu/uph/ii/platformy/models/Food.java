package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.InvalidValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menu")
@NamedQuery(name = "Food.findAllFoodUsingNamedQuery",
        query = "SELECT v FROM Food v WHERE upper(v.name)  LIKE upper(:phrase)")
@Getter
@Setter
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Length(min = 2, max = 30)// lub @Size(min = 2, max = 30)
    @InvalidValues(ignoreCase = true, values = {"XXX", "YYY"})
    private String name;

//    @NotBlank
//    @Size(min = 2, max = 50)
//    private String model;

    @Positive
    @Max(1000000)
    private Float price;

//    @Past
//    @Temporal(TemporalType.DATE)
//    @Column(name="production_date")
//    private Date productionDate;



//   @Column(name="created_date", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//   private Date createdDate;

    public Food() {


    }

    public Food(String name, Float price) {
        this.name = name;
        //this.model = model;
        this.price = price;
//        this.productionDate = productionDate;
 //       this.roomType = roomType;
//        this.createdDate = creationDate;
    }
}
