package com.javahelps.calculator;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends ActionBarActivity {
    //    IDs of all the the numeric buttons
    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnThree, R.id.btnTwo, R.id.btnFour,
            R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};
//    IDS of all the operator buttons
    private int[] operatorButtons = {R.id.btnDivide, R.id.btnMultiply, R.id.btnAdd, R.id.btnSubtract};
//    TextView used to display the output
    private TextView textScreen;
//    Represent whether the last pressed button is numeric or not
    private boolean lastNumeric;
//    Represent that current state is in error or not
    private boolean stateError;
//    If true, do not allow to add another DOT
    private boolean lastDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Find the TextView
        this.textScreen = (TextView) findViewById(R.id.textScreen);
//        Find and set OnClickListetner to numeric buttons
        setNumericOnClickListener();
//        Find and set OnClickListener to operator, equal and decimal buttons
        setOperatorOnClickListener();
    }

//    Find and set OnClickListener to numeric buttons
    private void setNumericOnClickListener() {
//        Create a common OnClickListener
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Append/set the text of clicked button
                Button button = (Button) view;
                if (stateError) {
//                    If current state is Error, replace the error message
                    textScreen.setText(button.getText());
                    stateError = false;
                } else {
//                    If not, there is already a valid expression so append it
                    textScreen.append(button.getText());
                }
//                Set the flag
                lastNumeric = true;
            }
        };
//        Assign the listener to all the numeric buttons
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    //        Find and set OnClickListener to operator, equal and decimal buttons
    private void setOperatorOnClickListener() {
//        Create a common OnClickListener for operators
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                If current State is false do not append
//                If the last input is number only, append the operator
                if (lastNumeric && !stateError) {
                    Button button = (Button) view;
                    textScreen.append(button.getText());
                    lastNumeric = false;
                    lastDot = false;//Reset the DOT flag
                }
            }
        };
//        Assign the listener to all operator buttons
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
//        Decimal point
        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (lastNumeric && !stateError && !lastDot) {
                    textScreen.append(".");
                    lastNumeric =false;
                    lastDot = true;
                }
            }
        });
//        Clear button
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textScreen.setText("");//Clear the screen
//                REset all the states and flags
                lastNumeric = false;
                stateError = false;
                lastDot =false;
            }
        });

        //Equal Button
        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onEqual();
            }
        });
    }

//    logic to calculate the solution
    private void onEqual() {
//        If the current state is error, do nothing
//        If the last input is a number only, solution can be found
        if (lastNumeric && !stateError) {
//            Read the expression
            String text = textScreen.getText().toString();
//            Create an expression (A class from exp4j library)
            Expression expression = new ExpressionBuilder(text).build();
            try {
//                Calculate the result and display
                double result = expression.evaluate();
                textScreen.setText(Double.toString(result));
                lastDot = true; //Result contains a dot
            } catch (ArithmeticException ex) {
//                Display an error message
                textScreen.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}
