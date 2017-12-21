package cl.ucn.disc.dam.cenve.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import cl.ucn.disc.dam.cenve.R;
import cl.ucn.disc.dam.cenve.adapters.VehicleAdapter;
import cl.ucn.disc.dam.cenve.model.DBHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * Actividad principal: se muestra una lista de registros de vehiculos
 * que se obtienen a traves de la base de datos.
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */
@Slf4j
public class MainActivity extends ListActivity{

    /**
     * Adapter de {@Link cl.ucn.disc.dam.cenve.model.Registro}
     */
    private BaseAdapter baseAdapter;
//    private GetRegisterTask getRegisterTask;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        // Mostrar barrita
        final ActionBar actionBar = super.getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("CENVE");
            actionBar.setSubtitle("Control de entrada vehicular");

            actionBar.show();
        }

        // Row division
        int[] colors = {0, 0xFF000000 , 0};
        this.getListView().setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        this.getListView().setDividerHeight(1);

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

        this.baseAdapter = new VehicleAdapter( this);
        super.setListAdapter(this.baseAdapter);

        ListView listView = super.getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = (MainActivity.this).getLayoutInflater();

                //Creo la vista
                View alertView = inflater.inflate(R.layout.register_car, null);
                alertDialogBuilder.setView(alertView);
                alertDialogBuilder.setCancelable(false);
                Button ingreso = (Button) view.findViewById(R.id.rc_ingreso);
                Button salida = (Button) alertView.findViewById(R.id.rc_volver);

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                salida.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });




                // show it
                alertDialog.show();

            }
        });



//        // Si no hay registros en el adaptador (por ende igual en base de datos)
//        if (this.baseAdapter.isEmpty()) {
//            // Ejecuto la tarea para obtenerlas.
//            this.runGetRegisterTask();
//        }

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
//    private void runGetRegisterTask() {
//
//        // Inicio la tarea
//        log.debug("Starting GetRegisterTask ..");
//        this.getRegisterTask = new GetRegisterTask(this,this);
//        this.getRegisterTask.execute();
//
//    }
}
