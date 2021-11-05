package com.example.ejercicio3convertidordetemperatura;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VentanaDefinicion extends AppCompatActivity {

    EditText cajaDefinicion;
    TextView titulo;
    String palabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_definicion);

        palabra = getIntent().getStringExtra("unidad");
        cajaDefinicion = findViewById(R.id.etTexto);
        titulo = findViewById(R.id.tvTitulo);

        mostrarDefinicion();


    }

    /**
     * Hacemos que el titulo y el texto mostrado cambie
     * en funcion del value, que nos envie el putExtra del MainActivity
     */
    public void mostrarDefinicion() {
        if (palabra.equals("Centigrados")) {
            titulo.setText(R.string.centigrados);
            cajaDefinicion.setText(R.string.definicionCentigrados);
        } else {
            titulo.setText(R.string.fahrenheit);
            cajaDefinicion.setText(R.string.definicionFahrenheit);
        }
    }
}