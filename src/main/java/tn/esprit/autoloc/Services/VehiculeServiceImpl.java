package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Repositories.AgenceRepo;
import tn.esprit.autoloc.Repositories.EquipementRepo;
import tn.esprit.autoloc.Repositories.VehiculeRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeServiceImpl implements IVehicule{
    private final VehiculeRepo  vehiculeRepo;
    private final AgenceRepo agenceRepo;
    private final EquipementRepo equipementRepo;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepo.save(vehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> afficherVehicules() {
        return vehiculeRepo.findAll();
    }

    @Override
    public Vehicule afficherVehicule(Long id) {
        return vehiculeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Véhicule introuvable avec l'identifiant : " + id
                        ));
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehicule) {

        Vehicule vehiculeExistant = vehiculeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Véhicule introuvable avec l'identifiant : " + id
                        ));

        vehiculeExistant.setImmatriculation(
                vehicule.getImmatriculation());
        vehiculeExistant.setMarque(vehicule.getMarque());
        vehiculeExistant.setModele(vehicule.getModele());
        vehiculeExistant.setCategorie(vehicule.getCategorie());
        vehiculeExistant.setTarifJournalier(
                vehicule.getTarifJournalier());
        vehiculeExistant.setStatut(vehicule.getStatut());

        return vehiculeRepo.save(vehiculeExistant);
    }

    @Override
    public void supprimerVehicule(Long id) {

        Vehicule vehicule = vehiculeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Véhicule introuvable avec l'identifiant : " + id
                        ));
        vehiculeRepo.deleteById(id);

    }

    @Override
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = vehiculeRepo.findById(idVehicule)
                .orElseThrow();
        Agence agence = agenceRepo.findById(idAgence)
                .orElseThrow();
        vehicule.setAgence(agence);
        vehicule.setStatut(StatutVehicule.DISPONIBLE);
        return vehiculeRepo.save(vehicule);
    }

    // ===== Partie I : Recherches simples =====

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParMarque(String marque) {
        return vehiculeRepo.findByMarque(marque);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParCategorie(CategorieVehicule categorie) {
        return vehiculeRepo.findByCategorie(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParStatut(StatutVehicule statut) {
        return vehiculeRepo.findByStatut(statut);
    }

    // ===== Partie II : Recherches partielles =====

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherMarqueContenant(String chaine) {
        return vehiculeRepo.findByMarqueContainingIgnoreCase(chaine);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherModeleCommencePar(String prefixe) {
        return vehiculeRepo.findByModeleStartingWithIgnoreCase(prefixe);
    }

    // ===== Partie III : Comparaisons numériques =====

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherTarifSuperieurA(BigDecimal tarif) {
        return vehiculeRepo.findByTarifJournalierGreaterThan(tarif);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherTarifInferieurA(BigDecimal tarif) {
        return vehiculeRepo.findByTarifJournalierLessThan(tarif);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherTarifSuperieurOuEgalA(BigDecimal tarif) {
        return vehiculeRepo.findByTarifJournalierGreaterThanEqual(tarif);
    }

    // ===== Partie IV : Combinaison de plusieurs critères =====

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParMarqueEtStatut(String marque, StatutVehicule statut) {
        return vehiculeRepo.findByMarqueAndStatut(marque, statut);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParCategorieOuCategorie(CategorieVehicule c1, CategorieVehicule c2) {
        return vehiculeRepo.findByCategorieOrCategorie(c1, c2);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> rechercherParMarqueEtTarifInferieurA(String marque, BigDecimal tarif) {
        return vehiculeRepo.findByMarqueAndTarifJournalierLessThan(marque, tarif);
    }

    // ===== Partie VI : Tri des résultats =====

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> afficherParStatutTarifCroissant(StatutVehicule statut) {
        return vehiculeRepo.findByStatutOrderByTarifJournalierAsc(statut);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> afficherTousTarifDecroissant() {
        return vehiculeRepo.findAllByOrderByTarifJournalierDesc();
    }

}

