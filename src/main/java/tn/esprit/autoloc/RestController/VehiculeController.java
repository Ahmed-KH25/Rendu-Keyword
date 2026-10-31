package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Services.IVehicule;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Tag(name="Gestion Vehicule")
@RequestMapping("/api/Vehicule")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehicule iVehicule;
    @Operation(summary="ajouter vehicule dans la BD")
    @PostMapping("/addVehicule")
    public ResponseEntity<Vehicule> ajouter(
            @Valid @RequestBody Vehicule vehicule) {

        Vehicule nouveau =
                iVehicule.ajouterVehicule(vehicule);

        return ResponseEntity.status(
                HttpStatus.CREATED)
                .body(nouveau);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<Vehicule>> afficherTous() {
        return ResponseEntity.ok(
                iVehicule.afficherVehicules());
    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<Vehicule> afficherParId(
            @Parameter(description ="l'identifiant du vehicule")
            @PathVariable Long id) {
        return ResponseEntity.ok(
                iVehicule.afficherVehicule(id));
    }

    @PutMapping("/PutById/{id}")
    public ResponseEntity<Vehicule> modifier(
            @PathVariable Long id,
            @Valid @RequestBody Vehicule vehicule) {

        return ResponseEntity.ok(
                iVehicule.modifierVehicule(id, vehicule));
    }

    @DeleteMapping("/Delete/{id}")
    public void supprimer(
            @PathVariable Long id) {

        iVehicule.supprimerVehicule(id);

    }
    @PutMapping("/affecterVehicule/{idVehicule}/{idAgence}")
    public Vehicule affecterVehicule(@PathVariable Long idVehicule,
                                  @PathVariable Long idAgence){
        return iVehicule.affecterVehicule(idVehicule,idAgence);
    }

    // ===== Partie I : Recherches simples =====

    @Operation(summary = "Scénario 1 : véhicules d'une marque précise")
    @GetMapping("/marque/{marque}")
    public ResponseEntity<List<Vehicule>> parMarque(@PathVariable String marque) {
        return ResponseEntity.ok(iVehicule.rechercherParMarque(marque));
    }

    @Operation(summary = "Scénario 2 : véhicules d'une catégorie donnée")
    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Vehicule>> parCategorie(@PathVariable CategorieVehicule categorie) {
        return ResponseEntity.ok(iVehicule.rechercherParCategorie(categorie));
    }

    @Operation(summary = "Scénario 3 : véhicules ayant le statut DISPONIBLE")
    @GetMapping("/disponibles")
    public ResponseEntity<List<Vehicule>> disponibles() {
        return ResponseEntity.ok(iVehicule.rechercherParStatut(StatutVehicule.DISPONIBLE));
    }

    // ===== Partie II : Recherches partielles =====

    @Operation(summary = "Scénario 5 : véhicules dont la marque contient une chaîne")
    @GetMapping("/marque-contient/{chaine}")
    public ResponseEntity<List<Vehicule>> marqueContient(@PathVariable String chaine) {
        return ResponseEntity.ok(iVehicule.rechercherMarqueContenant(chaine));
    }

    @Operation(summary = "Scénario 6 : véhicules dont le modèle commence par une chaîne")
    @GetMapping("/modele-commence/{prefixe}")
    public ResponseEntity<List<Vehicule>> modeleCommence(@PathVariable String prefixe) {
        return ResponseEntity.ok(iVehicule.rechercherModeleCommencePar(prefixe));
    }

    // ===== Partie III : Comparaisons numériques =====

    @Operation(summary = "Scénario 8 : véhicules dont le tarif est supérieur à 100 DT (valeur modifiable)")
    @GetMapping("/tarif-superieur")
    public ResponseEntity<List<Vehicule>> tarifSuperieur(
            @RequestParam(defaultValue = "100") BigDecimal tarif) {
        return ResponseEntity.ok(iVehicule.rechercherTarifSuperieurA(tarif));
    }

    @Operation(summary = "Scénario 9 : véhicules dont le tarif est inférieur à 50 DT (valeur modifiable)")
    @GetMapping("/tarif-inferieur")
    public ResponseEntity<List<Vehicule>> tarifInferieur(
            @RequestParam(defaultValue = "50") BigDecimal tarif) {
        return ResponseEntity.ok(iVehicule.rechercherTarifInferieurA(tarif));
    }

    @Operation(summary = "Scénario 10 : véhicules dont le tarif est supérieur ou égal à 80 DT (valeur modifiable)")
    @GetMapping("/tarif-superieur-egal")
    public ResponseEntity<List<Vehicule>> tarifSuperieurEgal(
            @RequestParam(defaultValue = "80") BigDecimal tarif) {
        return ResponseEntity.ok(iVehicule.rechercherTarifSuperieurOuEgalA(tarif));
    }

    // ===== Partie IV : Combinaison de plusieurs critères =====

    @Operation(summary = "Scénario 11 : véhicules d'une marque donnée ET ayant le statut DISPONIBLE")
    @GetMapping("/marque-et-statut")
    public ResponseEntity<List<Vehicule>> marqueEtStatut(
            @RequestParam String marque,
            @RequestParam(defaultValue = "DISPONIBLE") StatutVehicule statut) {
        return ResponseEntity.ok(iVehicule.rechercherParMarqueEtStatut(marque, statut));
    }

    @Operation(summary = "Scénario 12 : véhicules de catégorie SUV OU BERLINE")
    @GetMapping("/categorie-ou")
    public ResponseEntity<List<Vehicule>> categorieOu(
            @RequestParam(defaultValue = "SUV") CategorieVehicule categorie1,
            @RequestParam(defaultValue = "BERLINE") CategorieVehicule categorie2) {
        return ResponseEntity.ok(iVehicule.rechercherParCategorieOuCategorie(categorie1, categorie2));
    }

    @Operation(summary = "Scénario 13 : véhicules d'une marque donnée ET dont le tarif est inférieur à 100 DT")
    @GetMapping("/marque-et-tarif-inferieur")
    public ResponseEntity<List<Vehicule>> marqueEtTarifInferieur(
            @RequestParam String marque,
            @RequestParam(defaultValue = "100") BigDecimal tarif) {
        return ResponseEntity.ok(iVehicule.rechercherParMarqueEtTarifInferieurA(marque, tarif));
    }

    // ===== Partie VI : Tri des résultats =====

    @Operation(summary = "Scénario 17 : véhicules disponibles classés par tarif croissant")
    @GetMapping("/disponibles/tarif-croissant")
    public ResponseEntity<List<Vehicule>> disponiblesTarifCroissant() {
        return ResponseEntity.ok(
                iVehicule.afficherParStatutTarifCroissant(StatutVehicule.DISPONIBLE));
    }

    @Operation(summary = "Scénario 18 : véhicules classés par tarif décroissant")
    @GetMapping("/tarif-decroissant")
    public ResponseEntity<List<Vehicule>> tarifDecroissant() {
        return ResponseEntity.ok(iVehicule.afficherTousTarifDecroissant());
    }
}
