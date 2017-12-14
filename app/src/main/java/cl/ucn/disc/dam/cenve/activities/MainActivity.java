package cl.ucn.disc.dam.cenve.activities;

import android.app.ActionBar;
import android.app.ListActivity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import cl.ucn.disc.dam.cenve.adapters.RegisterAdapter;
import cl.ucn.disc.dam.cenve.model.Registro;
import cl.ucn.disc.dam.cenve.tasks.GetRegisterTask;
import lombok.extern.slf4j.Slf4j;

/**
 * Actividad principal: se muestra una lista de registros de vehiculos
 * que se obtienen a traves de la base de datos.
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */
@Slf4j
public class MainActivity extends ListActivity implements GetRegisterTask.TaskListener{

    /**
     * Adapter de {@Link cl.ucn.disc.dam.cenve.model.Registro}
     */
    private BaseAdapter baseAdapter;
    private GetRegisterTask getRegisterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mostrar barrita
        final ActionBar actionBar = super.getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("CENVE");
            actionBar.setSubtitle("Control de entrada vehicular");

            actionBar.show();
        }

        // Row division
        int[] colors = {0, 0xFFFF0000, 0};
        this.getListView().setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        this.getListView().setDividerHeight(5);


        this.baseAdapter = new RegisterAdapter(this);
        super.setListAdapter(this.baseAdapter);

        // Si no hay registros en el adaptador (por ende igual en base de datos)
        if (this.baseAdapter.isEmpty()) {
            // Ejecuto la tarea para obtenerlas.
            this.runGetRegisterTask();
        }

    }

    private void runGetRegisterTask() {

        // Si ya hay una tarea de obtencion de registros corriendo no ejecuto una nueva
        if (this.getRegisterTask != null) {
            Toast.makeText(this, "Actualizando la obtencion de patentes", Toast.LENGTH_SHORT).show();
            return;
        }

        // Inicio la tarea
        log.debug("Starting GetRegisterTask ..");
        this.getRegisterTask = new GetRegisterTask(this);
        this.getRegisterTask.execute();

    }

    @Override
    public void taskFinished(Integer registros) {
        // Mostrar mensaje
        Toast.makeText(this, "Nuevas patentes: " + registros, Toast.LENGTH_LONG).show();

        log.debug("Listo");
        this.baseAdapter.notifyDataSetChanged();
    }
}
