package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private String firstNum = "";
    private String operator = "";
    private String secondNum = "";
    private String result = "";
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result);

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);

        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);

        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);

        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_reciprocal).setOnClickListener(this);

        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String inputText;
        if (view.getId() == R.id.btn_sqrt) {
            inputText = "√";
        } else {
            inputText = ((TextView) view).getText().toString();
        }

        if (view.getId() == R.id.btn_clear) {
            clear();

        } else if (view.getId() == R.id.btn_cancel) {

        }

        //加减乘除
        else if (view.getId() == R.id.btn_plus) {//加
            operator = inputText;
            refreshText(showText + operator);
        } else if (view.getId() == R.id.btn_minus) {//减
            operator = inputText;
            refreshText(showText + operator);
        } else if (view.getId() == R.id.btn_multiply) {//乘
            operator = inputText;
            refreshText(showText + operator);
        } else if (view.getId() == R.id.btn_divide) {//除
            operator = inputText;
            refreshText(showText + operator);
        }

        //等于
        else if (view.getId() == R.id.btn_equal) {//等于
            double calculate_result = calculateFour();
            refreshOperate(String.valueOf(calculate_result));
            refreshText(showText + "=" + result);
//            if (!result.isEmpty() && operator.isEmpty()){
//                clear();
//            }
        }

        else if (view.getId() == R.id.btn_sqrt) {//开平方
            double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
            refreshOperate(String.valueOf(sqrt_result));
            refreshText(showText + "√=" + result);
        }

        else if (view.getId() == R.id.btn_reciprocal) {//倒数
            double reciprocal_result =1.0/Double.parseDouble(firstNum);
            refreshOperate(String.valueOf(reciprocal_result));
            refreshText(showText + "/=" + result);
        }

        else {//点击其他按钮 包括数字和小数点
            if (operator.equals("")) {
                //无运算符，则继续拼接第一个操作数
                firstNum = firstNum + inputText;
            } else {
                //有运算符，则拼接第二个操作数
                secondNum = secondNum + inputText;
            }
            //整数不需要拼接0
            if (showText.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                refreshText(showText + inputText);
            }
        }
    }

    //四则运算
    private double calculateFour() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "x":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            case "/":
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
        return 0;
    }

    //清空文本框
    private void clear() {
        refreshOperate("");
        refreshText("");
    }

    //刷新运算结果
    private void refreshOperate(String new_result) {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    //刷新文本框
    private void refreshText(String text) {
        showText = text;
        tv_result.setText(showText);
    }
}