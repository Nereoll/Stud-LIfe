package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Contrôleur pour la vue de création d'une nouvelle structure.
 * Cette vue permet à l'utilisateur de saisir les informations nécessaires pour créer une nouvelle structure comme un bar, un nightclub, un bowling ou un LaserGame.
 */
public class VueNouvelleStructure extends JavaFXGUI {

    @FXML
    private TextField newStructureNomTextField;
    @FXML
    private ComboBox newStructureTypeComboBox;
    @FXML
    private TextField newStructureAdressePostaleTextField;
    @FXML
    private TextField newStructureLatitudeTextField;
    @FXML
    private TextField newStructureLongitudeTextField;
    @FXML
    private TextField newStructureDescriptionTextField;
    @FXML
    private TextField newStructureHeureOuvertureTextField;
    @FXML
    private TextField newStructureHeureFermetureTextField;
    @FXML
    private TextField newStructureCapaciteTextField;
    @FXML
    private CheckBox newStructureExterieurCheckbox;
    @FXML
    private CheckBox newStructureRooftopCheckbox;
    @FXML
    private TextField newStructureNbreEtagesTextField;
    @FXML
    private TextField newStructureEmailTextField;
    @FXML
    private ComboBox newStructureEtoilesComboBox;
    @FXML
    private TextField newStructureTelephoneTextField;

    @FXML
    private Button newStructureOkButton;
    @FXML
    private Button newStructureCancelButton;

    /**
     * Constructeur par défaut de la classe VueNouvelleStructure.
     * Initialise la classe parente JavaFXGUI.
     */
    public VueNouvelleStructure() {
        super();
    }

    /**
     * Initialise les éléments de la vue.
     * Charge les choix possibles pour le type de structure et le nombre d'étoiles.
     */
    @FXML
    public void initialize() {
        this.controleur = Controleur.getInstance();

        this.newStructureTypeComboBox.getItems().addAll("Bar", "NightClub", "Bowling", "LaserGame");
        this.newStructureEtoilesComboBox.getItems().addAll("1", "2", "3", "4", "5");
    }

    /**
     * Action exécutée lors du clic sur le bouton de création d'une nouvelle structure.
     * Récupère les données saisies par l'utilisateur et les transmet au contrôleur pour la création de la structure.
     */
    @FXML
    private void createNewStructureAction() {
        IHM.InfosStructure data = new IHM.InfosStructure(
                this.newStructureNomTextField.getText().strip().toLowerCase(),
                this.newStructureTypeComboBox.getValue().toString(),
                this.newStructureAdressePostaleTextField.getText().strip().toLowerCase(),
                this.newStructureDescriptionTextField.getText().strip().toLowerCase(),
                this.newStructureHeureOuvertureTextField.getText().strip(),
                this.newStructureHeureFermetureTextField.getText().strip(),
                this.newStructureCapaciteTextField.getText().strip(),
                String.valueOf(this.newStructureExterieurCheckbox.isSelected()),
                String.valueOf(this.newStructureRooftopCheckbox.isSelected()),
                this.newStructureNbreEtagesTextField.getText().strip(),
                this.newStructureEmailTextField.getText().strip(),
                this.newStructureEtoilesComboBox.getValue().toString(),
                this.newStructureTelephoneTextField.getText().strip(),
                this.newStructureLatitudeTextField.getText().strip(),
                this.newStructureLongitudeTextField.getText().strip()
        );
        super.controleur.creerStructure(data);
        this.newStructureOkButton.getScene().getWindow().hide();
    }

    /**
     * Action exécutée lors du clic sur le bouton d'annulation.
     * Ferme la fenêtre actuelle.
     */
    @FXML
    private void cancelNewStructureAction() {
        this.newStructureCancelButton.getScene().getWindow().hide();
    }

    /**
     * Valide les champs de texte obligatoires pour activer ou désactiver le bouton de validation.
     */
    @FXML
    private void validateTextFields() {
        boolean isValid = true;

        isValid &= validateNonEmptyTextField(this.newStructureNomTextField);
        isValid &= validateNonEmptyTextField(this.newStructureAdressePostaleTextField);
        isValid &= validateNonEmptyTextField(this.newStructureDescriptionTextField);
        isValid &= validateNonEmptyTextField(this.newStructureHeureOuvertureTextField);
        isValid &= validateNonEmptyTextField(this.newStructureHeureFermetureTextField);
        isValid &= validateNonEmptyTextField(this.newStructureCapaciteTextField);
        isValid &= validateNonEmptyTextField(this.newStructureNbreEtagesTextField);
        isValid &= validateNonEmptyTextField(this.newStructureLatitudeTextField);
        isValid &= validateNonEmptyTextField(this.newStructureLongitudeTextField);

        this.newStructureOkButton.setDisable(!isValid);
    }

    /**
     * Marque visuellement un champ de texte selon sa validité.
     *
     * @param textField Le champ de texte à marquer.
     * @param isValid   Indique si le champ de texte est valide.
     */
    private static void markTextFieldErrorStatus(TextField textField, boolean isValid) {
        if (isValid) {
            textField.setStyle(null);
        } else {
            textField.setStyle("-fx-control-inner-background: f8d7da");
        }
    }

    /**
     * Valide qu'un champ de texte est non vide.
     *
     * @param textField Le champ de texte à valider.
     * @return true si le champ de texte est non vide, false sinon.
     */
    private static boolean validateNonEmptyTextField(TextField textField) {
        boolean isValid = textField.getText().strip().length() > 0;

        markTextFieldErrorStatus(textField, isValid);

        return isValid;
    }

    /**
     * Valide qu'un champ de texte contient une adresse email valide.
     *
     * @param textField Le champ de texte à valider.
     * @return true si le champ de texte contient une adresse email valide, false sinon.
     */
    private static boolean validateEmailTextField(TextField textField) {
        EmailValidator validator = EmailValidator.getInstance(false, false);
        boolean isValid = validator.isValid(textField.getText().strip().toLowerCase());

        markTextFieldErrorStatus(textField, isValid);

        return isValid;
    }

}

