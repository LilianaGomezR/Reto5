package Repositorio;

import Interface.ReservationCrudRepository;
import Modelo.Reservation;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class ReservationRepository {
    
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }
    
    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation);
    }

    public List<Reservation> ReservacionStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> ReservacionTiempo(Date fechaInicial, Date fechaFinal){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(fechaInicial, fechaFinal);
    }

       
    public   List<Object[]> reporteClientes() {
        return reservationCrudRepository.reporteClientes();

    }
}   