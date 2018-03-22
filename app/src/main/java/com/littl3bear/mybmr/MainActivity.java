package com.littl3bear.mybmr;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Character sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView show = (TextView) findViewById(R.id.tvShow);
        final EditText high = (EditText) findViewById(R.id.etHigh);
        final EditText weight = (EditText) findViewById(R.id.etWeight);
        final EditText age = (EditText) findViewById(R.id.etAge);

        findViewById(R.id.btSubmit).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String strH = high.getText().toString();
                String strW = weight.getText().toString();
                String strA = age.getText().toString();

                if (TextUtils.isEmpty(strH)) {
                    high.setError("Please enter your weight");
                    high.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(strW)) {
                    weight.setError("Please enter your height");
                    weight.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(strA)) {
                    age.setError("Please enter your age");
                    age.requestFocus();
                    return;
                }

                double dbW = Float.parseFloat(strW);
                double dbH = Float.parseFloat(strH);
                double dbA = Float.parseFloat(strA);
                double bmr = calculation(dbW, dbH, dbA);
                show.setText(String.valueOf(bmr));
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbMale:
                if (checked)
                    sex = 'm';
                break;
            case R.id.rbFemale:
                if (checked)
                    sex = 'f';
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        /*switch(view.getId()) {
            case R.id.checkBox:
                if (checked)
                    System.out.println("Put some meat on the sandwich");
                else
                    System.out.println("Remove the meat");
                break;
            case R.id.checkBox2:
                if (checked)
                    System.out.println("Cheese me");
                else
                    System.out.println("I'm lactose intolerant");
                break;
        }*/
    }

    private double calculation(double w, double h, double a) {
        if (sex == 'm') {
            return 66 + (13.7 * w) + (5 * h) - (6.8 * a);
        }
        else {
            return 665 + (9.6 * w) + (1.8 * h) - (4.7 * a);
        }
    }
}