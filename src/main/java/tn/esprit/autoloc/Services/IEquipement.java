package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Vehicule;

public interface IEquipement {
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement);
    void desaffecterEquipement(Long idVehicule, Long idEquipement);

}
