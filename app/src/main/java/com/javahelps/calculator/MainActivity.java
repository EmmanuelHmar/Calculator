package com.javahelps.calculator;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
//        Find and set Onclicklistener to operator, equal and decimal buttons
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

                }
            }
        }
    }
}
