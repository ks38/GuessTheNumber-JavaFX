module ru.knyazevvb.guessthenumber.guessthenumber {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.knyazevvb to javafx.fxml;
    exports ru.knyazevvb;
}