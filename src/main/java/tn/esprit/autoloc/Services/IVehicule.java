package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;

import java.math.BigDecimal;
import java.util.List;

public interface IVehicule {
    Vehicule ajouterVehicule(Vehicule vehicule);

    List<Vehicule> afficherVehicules();

    Vehicule afficherVehicule(Long id);

    Vehicule modifierVehicule(Long id, Vehicule vehicule);

    void supprimerVehicule(Long id);
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence);

    // ===== Partie I : Recherches simples =====
    List<Vehicule> rechercherParMarque(String marque);                    // Scénario 1

    List<Vehicule> rechercherParCategorie(CategorieVehicule categorie);   // Scénario 2

    List<Vehicule> rechercherParStatut(StatutVehicule statut);            // Scénario 3

    // ===== Partie II : Recherches partielles =====
    List<Vehicule> rechercherMarqueContenant(String chaine);              // Scénario 5

    List<Vehicule> rechercherModeleCommencePar(String prefixe);           // Scénario 6

    // ===== Partie III : Comparaisons numériques =====
    List<Vehicule> rechercherTarifSuperieurA(BigDecimal tarif);           // Scénario 8

    List<Vehicule> rechercherTarifInferieurA(BigDecimal tarif);           // Scénario 9

    List<Vehicule> rechercherTarifSuperieurOuEgalA(BigDecimal tarif);     // Scénario 10

    // ===== Partie IV : Combinaison de plusieurs critères =====
    List<Vehicule> rechercherParMarqueEtStatut(String marque, StatutVehicule statut);   // Scénario 11

    List<Vehicule> rechercherParCategorieOuCategorie(CategorieVehicule c1, CategorieVehicule c2); // Scénario 12

    List<Vehicule> rechercherParMarqueEtTarifInferieurA(String marque, BigDecimal tarif); // Scénario 13

    // ===== Partie VI : Tri des résultats =====
    List<Vehicule> afficherParStatutTarifCroissant(StatutVehicule statut);  // Scénario 17

    List<Vehicule> afficherTousTarifDecroissant();                          // Scénario 18

}
