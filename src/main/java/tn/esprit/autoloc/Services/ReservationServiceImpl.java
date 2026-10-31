package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Repositories.ReservationRepo;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements IReservation {
    private final ReservationRepo reservationRepo;

    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> afficherReservations() {
        return reservationRepo.findAll();
    }

    // ===== Partie V : Dates =====

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> rechercherDebutApres(LocalDate date) {
        return reservationRepo.findByDateDebutAfter(date);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> rechercherFinAvant(LocalDate date) {
        return reservationRepo.findByDateFinBefore(date);
    }
}
