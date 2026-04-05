package org.example.tipislab2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.example.tipislab2.CaesarCrypt;
import org.example.tipislab2.CaesarDecrypt;

public class CaesarController {

    @FXML private TextArea inputArea;
    @FXML private TextArea outputArea;
    @FXML private Spinner<Integer> shiftSpinner;
    @FXML private Button btnEncrypt;
    @FXML private Button btnDecrypt;
    @FXML private Button btnAutoDecrypt;
    @FXML private Button btnClear;
    @FXML private Button btnSwap;
    @FXML private Text statusLabel;

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(-32, 32, 3, 1);
        shiftSpinner.setValueFactory(valueFactory);
        shiftSpinner.setEditable(true);
    }

    @FXML
    private void onEncrypt() {
        String text = inputArea.getText();
        if (text.isBlank()) {
            showWarning("Введите текст для шифрования");
            return;
        }

        int shift = shiftSpinner.getValue();
        String result = CaesarCrypt.crypt(text, shift);

        outputArea.setText(result);
    }

    @FXML
    private void onDecrypt() {
        String text = inputArea.getText();
        if (text.isBlank()) {
            showWarning("Введите текст для расшифровки");
            return;
        }

        int shift = shiftSpinner.getValue();
        String result = CaesarDecrypt.decrypt(text, shift);

        outputArea.setText(result);
    }

    @FXML
    private void onAutoDecrypt() {
        String text = inputArea.getText();
        if (text.isBlank()) {
            showWarning("Введите зашифрованный текст");
            return;
        }

        try {
            int detectedShift = CaesarDecrypt.determineShift(text);
            String result = CaesarDecrypt.decrypt(text, detectedShift);

            outputArea.setText(result);
            shiftSpinner.getValueFactory().setValue(detectedShift);

        } catch (IllegalArgumentException e) {
            showError("Ошибка: " + e.getMessage());
        }
    }

    @FXML
    private void onClear() {
        inputArea.clear();
        outputArea.clear();
        shiftSpinner.getValueFactory().setValue(3);
    }

    @FXML
    private void onSwap() {
        String temp = inputArea.getText();
        inputArea.setText(outputArea.getText());
        outputArea.setText(temp);
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.setTitle("Предупреждение");
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Ошибка");
        alert.showAndWait();
    }
}
