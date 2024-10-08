package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant l'application GenEvent qui gère les structures et les événements associés.
 * Cette classe est sérialisable pour permettre la sauvegarde et le chargement d'instances.
 */
public class GenEvent implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final Map<String, Structure> structures;  // association qualifiée par le nom
    private final Map<String, Evenement> evenements;  // association qualifiée par le nom

    /**
     * Constructeur par défaut de GenEvent.
     * Initialise les collections de structures et d'événements.
     */
    public GenEvent() {
        this.structures = new HashMap<>();
        this.evenements = new HashMap<>();
    }

    /**
     * Ajoute une nouvelle structure à GenEvent si elle n'existe pas déjà.
     *
     * @param nom            Nom de la structure.
     * @param type           Type de la structure (Bar, NightClub, etc.).
     * @param adressePostale Adresse postale de la structure.
     * @param description    Description de la structure.
     * @param heureOuverture Heure d'ouverture de la structure.
     * @param heureFermeture Heure de fermeture de la structure.
     * @param capacite       Capacité d'accueil de la structure.
     * @param exterieur      Indique si la structure est en extérieur (true/false).
     * @param rooftop        Indique si la structure possède un rooftop (true/false).
     * @param nbreEtages     Nombre d'étages de la structure.
     * @param email          Email de contact de la structure.
     * @param etoiles        Nombre d'étoiles de la structure.
     * @param numTel         Numéro de téléphone de la structure.
     * @param latitude       Latitude géographique de la structure.
     * @param longitude      Longitude géographique de la structure.
     * @return true si la structure est ajoutée avec succès, false si elle existe déjà.
     */
    public boolean ajouteStructure(String nom, String type, String adressePostale, String description, String heureOuverture, String heureFermeture,
                                   String capacite, String exterieur, String rooftop, String nbreEtages, String email,
                                   String etoiles, String numTel, String latitude, String longitude) {
        if (this.structures.containsKey(nom)) {
            return false;
        } else {
            this.structures.put(nom, new Structure(nom, type, adressePostale, description, "", heureOuverture, heureFermeture,
                    capacite, exterieur, rooftop, nbreEtages, email, etoiles, numTel, latitude, longitude));
            return true;
        }
    }

    /**
     * Récupère la liste des structures enregistrées dans GenEvent.
     *
     * @return Map associant les noms des structures à leurs instances.
     */
    public Map<String, Structure> getStructures() {
        return this.structures;
    }

    /**
     * Crée un nouvel événement et l'ajoute à la liste d'evenement de GenEvent.
     *
     * @param nom            Nom de l'événement.
     * @param date           Date de l'événement.
     * @param heureDebut     Heure de début de l'événement.
     * @param nombrePersonne Nombre de personnes attendues à l'événement.
     * @param structures     Liste des structures impliquées dans l'événement.
     */
    public void nouvelEvenement(String nom, LocalDate date, LocalTime heureDebut, int nombrePersonne, ArrayList<Structure> structures) {
        assert !this.evenements.containsKey(nom);
        Evenement evt = Evenement.initialiseEvenement(this, nom, date, heureDebut, nombrePersonne, structures);
        this.evenements.put(nom, evt);
    }


    /**
     * Récupère la liste des événements enregistrés dans GenEvent.
     *
     * @return Map associant les noms des événements à leurs instances.
     */
    public Map<String, Evenement> getEvenements() {
        return this.evenements;
    }

}
