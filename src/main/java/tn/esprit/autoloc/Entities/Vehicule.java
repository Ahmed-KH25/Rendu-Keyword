package tn.esprit.autoloc.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;
//    @Column(nullable = false, unique = true, length = 20)
//    private String immatriculation;
//    @Column(nullable = false, length = 50)
//    private String marque;
//    @Column(nullable = false, length = 50)
//    private String modele;
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 20)
//    private CategorieVehicule categorie;
//    @Column(nullable = false, precision = 10, scale = 2)
//    private BigDecimal tarifJournalier;
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 20)
//    private StatutVehicule statut;

//Entité vehicule avec les validators
    @NotBlank(message = "L'immatriculation est obligatoire")
    @Size(max = 20, message = "20 caractères maximum")
    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    @NotBlank(message = "La marque est obligatoire")
    @Size(max = 50, message = "50 caractères maximum")
    @Column(nullable = false, length = 50)
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    @Size(max = 50, message = "50 caractères maximum")
    @Column(nullable = false, length = 50)
    private String modele;

    @NotNull(message = "La catégorie est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CategorieVehicule categorie;

    @NotNull(message = "Le tarif journalier est obligatoire")
    @DecimalMin(value = "0.01",
            message = "Le tarif doit être supérieur à zéro")
    @Digits(integer = 8, fraction = 2,
            message = "Le tarif doit comporter au maximum 8 chiffres entiers et 2 décimales")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifJournalier;

    @NotNull(message = "Le statut est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutVehicule statut;
    @ManyToOne
    @JsonIgnore
    Agence agence;
    @ManyToMany
    Set<Equipement> equipements;


}
