package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GenEventTest {

    @Test
    void ajouteStructure() {
        GenEvent genevent = new GenEvent();
        genevent.ajouteStructure("nom", "type", "adressePostale", "description", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(1, genevent.getStructures().size(), "La structure n'a pas été ajoutée");
    }

    @Test
    void nouvelEvenement() {
        GenEvent genevent = new GenEvent();
        genevent.nouvelEvenement("nom", null, null, 0, null);
        assertEquals(1, genevent.getEvenements().size(), "L'évènement n'a pas été ajouté");
    }

}