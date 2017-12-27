package cl.ucn.disc.dam.cenve.adapters;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.ucn.disc.dam.cenve.R;
import cl.ucn.disc.dam.cenve.model.DBHelper;
import cl.ucn.disc.dam.cenve.model.Persona;
import cl.ucn.disc.dam.cenve.model.Porteria;
import cl.ucn.disc.dam.cenve.model.Registro;
import cl.ucn.disc.dam.cenve.model.Tipo;
import cl.ucn.disc.dam.cenve.model.Vehiculo;
import lombok.extern.slf4j.Slf4j;


/**
 * Representa el adaptador de los registros
 *
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */

@Slf4j
public class VehicleAdapter extends BaseAdapter {

    /**
     * Listado de vehiculos
     */
    private List<Vehiculo> listaVehiculos = new ArrayList<>();


    /**
     * Context
     */
    Context context;
    private DBHelper dbHelper = null;
    private Dao<Persona, String> personaDao;
    private Dao<Vehiculo, String> vehiculoDao;
    private Dao<Registro, String> registroDao;


    public VehicleAdapter(Context context) {
        this.context = context;
        try{
            this.personaDao = getHelper().getPersonaDao();
            this.vehiculoDao = getHelper().getVehiculoDao();
            this.registroDao = getHelper().getRegistroDao();

            QueryBuilder<Persona, String> personaQb = personaDao.queryBuilder();
            QueryBuilder<Vehiculo, String> vehiculoQb = vehiculoDao.queryBuilder();
            QueryBuilder<Registro, String> registroQb = registroDao.queryBuilder();

            // join with the order query
            //listaVehiculos = registroQb.query();

            //PRUEBA
            Calendar calendar = Calendar.getInstance();
            Date fecha =  calendar.getTime();
            Persona persona1 = new Persona("18311347k","Jean Cortes","jeancortes.t@gmail.com",57634738,1010,"cualquier lugar", Tipo.EXTERNO.toString(),"Estudiante1");
            Persona persona2 = new Persona("185075958","Kevin Araya","kevxar@gmail.com",84367949,2020,"askdjhas kasjhds", "APOYO","Estudiante");
            Vehiculo vehiculo1 = new Vehiculo("BTWK-38","Peugeot","Azul","GT","2009","este es un auto","001",persona1);
            Vehiculo vehiculo2 = new Vehiculo("BKJJ-32","Tucson","Blanco","Algo","2010","este es un auto","002",persona2);
            Vehiculo vehiculo3 = new Vehiculo("BKLE-54","Nose","Amarillo","caca","2410","este es un auto","003",persona1);
            Registro registro1 = new Registro(Porteria.CENTRAL.toString(),fecha,vehiculo1);
            Registro registro2 = new Registro(Porteria.CENTRAL.toString(),fecha,vehiculo2);

            //si no existe registros en la base de datos hay que llenarla
            if (vehiculoDao.queryForAll().size() == 0){
                vehiculoDao.create(vehiculo1);
                vehiculoDao.create(vehiculo2);
                vehiculoDao.create(vehiculo3);

                personaDao.create(persona1);
                personaDao.create(persona2);

                registroDao.create(registro1);
            }

            //listaVehiculos.add(vehiculo1);
            //listaVehiculos.add(vehiculo2);


            //listaVehiculos = vehiculoDao.queryForAll();

//            List<Vehiculo> arrayListVehiculo = vehiculoDao.query(vehiculoQb.join(personaQb).prepare());
            listaVehiculos = vehiculoDao.query(vehiculoQb.join(personaQb).prepare());

//            for (int i = 0; i < arrayListVehiculo.size(); i++) {
//                listaVehiculos.add(arrayListVehiculo.get(i));
//            }
            //PRUEBA

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private DBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(context, DBHelper.class);
        }
        return dbHelper;
    }


    /**
     * Cuantos items hay en la lista representados en el adaptador
     *
     * @return Contador de items
     */
    @Override
    public int getCount() {
        return listaVehiculos.size();
    }

    /**
     * Obtiene la informacion asociada con la posicion especifica de la lista de datos
     * @param posicion Posicion del elemento cuyos datos queremos dentro del adaptador
     * @return La informacion en la posicion especificada.
     */
    @Override
    public Vehiculo getItem(int posicion) {
        return listaVehiculos.get(posicion);

    }

    /**
     * Obtiene la id de la fila asociada con la posicion especificada en la lista.
     *
     * @param posicion La posicion del elemento dentro del conjunto de datos del adaptador del id de la fila que se quiere
     * @return El id del item de la posicion especificada.
     */
    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        final View view;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_register, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Vehiculo vehiculo = this.getItem(position);
        if (vehiculo != null) {

            viewHolder.patente.setText(vehiculo.getPatente());
            viewHolder.duenyo.setText(vehiculo.getPersona().getNombre());
            viewHolder.marca.setText(vehiculo.getMarca());
            viewHolder.modelo.setText(vehiculo.getModelo());
            viewHolder.anio.setText(vehiculo.getAnio());
            viewHolder.tipo.setText(vehiculo.getPersona().getTipo());

            Typeface font = Typeface.createFromAsset(this.context.getAssets(), "font/cargo2.ttf");
            viewHolder.patente.setTypeface(font);

        }


        return view;
    }

    private static class ViewHolder {

        TextView patente;
        TextView duenyo;
        TextView marca;
        TextView modelo;
        TextView anio;
        TextView tipo;


        ViewHolder(final View view) {
            this.patente = view.findViewById(R.id.ra_patente);
            this.duenyo = view.findViewById(R.id.ra_duenyo);
            this.marca = view.findViewById(R.id.ra_marca);
            this.modelo = view.findViewById(R.id.ra_modelo);
            this.anio = view.findViewById(R.id.ra_anio);
            this.tipo = view.findViewById(R.id.ra_tipo);
        }
    }
}
