package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Cart;
import edu.uph.ii.platformy.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
