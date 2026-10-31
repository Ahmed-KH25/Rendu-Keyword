package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IReservation {
    Reservation ajouterReservation(Reservation reservation);

    List<Reservation> afficherReservations();

    // ===== Partie V : Dates =====
    List<Reservation> rechercherDebutApres(LocalDate date);   // Scénario 14

    List<Reservation> rechercherFinAvant(LocalDate date);     // Scénario 15
}
