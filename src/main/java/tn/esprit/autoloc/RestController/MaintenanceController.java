package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Services.IMaintenance;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Gestion Maintenance")
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {
    private final IMaintenance iMaintenance;

    @Operation(summary = "ajouter une maintenance dans la BD")
    @PostMapping("/add")
    public Maintenance ajouter(@RequestBody Maintenance maintenance) {
        return iMaintenance.ajouterMaintenance(maintenance);
    }

    @Operation(summary = "récupérer la liste des maintenances")
    @GetMapping("/getAll")
    public List<Maintenance> afficherTout() {
        return iMaintenance.afficherMaintenances();
    }

    // ===== Partie V : Dates =====

    @Operation(summary = "Scénario 16 : maintenances dont la date de fin est comprise entre deux dates (format AAAA-MM-JJ)")
    @GetMapping("/fin-entre")
    public List<Maintenance> finEntre(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return iMaintenance.rechercherFinEntre(debut, fin);
    }
}
