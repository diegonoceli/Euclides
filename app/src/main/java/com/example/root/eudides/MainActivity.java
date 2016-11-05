package com.example.root.eudides;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    private Button btncalc, btnvoltar;
    private EditText edtnu1, edtnu2;
    private ListView listView;
    private ArrayList<String> mdcs;
    private ArrayAdapter arrayAdapter;
    private int a, b, r, q1, q2, total, auxa, auxb;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncalc = (Button) findViewById(R.id.btncalcular);
        edtnu1 = (EditText) findViewById(R.id.editText);
        edtnu2 = (EditText) findViewById(R.id.editText2);

        mdcs = new ArrayList<String>();
        mdcs.clear();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mdcs);


        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ("".equals(edtnu1.getText().toString()) || "".equals(edtnu2.getText().toString())) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                    dlg.setNeutralButton("ok", null);
                    dlg.setMessage("há caixas de texto em branco");
                    dlg.show();
                }else {
                    a = Integer.parseInt(String.valueOf(edtnu1.getText()));
                    b = Integer.parseInt(edtnu2.getText().toString());

                    if (b == 0) {
                        arrayAdapter.add("mdc(" + a + "," + b + ")=" + a);

                        goAfterlogin();
                        return ;
                    }


                    if (b > a) {
                        int aux;
                        aux = b;
                        b = a;
                        a = aux;
                    }


                    auxa = a;
                    auxb = b;
                    int i = 0;

                    int r = 1;
                    while (r > 0) {
                        if (i == 0) {
                            q1 = a / b;
                        } else if (i == 1) {
                            q2 = a / b;
                        }
                        r = Euclides();
                        i = i + 1;

                    }


                    arrayAdapter.add("mdc(" + a + "," + b + ")=" + a);

                    goAfterlogin();
                }

            }
        }



    );


}

    private void VoltaMenu() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goAfterlogin() {

        setContentView(R.layout.activity__mdc);
        listView = (ListView) findViewById(R.id.listview);
        btnvoltar = (Button) findViewById(R.id.btnvoltar);
        textView = (TextView) findViewById(R.id.txtresposta2);
        listView.setAdapter(arrayAdapter);
        btnvoltar.setEnabled(true);
        textView.setText("R:" + String.valueOf(a) + "=" + String.valueOf(auxa) + ".(-" + String.valueOf(q2) + ")+" + String.valueOf(auxb) + "(" + String.valueOf(total) + ")");
        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VoltaMenu();
            }
        });

    }

    public int Euclides() {


        arrayAdapter.add("mdc(" + a + "," + b + ")");
        r = a % b;
        a = b;
        b = r;
        total = 1 + q1 * q2;



        return r;

    }


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
