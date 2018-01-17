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
@Table(name = "rooms")
@NamedQuery(name = "Vehicle.findAllVehiclesUsingNamedQuery",
        query = "SELECT v FROM Room v WHERE upper(v.name) LIKE upper(:phrase)  or upper(v.roomType.name) LIKE upper(:phrase)")
@Getter
@Setter
@AllArgsConstructor
public class Room {

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



    private long reserv_id;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)//EAGER powoduje zaciągnięcie obiektu VehicleType wraz z obiektem Vehicle.
    @JoinColumn(name="type_id", nullable = false)
    private RoomType roomType;


    @ManyToMany(fetch = FetchType.LAZY)//LAZY powoduje dociągnięcie tych elementów dopiero wtedy, gdy są używane
    private List<Accessory> accessories;


    public Room() {
//        this.createdDate = new Date();
        this.roomType = new RoomType();
        this.accessories = new ArrayList<>();
    }

    public Room(String name, Float price, RoomType roomType) {
        this.name = name;
        //this.model = model;
        this.price = price;
//        this.productionDate = productionDate;
        this.roomType = roomType;
//        this.createdDate = creationDate;
    }
}
