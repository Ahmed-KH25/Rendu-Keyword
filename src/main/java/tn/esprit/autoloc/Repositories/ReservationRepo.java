package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Entities.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {

    // ===== Partie V : Dates =====

    // Scénario 14 : réservations dont la date de début est postérieure à une date
    List<Reservation> findByDateDebutAfter(LocalDate date);

    // Scénario 15 : réservations dont la date de fin est antérieure à une date
    List<Reservation> findByDateFinBefore(LocalDate date);
}
