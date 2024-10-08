package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StructureTest {

    @Test
    void getNom() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getNom(), "nom", "Le nom de la structure est incorrect");
    }

    @Test
    void getType() {
        Structure structure = new Structure("nom", "Bar", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getType(), "Bar \uD83C\uDF7A", "Le type de la structure est incorrect");
    }

    @Test
    void getAdresse() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getAdresse(), "adressePostale", "L'adresse de la structure est incorrecte");
    }

    @Test
    void getDescription() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getDescription(), "description", "La description de la structure est incorrecte");
    }

    @Test
    void getImagePath() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getImagePath(), "", "Le chemin de l'image de la structure est incorrect");
    }

    @Test
    void getHeureOuverture() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getHeureOuverture(), "12", "L'heure d'ouverture de la structure est incorrecte");
    }

    @Test
    void getHeureFermeture() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getHeureFermeture(), "18", "L'heure de fermeture de la structure est incorrecte");
    }

    @Test
    void getCapacite() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getCapacite(), "120", "La capacité de la structure est incorrecte");
    }

    @Test
    void getExterieur() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getExterieur(), "false", "La présence d'un espace extérieur de la structure est incorrecte");
    }

    @Test
    void getRooftop() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getRooftop(), "true", "La présence d'un rooftop de la structure est incorrecte");
    }

    @Test
    void getEtage() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getEtage(), "2", "L'étage de la structure est incorrect");
    }

    @Test
    void getEmail() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getEmail(), "email@gmail.com", "L'email de la structure est incorrect");
    }

    @Test
    void getEtoiles() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getEtoiles(), "⭐⭐⭐", "Le nombre d'étoiles de la structure est incorrect");
    }

    @Test
    void getTelephone() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getTelephone(), "numTel", "Le numéro de téléphone de la structure est incorrect");
    }

    @Test
    void getLat() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getLat(), 0.0, "La latitude de la structure est incorrecte");
    }

    @Test
    void getLongi() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        assertEquals(structure.getLongi(), 0.0, "La longitude de la structure est incorrecte");
    }

    @Test
    void setNom() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setNom("nouveauNom");
        assertEquals(structure.getNom(), "nouveauNom", "Le nom de la structure n'a pas été correctement assigné");
    }

    @Test
    void setDescription() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setDescription("nouvelleDescription");
        assertEquals(structure.getDescription(), "nouvelleDescription", "La description de la structure n'a pas été correctement assignée");
    }

    @Test
    void setImagePath() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setImagePath("nouveauCheminImage");
        assertEquals(structure.getImagePath(), "nouveauCheminImage", "Le chemin de l'image de la structure n'a pas été correctement assigné");
    }

    @Test
    void setHeureOuverture() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setHeureOuverture("10");
        assertEquals(structure.getHeureOuverture(), "10", "L'heure d'ouverture de la structure n'a pas été correctement assignée");
    }

    @Test
    void setHeureFermeture() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setHeureFermeture("20");
        assertEquals(structure.getHeureFermeture(), "20", "L'heure de fermeture de la structure n'a pas été correctement assignée");
    }

    @Test
    void setCapacite() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setCapacite("150");
        assertEquals(structure.getCapacite(), "150", "La capacité de la structure n'a pas été correctement assignée");
    }

    @Test
    void setExterieur() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setExterieur("true");
        assertEquals(structure.getExterieur(), "true", "La présence d'un espace extérieur de la structure n'a pas été correctement assignée");
    }

    @Test
    void setRooftop() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setRooftop("false");
        assertEquals(structure.getRooftop(), "false", "La présence d'un rooftop de la structure n'a pas été correctement assignée");
    }

    @Test
    void setEtage() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setEtage("3");
        assertEquals(structure.getEtage(), "3", "L'étage de la structure n'a pas été correctement assigné");
    }

    @Test
    void setEmail() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setEmail("nouvelEmail@gmail.com");
        assertEquals(structure.getEmail(), "nouvelEmail@gmail.com", "L'email de la structure n'a pas été correctement assigné");
    }

    @Test
    void setEtoiles() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setEtoiles("2");
        assertEquals(structure.getEtoiles(), "⭐⭐", "Le nombre d'étoiles de la structure n'a pas été correctement assigné");
    }

    @Test
    void setTelephone() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setTelephone("nouveauNumTel");
        assertEquals(structure.getTelephone(), "nouveauNumTel", "Le numéro de téléphone de la structure n'a pas été correctement assigné");
    }

    @Test
    void setLat() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setLat("1.0");
        assertEquals(structure.getLat(), 1.0, "La latitude de la structure n'a pas été correctement assignée");
    }

    @Test
    void setLongi() {
        Structure structure = new Structure("nom", "type", "adressePostale", "description", "", "12", "18",
                "120", "false", "true", "2", "email@gmail.com", "3", "numTel", "0.0", "0.0");
        structure.setLongi("1.0");
        assertEquals(structure.getLongi(), 1.0, "La longitude de la structure n'a pas été correctement assignée");
    }
}