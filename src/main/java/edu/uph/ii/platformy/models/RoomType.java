package edu.uph.ii.platformy.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "room_types")
@Getter @Setter
@NoArgsConstructor
public class RoomType {

	@Min(0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	public RoomType(String name){
		this.name = name;
	}
}
