package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CalendrierTest {

    @Test
    void getDate() {
        ZonedDateTime date = ZonedDateTime.now();
        Calendrier calendrier = new Calendrier(date, "nom", 0, null, null);
        assertEquals(calendrier.getDate(), date, "La date de l'évènement est incorrecte");
    }

    @Test
    void setDate() {
        ZonedDateTime date = ZonedDateTime.now();
        Calendrier calendrier = new Calendrier(null, "nom", 0, null, null);
        calendrier.setDate(date);
        assertEquals(calendrier.getDate(), date, "La date de l'évènement n'a pas été correctement assignée");
    }

    @Test
    void getClientName() {
        Calendrier calendrier = new Calendrier(null, "nom", 0, null, null);
        assertEquals(calendrier.getClientName(), "nom", "Le nom de l'évènement est incorrect");
    }

    @Test
    void setClientName() {
        Calendrier calendrier = new Calendrier(null, null, 0, null, null);
        calendrier.setClientName("nom");
        assertEquals(calendrier.getClientName(), "nom", "Le nom de l'évènement n'a pas été correctement assigné");
    }

    @Test
    void getnombrePersonne() {
        Calendrier calendrier = new Calendrier(null, null, 10, null, null);
        assertEquals(calendrier.getnombrePersonne(), 10, "Le nombre de personnes de l'évènement est incorrect");
    }

    @Test
    void setnombrePersonne() {
        Calendrier calendrier = new Calendrier(null, null, 0, null, null);
        calendrier.setnombrePersonne(10);
        assertEquals(calendrier.getnombrePersonne(), 10, "Le nombre de personnes de l'évènement n'a pas été correctement assigné");
    }

    @Test
    void getHeureDebut() {
        LocalTime heure = LocalTime.of(8, 0);
        Calendrier calendrier = new Calendrier(null, null, 0, heure, null);
        assertEquals(calendrier.getHeureDebut(), heure, "L'heure de début de l'évènement est incorrecte");
    }

    @Test
    void setHeureDebut() {
        LocalTime heure = LocalTime.of(8, 0);
        Calendrier calendrier = new Calendrier(null, null, 0, null, null);
        calendrier.setHeureDebut(heure);
        assertEquals(calendrier.getHeureDebut(), heure, "L'heure de début de l'évènement n'a pas été correctement assignée");
    }
}