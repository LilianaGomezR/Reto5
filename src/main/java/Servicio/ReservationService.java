package Servicio;

import Modelo.Client;
import Modelo.Reservation;
import Reportes.ContadorClientes;
import Reportes.StatusReservas;
import Repositorio.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    
    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservation1 = reservationRepository.getReservation(reservation.getIdReservation());
            if (reservation1.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }


    public StatusReservas ReservacionStatus(){
       
        List<Reservation> completed = reservationRepository.ReservacionStatus("completed");
        List<Reservation> cancelled = reservationRepository.ReservacionStatus("cancelled");

        return new StatusReservas(completed.size(), cancelled.size());
    }

    public List<Reservation> ReservacionTiempo(String fechaInicial,String fechaFinal){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaUno = new Date();
        Date fechaDos = new Date();

        try {
            fechaUno = parser.parse(fechaInicial);
            fechaDos = parser.parse(fechaFinal);
        } catch (ParseException evt) {
            evt.printStackTrace();
        }
        if (fechaUno.before(fechaDos)) {
            return reservationRepository.ReservacionTiempo(fechaUno, fechaDos);
        } else {
            return new ArrayList<>();
        }
    }

  

    public List<ContadorClientes> reporteClientes() {        
        List<ContadorClientes> resultado = new ArrayList<>();
        List<Object[]> reporte = reservationRepository.reporteClientes();
        System.out.println(reporte);
        for (int i = 0; i < reporte.size(); i++) {
            resultado.add(new ContadorClientes((Long) reporte.get(i)[1], (Client) reporte.get(i)[0]));
        }
        return resultado;
    }


}
