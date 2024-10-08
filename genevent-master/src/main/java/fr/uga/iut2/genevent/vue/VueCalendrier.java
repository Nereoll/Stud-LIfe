package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.modele.Calendrier;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Structure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;

/**
 * La classe VueCalendrier gère l'affichage du calendrier des événements dans l'interface utilisateur de GenEvent.
 * Elle permet de naviguer entre les mois, afficher les événements par jour, et visualiser les détails des événements.
 */
public class VueCalendrier {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML private Text year;
    @FXML private Text month;
    @FXML private FlowPane calendar;

    @FXML private Label selectedDateLabel;

    @FXML private TextArea dateInfo;

    private Controleur controleur;

    //private ArrayList<Evenement> evenements = new ArrayList<>();


    /**
     * Initialise le contrôleur et configure l'affichage initial du calendrier.
     */
    @FXML
    private void initialize() {
        this.controleur = Controleur.getInstance();
        if (selectedDateLabel == null) {
            dateFocus = ZonedDateTime.now();
            today = ZonedDateTime.now();
            drawCalendar();
        }
    }

    /**
     * Action exécutée lors du clic sur le bouton "Mois précédent".
     * Déplace la vue du calendrier vers le mois précédent.
     *
     * @param event Événement associé au clic sur le bouton.
     */
    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    /**
     * Action exécutée lors du clic sur le bouton "Mois suivant".
     * Déplace la vue du calendrier vers le mois suivant.
     *
     * @param event Événement associé au clic sur le bouton.
     */
    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    /**
     * Méthode pour définir la date sélectionnée dans l'étiquette de la vue.
     *
     * @param date Date à afficher dans l'étiquette.
     */
    public void setDate(String date) {
        selectedDateLabel.setText(date);
    }

    /**
     * Méthode pour fermer la boîte de dialogue de la date.
     */
    @FXML
    private void handleOK() {
        Stage stage = (Stage) selectedDateLabel.getScene().getWindow();
        stage.close();
    }

    /**
     * Dessine le calendrier pour le mois et l'année actuellement focalisés.
     * Configure les jours du mois avec leurs événements associés.
     */
    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));

        // Utiliser DateTimeFormatter pour obtenir le nom du mois en français
        DateTimeFormatter frenchMonthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.FRANCE);
        String monthName = dateFocus.format(frenchMonthFormatter);

        month.setText(capitalizeFirstLetter(monthName));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        // Liste des activités pour un mois donné
        Map<Integer, List<Calendrier>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        // Vérification pour l'année bissextile
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        // Positionner le texte de la date en bas à gauche
                        Text date = new Text(String.valueOf(currentDate));
                        StackPane.setAlignment(date, Pos.BOTTOM_LEFT);
                        double margin = 10; // Ajouter une marge pour le décalage de la position
                        StackPane.setMargin(date, new Insets(0, 0, margin, margin));
                        stackPane.getChildren().add(date);

                        // Ajouter un EventHandler pour ouvrir un modal avec la date de la case
                        stackPane.setOnMouseClicked(event -> {;
                            demarrerDateDialog(currentDate);
                        });

                        List<Calendrier> calendrierActivities = calendarActivityMap.get(currentDate);
                        if (calendrierActivities != null) {
                            createCalendarActivity(calendrierActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }

    }

    /**
     * Crée une boîte avec toutes les activités pour une date donnée.
     *
     * @param calendrierActivities Liste des activités pour la date donnée.
     * @param rectangleHeight Hauteur du rectangle contenant la date dans le calendrier.
     * @param rectangleWidth Largeur du rectangle contenant la date dans le calendrier.
     * @param stackPane StackPane où ajouter la boîte d'activités.
     */
    private void createCalendarActivity(List<Calendrier> calendrierActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendrierActivities.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                });
                break;
            }
            Text text = new Text(calendrierActivities.get(k).getClientName() +"\n\t début : " + calendrierActivities.get(k).getHeureDebut()+" h");
            calendarActivityBox.getChildren().add(text);
        }
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-border-color:red");
        stackPane.getChildren().add(calendarActivityBox);
    }

    /**
     * Crée une carte associant chaque jour du mois à ses activités.
     *
     * @param calendrierActivities Liste des activités pour le mois.
     * @return Map associant chaque jour du mois à ses activités.
     */
    private Map<Integer, List<Calendrier>> createCalendarMap(List<Calendrier> calendrierActivities) {
        Map<Integer, List<Calendrier>> calendarActivityMap = new HashMap<>();

        for (Calendrier activity: calendrierActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<Calendrier> OldListByDate = calendarActivityMap.get(activityDate);

                List<Calendrier> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

    /**
     * Obtient la liste des activités planifiées pour le mois actuellement focalisé.
     *
     * @param dateFocus Date représentant le mois et l'année actuels.
     * @return Map associant chaque jour du mois à ses activités.
     */
    private Map<Integer, List<Calendrier>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<Calendrier> calendrierActivities = new ArrayList<>();
        //List<Structure> structuresList = this.controleur.getStructures().values().stream().toList();
        //ArrayList<Structure> structuresArrayList = new ArrayList<>(structuresList);
        for (Evenement evenement : controleur.getEvenements().values()) {
            calendrierActivities.add(new Calendrier(evenement.getDateSelectionnee().atStartOfDay(ZoneOffset.UTC), evenement.getNom(),evenement.getNombrePersonne(), evenement.getHeureDebut(), evenement.getStructures()));
        }
            return createCalendarMap(calendrierActivities);
        }

    /**
     * Capitalise la première lettre d'une chaîne de caractères.
     *
     * @param input Chaîne de caractères à capitaliser.
     * @return Chaîne de caractères avec la première lettre en majuscule.
     */
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Démarre une boîte de dialogue pour afficher les détails des événements d'une date sélectionnée.
     *
     * @param currentDate Jour du mois sélectionné.
     */
    public void demarrerDateDialog(int currentDate) {
        try {
            FXMLLoader dateDialogViewLoader = new FXMLLoader(getClass().getResource("date-dialog-view.fxml"));
            dateDialogViewLoader.setController(this);
            Scene dateDialogScene = new Scene(dateDialogViewLoader.load());

            Stage newUserWindow = new Stage();
            newUserWindow.setTitle("Détail de la date");
            newUserWindow.initModality(Modality.APPLICATION_MODAL);
            newUserWindow.setScene(dateDialogScene);

            // Formatter pour la date complète
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRANCE);
            String fullDate = dateFocus.withDayOfMonth(currentDate).format(dateFormatter);

            selectedDateLabel.setText(fullDate);
            for (Calendrier activity : getCalendarActivitiesMonth(dateFocus).get(currentDate)) {
                dateInfo.appendText(activity.toString() + "\n");
                this.controleur.getStructures().clear();
            }

            newUserWindow.showAndWait();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }

    }
}
