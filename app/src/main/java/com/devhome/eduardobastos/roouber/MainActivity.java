package com.devhome.eduardobastos.roouber;


import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//import android.widget.Toast;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.MobileAds;
import androidx.appcompat.app.AlertDialog;
//import android.content.Intent;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTotal;
    private EditText editTextMotorista;
    private TextView textViewResult;
    private Button buttonCalc;
    private Button buttonLimpar;

    private LinearLayout linearLayout;

    private double total;
    private double motorista;
    private double porcUber;
    private double resultado = (motorista / total) * 100;
    private double valorUber;


    //private AdView adView;
    // private Button buttonIntersticial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearLayout = findViewById(R.id.linearLayout);
        editTextTotal = findViewById(R.id.editTextTotal);
        editTextMotorista = findViewById(R.id.editTextMotorista);
        textViewResult = findViewById(R.id.textViewResult);
        buttonCalc = findViewById(R.id.buttonCalc);
        buttonLimpar = findViewById(R.id.buttonLimpar);


        //adView = findViewById(R.id.adView);


        //AdView adView = new AdView(this);
        //adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3253976680799709/1609747993");


        //AdRequest adRequest = new  AdRequest.Builder().build();
        //adView.loadAd(adRequest);


        // VERIFICA SE OS CAMPOS ESTAO VAZIOS


        //Ocultar teclado

        editTextTotal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                if (!b) {
                    escondeTeclado(v);
                }
            }
        });


        editTextMotorista.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View vv, boolean bb) {
                if (!bb) {
                    escondeTeclado(vv);
                }
            }
        });

    }


    public void calcula(View view) {
// recuperar valores digitados


        //double total = Double.parseDouble(editTextTotal.getText().toString());
        //double motorista = Double.parseDouble(editTextMotorista.getText().toString());

        String valorTotal = editTextTotal.getText().toString();
        String valorMotorista = editTextMotorista.getText().toString();


        //VALIDAR OS CAMPOS DIGITADOS

        Boolean camposValidados = this.validarCampos(valorTotal, valorMotorista);

        if (camposValidados) {

            this.Convert(valorTotal, valorMotorista);
        } else {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso!");
            dlg.setMessage("Há campos vazios.");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

        escondeTeclado(view);


    }

    public void Convert (String vTotal, String vMotorista) {

        //CONVERTER VALORES STRING PARA NUMEROS

        Double valorTotal = Double.parseDouble(vTotal);
        Double valorMotorista = Double.parseDouble(vMotorista);

        double valorUber = valorTotal - valorMotorista;
        double resultado = (valorMotorista / valorTotal) * 100;

        porcUber = 100 - resultado;

        DecimalFormat df = new DecimalFormat("0.##");


        


        // exibe a porcentagem da uber na viagem
        textViewResult.setText("A uber estuprou do motorista R$" + df.format(valorUber) + ", ou seja, " + df.format(porcUber) + "% do total que o usuário pagou.");

    }
    

        //VALIDAR OS CAMPOS DIGITADOS!!!

    public Boolean validarCampos(String vTotal, String vMotorista) {


        Boolean camposValidados = true;

        //Validar Campos

        if (vTotal == null || vTotal.equals("")) {

            camposValidados = false;

        } else if (vMotorista == null || vMotorista.equals("")) {

            camposValidados = false;

        }
        return camposValidados;


    }






    public void escondeTeclado(View v) {

        if (v != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void limpa (View view ) {

        textViewResult.setText("");
        editTextMotorista.setText("");
        editTextTotal.setText("");

        linearLayout.requestFocus(View.KEEP_SCREEN_ON);
       // editTextMotorista.setOnClickListener((View.OnClickListener)this);




    }

    private boolean isCampoVazio(String valor) {

        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return result;
    }

}
