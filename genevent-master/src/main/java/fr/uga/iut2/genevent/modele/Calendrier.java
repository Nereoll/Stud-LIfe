package fr.uga.iut2.genevent.modele;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Classe représentant un événement dans le calendrier.
 * Un événement est caractérisé par :
 * - une date
 * - un nom de client
 * - un nombre de personnes
 * - une heure de début
 * - une liste de structures
*/

public class Calendrier {
    private ZonedDateTime date;
    private String clientName;
    private Integer nombrePersonne;
    private LocalTime heureDebut;
    private ArrayList<Structure> structures;


    public Calendrier(ZonedDateTime date, String clientName, Integer nombrePersonne, LocalTime heureDebut, ArrayList<Structure> structures) {
        setDate(date);
        setClientName(clientName);
        setnombrePersonne(nombrePersonne);
        setHeureDebut(heureDebut);
        this.structures = structures;
    }

    //getteurs et setteurs de la classe Calendrier, permettent d'assurer l'encapsulation des données

/**
 * @return La date de l'événement.
 */
    public ZonedDateTime getDate() {
        return date;
    }
/**
 * 
 * @param date La date de l'événement.
 */
    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
/**
 * 
 * @return Le nom du créateur de l'evenement
 */
    public String getClientName() {
        return clientName;
    }
/**
 * 
 * @param clientName Le nom du créateur de l'evenement
 */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
/**
 * 
 * @return Le nombre de personne participant à l'evenement
 */
    public Integer getnombrePersonne() {
        return nombrePersonne;
    }
/**
 * 
 * @param nombrePersonne Le nombre de personne participant à l'evenement
 */
    public void setnombrePersonne(Integer nombrePersonne) {
        this.nombrePersonne = nombrePersonne;
    }
    /**
     * 
     * @return L'heure de début de l'evenement
     */
    public LocalTime getHeureDebut() {
        return heureDebut;
    }
    /**
     * 
     * @param heureDebut L'heure de début de l'evenement
     */
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }
/**
 * methode toString permettant d'afficher les informations de l'evenement
 * @return les informations de l'evenement sous forme d'une chaine de caractère
 */
    @Override
    public String toString() {
        String string = "Evenement\n" +
                "Nom de l'evenement=" + clientName + "\n" +
                "nombre de personne=" + nombrePersonne +"\n" +
                "heure de debut=" + heureDebut+"\n"+
                "structure : ";
        for (Structure structure : structures) {
            string += "\n\t"+structure.getNom();
        }
        return string;
    }

}
