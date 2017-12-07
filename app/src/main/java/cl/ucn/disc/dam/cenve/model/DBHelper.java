package cl.ucn.disc.dam.cenve.model;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "cenve.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Persona, Integer> personaDao;
    private Dao<Registro, Integer> registroDao;
    private Dao<Vehiculo, Integer> vehiculoDao;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Persona.class);
            TableUtils.createTable(connectionSource, Registro.class);
            TableUtils.createTable(connectionSource, Vehiculo.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
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
