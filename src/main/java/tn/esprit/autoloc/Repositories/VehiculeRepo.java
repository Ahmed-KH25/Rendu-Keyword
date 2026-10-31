package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehiculeRepo extends JpaRepository<Vehicule,Long> {

    // ===== Partie I : Recherches simples =====

    // Scénario 1 : véhicules d'une marque précise (égalité sur l'attribut "marque")
    List<Vehicule> findByMarque(String marque);

    // Scénario 2 : véhicules d'une catégorie donnée (attribut "categorie", de type enum)
    List<Vehicule> findByCategorie(CategorieVehicule categorie);

    // Scénario 3 : véhicules ayant un statut donné (appelée avec StatutVehicule.DISPONIBLE)
    List<Vehicule> findByStatut(StatutVehicule statut);

    // ===== Partie II : Recherches partielles =====

    // Scénario 5 : marque contenant une chaîne (LIKE %chaine%, sans tenir compte de la casse)
    List<Vehicule> findByMarqueContainingIgnoreCase(String chaine);

    // Scénario 6 : modèle commençant par une chaîne (LIKE chaine%)
    List<Vehicule> findByModeleStartingWithIgnoreCase(String prefixe);

    // ===== Partie III : Comparaisons numériques =====

    // Scénario 8 : tarif strictement supérieur à une valeur (appelée avec 100)
    List<Vehicule> findByTarifJournalierGreaterThan(BigDecimal tarif);

    // Scénario 9 : tarif strictement inférieur à une valeur (appelée avec 50)
    List<Vehicule> findByTarifJournalierLessThan(BigDecimal tarif);

    // Scénario 10 : tarif supérieur ou égal à une valeur (appelée avec 80)
    List<Vehicule> findByTarifJournalierGreaterThanEqual(BigDecimal tarif);

    // ===== Partie IV : Combinaison de plusieurs critères =====

    // Scénario 11 : marque ET statut
    List<Vehicule> findByMarqueAndStatut(String marque, StatutVehicule statut);

    // Scénario 12 : catégorie SUV OU catégorie BERLINE
    List<Vehicule> findByCategorieOrCategorie(CategorieVehicule categorie1, CategorieVehicule categorie2);

    // Scénario 13 : marque ET tarif inférieur à une valeur
    List<Vehicule> findByMarqueAndTarifJournalierLessThan(String marque, BigDecimal tarif);

    // ===== Partie VI : Tri des résultats =====

    // Scénario 17 : véhicules d'un statut donné, triés par tarif croissant
    List<Vehicule> findByStatutOrderByTarifJournalierAsc(StatutVehicule statut);

    // Scénario 18 : tous les véhicules, triés par tarif décroissant
    List<Vehicule> findAllByOrderByTarifJournalierDesc();
}
