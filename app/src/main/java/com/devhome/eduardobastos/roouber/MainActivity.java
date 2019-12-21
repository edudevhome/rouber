package com.devhome.eduardobastos.roouber;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.DecimalFormat;
//Classes ADS AdMob
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;



public class MainActivity extends AppCompatActivity {

    private EditText editTextTotal;
    private EditText editTextMotorista;
    private TextView textViewResult;
    private Button buttonCalc;
    private Button buttonLimpar;
    private FloatingActionButton floatingActionButtonGliq;
    private LinearLayout linearLayout;
    private double total;
    private double motorista;
    private double porcUber;
    private double resultado = (motorista / total) * 100;
    private double valorUber;
    //Banner/Interstitial
    private AdView adView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "@string/IdBannerMain");

        //Configurando Interstitial_1

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3253976680799709/2294894573");
        AdRequest adRequestInter = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequestInter);


        //Configurando Banner1

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        //Configurando objetos de interação na tela
        linearLayout = findViewById(R.id.linearLayout);
        editTextTotal = findViewById(R.id.editTextTotal);
        editTextMotorista = findViewById(R.id.editTextMotorista);
        textViewResult = findViewById(R.id.textViewResult);
        buttonCalc = findViewById(R.id.buttonCalc);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        floatingActionButtonGliq = findViewById(R.id.floatingActionButtonGliq);


        //Implementando Ad Interstitial ao evento de click do FAB

        floatingActionButtonGliq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                Intent intent = new Intent(getApplicationContext(), GanhosLiquidosActivity.class);

                startActivity(intent);
            }
        });

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

    //Executa operações aritméticas
    public void calcula(View view) {

        // recuperar valores digitados
        String valorTotal = editTextTotal.getText().toString();
        String valorMotorista = editTextMotorista.getText().toString();


        //VALIDA CAMPOS DIGITADOS para posterior conversão de tipos

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

    public void Convert(String vTotal, String vMotorista) {

        //CONVERTENDO STRINGS EM VALORES DOUBLES

        Double valorTotal = Double.parseDouble(vTotal);
        Double valorMotorista = Double.parseDouble(vMotorista);

        double valorUber = (valorTotal - valorMotorista);
        double resultado = (valorMotorista / valorTotal) * 100;
        double gBruto = (valorMotorista + valorUber);

        porcUber = 100 - resultado;

        DecimalFormat df = new DecimalFormat("0.##");


        // EXIBINDO as informaçoes na tela
        textViewResult.setText(
                ">> Porcentagem do aplicativo: " + df.format(porcUber) + "%" + "\n" +
                        ">> Lucro do aplicativo: R$" + df.format(valorUber) + ".\n");
        //">> Ganhos brutos: R$"+gBruto)
    }


    //VALIDAR OS CAMPOS DIGITADOS(não permite campos vazios)!!!

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

    //Limpa todos os campos
    public void limpa(View view) {

        textViewResult.setText("");
        editTextMotorista.setText("");
        editTextTotal.setText("");

        linearLayout.requestFocus(View.KEEP_SCREEN_ON);
        // editTextMotorista.setOnClickListener((View.OnClickListener)this);

        Toast.makeText(getApplicationContext(), "Campos apagados com sucesso!",
                Toast.LENGTH_LONG).show();

    }

    private boolean isCampoVazio(String valor) {

        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return result;
    }

}