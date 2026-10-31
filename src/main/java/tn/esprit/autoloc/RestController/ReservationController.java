package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Services.IReservation;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Gestion Reservation")
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservation iReservation;

    @Operation(summary = "ajouter une réservation dans la BD")
    @PostMapping("/add")
    public Reservation ajouter(@RequestBody Reservation reservation) {
        return iReservation.ajouterReservation(reservation);
    }

    @Operation(summary = "récupérer la liste des réservations")
    @GetMapping("/getAll")
    public List<Reservation> afficherTout() {
        return iReservation.afficherReservations();
    }

    // ===== Partie V : Dates =====

    @Operation(summary = "Scénario 14 : réservations dont la date de début est postérieure à une date (format AAAA-MM-JJ)")
    @GetMapping("/debut-apres")
    public List<Reservation> debutApres(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return iReservation.rechercherDebutApres(date);
    }

    @Operation(summary = "Scénario 15 : réservations dont la date de fin est antérieure à une date (format AAAA-MM-JJ)")
    @GetMapping("/fin-avant")
    public List<Reservation> finAvant(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return iReservation.rechercherFinAvant(date);
    }
}
