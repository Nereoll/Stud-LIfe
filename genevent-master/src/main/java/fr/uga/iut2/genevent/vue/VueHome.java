package fr.uga.iut2.genevent.vue;

import com.gluonhq.maps.MapPoint;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class VueHome {

    @FXML
    private Button btnSunValley, btnLaFabryk, btnKFeedesJeux, btnAuDetour;

    @FXML
    private ImageView nightclub, laserGame, bowling, bar;

    /**
     * Charge la vue de la carte lorsque le bouton Sun Valley est cliqué.
     *
     * @throws IOException En cas d'erreur lors du chargement de la vue maps.fxml.
     */
    @FXML
    public void OnRecomendationclickSunValley() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("maps.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnSunValley.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Charge la vue de la carte lorsque le bouton La Fabryk est cliqué.
     *
     * @throws IOException En cas d'erreur lors du chargement de la vue maps.fxml.
     */
    @FXML
    public void OnRecomendationclickLaFabryk() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("maps.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnLaFabryk.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Charge la vue de la carte lorsque le bouton KFeedesJeux est cliqué.
     *
     * @throws IOException En cas d'erreur lors du chargement de la vue maps.fxml.
     */
    @FXML
    public void OnRecomendationclickKFeedesJeux() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("maps.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnKFeedesJeux.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Charge la vue de la carte lorsque le bouton AuDetour est cliqué.
     *
     * @throws IOException En cas d'erreur lors du chargement de la vue maps.fxml.
     */
    @FXML
    public void OnRecomendationclickAuDetour() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("maps.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnAuDetour.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Charge la vue du NightClub lorsque l'ImageView nightclub est cliquée.
     *
     * @param event L'événement de clic de souris associé à l'ImageView nightclub.
     * @throws IOException En cas d'erreur lors du chargement de la vue NightClub-view.fxml.
     */
    @FXML
    public void OnNightClubClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NightClub-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) nightclub.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Charge la vue du Bar lorsque l'ImageView bar est cliquée.
     *
     * @param event L'événement de clic de souris associé à l'ImageView bar.
     * @throws IOException En cas d'erreur lors du chargement de la vue Bar-view.fxml.
     */
    @FXML
    public void OnBar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bar-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) bar.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Charge la vue du LaserGame lorsque l'ImageView laserGame est cliquée.
     *
     * @param event L'événement de clic de souris associé à l'ImageView laserGame.
     * @throws IOException En cas d'erreur lors du chargement de la vue LaserGame-view.fxml.
     */
    @FXML
    public void OnLaserGame(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LaserGame-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) laserGame.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Charge la vue du Bowling lorsque l'ImageView bowling est cliquée.
     *
     * @param event L'événement de clic de souris associé à l'ImageView bowling.
     * @throws IOException En cas d'erreur lors du chargement de la vue Bowling-view.fxml.
     */
    @FXML
    public void OnBowling(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bowling-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) bowling.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
