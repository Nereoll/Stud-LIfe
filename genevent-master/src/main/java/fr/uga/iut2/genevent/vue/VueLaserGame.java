package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Structure;
import fr.uga.iut2.genevent.util.Utilitaire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class VueLaserGame {
    @FXML
    private VBox laserGameVbox; // VBox où seront ajoutés les éléments LaserGame

    @FXML
    private ScrollPane scrollPane; // ScrollPane pour gérer le défilement si nécessaire

    @FXML
    private ImageView arrowBack;

    private boolean flag = true;

    /**
     * Initialise l'interface utilisateur en lisant un fichier CSV contenant la liste des structures en conservant
     * seulement les LaserGames et en ajoutant les informations correspondantes dans une VBox.
     * <p>
     * Cette méthode est déclenchée automatiquement à l'initialisation du contrôleur si le drapeau {@code flag} est
     * défini à {@code true} pour éviter la duplication d'information lors du déclenchement de la méthode {@link #infosLasergame(Structure)}
     * Les LaserGames sont ajoutés à l'interface en utilisant des HBox contenant une ImageView,
     * un TextArea pour les informations et un bouton d'information.
     *
     * @see BufferedReader
     * @see FileReader
     * @see HBox
     * @see ImageView
     * @see Image
     * @see TextArea
     * @see Button
     * @see ScrollPane
     */
    @FXML
    public void initialize() {
        if (flag) {
            // Lecture du fichier CSV et ajout des bars à l'interface
            String csvFile = "src/main/resources/fr/uga/iut2/genevent/csv/liste_structure.csv";
            String line;
            String cvsSplitBy = ";"; // Séparateur dans votre fichier CSV

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                double yOffset = 10.0;

                // Ignorer la ligne d'en-tête
                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] bar = line.split(cvsSplitBy);

                    if (bar.length < 16 || !bar[1].equalsIgnoreCase("LaserGame")) {
                        continue; // Ignorer les lignes qui ne sont pas des bars
                    }


                    Structure structure = new Structure(bar[0], bar[1], bar[2], bar[3], bar[4], bar[5], bar[6], bar[7], bar[8], bar[9], bar[10], bar[11], bar[12], bar[13], bar[14], bar[15]);
                    // Création d'un HBox pour chaque Bar
                    HBox hBox = new HBox();
                    hBox.setPadding(new Insets(20, 10, 20, 10));
                    hBox.getStylesheets().add(getClass().getResource("da.css").toExternalForm());

                    // Création d'une ImageView pour l'image du Bar
                    ImageView imageView = new ImageView();
                    Image image = new Image(new File(structure.getImagePath()).toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitHeight(150.0);
                    imageView.setFitWidth(200.0);
                    hBox.getChildren().add(imageView); // Ajout de l'imageView à hBox

                    // Création d'un Label avec les informations (nom, adresse, description)
                    TextArea info = new TextArea("Nom : " + structure.getNom() + "\nDescription : " + structure.getDescription() + "\nCapacité : " + structure.getCapacite() + " personnes");
                    info.setWrapText(true);
                    info.setEditable(false);
                    info.setPrefHeight(157.0);
                    hBox.getChildren().add(info);

                    Button infoButton = new Button("i");
                    infoButton.getStyleClass().add("info_button");
                    infoButton.getStylesheets().add(getClass().getResource("da.css").toExternalForm());
                    hBox.getChildren().add(infoButton);
                    infoButton.onMouseClickedProperty().set(event -> {
                        try {
                            infosLasergame(structure);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    // Ajout du HBox au VBox
                    laserGameVbox.getChildren().add(hBox);

                    // Mettre à jour yOffset pour le prochain élément
                    yOffset += hBox.getPrefHeight() + 10;
                }

                // Ajustement de la hauteur du VBox et du ScrollPane
                laserGameVbox.setPrefHeight(yOffset);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Afficher la barre de défilement verticale au besoin
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private Label nomBar, starBar, infoBarType, infoBarOH, infoBarCH, infoBarCapa, infoBarExte, infoBarRooftop, infoBarEtages;

    @FXML
    private ImageView imageBar;

    @FXML
    private TextArea coordonnéesBar, infoBarDesc;

    /**
     * Initialise la vue en chargeant les données des bars depuis un fichier CSV et les affiche dans l'interface graphique.
     * Cette méthode est appelée automatiquement après que tous les éléments FXML ont été injectés.
     * Si {@code flag} est vrai, cette méthode lit un fichier CSV de structures (en filtrant les bars) et affiche chaque bar
     * dans un HBox contenant une image, des informations textuelles et un bouton d'information.
     * Les informations sont affichées dans un VBox nommé {@code barVBox}.
     * Si une erreur d'entrée/sortie se produit lors de la lecture du fichier CSV, elle est imprimée dans la console.
     */
    private void infosLasergame(Structure structure) {
        try {
            // Charger la vue et son contrôleur
            FXMLLoader infosBarViewLoader = new FXMLLoader(getClass().getResource("infoBar.fxml"));
            this.flag = false; // Empêche de recharger la page principale à chaque ouverture de la page informative
            infosBarViewLoader.setController(this);
            Scene infosBarScene = new Scene(infosBarViewLoader.load());

            // Créer une nouvelle fenêtre contenant la vue
            Stage newUserWindow = new Stage();
            newUserWindow.setTitle("Détail du Lasergame");
            newUserWindow.initModality(Modality.APPLICATION_MODAL);
            newUserWindow.setScene(infosBarScene);

            // Remplir les champs avec les données passées
            nomBar.setText(structure.getNom());

            starBar.setText(structure.getEtoiles());
            Image image = new Image(new File(structure.getImagePath()).toURI().toString());
            imageBar.setImage(image);
            infoBarDesc.setText(structure.getDescription());
            infoBarDesc.setEditable(false);
            infoBarType.setText(structure.getType());
            infoBarOH.setText(structure.getHeureOuverture() + " heure");
            infoBarCH.setText(structure.getHeureFermeture() + " heure");
            infoBarCapa.setText(structure.getCapacite() + "👤");
            infoBarExte.setText(Utilitaire.stringBoolToHumanReadable(structure.getExterieur(), "✅", "❌"));
            infoBarRooftop.setText(Utilitaire.stringBoolToHumanReadable(structure.getRooftop(), "✅", "❌"));
            infoBarEtages.setText(structure.getEtage() + " étages");
            coordonnéesBar.setText("Téléphone: " + structure.getTelephone() + "\nEmail: " + structure.getEmail());
            coordonnéesBar.setEditable(false);


            newUserWindow.showAndWait(); // Afficher la fenêtre

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère le clic sur une flèche de retour, permettant de charger la vue "index.fxml" dans la scène courante.
     *
     * @param event L'événement de clic de souris associé à la flèche de retour
     * @throws IOException Si une erreur survient lors du chargement de "index.fxml"
     */
    @FXML
    public void OnArrowClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("index.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) arrowBack.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
