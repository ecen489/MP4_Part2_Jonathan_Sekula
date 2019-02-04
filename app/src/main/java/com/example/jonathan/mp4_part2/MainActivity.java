package com.example.jonathan.mp4_part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // UI objects
    TextView textView;

    // other global variables
    String text = "0";
    boolean answer_found = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize UI objects
        textView = findViewById(R.id.textView);

    }

    public void onClickDigit(View view) {

        // check if string already contains an operator
        boolean contains_operator;
        if (text.charAt(0) == '-')
            contains_operator = text.substring(1, text.length()).contains("/") || text.substring(1, text.length()).contains("*") || text.substring(1, text.length()).contains("-") || text.substring(1, text.length()).contains("+");
        else
            contains_operator = text.contains("/") || text.contains("*") || text.contains("-") || text.contains("+");

        boolean has_decimal = false;
        // find index of operator
        if (contains_operator) {
            int index = 0;
            for (int i = 1; i < text.length(); ++i) {
                if (!Character.isDigit(text.charAt(i)) && text.charAt(i) != '.') {
                    index = i;
                    break;
                }
            }

            // don't want to delete "leading zeros" after decimal point
            if (index != text.length()-1) {
                for (int i = index+1; i < text.length(); ++i) {
                    if (!Character.isDigit(text.charAt(i))) {
                        has_decimal = true;
                        break;
                    }
                }
            }

        }
        else {
            for (int i = 1; i < text.length(); ++i) {
                if (!Character.isDigit(text.charAt(i))) {
                    has_decimal = true;
                    break;
                }
            }
        }

        if (!has_decimal) {
            // check for leading zeros
            boolean leading_zeros = text.charAt(text.length() - 1) == '0';
            if (leading_zeros)
                text = text.substring(0, text.length() - 1);
        }

        switch(view.getId()) {
            case R.id.button0:
                if (!answer_found)
                    text = text + "0";
                else
                    text = "0";
                break;
            case R.id.button1:
                if (!answer_found)
                    text = text + "1";
                else
                    text = "1";
                break;
            case R.id.button2:
                if (!answer_found)
                    text = text + "2";
                else
                    text = "2";
                break;
            case R.id.button3:
                if (!answer_found)
                    text = text + "3";
                else
                    text = "3";
                break;
            case R.id.button4:
                if (!answer_found)
                    text = text + "4";
                else
                    text = "4";
                break;
            case R.id.button5:
                if (!answer_found)
                    text = text + "5";
                else
                    text = "5";
                break;
            case R.id.button6:
                if (!answer_found)
                    text = text + "6";
                else
                    text = "6";
                break;
            case R.id.button7:
                if (!answer_found)
                    text = text + "7";
                else
                    text = "7";
                break;
            case R.id.button8:
                if (!answer_found)
                    text = text + "8";
                else
                    text = "8";
                break;
            case R.id.button9:
                if (!answer_found)
                    text = text + "9";
                else
                    text = "9";
                break;
        }

        answer_found = false;
        textView.setText(text);

    }

    public void onClickOperator(View view) {

        // check if string already contains an operator
        boolean contains_operator;
        if (text.charAt(0) == '-')
            contains_operator = text.substring(1, text.length()).contains("/") || text.substring(1, text.length()).contains("*") || text.substring(1, text.length()).contains("-") || text.substring(1, text.length()).contains("+");
        else
            contains_operator = text.contains("/") || text.contains("*") || text.contains("-") || text.contains("+");

        // avoid two consecutive operators
        if (contains_operator) {
            int index = 0;
            for (int i = 1; i < text.length(); ++i) {
                if (!Character.isDigit(text.charAt(i)) && text.charAt(i) != '.') {
                    index = i;
                    break;
                }
            }

            if (index == text.length()-1) {
                return;
            }

        }

        switch(view.getId()) {
            case R.id.button_divide:
                if (!contains_operator) {
                    text = text + "/";
                    answer_found = false;
                }
                else {
                    text = Evaluate(text) + "/";
                    answer_found = false;
                }
                break;
            case R.id.button_multiply:
                if (!contains_operator) {
                    text = text + "*";
                    answer_found = false;
                }
                else {
                    text = Evaluate(text) + "*";
                    answer_found = false;
                }
                break;
            case R.id.button_subtract:
                if (!contains_operator) {
                    text = text + "-";
                    answer_found = false;
                }
                else {
                    text = Evaluate(text) + "-";
                    answer_found = false;
                }
                break;
            case R.id.button_add:
                if (!contains_operator) {
                    text = text + "+";
                    answer_found = false;
                }
                else {
                    text = Evaluate(text) + "+";
                    answer_found = false;
                }
                break;
        }

        textView.setText(text);

    }

    public void onClickEquals(View view) {
        text = Evaluate(text);
        textView.setText(text);

    }

    public void onClickDecimal(View view) {

        if (answer_found) {
            text = "0.";
            textView.setText(text);
            answer_found = false;
        }
        else {
            String substring = text;

            // check if string already contains an operator
            boolean contains_operator = text.contains("/") || text.contains("*") || text.contains("-") || text.contains("+");

            if(contains_operator) {
                // find index of operator
                int index = 0;

                for (int i = 0; i < text.length(); ++i) {
                    if (!Character.isDigit(text.charAt(i)) && text.charAt(i) != '.') {
                        index = i;
                        break;
                    }
                }
                substring = text.substring(index+1, text.length());
            }

            if (substring.isEmpty()) {
                text = text + "0.";
                textView.setText(text);
            }
            else {
                // check if a decimal point already exists in the current number
                boolean decimal_exists = false;
                for (int i = 0; i < substring.length(); ++i) {
                    if (substring.charAt(i) == '.') {
                        decimal_exists = true;
                        break;
                    }
                }

                if (!decimal_exists) {
                    text = text + ".";
                    textView.setText(text);
                }
            }

        }

    }

    public void onClickClear(View view) {
        text = "0";
        textView.setText(text);
    }

    public String Evaluate(String string) {

        // avoid incomplete statements
        int idx = 0;
        for (int i = 1; i < text.length(); ++i) {
            if (!Character.isDigit(text.charAt(i)) && text.charAt(i) != '.') {
                idx = i;
                break;
            }
        }

        if (idx == text.length()-1) {
            return text;
        }

        answer_found = true;

        // find first character that isn't a digit
        int index = 0;
        char operator = ' ';
        boolean operator_found = false;

        for (int i = 1; i < string.length(); ++i) {
            if (!Character.isDigit(string.charAt(i)) && string.charAt(i) != '.') {
                operator = string.charAt(i);
                index = i;
                operator_found = true;
                break;
            }
        }

        if (operator_found) {

            // extract numbers from string
            double operand1 = Double.parseDouble(string.substring(0, index));
            double operand2 = Double.parseDouble(string.substring(index + 1, string.length()));

            double result = 0.0d;

            switch (operator) {
                case '/':
                    result = operand1 / operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '+':
                    result = operand1 + operand2;
                    break;
            }

            // check if result is integer. eg. 1.0
            int result_int = (int) result;

            if (result_int - result == 0.0d) {
                return String.valueOf(result_int);
            }
            else {
                return String.valueOf(result);
            }

        }
        else {
            return text;
        }

    }

}