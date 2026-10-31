package tn.esprit.autoloc.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Services.IEquipement;

@RestController
@RequestMapping("/api/Equipent")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipement iEquipement;
    @PutMapping("/affecterEquipement/{idEquipement}/{idVehicule}")
    public Vehicule affecterVehicule(@PathVariable Long idEquipement,
                                     @PathVariable Long idVehicule){
        return iEquipement.affecterEquipement(idEquipement,idVehicule);
    }

    @PutMapping("/desaffecterEquipement/{idEquipement}/{idVehicule}")
    public void desaffecterEquipement(@PathVariable Long idEquipement,
                                      @PathVariable Long idVehicule){
       iEquipement.desaffecterEquipement(idEquipement,idVehicule);
    }
}
