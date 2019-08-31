package com.devhome.eduardobastos.roouber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

public class GanhosLiquidosActivity extends AppCompatActivity {

    private EditText editKmTextTotal;
    private EditText editTextMotorista;
    private EditText editTextAutonomia;
    private EditText editTextPreco;
    private TextView textViewResult;
    private Button buttonCalc;
    private Button buttonLimpar;


    private LinearLayout linearLayout;

    private double autonomia;
    private double result;
    //private double preco;
    //private double kmTotal;
    //private double combustivelTotalGasto;

    //private double gastoTotalCombustivel = (combustivelTotalGasto * preco);
    //private double ganhosLiquidos = (motorista - gastoTotalCombustivel);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganhos_liquidos);

        editKmTextTotal = findViewById(R.id.editTextKmTotal);
        editTextMotorista = findViewById(R.id.editTextMotorista);
        editTextAutonomia = findViewById(R.id.editTextAutonomia);
        editTextPreco = findViewById(R.id.editTextPreco);
        textViewResult = findViewById(R.id.textViewResult);
        buttonCalc = findViewById(R.id.buttonCalc);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        linearLayout = findViewById(R.id.linearLayout);


        editKmTextTotal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                if (!b) {
                    escondeTeclado(v);
                }
            }
        });


        editTextAutonomia.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View vv, boolean bb) {
                if (!bb) {
                    escondeTeclado(vv);
                }
            }
        });
    }

    public void calculaa(View view) {
// recuperar valores digitados


        String valorKmTotal = editKmTextTotal.getText().toString();
        String autonomiaLitro = editTextAutonomia.getText().toString();
        String valorPreco = editTextPreco.getText().toString();
        String valorMotorista = editTextMotorista.getText().toString();


        //VALIDAR OS CAMPOS DIGITADOS

        Boolean camposValidados = this.validarCampos(valorKmTotal, autonomiaLitro);
        Boolean camposValidados2 = this.validarCampos2(valorPreco, valorMotorista);

        if (camposValidados && camposValidados2) {

            this.Convert(valorKmTotal, autonomiaLitro);
            this.Convert2(valorPreco, valorMotorista);
        } else {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso!");
            dlg.setMessage("Há campos vazios.");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

        escondeTeclado(view);


    }

    public void Convert(String vKmTotal, String vAutonomiaLitro) {



        //CONVERTER VALORES STRING PARA NUMEROS

        Double valorKmTotal = Double.parseDouble(vKmTotal);
        Double valorAutonomia = Double.parseDouble(vAutonomiaLitro);

        double totalLgasto = (valorKmTotal / valorAutonomia);

        result = totalLgasto;

        DecimalFormat df = new DecimalFormat("0.##");


        // exibe a porcentagem da uber na viagem
        textViewResult.setText("O total de litros gasto na viagem foi " + df.format(totalLgasto));

    }

    public void Convert2(String vPreco, String vMotorista){

            //CONVERTER VALORES STRING PARA NUMEROS

            Double valorPrecoTotal = Double.parseDouble(vPreco);
            Double valorMotoristaTotal = Double.parseDouble(vMotorista);


            double totalLgasto$$$ = (valorPrecoTotal * result);
            double resultadoLiq = (valorMotoristaTotal - totalLgasto$$$);

            //totalLgasto = 100 - resultado;

            DecimalFormat df = new DecimalFormat("0.##");


            // exibe a porcentagem da uber na viagem
            textViewResult.setText("O total de litros/m³ gasto na viagem foi " + df.format(result)+"L/M³. O valor total gasto " +
                    "em combustível foi de R$"+df.format(totalLgasto$$$)+   "  . Valor líquido total da viagem foi de R$"+df.format(resultadoLiq)+".");
        }







    //VALIDAR OS CAMPOS DIGITADOS!!!

    public Boolean validarCampos(String vKmTotal, String vAutonomia) {


        Boolean camposValidados = true;

        //Validar Campos

        if (vKmTotal == null || vKmTotal.equals("")) {

            camposValidados = false;

        } else if (vAutonomia == null || vAutonomia.equals("")) {

            camposValidados = false;

        }
        return camposValidados;


    }

    //VALIDAR OS CAMPOS DIGITADOS!!!

    public Boolean validarCampos2(String vPreco, String vMotorista) {


        Boolean camposValidados2 = true;

        //Validar Campos

        if (vPreco == null || vPreco.equals("")) {

            camposValidados2 = false;

        } else if (vMotorista == null || vMotorista.equals("")) {

            camposValidados2 = false;

        }
        return camposValidados2;


    }




    public void escondeTeclado(View v) {

        if (v != null) {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void limpaa (View view ) {

        textViewResult.setText("");
        editKmTextTotal.setText("");
        editTextAutonomia.setText("");
        editTextMotorista.setText("");
        editTextPreco.setText("");

        Toast.makeText(getApplicationContext(), "Campos apagados com sucesso!", Toast.LENGTH_SHORT).show();

        linearLayout.requestFocus(View.KEEP_SCREEN_ON);


    }

    private boolean isCampoVazio(String valor) {

        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return result;
    }

}










