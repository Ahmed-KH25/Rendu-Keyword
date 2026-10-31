package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Maintenance;

import java.time.LocalDate;
import java.util.List;

public interface IMaintenance {
    Maintenance ajouterMaintenance(Maintenance maintenance);

    List<Maintenance> afficherMaintenances();

    // ===== Partie V : Dates =====
    List<Maintenance> rechercherFinEntre(LocalDate debut, LocalDate fin);   // Scénario 16
}
