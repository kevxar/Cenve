package cl.ucn.disc.dam.cenve.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cl.ucn.disc.dam.cenve.R;
import cl.ucn.disc.dam.cenve.model.Registro;

public class RegisterCarActivity extends Activity {

    //Inicializando los textviews
    TextView patente;
    TextView duenyo;
    TextView marca;
    TextView modelo;
    TextView anio;
    TextView tipo;
    TextView color;
    TextView rut;
    TextView nombre;
    TextView correo;
    TextView telefono;
    TextView numeroAnexo;
    TextView localizacion;
    TextView tipoDuenio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_car);

        this.patente = (TextView) findViewById(R.id.ra_patente);
        this.duenyo = (TextView) findViewById(R.id.ra_duenyo);
        this.marca = (TextView) findViewById(R.id.ra_marca);


        Button regresar = findViewById(R.id.ra_volver);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
