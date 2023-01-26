package com.example.labo1calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView allInputsTxtView;
    private TextView currInputTxtView;
    private Button clearBtn;
    private Button inverseValBtn;
    private Button divBtn;
    private Button multBtn;
    private Button minusBtn;
    private Button plusBtn;
    private Button equalBtn;
    private Button decimalBtn;
    private Button zeroBtn;
    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;
    private Button fourBtn;
    private Button fiveBtn;
    private Button sixBtn;
    private Button sevenBtn;
    private Button eightBtn;
    private Button nineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        allInputsTxtView = findViewById(R.id.all_inputs_txtview);
        currInputTxtView = findViewById(R.id.curr_input_txtview);
        clearBtn = findViewById(R.id.clear_btn);
        clearBtn.setOnClickListener(this);
        inverseValBtn = findViewById(R.id.inverse_val_btn);
        inverseValBtn.setOnClickListener(this);
        divBtn = findViewById(R.id.div_btn);
        divBtn.setOnClickListener(this);
        multBtn = findViewById(R.id.mult_btn);
        multBtn.setOnClickListener(this);
        minusBtn = findViewById(R.id.minus_btn);
        minusBtn.setOnClickListener(this);
        plusBtn = findViewById(R.id.plus_btn);
        plusBtn.setOnClickListener(this);
        equalBtn = findViewById(R.id.equal_btn);
        equalBtn.setOnClickListener(this);
        decimalBtn = findViewById(R.id.decimal_btn);
        decimalBtn.setOnClickListener(this);
        zeroBtn = findViewById(R.id.zero_btn);
        zeroBtn.setOnClickListener(this);
        oneBtn = findViewById(R.id.one_btn);
        oneBtn.setOnClickListener(this);
        twoBtn = findViewById(R.id.two_btn);
        twoBtn.setOnClickListener(this);
        threeBtn = findViewById(R.id.three_btn);
        threeBtn.setOnClickListener(this);
        fourBtn = findViewById(R.id.four_btn);
        fourBtn.setOnClickListener(this);
        fiveBtn = findViewById(R.id.five_btn);
        fiveBtn.setOnClickListener(this);
        sixBtn = findViewById(R.id.six_btn);
        sixBtn.setOnClickListener(this);
        sevenBtn = findViewById(R.id.seven_btn);
        sevenBtn.setOnClickListener(this);
        eightBtn = findViewById(R.id.eight_btn);
        eightBtn.setOnClickListener(this);
        nineBtn = findViewById(R.id.nine_btn);
        nineBtn.setOnClickListener(this);
    }

    private void calculateAllInputs() {
        String op = allInputsTxtView.getText().toString();
        op = op.replace(" ", "");

    }

    private boolean operationIsCalculable() {
        String op = allInputsTxtView.getText().toString();
        if(op.length() > 0){
            return true;
        }
        return false;
    }

    private boolean operationIsValid() {
        String number = currInputTxtView.getText().toString();
        if(number.length() > 0 && number.charAt(number.length()-1) != '.'){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.clear_btn:
                currInputTxtView.setText("");
                allInputsTxtView.setText("");
                break;
            case R.id.zero_btn:
                currInputTxtView.append("0");
                break;
            case R.id.one_btn:
                currInputTxtView.append("1");
                break;
            case R.id.two_btn:
                currInputTxtView.append("2");
                break;
            case R.id.three_btn:
                currInputTxtView.append("3");
                break;
            case R.id.four_btn:
                currInputTxtView.append("4");
                break;
            case R.id.five_btn:
                currInputTxtView.append("5");
                break;
            case R.id.six_btn:
                currInputTxtView.append("6");
                break;
            case R.id.seven_btn:
                currInputTxtView.append("7");
                break;
            case R.id.eight_btn:
                currInputTxtView.append("8");
                break;
            case R.id.nine_btn:
                currInputTxtView.append("9");
                break;
            case R.id.decimal_btn:
                boolean isClickable = true;
                for(int i=0; i < currInputTxtView.length(); i++){
                    if(currInputTxtView.getText().charAt(i) == '.'){
                        isClickable = false;
                    }
                }
                if(isClickable){
                    currInputTxtView.append(".");
                }
                break;
            case R.id.plus_btn:
                if(operationIsValid()){
                    allInputsTxtView.append(currInputTxtView.getText()+" + ");
                    currInputTxtView.setText("");
                }
                break;
            case R.id.minus_btn:
                if(operationIsValid()){
                    allInputsTxtView.append(currInputTxtView.getText()+" â”€ ");
                    currInputTxtView.setText("");
                }
                break;
            case R.id.mult_btn:
                if(operationIsValid()){
                    allInputsTxtView.append(currInputTxtView.getText()+" * ");
                    currInputTxtView.setText("");
                }
                break;
            case R.id.div_btn:
                if(operationIsValid()){
                    allInputsTxtView.append(currInputTxtView.getText()+" / ");
                    currInputTxtView.setText("");
                }
                break;
            case R.id.inverse_val_btn:
                if(currInputTxtView.length() > 0){
                    currInputTxtView.setText("-"+currInputTxtView.getText());
                }
                break;
            case R.id.equal_btn:
                String number = currInputTxtView.getText().toString();
                if(number.length() > 0 && number.charAt(number.length()-1) != '.') {
                    allInputsTxtView.append(number+" ");
                    if(operationIsCalculable()){
                        calculateAllInputs();
                    }
                }
                break;
            default:
                System.out.println("default");
        }
    }
}
