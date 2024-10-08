package fr.uga.iut2.genevent.vue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;

import fr.uga.iut2.genevent.modele.Structure;


public abstract class IHM {
    /**
     * Classe conteneur pour les informations saisies à propos d'un
     * {@link fr.uga.iut2.genevent.modele.Structure}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosStructure {
        public final String nom;
        public final String type;
        public final String adressePostale;
        public final String description;
        public final String heureOuverture;
        public final String heureFermeture;
        public final String capacite;
        public final String exterieur;
        public final String rooftop;
        public final String nbreEtages;
        public final String email;
        public final String etoiles;
        public final String numTel;
        public final String latitude;
        public final String longitude;

        public InfosStructure(final String nom, final String type, final String adressePostale, final String description, final String heureOuverture, final String heureFermeture,
                                final String capacite, final String exterieur, final String rooftop, final String nbreEtages,
                                final String email, final String etoiles, final String numTel, final String latitude, final String longitude) {
            this.nom = nom;
            this.type = type;
            this.adressePostale = adressePostale;
            this.description = description;
            this.heureOuverture = heureOuverture;
            this.heureFermeture = heureFermeture;
            this.capacite = capacite;
            this.exterieur = exterieur;
            this.rooftop = rooftop;
            this.nbreEtages = nbreEtages;
            this.email = email;
            this.etoiles = etoiles;
            this.numTel = numTel;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    /**
     * Classe conteneur pour les informations saisies pour un nouvel
     * {@link fr.uga.iut2.genevent.modele.Evenement}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosNouvelEvenement {
        public final String nom;
        public final LocalDate date;
        public final LocalTime heureDebut;
        public final int nombrePersonne ;
        public ArrayList<Structure> structures;

        public InfosNouvelEvenement(final String nom, final LocalDate date, final LocalTime heureDebut,final int nombrePersonne,ArrayList<Structure> structures) {
            this.nom = nom;
            this.date = date;
            this.heureDebut = heureDebut;
            this.nombrePersonne=nombrePersonne;
            this.structures=structures;
        }
    }

    /**
     * Rend actif l'interface Humain-machine.
     *
     * L'appel est bloquant : le contrôle est rendu à l'appelant une fois que
     * l'IHM est fermée.
     *
     */
    public abstract void demarrerInteraction();

    /**
     * Affiche un message d'information à l'attention de l'utilisa·teur/trice.
     *
     * @param msg Le message à afficher.
     *
     * @param succes true si le message informe d'une opération réussie, false
     *     sinon.
     */
    public abstract void informerUtilisateur(final String msg, final boolean succes);


    /**
     * Récupère les informations nécessaires à la création d'un nouvel
     * {@link fr.uga.iut2.genevent.modele.Evenement}.
     *
     * @param nomsExistants L'ensemble des noms d'évenements qui ne sont plus
     *     disponibles.
     *
     */
    public abstract void saisirNouvelEvenement(final Set<String> nomsExistants);

}
