package ru.knyazevvb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GuessTheNumberController implements Initializable {

    private final Random random = new Random();

    private static int randomNumber;
    private int guessCount = 0;

    @FXML
    private Text guessedText;
    @FXML
    private ImageView upArrow;
    @FXML
    private ImageView downArrow;
    @FXML
    private ImageView youGuess;
    @FXML
    private TextField guess;
    @FXML
    private Text guessCounterText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        randomNumber = random.nextInt(10);
        System.out.println(randomNumber);
        downArrow.setVisible(false);
        upArrow.setVisible(false);
        youGuess.setVisible(false);
    }

    @FXML
    public void checkGuess(ActionEvent actionEvent) {
        if (guess.getText().matches("-?[\\d]+")) {
            try {
                if (guess != null && (guess.getText().equals(""))) {
                    System.out.println("Введите число");
                } else {
                    if (Integer.parseInt(guess.getText()) == randomNumber) {
                        downArrow.setVisible(false);
                        upArrow.setVisible(false);
                        youGuess.setVisible(true);
                        guess.clear();
                        guessedText.setText("");
                        isPlayerWantsToPlayAgain();
                    } else if (Integer.parseInt(guess.getText()) < randomNumber) {
                        downArrow.setVisible(false);
                        upArrow.setVisible(true);
                        youGuess.setVisible(false);
                        guess.clear();
                        guessedText.setText("Загаданное число больше");
                    } else if (Integer.parseInt(guess.getText()) > randomNumber) {
                        downArrow.setVisible(true);
                        upArrow.setVisible(false);
                        youGuess.setVisible(false);
                        guess.clear();
                        guessedText.setText("Загаданное число меньше");
                    }
                    guessCount++;
                    guessCounterText.setText("Количество попыток угадать: " + guessCount);
                }
            } catch (RuntimeException e) {
                System.out.println("Вы ввели не число");
                guess.clear();
            }
        } else isItNumber();
    }

    public void reset() {
        randomNumber = new Random().nextInt(10);
        downArrow.setVisible(false);
        upArrow.setVisible(false);
        youGuess.setVisible(false);
        guessCount = 0;
        guess.setText("");
        guessCounterText.setText("Количество попыток угадать: " + guessCount);
        guessedText.setText("");
    }

    private boolean isItNumber() {
        final Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели не цифру",
                new ButtonType("Вернуться", ButtonBar.ButtonData.APPLY));
        final ButtonType buttonType = alert.showAndWait().get();
        return buttonType.getButtonData() == ButtonBar.ButtonData.APPLY;
    }

    private boolean isPlayerWantsToPlayAgain() {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Поздравляю, вы выиграли!!!\n" +
                        "Загаданное число: " + randomNumber +
                        "\n" + "Желаете сыграть еще?",
                new ButtonType("Да", ButtonBar.ButtonData.YES),
                new ButtonType("Нет", ButtonBar.ButtonData.NO));
        final ButtonType buttonType = alert.showAndWait().get();
        return buttonType.getButtonData() == ButtonBar.ButtonData.YES;
    }


    public void onExitSelect(ActionEvent actionEvent) {
        System.exit(0);
    }
}
