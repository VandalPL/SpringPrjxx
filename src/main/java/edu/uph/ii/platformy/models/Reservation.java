package edu.uph.ii.platformy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long id_room;
    private long id_user;


    private long reserv_id;

    public Reservation(Long id_room, long user_id) {
        this.id_room = id_room;
        //this.model = model;
        this.id_user = user_id;

    }


}
