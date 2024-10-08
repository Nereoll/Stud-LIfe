package fr.uga.iut2.genevent.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * La classe VueAideOrganisation gère la fermeture de la fenêtre d'aide à l'organisation.
 */
public class VueAideOrganisation {

    @FXML Button helpOrganizationCancelButton;

    /**
     * Action exécutée lors du clic sur le bouton d'annulation.
     * Cache la fenêtre actuelle.
     */
    @FXML
    private void cancelHelpOrganizationAction() {
        this.helpOrganizationCancelButton.getScene().getWindow().hide();
    }

}
