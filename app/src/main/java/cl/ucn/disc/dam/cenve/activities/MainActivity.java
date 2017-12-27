package cl.ucn.disc.dam.cenve.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import cl.ucn.disc.dam.cenve.R;
import cl.ucn.disc.dam.cenve.adapters.VehicleAdapter;
import cl.ucn.disc.dam.cenve.model.DBHelper;
import cl.ucn.disc.dam.cenve.model.Porteria;
import cl.ucn.disc.dam.cenve.model.Registro;
import cl.ucn.disc.dam.cenve.model.Vehiculo;
import lombok.extern.slf4j.Slf4j;

/**
 * Actividad principal: se muestra una lista de registros de vehiculos
 * que se obtienen a traves de la base de datos.
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */
@Slf4j
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    /**
     * Adapter de {@Link cl.ucn.disc.dam.cenve.model.Registro}
     */
    private BaseAdapter baseAdapter;
//    private GetRegisterTask getRegisterTask;
    private DBHelper dbHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);

        listView = (ListView) findViewById(R.id.listView);
        this.baseAdapter = new VehicleAdapter( this);
        listView.setAdapter(baseAdapter);
        listView.setOnItemClickListener(this);


        // Mostrar barrita
        final ActionBar actionBar = super.getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("CENVE");
            actionBar.setSubtitle("Control de entrada vehicular");

            actionBar.show();
        }

        // Row division
        int[] colors = {0, 0xFF000000 , 0};
        listView.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        listView.setDividerHeight(1);

//        //PRUEBAPRUEBAPRUEBA
//        //Crearemos los registros dentro de la base de datos con DAO
//
//            Calendar calendar = Calendar.getInstance();
//            Date fecha =  calendar.getTime();
//
//            Persona persona1 = new Persona("185075958","Kevin Araya","kevxar@gmail.com",84367949,2020,"askdjhas kasjhds", "APOYO","Estudiante");
//            Vehiculo vehiculo1 = new Vehiculo("BTWK-38","Peugeot","Azul","GT","2009","este es un auto","001",persona1);
//            Registro registro1 = new Registro(Porteria.CENTRAL.toString(),fecha,vehiculo1);
//            try{
//                final Dao<Persona, String> personaDao = getHelper().getPersonaDao();
//                personaDao.create(persona1);
//                final Dao<Vehiculo, String> vehiculoDao = getHelper().getVehiculoDao();
//                vehiculoDao.create(vehiculo1);
//                final Dao<Registro, String> registroDao = getHelper().getRegistroDao();
//                registroDao.create(registro1);
//
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        //PRUEBAPRUEBAPRUEBA







    }

    // This is how, DatabaseHelper can be initialized for future use
    private DBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(this,DBHelper.class);
        }
        return dbHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper= null;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View alertView, int i, long l) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        // Get the layout inflater
        LayoutInflater inflater = (MainActivity.this).getLayoutInflater();
        //Creo la vista
        alertView = inflater.inflate(R.layout.register_car, null);
        alertDialogBuilder.setView(alertView);
        alertDialogBuilder.setCancelable(false);

        Button ingreso = alertView.findViewById(R.id.rc_ingreso);
        Button salida = alertView.findViewById(R.id.rc_volver);

        TextView patente = alertView.findViewById(R.id.rc_patente);
        TextView modelo = alertView.findViewById(R.id.rc_modelo);
        TextView marca = alertView.findViewById(R.id.rc_marca);
        TextView duenio = alertView.findViewById(R.id.rc_duenio);
        TextView color = alertView.findViewById(R.id.rc_color);
        TextView anio = alertView.findViewById(R.id.rc_anio);
        TextView rut = alertView.findViewById(R.id.rc_rut);
        TextView nombre = alertView.findViewById(R.id.rc_nombreduenio);
        TextView correo = alertView.findViewById(R.id.rc_correo);
        TextView telefono = alertView.findViewById(R.id.rc_telefono);
        TextView anexo = alertView.findViewById(R.id.rc_anexo);
        TextView localizacion = alertView.findViewById(R.id.rc_localizacion);
        TextView tipoDuenio = alertView.findViewById(R.id.rc_tipoduenio);

        Vehiculo auto = (Vehiculo) baseAdapter.getItem(i);
        patente.setText(auto.getPatente());
        modelo.setText(auto.getModelo());
        marca.setText(auto.getMarca());
        duenio.setText(auto.getPersona().getNombre());
        color.setText(auto.getColor());
        anio.setText(auto.getAnio());
        rut.setText(auto.getPersona().getRut());
        nombre.setText(auto.getPersona().getNombre());
        correo.setText(auto.getPersona().getCorreo());
        telefono.setText(String.valueOf(auto.getPersona().getTelefono()));
        anexo.setText(String.valueOf(auto.getPersona().getNumeroAnexo()));
        localizacion.setText(auto.getPersona().getLocalizacion());
        tipoDuenio.setText(auto.getPersona().getTipo());


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();



        salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hacer un registro en la Base de Datos
                try{
                    Dao<Registro, String> registroDao;
                    registroDao = getHelper().getRegistroDao();
                    QueryBuilder<Registro, String> registroQb = registroDao.queryBuilder();

                    Calendar calendar = Calendar.getInstance();
                    Date fecha =  calendar.getTime();

                    //Crear Registro a ingresar
                    Registro reg = new Registro(Porteria.NORTE.toString(), fecha, auto);

                    //Crear registro en la Base de Datos
                    registroDao.create(reg);
                    registroQb.setCountOf(true);

                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Registros en BD: " +registroDao.countOf(registroQb.where()
                                            .eq(Registro.PORTERIA, Porteria.NORTE.toString())
                                            .and()
                                            .eq(Registro.VEHICULO, auto)
                                            .prepare())
                                    , Toast.LENGTH_SHORT);
                    toast1.show();

                }catch(SQLException e){
                    e.printStackTrace();
                }


            }
        });


        // show it
        alertDialog.show();
    }
//    private void runGetRegisterTask() {
//
//        // Inicio la tarea
//        log.debug("Starting GetRegisterTask ..");
//        this.getRegisterTask = new GetRegisterTask(this,this);
//        this.getRegisterTask.execute();
//
//    }
}
