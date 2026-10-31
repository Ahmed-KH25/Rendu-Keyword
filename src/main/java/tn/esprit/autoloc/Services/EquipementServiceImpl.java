package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Repositories.EquipementRepo;
import tn.esprit.autoloc.Repositories.VehiculeRepo;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipementServiceImpl implements IEquipement{
    private final EquipementRepo equipementRepo;
    private final VehiculeRepo vehiculeRepo;

    @Override
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepo.findById(idVehicule).orElseThrow(null);
        Equipement equipement = equipementRepo.findById(idEquipement).orElseThrow(null);

        vehicule.getEquipements().add(equipement);
        return vehiculeRepo.save(vehicule);

}
    @Override
    public void desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepo.findById(idVehicule).orElseThrow();
        Equipement equipement = equipementRepo.findById(idEquipement).orElseThrow();

        vehicule.getEquipements().remove(equipement);

        vehiculeRepo.save(vehicule);
    }
}
