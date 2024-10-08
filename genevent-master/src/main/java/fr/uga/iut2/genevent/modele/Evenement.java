package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Classe représentant un événement.
 * Un événement est caractérisé par :
 * - une date
 * - un nom de client
 * - un nombre de personnes
 * - une heure de début
 * - une liste de structures
 */
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private ArrayList<Structure> structures;//liste des structures d'un événement
    private final GenEvent genevent;
    private final String nom;
    private LocalDate dateSelectionnee;
    private LocalTime heureDebut;
    private int nombrePersonne;

/**
 * initialise un événement
 * @param genevent 
 * @param nom
 * @param dateSelectionnee
 * @param heureDebut
 * @param nombrePersonne
 * @param structures
 * @return
 */
    public static Evenement initialiseEvenement(GenEvent genevent, String nom, LocalDate dateSelectionnee, LocalTime heureDebut, int nombrePersonne, ArrayList<Structure> structures) {
        Evenement evt = new Evenement(genevent, nom, dateSelectionnee, heureDebut, nombrePersonne, structures);
        return evt;
    }
/**
 * constructeur d'un événement
 * @param genevent
 * @param nom
 * @param dateSelectionnee
 * @param heureDebut
 * @param nombrePersonne
 * @param structures
 */
    public Evenement(GenEvent genevent, String nom, LocalDate dateSelectionnee, LocalTime heureDebut, int nombrePersonne, ArrayList<Structure> structures) {
        this.genevent = genevent;
        this.nom = nom;
        setDateSelectionnee(dateSelectionnee);
        setHeureDebut(heureDebut);
        this.nombrePersonne = nombrePersonne;
        this.structures = structures;

    }

    public String getNom() {
        return this.nom;
    }

    public LocalDate getDateSelectionnee() {
        return this.dateSelectionnee;
    }

    public void setDateSelectionnee(LocalDate dateSelectionnee) {
        this.dateSelectionnee = dateSelectionnee;
    }

    public LocalTime getHeureDebut() {
        return this.heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }
    public int getNombrePersonne() {
        return nombrePersonne;
    }
    public ArrayList<Structure> getStructures() {
        return structures;
    }

    /**
     * permets d'ajouter des structures à l'événement
     * @param structure
     */
    public void ajouteStructures(Structure structure) {
        this.structures.add (structure);
    }
}
