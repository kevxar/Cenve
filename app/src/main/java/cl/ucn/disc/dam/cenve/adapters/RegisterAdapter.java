package cl.ucn.disc.dam.cenve.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dam.cenve.R;
import cl.ucn.disc.dam.cenve.model.Registro;
import lombok.extern.slf4j.Slf4j;


/**
 * Representa el adaptador de los registros
 *
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */

@Slf4j
public class RegisterAdapter extends BaseAdapter{

    /**
     * Listado de registros
     */
    private List<Registro> registros = new ArrayList<>();

    /**
     * Context
     */
    private final Context context;

    /**
     * @param context
     */
    public RegisterAdapter(final Context context) {
        this.context = context;
    }

    /**
     * Cuantos items hay en la lista representados en el adaptador
     *
     * @return Contador de items
     */
    @Override
    public int getCount() {
        return registros.size();
    }

    /**
     * Obtiene la informacion asociada con la posicion especifica de la lista de datos
     * @param posicion Posicion del elemento cuyos datos queremos dentro del adaptador
     * @return La informacion en la posicion especificada.
     */
    @Override
    public Registro getItem(int posicion) {
        return registros.get(posicion);
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

        final Registro registro = this.getItem(position);
        if (registro != null) {

            viewHolder.patente.setText(registro.getVehiculo().getPatente());
            viewHolder.duenyo.setText(registro.getVehiculo().getPersona().getNombre());
            viewHolder.marca.setText(registro.getVehiculo().getMarca());
            viewHolder.modelo.setText(registro.getVehiculo().getModelo());
            viewHolder.anio.setText(registro.getVehiculo().getAnio());
            viewHolder.tipo.setText(registro.getVehiculo().getPersona().getTipo());
        }

        return view;
    }

    /**
     * Agrega un listado de registros al {@link List} de {@link Registro}.
     *
     * @param registros
     * @return RegisterAdapter
     */
    public void addAll(final List<Registro> registros) {

        boolean changed = false;

        // Agrego los registros
        if (registros != null) {

            //log.debug("Adding registros: {}", registros.size());
            changed = this.registros.addAll(registros);
            //log.debug("Added {} registros.", registros.size());
        }

        // Si cambio la coleccion, se refresca.
        if (changed) {

            super.notifyDataSetChanged();
        }
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
