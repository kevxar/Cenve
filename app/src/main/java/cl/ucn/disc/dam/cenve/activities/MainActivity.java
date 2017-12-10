package cl.ucn.disc.dam.cenve.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;

import cl.ucn.disc.dam.cenve.R;
import lombok.extern.slf4j.Slf4j;

/**
 * Actividad principal: se muestra una lista de registros de vehiculos
 * que se obtienen a traves de la base de datos.
 * @author Kevin Araya Reygada
 */
@Slf4j
public class MainActivity extends ListActivity {

    /**
     * Adapter de {@Link cl.ucn.disc.dam.cenve.model.Registro}
     */
    private BaseAdapter registerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
