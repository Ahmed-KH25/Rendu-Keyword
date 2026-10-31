package tn.esprit.autoloc.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Entities.StatutReservation;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Repositories.AgenceRepo;
import tn.esprit.autoloc.Repositories.MaintenanceRepo;
import tn.esprit.autoloc.Repositories.ReservationRepo;
import tn.esprit.autoloc.Repositories.VehiculeRepo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Jeu de données de test pour l'atelier (base H2 en mémoire).
 * Désactivé avec le profil "mysql" pour ne pas polluer une vraie base.
 * Ne s'exécute que si la base est vide.
 */
@Component
@Profile("!mysql")
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AgenceRepo agenceRepo;
    private final VehiculeRepo vehiculeRepo;
    private final ReservationRepo reservationRepo;
    private final MaintenanceRepo maintenanceRepo;

    @Override
    public void run(String... args) {
        if (vehiculeRepo.count() > 0) {
            return;
        }

        Agence tunis = creerAgence("AutoLoc Tunis Centre", "Tunis", "Avenue Habib Bourguiba", "71000001");
        Agence sousse = creerAgence("AutoLoc Sousse", "Sousse", "Boulevard du 14 Janvier", "73000002");
        Agence sfax = creerAgence("AutoLoc Sfax", "Sfax", "Route de Tunis", "74000003");

        creerVehicule("100 TUN 1001", "Renault", "Clio", CategorieVehicule.CITADINE, "45.00", StatutVehicule.DISPONIBLE, tunis);
        creerVehicule("100 TUN 1002", "Renault", "Megane", CategorieVehicule.BERLINE, "80.00", StatutVehicule.DISPONIBLE, tunis);
        creerVehicule("100 TUN 1003", "Renault", "Kangoo", CategorieVehicule.UTILITAIRE, "55.00", StatutVehicule.LOUE, sousse);
        creerVehicule("100 TUN 1004", "Peugeot", "3008", CategorieVehicule.SUV, "120.00", StatutVehicule.LOUE, sousse);
        creerVehicule("100 TUN 1005", "Peugeot", "208", CategorieVehicule.CITADINE, "40.00", StatutVehicule.DISPONIBLE, tunis);
        creerVehicule("100 TUN 1006", "Toyota", "Corolla", CategorieVehicule.BERLINE, "95.00", StatutVehicule.MAINTENANCE, sousse);
        creerVehicule("100 TUN 1007", "Dacia", "Duster", CategorieVehicule.SUV, "70.00", StatutVehicule.DISPONIBLE, sfax);
        creerVehicule("100 TUN 1008", "Hyundai", "Tucson", CategorieVehicule.SUV, "150.00", StatutVehicule.DISPONIBLE, sfax);

        creerReservation(LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 5), StatutReservation.TERMINEE);
        creerReservation(LocalDate.of(2026, 11, 5), LocalDate.of(2026, 11, 10), StatutReservation.CONFIRMEE);
        creerReservation(LocalDate.of(2026, 11, 20), LocalDate.of(2026, 11, 25), StatutReservation.EN_ATTENTE);
        creerReservation(LocalDate.of(2026, 12, 15), LocalDate.of(2026, 12, 20), StatutReservation.CONFIRMEE);

        creerMaintenance(LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 10), "Vidange");
        creerMaintenance(LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 8), "Changement des freins");
        creerMaintenance(LocalDate.of(2026, 11, 20), LocalDate.of(2026, 11, 30), "Revision complete");
    }

    private Agence creerAgence(String nom, String ville, String adresse, String telephone) {
        Agence a = new Agence();
        a.setNom(nom);
        a.setVille(ville);
        a.setAdresse(adresse);
        a.setTelephone(telephone);
        return agenceRepo.save(a);
    }

    private void creerVehicule(String immatriculation, String marque, String modele,
                               CategorieVehicule categorie, String tarif,
                               StatutVehicule statut, Agence agence) {
        Vehicule v = new Vehicule();
        v.setImmatriculation(immatriculation);
        v.setMarque(marque);
        v.setModele(modele);
        v.setCategorie(categorie);
        v.setTarifJournalier(new BigDecimal(tarif));
        v.setStatut(statut);
        v.setAgence(agence);
        vehiculeRepo.save(v);
    }

    private void creerReservation(LocalDate debut, LocalDate fin, StatutReservation statut) {
        Reservation r = new Reservation();
        r.setDateDebut(debut);
        r.setDateFin(fin);
        r.setStatut(statut);
        reservationRepo.save(r);
    }

    private void creerMaintenance(LocalDate debut, LocalDate fin, String description) {
        Maintenance m = new Maintenance();
        m.setDateDebut(debut);
        m.setDateFin(fin);
        m.setDescription(description);
        maintenanceRepo.save(m);
    }
}
