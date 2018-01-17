package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.VehicleFilter;
import edu.uph.ii.platformy.exceptions.VehicleNotFoundException;
import edu.uph.ii.platformy.models.Cart;
import edu.uph.ii.platformy.models.Reservation;
import edu.uph.ii.platformy.models.Room;
import edu.uph.ii.platformy.repositories.CartRepository;
import edu.uph.ii.platformy.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public Page<Reservation> getAllReservations( Pageable pageable) {
        Page page;

            page = reservationRepository.findAll(pageable);


        return page;

    }

    @Override
    public void deleteReservation(long id) {
        if(reservationRepository.existsById(id) == true){
            reservationRepository.deleteById(id);
        }else{
            throw new VehicleNotFoundException(id);
        }
    }

    @Override
    public Reservation getReservation(long id) {
        Optional<Reservation> optionalVehicle = reservationRepository.findById(id);
        Reservation reservation = optionalVehicle.orElseThrow( () -> new VehicleNotFoundException(id) );
        //room.getAccessories().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return reservation;
    }
}
