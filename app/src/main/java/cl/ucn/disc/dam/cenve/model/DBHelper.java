package cl.ucn.disc.dam.cenve.model;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import cl.ucn.disc.dam.cenve.R;

/**
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "cenve.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Persona, Integer> personaDao;
    private Dao<Registro, Integer> registroDao;
    private Dao<Vehiculo, Integer> vehiculoDao;
//    private RuntimeExceptionDao<Persona, Integer> personaIntegerRuntimeExceptionDao = null;
//    private RuntimeExceptionDao<Registro, Integer> registroIntegerRuntimeExceptionDao = null;
//    private RuntimeExceptionDao<Vehiculo, Integer> vehiculoIntegerRuntimeExceptionDao = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, Persona.class);
            TableUtils.createTable(connectionSource, Registro.class);
            TableUtils.createTable(connectionSource, Vehiculo.class);

        } catch (SQLException e) {
            Log.e(DBHelper.class.getName(), "No se pudieron crear las tablas");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{

            TableUtils.dropTable(connectionSource, Persona.class, true);
            TableUtils.dropTable(connectionSource, Registro.class, true);
            TableUtils.dropTable(connectionSource, Vehiculo.class, true);
            onCreate(sqLiteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DBHelper.class.getName(), "Unable to upgrade database from version " + oldVersion + " to new " + newVersion, e);
        }
    }

    public Dao<Persona, Integer> getPersonaDao() throws SQLException {
        if (personaDao == null) {
            personaDao = getDao(Persona.class);
        }
        return personaDao;
    }

    public Dao<Registro, Integer> getRegistroDao() throws SQLException {
        if (registroDao == null) {
            registroDao = getDao(Registro.class);
        }
        return registroDao;
    }

    public Dao<Vehiculo, Integer> getVehiculoDao() throws SQLException {
        if (vehiculoDao == null) {
            vehiculoDao = getDao(Vehiculo.class);
        }
        return vehiculoDao;
    }

    @Override
    public void close() {
        super.close();
        personaDao = null;
        registroDao = null;
        vehiculoDao = null;
    }

}
