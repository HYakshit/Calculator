package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
//imported
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView ans,sol;
    MaterialButton c, openbracket, closebracket, divide;
    MaterialButton multiply, minus, plus, equalto, dot, ac;
    MaterialButton zero, one, two, three, four, five, six, seven, eight, nine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ans = findViewById(R.id.idans);
        sol = findViewById(R.id.solution);

        assign(c, R.id.del);

        assign(openbracket, R.id.op);
        assign(closebracket, R.id.cl);
        assign(divide, R.id.iddivide);
        assign(seven, R.id.idseven);
        assign(eight, R.id.ideight);
        assign(nine, R.id.idnine);
        assign(four, R.id.idfour);
        assign(five, R.id.idfive);
        assign(six, R.id.idsix);
        assign(one, R.id.idone);
        assign(two, R.id.idtwo);
        assign(three, R.id.idthree);
        assign(ac, R.id.idac);
        assign(zero, R.id.idzero);
        assign(dot, R.id.iddot);
        assign(multiply, R.id.idstar);
        assign(minus, R.id.idminus);
        assign(plus, R.id.idplus);
        assign(equalto, R.id.idequals);
    }

    void assign(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
String dellast(String s){
    s=s.substring(0,s.length()-1);
    return s;

}

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        // Assigning input to local variable

        String inputdata = sol.getText().toString();
        //getting text of button
        String buttontext = button.getText().toString();
        if(inputdata.length()>=1){
      String ch= String.valueOf(inputdata.charAt(inputdata.length()-1));
        if(ch.equals("+") || ch.equals("*") ||ch.equals("/") ||ch.equals("-")||ch.equals(".")){
            if ( buttontext.equals("+") || buttontext.equals("*") || buttontext.equals("/") || buttontext.equals("-")|| buttontext.equals(".")){
                notAllowed();
                return;
            }
            }

        }

        // shows toast and empty string if operator is clicked before number if input is empty.
        if(inputdata.length()==0 &&  ( buttontext.equals("+") || buttontext.equals("*") || buttontext.equals("/") || buttontext.equals("-")|| buttontext.equals("."))){
            notAllowed();
            return;
        }
            if (buttontext.equals("AC")) {
                sol.setText("");
                ans.setText("");
                return;
            }

            if (buttontext.equals("=")) {
                //to keep the ans if ans is empty
                if (!(ans.getText().equals(""))) {
                    sol.setText((ans.getText()));
                }


                ans.setText("");
                return;
            }
            if (buttontext.equals("del")) {
                //to remove array out of bond exception
                if (inputdata.length() > 0) {
                    inputdata = dellast(inputdata);
                    ans.setText("");
                }
            }
            else {
                inputdata = inputdata + buttontext;
            }
            sol.setText(inputdata);
            //wishes me
            if (sol.getText().equals("112002")) {
                Toast.makeText(this, "Akshit's Birthday", Toast.LENGTH_SHORT).show();
            }
            String finalresult = getResult(inputdata);
            //assign value if calculation is error free
            if (!finalresult.equals("Err")) {
                ans.setText(finalresult);
            }

    }

    String getResult(String data) {
        try {
           Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
            if (finalresult.endsWith(".0")) {
                finalresult = finalresult.replace(".0","");
            }

           return finalresult;
        } catch(Exception e){
            return "Err";
        }
    }
    void notAllowed(){
        Toast.makeText(this, "Not Allowed", Toast.LENGTH_SHORT).show();
    }
}