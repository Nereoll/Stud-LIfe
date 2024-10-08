package fr.uga.iut2.genevent.vue;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.uga.iut2.genevent.util.Utilitaire;



import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.GenEvent;
import fr.uga.iut2.genevent.modele.Structure;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VueMap extends JavaFXGUI {

    private final MapPoint grenobleMapPoint = new MapPoint(45.188311, 5.724575);
    private MapPoint customMapPoint;

    private Structure structure;

    private ArrayList<Structure> structures = new ArrayList<>();

    private static Logger logger = Logger.getLogger(VueMap.class.getName());

    private boolean flag=true;

    int capa_max=0;

    private Controleur controleur;

    @FXML
    private Pane mapViewContainer;

    @FXML
    private VBox itemsContainer;

    @FXML
    private Button mapBtnReserver;

    public VueMap() {
        super();
    }

    @FXML
    public void initialize() {
        this.controleur = Controleur.getInstance();
        if (flag) {
        MapPoint mapPoint = grenobleMapPoint;
        mapBtnReserver.onMouseClickedProperty().set(event -> {
            try {
                CreerEvenements(structures);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //lecture du csv contenant toutes les données sur les structures
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/fr/uga/iut2/genevent/csv/liste_structure.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // On ajoute les items à la liste
        for (List<String> record : records.subList(1, records.size())) {
            try {
                structure= new Structure(record.get(0), record.get(1),record.get(2), record.get(3), record.get(4), record.get(5), record.get(6), record.get(7), record.get(8), record.get(9), record.get(10), record.get(11), record.get(12), record.get(13), record.get(14), record.get(15));
                itemsContainer.getChildren().add(creerItem(structure, true));
            } catch (Exception e) {
                e.printStackTrace();
            }

            MapView mapview = Utilitaire.createMapView(mapPoint);
            mapViewContainer.getChildren().add(mapview);
            HBox.setHgrow(mapview, Priority.ALWAYS);
        }
        // créé la liste des structures, sous forme d'item, a coté de la carte
        for (Structure structure : this.controleur.getStructures().values()) {
            try {
                itemsContainer.getChildren().add(creerItem(structure, true));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }
    }
        /**
         * Met à jour la carte avec un nouveau point
         * à n'utiliser que si une carte existe déjà sinon levé d'une return_value
         * @param mapPoint point central de la carte
         */
    private void updateMapView(MapPoint mapPoint){
        try {
            mapViewContainer.getChildren().clear();
            MapView mapview = Utilitaire.createMapView(mapPoint);
            mapViewContainer.getChildren().add(mapview);
            mapview.setZoom(20);
            logger.log(Level.INFO, "carte mise à jour");
            HBox.setHgrow(mapview, Priority.ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();}
    }
    @FXML
    Label nomBar, starBar, infoBarType, infoBarOH, infoBarCH, infoBarCapa, infoBarExte, infoBarRooftop, infoBarEtages;
    @FXML
    ImageView imageBar;
    @FXML
    TextArea coordonnéesBar, infoBarDesc;
        /**
         * créé une fenetre popup contenant les informations sur une structure
         * @param structure
         */
    private void infosBar(Structure structure) {
        try {
            //chargement de la vue et de son controleur
            FXMLLoader infosBarViewLoader = new FXMLLoader(getClass().getResource("infoBar.fxml"));
            this.flag=false;//empeche de recharger la page principal a chaque ouverturee de la page informative
            infosBarViewLoader.setController(this);
            Scene infosBarScene = new Scene(infosBarViewLoader.load());
            //création de la fenêtre contenant la vue (fenetre à part)
            Stage newUserWindow = new Stage();
            newUserWindow.setTitle("Informations sur le bar");
            newUserWindow.initModality(Modality.APPLICATION_MODAL);
            newUserWindow.setScene(infosBarScene);
            //remplissage des champs de la vue avec les données en paramètre et rendu en fonction des données
            nomBar.setText(structure.getNom());
            Image image = new Image(new File(structure.getImagePath()).toURI().toString());
            imageBar.setImage(image);
            starBar.setText(structure.getEtoiles());//on affiche les étoiles en fonction du nombre d'etoiles
            infoBarDesc.setText(structure.getDescription());
            infoBarDesc.setEditable(false);
            infoBarType.setText(structure.getType());
            infoBarOH.setText(structure.getHeureOuverture()+" heure");
            infoBarCH.setText(structure.getHeureFermeture()+" heure");
            infoBarCapa.setText(structure.getCapacite()+"👤");
            infoBarExte.setText(Utilitaire.stringBoolToHumanReadable(structure.getExterieur(), "✅", "❌"));
            infoBarRooftop.setText(Utilitaire.stringBoolToHumanReadable(structure.getRooftop(),"✅", "❌"));
            infoBarEtages.setText(structure.getEtage() + "étages");

            coordonnéesBar.setText("Telephone : "+structure.getTelephone()+
                                    "\nEmail : "+structure.getEmail());

            //apres avoir écrit on rends les champs non éditable
            coordonnéesBar.setEditable(false);

            newUserWindow.showAndWait(); //generation de la fenetre


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        /**
         * Crée un item JavaFX de la liste des structures
         * @param structure structure à afficher sous forme d'un item JavaFX
         * @param checkButtonadd boolean permettant de savoir si on doit ajouter un bouton d'ajout à la liste des structures, spécifique à la page de réservation
         * @return HBox itemIcon montrant les informations importantes d'une structure
         */
    private HBox creerItem(Structure structure, boolean checkButtonadd) {

        HBox itemIcon = new HBox();
        itemIcon.nodeOrientationProperty().set(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        itemIcon.setAlignment(Pos.CENTER);
        itemIcon.setPrefHeight(180.0);
        itemIcon.setPrefWidth(500.0);
        itemIcon.setSpacing(20.0);
        itemIcon.getStyleClass().add("mapItems");
        itemIcon.getStylesheets().add(getClass().getResource("da.css").toExternalForm());
        /**
         * mise à jour de la carte lors du clic sur l'item,
         * recharge une carte avec comme centre les coordonnées liés à l'item
         */
        itemIcon.onMouseClickedProperty().set(event -> {
            try {
                customMapPoint = new MapPoint(structure.getLat(), structure.getLongi());
                updateMapView(customMapPoint);

            } catch (Exception e) {
        }
        });

        // Creation de l'ImageView
        ImageView imageView = new ImageView();
        imageView.setFitHeight(150.0);
        imageView.setFitWidth(200.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        Image image = new Image(new File(structure.getImagePath()).toURI().toString());
        imageView.setImage(image);

        HBox.setMargin(imageView, new Insets(0));

        // Création du TextArea
        TextArea textArea = new TextArea();
        textArea.setPrefHeight(157.0);
        textArea.setPrefWidth(215.0);
        textArea.setWrapText(true);
        textArea.setText("Nom : "+structure.getNom()+"\nType : "+structure.getType()+"\ndescription : "+structure.getDescription());
        textArea.setEditable(false);

        // Création des boutons
        Button infoButton = new Button("i");
        infoButton.getStyleClass().add("info_button");
        infoButton.getStylesheets().add(getClass().getResource("da.css").toExternalForm());
        infoButton.onMouseClickedProperty().set(event -> {
            try {
                infosBar(structure);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        // Création du conteneur VBox
        VBox vbox = new VBox();
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setPrefHeight(176.0);
        vbox.setSpacing(40.0);
        vbox.getChildren().addAll(infoButton);

        if (checkButtonadd){
            Button checkButton = new Button("+");
            checkButton.setVisible(true);
            checkButton.getStyleClass().add("check_button");
            checkButton.getStylesheets().add(getClass().getResource("da.css").toExternalForm());

            Tooltip tooltip = new Tooltip("Cliquer sur le bouton pour ajouter la structure à l'événement");
            tooltip.setStyle("-fx-font: normal bold 10 Langdon; ");
            Tooltip.install(checkButton, tooltip);

            checkButton.onMouseClickedProperty().set(event -> {
                try {
                    if (checkButton.getText().equals("x")) {
                        removeStructure(structure);
                        checkButton.setText("+");
                    }
                    else{
                        addStructures(structure);
                        checkButton.setText("x");
                    }
                    logger.log(Level.INFO, "structure : "+structure.getNom()+" ajoutée");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            vbox.getChildren().add(checkButton);
        }

        // Ajouts des enfants au HBox
        itemIcon.getChildren().addAll(imageView, textArea, vbox);
        logger.log(Level.INFO, "Item créé");
        return itemIcon;

    }
    private void addStructures(Structure structure){
        this.structures.add(structure);
        mapBtnReserver.setText("Prévoir "+structures.size()+" structures");
        mapBtnReserver.setDisable(false);
    }
    private void removeStructure(Structure structure){
        this.structures.remove(structure);
        mapBtnReserver.setText("Prévoir "+structures.size()+" structures");
        if (structures.size()==0){
            mapBtnReserver.setDisable(true);
        }
    }

    @FXML
    Label nb_personnes;
    @FXML
    VBox vbListeStructures;
    @FXML
    Spinner sp_Heure, sp_personne;
    @FXML
    Button btnAideOrganisation;
    @FXML
    Button btn_valider;

    @FXML
    private void goToHelpOrganization() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("help-organization-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Aide à l'organisation");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    Label erreur_personne, erreur_heure, erreur_date;
    @FXML
    DatePicker df_Date;
    @FXML
    TextField tf_evenement;


    private boolean validiterEvenement(){
        sp_Heure.getStyleClass().remove("error");
        df_Date.getStyleClass().remove("error");
        sp_personne.getStyleClass().remove("error");
        tf_evenement.getStyleClass().remove("error");
        erreur_personne.setVisible(false);
        erreur_heure.setVisible(false);
        erreur_date.setVisible(false);
        boolean return_value=true;

        if (tf_evenement.getText().isEmpty()){
            erreur_date.setVisible(true);
            tf_evenement.getStyleClass().add("error");
            return_value=false;
        }


        if (df_Date.getValue()==null){
            erreur_date.setVisible(true);
            df_Date.getStyleClass().add("error");
            return_value=false;
        }

        if (df_Date.getValue()==null){
            erreur_date.setVisible(true);
            df_Date.getStyleClass().add("error");
            return_value=false;
        }

        if (sp_Heure.getValue()==null){
            erreur_heure.setVisible(true);
            sp_Heure.getStyleClass().add("error");
            return_value=false;
        }

        if (sp_personne.getValue()==null || Integer.parseInt(sp_personne.getValue().toString())>this.capa_max){
            System.out.println(nb_personnes.getText().substring(26,29));
            erreur_personne.setVisible(true);
            sp_personne.getStyleClass().add("error");
            return_value=false;
        }
        if (return_value){
            logger.log(Level.INFO, "Evenement validé");
            return return_value;
    }
    logger.log(Level.INFO, "Evenement non validé");
    return return_value;

}

    private void CreerEvenements(ArrayList<Structure> structures){
        try {
            //chargement de la vue et de son controleur
            FXMLLoader bookformViewLoader = new FXMLLoader(getClass().getResource("book-form-view.fxml"));
            this.flag=false;//empeche de recharger la page principal a chaque ouverturee de la page informative
            bookformViewLoader.setController(this);
            Scene bookformScene = new Scene(bookformViewLoader.load());
            //création de la fenêtre contenant la vue (fenetre à part)
            Stage newUserWindow = new Stage();
            newUserWindow.setTitle("Détails de l'événement");
            newUserWindow.initModality(Modality.APPLICATION_MODAL);
            newUserWindow.setScene(bookformScene);

            SpinnerValueFactory<Integer> valueFactorySp_heure = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 23, 00);
            SpinnerValueFactory<Integer> valueFactorySp_personne = new SpinnerValueFactory.IntegerSpinnerValueFactory(0001, 1000, 1);
            sp_Heure.setValueFactory(valueFactorySp_heure);
            sp_personne.setValueFactory(valueFactorySp_personne);


            btn_valider.onMouseClickedProperty().set(event -> {
                try {
                    if (validiterEvenement()){
                        /**
                         * créé un événement et l'ajoutes à la liste d'événement persistente de Genevent
                         */
                        controleur.creerEvenement(new IHM.InfosNouvelEvenement(
                            tf_evenement.getText(),
                            df_Date.getValue(),
                            Utilitaire.stringToHour(sp_Heure.getValue().toString()),
                            (int) sp_personne.getValue(),
                            structures));
                        newUserWindow.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            btnAideOrganisation.onMouseClickedProperty().set(event -> {
                goToHelpOrganization();
            });
            

            for (Structure structure : structures) {
                this.capa_max+=Integer.parseInt(structure.getCapacite());
                vbListeStructures.getChildren().add(creerItem(structure, false));
            }
            //remplissage des champs de la vue avec les données en paramètre et rendu en fonction des données
            nb_personnes.setText("Nombres de personnes (max : "+capa_max+")");
            newUserWindow.showAndWait(); //generation de la fenetre
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




