package cl.ucn.disc.dam.cenve.activities;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import cl.ucn.disc.dam.cenve.R;
import cl.ucn.disc.dam.cenve.model.DBHelper;
import cl.ucn.disc.dam.cenve.model.Persona;

import static android.content.ContentValues.TAG;
import lombok.extern.slf4j.Slf4j;

/**
 * Actividad principal: se muestra una lista de registros de vehiculos
 * que se obtienen a traves de la base de datos.
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */
@Slf4j
public class MainActivity extends ListActivity {

    /**
     * Adapter de {@Link cl.ucn.disc.dam.cenve.model.Registro}
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
