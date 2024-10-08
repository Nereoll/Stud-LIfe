package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EvenementTest {

    @Test
    void getNom() {
        Evenement evenement = new Evenement(null,"nom", null, null, 0, null);
        assertEquals("nom", evenement.getNom(), "Le nom de l'évènement est incorrect");
    }

    @Test
    void getDateSelectionnee() {
        Evenement evenement = new Evenement(null, null, null, null, 0, null);
        assertNull(evenement.getDateSelectionnee(), "La date de l'évènement est incorrecte");
    }

    @Test
    void setDateSelectionnee() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement evenement = new Evenement(null,null, null, null, 0, null);
        evenement.setDateSelectionnee(date);
        assertEquals(evenement.getDateSelectionnee(), date, "La date de l'évènement n'a pas été correctement assignée");
    }

    @Test
    void getHeureDebut() {
        LocalTime heure = LocalTime.of(8, 0);
        Evenement evenement = new Evenement(null,null, null, heure, 0, null);
        assertEquals(evenement.getHeureDebut(), heure, "L'heure de début de l'évènement est incorrecte");
    }

    @Test
    void setHeureDebut() {
        LocalTime heure = LocalTime.of(8, 0);
        Evenement evenement = new Evenement(null,null, null, null, 0, null);
        evenement.setHeureDebut(heure);
        assertEquals(evenement.getHeureDebut(), heure, "L'heure de début de l'évènement n'a pas été correctement assignée");
    }

    @Test
    void getNombrePersonne() {
        Evenement evenement = new Evenement(null,null, null, null, 10, null);
        assertEquals(evenement.getNombrePersonne(), 10, "Le nombre de personnes de l'évènement est incorrect");
    }
}