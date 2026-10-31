package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Repositories.MaintenanceRepo;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MaintenanceServiceImpl implements IMaintenance {
    private final MaintenanceRepo maintenanceRepo;

    @Override
    public Maintenance ajouterMaintenance(Maintenance maintenance) {
        return maintenanceRepo.save(maintenance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> afficherMaintenances() {
        return maintenanceRepo.findAll();
    }

    // ===== Partie V : Dates =====

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> rechercherFinEntre(LocalDate debut, LocalDate fin) {
        return maintenanceRepo.findByDateFinBetween(debut, fin);
    }
}
