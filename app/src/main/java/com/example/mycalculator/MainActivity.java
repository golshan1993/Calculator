package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String FIRST_OPERAND = "first operand";
    public static final String SECOND_OPERAND = "second operand";
    public static final String TEXTVIEW = "textview";
    public static final String AFTER_OPERAND = "after operand";
    private int[] numbers = {R.id.btn_0,R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5,R.id.btn_6,R.id.btn_7,R.id.btn_8,R.id.btn_9};
    private Button mButton0;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButtonadd;
    private Button mButtonmin;
    private Button mButtondiv;
    private Button mButtonmul;
    private Button mButtonper;
    private Button mButtonpow;
    private Button mButtonsqrt;
    private Button mButtonclear;
    private Button mButtondec;
    private Button mButtonres;
    private Button mButtoneql;
    private float firstOperand;
    private float secondOperand;
    int cnt = 0;
    private Operation operation;
    private TextView mTextview;
    private float result;
    private String shown;
    private String afterOperand = "";
    private enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        SQRT,
        RESET,
        PERCENT,
        POWER
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setID();
        for (int i:numbers) {
            final Button button = findViewById(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = (String)button.getText();
                    shown = (String)mTextview.getText();
                    if(cnt == 1){
                        afterOperand = afterOperand + text;
                    }
                    mTextview.setText(shown+text);
                }
            });
        }
        if (savedInstanceState != null) {

            mTextview.setText(savedInstanceState.getString(TEXTVIEW, null));
            if (mTextview.getText().toString().contains("[+-*/^%√]")) {
                firstOperand = savedInstanceState.getFloat(FIRST_OPERAND, 0);
                afterOperand = savedInstanceState.getString(AFTER_OPERAND, null);
                if (mTextview.getText().toString().contains("+")) {
                    operation = Operation.ADD;
                } else if (mTextview.getText().toString().contains("-")) {
                    operation = Operation.SUBTRACT;
                } else if (mTextview.getText().toString().contains("*")) {
                    operation = Operation.MULTIPLY;
                } else if (mTextview.getText().toString().contains("/")) {
                    operation = Operation.DIVIDE;
                } else if (mTextview.getText().toString().contains("^")) {
                    operation = Operation.POWER;
                } else if (mTextview.getText().toString().contains("%")) {
                    operation = Operation.PERCENT;
                } else if (mTextview.getText().toString().contains("√")) {
                    operation = Operation.SQRT;
                }
            }
            else if (mTextview.getText().toString().contains("[=]")){
                secondOperand = savedInstanceState.getFloat(SECOND_OPERAND, 0);
            }
        }


        setListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(FIRST_OPERAND, firstOperand);
        outState.putFloat(SECOND_OPERAND, secondOperand);
        outState.putString(TEXTVIEW, (String) mTextview.getText());
        outState.putString(AFTER_OPERAND, afterOperand);
    }

    private void setOperationResult() {
            if (operation == Operation.ADD){
                    result = firstOperand + secondOperand;
            }
            else if (operation == Operation.DIVIDE){
                    result = firstOperand/secondOperand;
            }
            else if (operation == Operation.SUBTRACT){
                    result = firstOperand-secondOperand;
            }
            else if (operation == Operation.MULTIPLY){
                    result = firstOperand*secondOperand;
            }
            else if (operation == Operation.POWER){
                    result = (float) Math.pow(firstOperand,secondOperand);
            }
            else if (operation == Operation.SQRT){
                    result = (float) Math.sqrt(firstOperand);
            }
            else if (operation == Operation.PERCENT){
                result = (float) (firstOperand/100);
            }
            cnt = 0;

    }

    private void setListeners() {
        shown = (String)mTextview.getText();
        mButtonres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shown = (String)mTextview.getText();
                String s = shown.substring(0,shown.length()-1);
                if (cnt == 2 && String.valueOf(shown.charAt(shown.length() - 1)).equals("[=]")){
                    cnt--;
                }
                else if (cnt == 1 && !String.valueOf(shown.charAt(shown.length() - 1)).equals("[+-*/.^ ])")) {
                    afterOperand = afterOperand.substring(0, afterOperand.length() - 1);
                }
                else if (cnt == 1 && String.valueOf(shown.charAt(shown.length() - 1)).equals("[+-*/.^])")){
                    cnt--;
                }
                mTextview.setText(s);
            }
        });
        mButtonmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstOperand = Float.parseFloat((String) mTextview.getText());
                mTextview.setText(mTextview.getText()+ " - ");
                operation = Operation.SUBTRACT;
                cnt++;
            }
        });

        mButtonmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstOperand = Float.parseFloat((String) mTextview.getText());
                mTextview.setText(mTextview.getText()+ " * ");
                operation = Operation.MULTIPLY;
                cnt++;
            }
        });

        mButtondiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstOperand = Float.parseFloat((String) mTextview.getText());
                mTextview.setText(mTextview.getText()+ " / ");
                operation = Operation.DIVIDE;
                cnt++;
            }
        });

        mButtonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstOperand = Float.parseFloat((String) mTextview.getText());
                mTextview.setText(mTextview.getText()+ " + ");
                operation = Operation.ADD;
                cnt++;
            }
        });

        mButtonsqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstOperand = Float.parseFloat((String) mTextview.getText());
                mTextview.setText("√"+mTextview.getText());
                operation = Operation.SQRT;
                cnt++;
            }
        });

        mButtoneql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (afterOperand != "" && afterOperand != null) {
                    secondOperand = Float.parseFloat(afterOperand);
                }
                setOperationResult();
                cnt = 2;
                mTextview.setText(mTextview.getText()+" = "+result);
            }
        });

        mButtonpow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstOperand = Float.parseFloat((String) mTextview.getText());
                mTextview.setText(mTextview.getText()+ " ^ ");
                operation = Operation.POWER;
                cnt++;
            }
        });

        mButtonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstOperand = 0;
                secondOperand = 0;
                mTextview.setText("");
                afterOperand = "";
                operation = Operation.RESET;
                cnt = 0;
            }
        });

        mButtondec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextview.setText(mTextview.getText()+".");
            }
        });

        mButtonper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstOperand = Float.parseFloat((String) mTextview.getText());
                mTextview.setText("%"+mTextview.getText());
                operation = Operation.PERCENT;
            }
        });

        /*mButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton0.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton1.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton2.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton3.getText();
                shown = (String)mTextview.getText();
                if (cnt == 1) {
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton4.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton5.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton6.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton7.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton8.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;
                }
                mTextview.setText(shown+text);
            }
        });

        mButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String)mButton9.getText();
                shown = (String)mTextview.getText();
                if(cnt == 1){
                    afterOperand = afterOperand + text;

                }
                mTextview.setText(shown+text);
            }
        });*/


    }

    private void setID() {
        mButton0 =findViewById(R.id.btn_0);
        mButton1 = findViewById(R.id.btn_1);
        mButton2 = findViewById(R.id.btn_2);
        mButton3 = findViewById(R.id.btn_3);
        mButton4 = findViewById(R.id.btn_4);
        mButton5 = findViewById(R.id.btn_5);
        mButton6 = findViewById(R.id.btn_6);
        mButton7 = findViewById(R.id.btn_7);
        mButton8 = findViewById(R.id.btn_8);
        mButton9 = findViewById(R.id.btn_9);
        mButtonadd = findViewById(R.id.btn_add);
        mButtondiv = findViewById(R.id.btn_divide);
        mButtonmul = findViewById(R.id.btn_multiply);
        mButtonmin = findViewById(R.id.btn_minus);
        mButtonclear = findViewById(R.id.btn_clear);
        mButtonper = findViewById(R.id.btn_percent);
        mButtonpow = findViewById(R.id.btn_power);
        mButtonsqrt = findViewById(R.id.btn_root);
        mButtondec = findViewById(R.id.btn_decimal);
        mButtonres = findViewById(R.id.btn_reset);
        mButtoneql = findViewById(R.id.btn_equals);
        mTextview = findViewById(R.id.text_show);
    }
}