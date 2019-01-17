package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    public TextField txtFieldData;
    @FXML
    private Label labelMemory;
    private Calculator calculator;
    private boolean isSecondNumber;

    public Controller() {
        txtFieldData = new TextField();
        calculator = new Calculator();
        isSecondNumber = false;
    }

    private String DeleteZeroEnd(String input){
        return input.endsWith("0") ? input.substring(0,input.length()-2): input;
    }

    public void btnClickNumber(ActionEvent actionEvent)
    {
        String btnText = ((Button) actionEvent.getSource()).getText();
        txtFieldData.setText(txtFieldData.getText().equals("0") || (isSecondNumber) ? btnText : txtFieldData.getText() + btnText);
        isSecondNumber = false;
        calculator.isFirstCalculation = true;
    }

    public void btnClickComa(ActionEvent actionEvent)
    {
        txtFieldData.setText(txtFieldData.getText() + (!txtFieldData.getText().contains(".") ? "." : ""));
        calculator.isFirstCalculation = true;
    }

    public void btnClickClear(ActionEvent actionEvent)
    {
        txtFieldData.setText("0");
        calculator.isFirstCalculation = true;
    }


    public void btnClickOperation(ActionEvent actionEvent) {
        try {
            calculator.number1 = Double.parseDouble(txtFieldData.getText());
            switch (((Button) actionEvent.getSource()).getText()) {
                case "+":
                    calculator.operation = 1;
                    break;
                case "-":
                    calculator.operation = 2;
                    break;
                case "*":
                    calculator.operation = 3;
                    break;
                case "/":
                    calculator.operation = 4;
                    break;
                case "√":
                    calculator.operation = 5;
                    break;
                case "x^y":
                    calculator.operation = 6;
                    break;
                case "sin":
                    calculator.operation = 7;
                    break;
                case "cos":
                    calculator.operation = 8;
                    break;
                case "tan":
                    calculator.operation = 9;
                    break;
                case "log":
                    calculator.operation = 10;
                    break;
                case "ln":
                    calculator.operation = 11;
                calculator.calculate();
                    txtFieldData.setText(DeleteZeroEnd(calculator.result));
                    break;
                default:
                    calculator.operation = 0;
            }
            isSecondNumber = true;
            calculator.isFirstCalculation = true;
        } catch (NumberFormatException exc)
        {
            txtFieldData.setText("Ошибка вычисления!");
        } catch (Exception exc)
        {
            txtFieldData.setText(exc.getMessage());
        }
    }

    public void btnClickResult (ActionEvent actionEvent)
    {
        try{
            if(calculator.isFirstCalculation){
                calculator.number2 = Double.parseDouble(txtFieldData.getText());
            }
        } catch (NumberFormatException exc)
        {
            txtFieldData.setText("Ошибка!");
        }

        calculator.calculate();
        calculator.number1 = Double.parseDouble(calculator.result);
        calculator.isFirstCalculation = false;
        txtFieldData.setText(calculator.result);
    }

    public void btnClickMemoryClear(ActionEvent actionEvent) {
        calculator.setMemory(0);
        isSecondNumber = true;
        labelMemory.setVisible(false);
    }
    public void btnClickMemoryRead(ActionEvent actionEvent) {
        txtFieldData.setText(DeleteZeroEnd(String.valueOf(calculator.getMemory())));
        isSecondNumber = true;
    }

    public void btnClickMemoryAdd(ActionEvent actionEvent) {
        double memory = 0;
        try {
            memory = Double.parseDouble(txtFieldData.getText());
        } catch (NumberFormatException ex)
        {
            txtFieldData.setText("Ошибка!");
            return;
        }
        calculator.setMemory(calculator.getMemory() + memory);
        isSecondNumber = true;
        labelMemory.setVisible(calculator.getMemory() != 0);
    }

    public void btnClickMemorySub(ActionEvent actionEvent) {
        double memory = 0;
        try {
            memory = Double.parseDouble(txtFieldData.getText());
        } catch (Exception ex) {
            txtFieldData.setText("Ошибка!");
        }
        calculator.setMemory(calculator.getMemory() - memory);
        isSecondNumber = true;
        labelMemory.setVisible(calculator.getMemory() != 0);
    }

    public void buttonPressed(KeyEvent keyEvent) {
        txtFieldData.setText(txtFieldData.getText() + (keyEvent.getText().contains("0") ? keyEvent.getText() : ""));
    }
}
