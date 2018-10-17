package com.mirhack.ioscalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
//IOS Calc
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero;
    Button btnMultiply, btnDivide, btnEquals, btnPlus, btnMinus, btnClear, btnPercent, btnChangePlusMinus, btnComma;
    TextView tvResult;

    int operationType;
    final int NOOPERATION = 0;
    final int ADDITION = 1;
    final int SUBTRACTION = 2;
    final int MULTIPLICATION = 3;
    final int DIVISION = 4;
    String strValueOne = "0";
    String strValueTwo = "0";
    BigDecimal valueOne = BigDecimal.ZERO;
    BigDecimal valueTwo = BigDecimal.ZERO;
    Toast divideErrorToast;
    boolean isComma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Находим элементы
        tvResult = findViewById(R.id.tvResult);

        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);

        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);
        btnPercent = findViewById(R.id.btnPercent);
        btnChangePlusMinus = findViewById(R.id.btnChangePlusMinus);
        btnComma = findViewById(R.id.btnComma);

        //Назначаем Listener
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);

        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnChangePlusMinus.setOnClickListener(this);
        btnComma.setOnClickListener(this);

        operationType = NOOPERATION;

        tvResult.setText("0");

        divideErrorToast = Toast.makeText(this, "Ошибка: деление на 0", Toast.LENGTH_SHORT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOne:
                clickNumber("1");
                break;
            case R.id.btnTwo:
                clickNumber("2");
                break;
            case R.id.btnThree:
                clickNumber("3");
                break;
            case R.id.btnFour:
                clickNumber("4");
                break;
            case R.id.btnFive:
                clickNumber("5");
                break;
            case R.id.btnSix:
                clickNumber("6");
                break;
            case R.id.btnSeven:
                clickNumber("7");
                break;
            case R.id.btnEight:
                clickNumber("8");
                break;
            case R.id.btnNine:
                clickNumber("9");
                break;
            case R.id.btnZero:
                clickNumber("0");
                break;

            case R.id.btnClear:
                tvResult.setText("0");
                strValueOne = "0";
                strValueTwo = "0";
                valueOne = BigDecimal.ZERO;
                valueTwo = BigDecimal.ZERO;
                operationType = 0;
                isComma = false;
                break;

            case R.id.btnPlus:
                isComma = false;
                if (Float.parseFloat(strValueTwo) != 0) calculate();
                operationType = ADDITION;
                break;
            case R.id.btnMinus:
                isComma = false;
                if (Float.parseFloat(strValueTwo) != 0) calculate();
                operationType = SUBTRACTION;
                break;
            case R.id.btnMultiply:
                isComma = false;
                if (Float.parseFloat(strValueTwo) != 0) calculate();
                operationType = MULTIPLICATION;
                break;
            case R.id.btnDivide:
                isComma = false;
                if (Float.parseFloat(strValueTwo) != 0) calculate();
                operationType = DIVISION;
                break;
            case R.id.btnEquals:
                isComma = false;
                calculate();
                break;
            case R.id.btnPercent:
                isComma = false;
                calculatePercent();
                break;
            case R.id.btnChangePlusMinus:
                if (Float.parseFloat(strValueTwo) != 0) {
                    valueTwo = new BigDecimal(strValueTwo);
                    valueTwo = valueTwo.negate();
                    strValueTwo = valueTwo.stripTrailingZeros().toPlainString();
                    tvResult.setText(strValueTwo);
                } else {
                    valueOne = new BigDecimal(strValueOne);
                    valueOne = valueOne.negate();
                    strValueOne = valueOne.stripTrailingZeros().toPlainString();
                    tvResult.setText(strValueOne);
                }
                break;
            case R.id.btnComma:
                if (operationType == 0) {
                    if (!isComma) {
                        strValueOne += ".";
                        isComma = true;
                    }
                    tvResult.setText(strValueOne);
                } else {
                    if (!isComma) {
                        strValueTwo += ".";
                        isComma = true;
                    }
                    tvResult.setText(strValueTwo);
                }
                break;

        }
    }

    private void clickNumber(String num) {

        if (operationType == 0) {
            if (strValueOne.equals("0")) {
                strValueOne = num;

            } else {
                strValueOne += num;
            }
            tvResult.setText(strValueOne);
        } else {
            if (strValueTwo.equals("0")) {
                strValueTwo = num;
            } else {
                strValueTwo += num;
            }
            tvResult.setText(strValueTwo);
        }
    }

    private void calculate() {
        switch (operationType) {
            case ADDITION:
                valueOne = new BigDecimal(strValueOne);
                valueTwo = new BigDecimal(strValueTwo);
                valueOne = valueOne.add(valueTwo);
                strValueOne = valueOne.stripTrailingZeros().toPlainString();
                strValueTwo = "0";
                valueTwo = BigDecimal.ZERO;
                tvResult.setText(strValueOne);
                break;
            case SUBTRACTION:
                valueOne = new BigDecimal(strValueOne);
                valueTwo = new BigDecimal(strValueTwo);
                valueOne = valueOne.subtract(valueTwo);
                strValueOne = valueOne.stripTrailingZeros().toPlainString();
                strValueTwo = "0";
                valueTwo = BigDecimal.ZERO;
                tvResult.setText(strValueOne);
                break;
            case MULTIPLICATION:
                valueOne = new BigDecimal(strValueOne);
                valueTwo = new BigDecimal(strValueTwo);
                valueOne = valueOne.multiply(valueTwo);
                strValueOne = valueOne.stripTrailingZeros().toPlainString();
                strValueTwo = "0";
                valueTwo = BigDecimal.ZERO;
                tvResult.setText(strValueOne);
                break;
            case DIVISION:
                valueOne = new BigDecimal(strValueOne);
                valueTwo = new BigDecimal(strValueTwo);
                try {
                    valueOne = valueOne.divide(valueTwo, 7, RoundingMode.HALF_UP);
                } catch (ArithmeticException e) {
                    divideErrorToast.show();
                    tvResult.setText("0");
                    strValueOne = "0";
                    strValueTwo = "0";
                    valueOne = BigDecimal.ZERO;
                    valueTwo = BigDecimal.ZERO;
                    operationType = 0;
                    break;
                }
                strValueOne = valueOne.stripTrailingZeros().toPlainString();
                strValueTwo = "0";
                valueTwo = BigDecimal.ZERO;
                tvResult.setText(strValueOne);
                break;
        }
    }

    private void calculatePercent() {
        switch (operationType) {
            case ADDITION:
                valueOne = new BigDecimal(strValueOne);
                valueTwo = new BigDecimal(strValueTwo);
                valueOne = valueOne.multiply(BigDecimal.ONE.add(valueTwo.divide(new BigDecimal(100))));
                strValueOne = valueOne.stripTrailingZeros().toPlainString();
                strValueTwo = "0";
                valueTwo = BigDecimal.ZERO;
                tvResult.setText(strValueOne);
                break;
            case SUBTRACTION:
                valueOne = new BigDecimal(strValueOne);
                valueTwo = new BigDecimal(strValueTwo);
                valueOne = valueOne.multiply(BigDecimal.ONE.subtract(valueTwo.divide(new BigDecimal(100))));
                strValueOne = valueOne.stripTrailingZeros().toPlainString();
                strValueTwo = "0";
                valueTwo = BigDecimal.ZERO;
                tvResult.setText(strValueOne);
                break;
        }
    }
}

