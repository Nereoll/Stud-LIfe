package fr.uga.iut2.genevent.util;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;



public class Utilitaire {
    private static Logger logger = Logger.getLogger(Utilitaire.class.getName());

    /**
     * Convertit une chaîne représentant une heure au format "HH:mm" en objet LocalTime.
     *
     * @param timeString La chaîne à convertir en heure.
     * @return L'objet LocalTime représentant l'heure convertie, ou {@code null} si la conversion échoue.
     * @throws DateTimeParseException Si la chaîne n'est pas au format attendu "HH:mm".
     */
    public static LocalTime stringToHour(String timeString) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Définir le format de l'heure
        
        try {
            if (timeString.length() == 1) {
                timeString = "0" + timeString;
            }
            return LocalTime.parse(timeString+":00", formatter);
        } catch (DateTimeParseException e) {
            // Gérer l'erreur si la chaîne ne peut pas être parsée
            logger.log(Level.SEVERE, "Erreur lors de la conversion de la chaîne en heure", e);
            return null;
        }
    }

    /**
     * Fonction permettant de remplacer une chaine de caractère booléenne par une chaine de caractère correspondant à celles passés en entrées
     * @param toReplace chaine de caractère à remplacer
     * @param vrai chaine de carractère si la valeur est true
     * @param faux chaine de carractère si la valeur est false
     * @return chaine de caractère correspondant à la valeur booléenne, ou null si la chaine n'est pas un booléen
     */
    public static String stringBoolToHumanReadable(String toReplace, String vrai, String faux){
        if (toReplace.compareTo("true")==0 || toReplace.compareTo("false")==0){
            if (toReplace.compareTo("true")==0){
                return vrai;
            }
            return faux;
        }
        return null;
    }
        /**
        * Crée une carte centrée sur un point donné
        * @param mapPoint
        * @return
        */
    public static MapView createMapView(MapPoint mapPoint) {
        MapView mapView = new MapView();
        mapView.setPrefSize(1200, 800);
        mapView.setCenter(mapPoint);
        mapView.setZoom(14);
        return mapView;
    }


}