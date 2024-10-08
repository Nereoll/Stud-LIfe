package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La classe VueNavBar gère la barre de navigation de l'interface utilisateur de GenEvent.
 * Elle permet de naviguer entre différentes vues de l'application.
 */
public class VueNavBar extends JavaFXGUI {
    @FXML
    private VBox houseIcon, userIcon, planIcon, calendarIcon;

    /**
     * Constructeur par défaut de VueNavBar.
     * Initialise le contrôleur de l'application.
     */
    public VueNavBar() {
        super();
    }

    /**
     * Méthode d'initialisation appelée après le chargement du fichier FXML.
     * Initialise le contrôleur de l'application.
     */
    @FXML
    public void initialize() {
        this.controleur = Controleur.getInstance();
    }

    /**
     * Action déclenchée lors du clic sur la houseIcon.
     * Charge la vue principale (index.fxml).
     *
     * @param event Evénement de clic de la souris.
     * @throws IOException En cas d'erreur de chargement de la vue.
     */
    @FXML
    public void OnHouseClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("index.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) houseIcon.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Action déclenchée lors du clic sur l'icône user.
     * Charge la vue de création de nouvelle structure (new-structure-view.fxml).
     * Ouvre une nouvelle fenêtre pour cette vue.
     *
     * @param event Evénement de clic de la souris.
     * @throws IOException En cas d'erreur de chargement de la vue.
     */
    @FXML
    public void OnUserClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("new-structure-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Nouvelle Structure");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Action déclenchée lors du clic sur l'icône map.
     * Charge la vue de la map (maps.fxml).
     *
     * @param event Evénement de clic de la souris.
     * @throws IOException En cas d'erreur de chargement de la vue.
     */
    @FXML
    public void OnPlanClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("maps.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) planIcon.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Action déclenchée lors du clic sur l'icône du calendrier.
     * Charge la vue de calendrier (calendar-view.fxml).
     *
     * @param event Evénement de clic de la souris.
     * @throws IOException En cas d'erreur de chargement de la vue.
     */
    @FXML
    public void OnCalendarClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calendar-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) calendarIcon.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
        /**
     * Action déclenchée lors du clic sur l'icône map.
     * Charge la vue de la map (pro.fxml).
     *
     * @param event Evénement de clic de la souris.
     * @throws IOException En cas d'erreur de chargement de la vue.
     */
    @FXML
    public void OnTruckClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pro.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Notification pro");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        stage.showAndWait();
    }

}
