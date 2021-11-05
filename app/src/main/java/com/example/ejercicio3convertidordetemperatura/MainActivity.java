package com.example.ejercicio3convertidordetemperatura;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_cajaGrados, et_cajaFahrenheit, et_cajaDefinicion;
    Button bt_convertir, bt_limpiar;
    TextView tv_celsius, tv_fahrenheit, tv_titulo;

    int grados, fah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vistas();
    }


    /**
     * Iniciamos las vistas
     */
    private void vistas() {
        et_cajaGrados = findViewById(R.id.etGrados);
        et_cajaFahrenheit = findViewById(R.id.etFahrenheit);
        et_cajaDefinicion = findViewById(R.id.etTexto);
        bt_convertir = findViewById(R.id.btConvertir);
        bt_limpiar = findViewById(R.id.btLimpiar);
        tv_celsius = findViewById(R.id.tvGrados);
        tv_fahrenheit = findViewById(R.id.tvFahrenheit);
        tv_titulo = findViewById(R.id.tvTitulo);

        tv_celsius.setOnClickListener(this);
        tv_fahrenheit.setOnClickListener(this);
        bt_convertir.setOnClickListener(this);
        bt_limpiar.setOnClickListener(this);
    }


    /**
     * Metodo del boton convertir que pasa los grados a fahrenheit y viceversa,
     * dependiendo de cual casilla este vacia
     */
    public void convertirGrados() {
        if (et_cajaFahrenheit.getText().toString().isEmpty()) {
            grados = Integer.parseInt(et_cajaGrados.getText().toString());
            fah = (grados * 9 / 5) + 32;
            et_cajaFahrenheit.setText(String.valueOf(fah));
            tv_fahrenheit.setTextColor(Color.RED);
        }
        if (et_cajaGrados.getText().toString().isEmpty()) {
            fah = Integer.parseInt(et_cajaFahrenheit.getText().toString());
            grados = (fah - 32) * 5 / 9;
            et_cajaGrados.setText(String.valueOf(grados));
            tv_celsius.setTextColor(Color.RED);
        }
    }

    /**
     * Metodo del boton limpiar que borra lo que haya dentro de las cajas de texto
     */
    public void limpiar() {
        et_cajaGrados.getText().clear();
        tv_celsius.setTextColor(Color.GRAY);
        et_cajaFahrenheit.getText().clear();
        tv_fahrenheit.setTextColor(Color.GRAY);
    }

    public void abrirSegundaVentana(View view) {
        Intent segundaVentana = new Intent(this, VentanaDefinicion.class);

        if (view.getId() == R.id.tvGrados)
            segundaVentana.putExtra("unidad", "Centigrados");
        else
            segundaVentana.putExtra("unidad", "Fahrenheit");

        startActivity(segundaVentana);
    }

    //modificar el ejercicio para cuando se convierta de C a F la F salga en rojo y a la inversa
    public void cambiarDefinicion(View view) {
        if (view.getId() == R.id.tvGrados) {
            tv_titulo.setText(R.string.centigrados);
            et_cajaDefinicion.setText(R.string.definicionCentigrados);
        } else {
            tv_titulo.setText(R.string.fahrenheit);
            et_cajaDefinicion.setText(R.string.definicionFahrenheit);
        }
    }

    /**
     * override del metodo onClick donde el
     *
     * @param view sera el icono que se pulse y dependiendo de cual sea,
     *             mandara al usuario a otra ventana con la definicion del mismo
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (tv_titulo == null) {
            switch (view.getId()) {
                case R.id.btConvertir:
                    convertirGrados();
                    break;
                case R.id.btLimpiar:
                    limpiar();
                    break;
                case R.id.tvGrados:
                case R.id.tvFahrenheit:
                    abrirSegundaVentana(view);
                    break;

                default:
                    throw new IllegalStateException("Error en: " + view.getId());
            }
        } else {
            switch (view.getId()) {
                case R.id.btConvertir:
                    convertirGrados();
                    break;
                case R.id.btLimpiar:
                    limpiar();
                    break;
                case R.id.tvGrados:
                case R.id.tvFahrenheit:
                    cambiarDefinicion(view);
                    break;
                default:
                    throw new IllegalStateException("Error en: " + view.getId());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Override
    protected void onPause() {
        super.onPause();
        //captura de pantalla
    /*    View v = getWindow().getDecorView().getRootView();
        v.setDrawingCacheEnabled(true);
        Bitmap bm = getWindow().getDecorView().getDrawingCache();
        v.setDrawingCacheEnabled(false);
*/
    }
}