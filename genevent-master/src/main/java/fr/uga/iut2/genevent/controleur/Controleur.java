package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.GenEvent;
import fr.uga.iut2.genevent.modele.Structure;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;
import java.util.Map;


/**
 * Le contrôleur de l'application GenEvent.
 * <p>
 * Cette classe agit comme un intermédiaire entre le modèle (GenEvent) et la vue (IHM). 
 * Elle gère la logique de l'application et orchestre les interactions entre les composants.
 * </p>
 */

public class Controleur {

    private final GenEvent genevent;
    private final IHM ihm;

    private static Controleur instance;
 /**
     * Constructeur de la classe Controleur.
     * <p>
     * Initialise une nouvelle instance du contrôleur avec une référence au modèle GenEvent
     * et crée une instance de l'interface utilisateur (IHM) en utilisant JavaFX.
     * </p>
     *
     * @param genevent le modèle GenEvent utilisé par le contrôleur pour gérer les données de l'application
     */
    public Controleur(GenEvent genevent) {
        this.genevent = genevent;
        this.ihm = new JavaFXGUI(this);
        instance = this;
    }

    /**
     * Méthode permettant de récupérer l'instance du contrôleur.
     *
     * @return L'instance du contrôleur
     */
    public static Controleur getInstance() {
        return instance;
    }

    /**
     * Méthode permettant de démarrer l'interaction avec l'utilisateur.
     */
    public void demarrer() {
        this.ihm.demarrerInteraction();
    }

    /**
     * Méthode permettant de créer une structure à partir des informations mises en paramètres.
     *
     * @param infos Les informations de la structure à créer.
     */
    public void creerStructure(IHM.InfosStructure infos) {
        boolean nouvelUtilisateur = this.genevent.ajouteStructure(
                infos.nom,
                infos.type,
                infos.description,
                infos.adressePostale,
                infos.heureOuverture,
                infos.heureFermeture,
                infos.capacite,
                infos.exterieur,
                infos.rooftop,
                infos.nbreEtages,
                infos.email,
                infos.etoiles,
                infos.numTel,
                infos.latitude,
                infos.longitude
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur(
                    "Nouvelle structure : " + infos.nom,
                    true
            );
        } else {
            this.ihm.informerUtilisateur(
                    infos.nom + " est déjà connue de GenEvent.",
                    false
            );
        }
    }

    public void saisirEvenement() {
        this.ihm.saisirNouvelEvenement(this.genevent.getEvenements().keySet());
    }

    /**
     * Méthode permettant de créer un nouvel évènement à partir des informations mises en paramètres.
     *
     * @param infos Les informations de l'évènement à créer.
     */
    public void creerEvenement(IHM.InfosNouvelEvenement infos) {

        this.genevent.nouvelEvenement(
                infos.nom,
                infos.date,
                infos.heureDebut,
                infos.nombrePersonne,
                infos.structures

        );
        this.ihm.informerUtilisateur(
                "Nouvel évènement : " + infos.nom,
                true
        );
        
    }

    /**
     * Méthode permettant de récupérer les structures enregistrées dans l'application.
     *
     * @return La liste des structures enregistrées.
     */
    public Map<String, Structure> getStructures() {
        return this.genevent.getStructures();
    }

    /**
     * Méthode permettant de récupérer les évènements enregistrés dans l'application.
     *
     * @return La liste des évènements enregistrés.
     */
    public Map<String, Evenement>getEvenements() {
        return this.genevent.getEvenements();
    }

}
