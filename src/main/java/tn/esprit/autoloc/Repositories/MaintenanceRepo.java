package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Entities.Paiement;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepo extends JpaRepository<Maintenance,Long> {

    // ===== Partie V : Dates =====

    // Scénario 16 : maintenances dont la date de fin est comprise entre deux dates (bornes incluses)
    List<Maintenance> findByDateFinBetween(LocalDate debut, LocalDate fin);
}
