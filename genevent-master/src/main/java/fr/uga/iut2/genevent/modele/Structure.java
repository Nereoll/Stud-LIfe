package fr.uga.iut2.genevent.modele;

import org.apache.commons.validator.routines.EmailValidator;

import java.io.Serializable;

public class Structure implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private String nom;
    private String type;
    private String adresse;
    private String description;
    private String imagePath;
    private String heureOuverture;
    private String heureFermeture;
    private String capacite;
    private String exterieur;
    private String rooftop;
    private String etage;
    private String email;
    private String etoiles;
    private String telephone;
    private Double lat;
    private Double longi;

    public Structure(String nom, String type,String adresse, String description, String imagePath, String heureOuverture, String heureFermeture, String capacite, String exterieur, String rooftop, String etage, String email, String etoiles, String telephone, String lat, String longi) {
        assert EmailValidator.getInstance(false, false).isValid(email);
        setNom(nom);
        setType(type);
        setAdresse(adresse);
        setDescription(description);
        setImagePath(imagePath);
        setHeureOuverture(heureOuverture);
        setHeureFermeture(heureFermeture);
        setCapacite(capacite);
        setExterieur(exterieur);
        setRooftop(rooftop);
        setEtage(etage);
        setEmail(email);
        setEtoiles(etoiles);
        setTelephone(telephone);
        setLat(lat);
        setLongi(longi);
    }

    public String getNom() {
        return nom;
    }
    public String getType() {
        return type;
    }
    public String getAdresse(){
        return adresse;
    }
    public String getDescription() {
        return description;
    }
    public String getImagePath() {
        if (imagePath == null) {
            return "src/main/resources/fr/uga/iut2/genevent/vue/images/exampleBar.jpg";
        }
        return "src/main/resources/fr/uga/iut2/genevent/vue/images/"+imagePath+".jpg";
    }
    public String getHeureOuverture() {
        return heureOuverture;
    }
    public String getHeureFermeture() {
        return heureFermeture;
    }
    public String getCapacite() {
        return capacite;
    }
    public String getExterieur() {
        return exterieur;
    }
    public String getRooftop() {
        return rooftop;
    }
    public String getEtage() {
        return etage;
    }
    public String getEmail() {
        return email;
    }
    public String getEtoiles() {
        return etoiles;
    }
    public String getTelephone() {
        return telephone;
    }
    public Double getLat() {
        return lat;
    }
    public Double getLongi() {
        return longi;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    private void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setHeureOuverture(String heureOuverture) {
        this.heureOuverture = heureOuverture;
    }
    public void setHeureFermeture(String heureFermeture) {
        this.heureFermeture = heureFermeture;
    }
    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }
    public void setExterieur(String exterieur) {
        this.exterieur = exterieur;
    }
    public void setRooftop(String rooftop) {
        this.rooftop = rooftop;
    }
    public void setEtage(String etage) {
        this.etage = etage;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * convertit le nombre d'etoiles en une chaine d'etoiles "⭐"
     * @param etoiles
     */
    public void setEtoiles(String etoiles) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Integer.parseInt(etoiles); i++) {
            result.append("⭐");
        }
        this.etoiles = result.toString();
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * convertit le String en double
     * @param lat
     */
    public void setLat(String lat) {
        this.lat = Double.parseDouble(lat);
    }

    /**
     * convertit le String en double
     * @param longi
     */
    public void setLongi(String longi) {
        this.longi = Double.parseDouble(longi);
    }

    /**
     * Fonction permettant d'ajouter à un type de structure (sous forme de String) un emoji correspondant associé à ce type
     * @param type
     * @return
     */
    private void setType(String type){
        if (type.compareToIgnoreCase("Bar")==0){
            this.type= "Bar 🍺";
        }
        else if (type.compareToIgnoreCase("NightClub")==0){
            this.type="Nightclub 🎉";
        }
        else if (type.compareToIgnoreCase("Bowling")==0){
            this.type= "Bowling 🎳";
        }
        else if (type.compareToIgnoreCase("LaserGame")==0){
            this.type= "Laser Game 🔫";
        }
        else this.type= null;
    }
}
