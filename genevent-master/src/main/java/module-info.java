module genevent {
    requires commons.validator;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.gluonhq.maps;
    requires jdk.jshell;

    opens fr.uga.iut2.genevent to javafx.fxml;
    exports fr.uga.iut2.genevent;

    opens fr.uga.iut2.genevent.controleur to javafx.fxml;
    exports fr.uga.iut2.genevent.controleur;

    opens fr.uga.iut2.genevent.vue to javafx.fxml;
    exports fr.uga.iut2.genevent.vue;

}
