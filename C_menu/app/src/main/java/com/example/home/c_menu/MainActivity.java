package com.example.home.c_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private List<Double> value_list = new ArrayList<Double>();// 存用户输入的数字
    private List<Integer> operator_list = new ArrayList<Integer>();
    // 存用户输入的运算符，定义+为0，-为1，×为2，÷为3
    // 状态记录
    private boolean add_flag = false;// +按下
    private boolean minus_flag = false;// -按下
    private boolean multi_flag = false;// ×按下
    private boolean div_flag = false;// ÷按下
    private boolean result_flag = false;// =按下
    private boolean can_operate_flag = false;// 按下=是否响应

    private TextView textView1;
    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_0).setOnClickListener(this);
        findViewById(R.id.bt_1).setOnClickListener(this);
        findViewById(R.id.bt_2).setOnClickListener(this);
        findViewById(R.id.bt_3).setOnClickListener(this);
        findViewById(R.id.bt_4).setOnClickListener(this);
        findViewById(R.id.bt_5).setOnClickListener(this);
        findViewById(R.id.bt_6).setOnClickListener(this);
        findViewById(R.id.bt_7).setOnClickListener(this);
        findViewById(R.id.bt_8).setOnClickListener(this);
        findViewById(R.id.bt_9).setOnClickListener(this);
        findViewById(R.id.bt_point).setOnClickListener(this);
        findViewById(R.id.bt_clear).setOnClickListener(this);
        findViewById(R.id.bt_plus).setOnClickListener(this);
        findViewById(R.id.bt_minus).setOnClickListener(this);
        findViewById(R.id.bt_multi).setOnClickListener(this);
        findViewById(R.id.bt_div).setOnClickListener(this);
        findViewById(R.id.bt_result).setOnClickListener(this);
        textView1 = (TextView) findViewById(R.id.output_window);
        editText1 = (EditText) findViewById(R.id.answer_window);

    }

    @Override
    public void onClick(View v) {
        String str1=textView1.getText().toString();
        String str2=editText1.getText().toString();
        switch (v.getId()) {
            case R.id.bt_0:
                num_down("0");
                break;
            case R.id.bt_1:
                num_down("1");
                break;
            case R.id.bt_2:
                num_down("2");
                break;
            case R.id.bt_3:
                num_down("3");
                break;
            case R.id.bt_4:
                num_down("4");

                break;
            case R.id.bt_5:
                num_down("5");
                break;
            case R.id.bt_6:
                num_down("6");
                break;
            case R.id.bt_7:
                num_down("7");
                break;
            case R.id.bt_8:
                num_down("8");
                break;
            case R.id.bt_9:
                num_down("9");
                break;
            case R.id.bt_point:
                num_down(".");
                break;
            case R.id.bt_plus:
                if (!add_flag)// 防止用户多次输入一个符号键，符号键只允许输入一次
                {
                    result_flag = false;
                    value_list.add(Double.parseDouble(editText1.getText()
                            .toString()));// 将当前已输入的数字放入value_list
                    operator_list.add(0);
                    textView1.setText(textView1.getText() + "+");
                    add_flag = true;
                    can_operate_flag = false;// 刚刚输入完符号，不能构成一条正常的表达式，如111+，设置为不可运行状态
                }
                break;
            case R.id.bt_minus:
                if (!minus_flag) {
                    result_flag = false;
                    value_list.add(Double.parseDouble(editText1.getText()
                            .toString()));
                    operator_list.add(1);
                    textView1.setText(textView1.getText() + "-");
                    minus_flag = true;
                    can_operate_flag = false;
                }
                break;
            case R.id.bt_multi:
                if (!multi_flag) {
                    result_flag = false;
                    value_list.add(Double.parseDouble(editText1.getText()
                            .toString()));
                    operator_list.add(2);
                    textView1.setText("(" + textView1.getText() + ")×");// 给前面的已经输入的东西加个括号。（运算符栈问题是一个很复杂的数据结构问题，这里不做，：P）
                    multi_flag = true;
                    can_operate_flag = false;
                }
                break;
            case R.id.bt_div:
                if (!div_flag) {
                    result_flag = false;
                    value_list.add(Double.parseDouble(editText1.getText()
                            .toString()));
                    operator_list.add(3);
                    textView1.setText("(" + textView1.getText() + ")÷");
                    div_flag = true;
                    can_operate_flag = false;
                }
                break;
            case R.id.bt_result:
                if (value_list.size() > 0 && operator_list.size() > 0
                        && can_operate_flag) {// 需要防止用户没输入数字，或者只输入了一个数，就按=。
                    value_list.add(Double.parseDouble(editText1.getText()
                            .toString()));
                    double total = value_list.get(0);
                    for (int i = 0; i < operator_list.size(); i++) {
                        int _operator = operator_list.get(i);// operator是C#的运算符重载的关键字，前面加个_来区别
                        switch (_operator) {
                            case 0:
                                total += value_list.get(i + 1);
                                break;
                            case 1:
                                total -= value_list.get(i + 1);
                                break;
                            case 2:
                                total *= value_list.get(i + 1);
                                break;
                            case 3:
                                total /= value_list.get(i + 1);
                                break;
                        }
                    }
                    editText1.setText(total + "");
                    textView1.setText(total + "");
                    operator_list.clear();// 算完，就清空累积数字与运算数组
                    value_list.clear();
                    result_flag = true;// 表示=按下
                }
                break;
            case R.id.bt_del:


              /* if(str1!=null&&!str1.equals("")){
                    textView1.setText(str1.substring(0,value_list.size()-1));
                    editText1.setText(str2.substring(0,value_list.size()-1));

                }*/
                break;
            case R.id.bt_clear:
                operator_list.clear();
                value_list.clear();
                add_flag = false;
                minus_flag = false;
                multi_flag = false;
                div_flag = false;
                result_flag = false;
                can_operate_flag = false;
                editText1.setText("");
                textView1.setText("");
                break;
        }
    }

    // 数字键按下
    private void num_down(String num) {
        if (add_flag || minus_flag || multi_flag || div_flag || result_flag) {
            if (result_flag)// 按下等号，刚刚算完一个运算的状态
            {
                textView1.setText("");
            }
            editText1.setText("");// 如果用户刚刚输入完一个运算符
            add_flag = false;
            minus_flag = false;
            multi_flag = false;
            div_flag = false;
            result_flag = false;
        }
        if ((num.equals(".") && editText1.getText().toString().indexOf(".") < 0)
                || !num.equals(".")) {
            // 如果用户输入的是小数点.，则要判断当前已输入的数字中是否含有小数点.才允许输入
            editText1.setText(editText1.getText() + num);
            textView1.setText(textView1.getText() + num);
            can_operate_flag = true;
        }
    }

}
