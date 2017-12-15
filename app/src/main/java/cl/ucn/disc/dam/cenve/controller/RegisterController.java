package cl.ucn.disc.dam.cenve.controller;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.ucn.disc.dam.cenve.model.DBHelper;
import cl.ucn.disc.dam.cenve.model.Persona;
import cl.ucn.disc.dam.cenve.model.Porteria;
import cl.ucn.disc.dam.cenve.model.Registro;
import cl.ucn.disc.dam.cenve.model.Tipo;
import cl.ucn.disc.dam.cenve.model.Vehiculo;

/**
 * Representa el controlador de los registros
 *
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */

public class RegisterController {

    private DBHelper dbHelper = null;
    private final Context context;

    public RegisterController(Context context) {
        this.context = context;
    }

    //Crearemos los registros dentro de la base de datos con DAO
    public List<Registro> getRegister() throws IOException {
        //TODO hacer codigo para crear los registros de prueba

//        Calendar calendar = Calendar.getInstance();
//        Date fecha =  calendar.getTime();
//
//        Persona persona1 = new Persona("185075958","Kevin Araya","kevxar@gmail.com",84367949,2020,"askdjhas kasjhds", Tipo.APOYO.toString(),"Estudiante");
//        Vehiculo vehiculo1 = new Vehiculo("BTWK-38","Peugeot","Azul","GT",2009,"este es un auto",001,persona1);
//        Registro registro1 = new Registro(Porteria.CENTRAL.toString(),fecha,vehiculo1);
//        try{
//            final Dao<Persona, Integer> personaDao = getHelper().getPersonaDao();
//            final Dao<Vehiculo, Integer> vehiculoDao = getHelper().getVehiculoDao();
//            final Dao<Registro, Integer> registroDao = getHelper().getRegistroDao();
//
//            personaDao.create(persona1);
//            vehiculoDao.create(vehiculo1);
//            registroDao.create(registro1);
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }



        return null;
    }

    // This is how, DatabaseHelper can be initialized for future use
    private DBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(context,DBHelper.class);
        }
        return dbHelper;
    }

}
