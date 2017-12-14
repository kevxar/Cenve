package cl.ucn.disc.dam.cenve.tasks;


import android.os.AsyncTask;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import cl.ucn.disc.dam.cenve.activities.MainActivity;

import cl.ucn.disc.dam.cenve.adapters.RegisterAdapter;
import cl.ucn.disc.dam.cenve.controller.RegisterController;
import cl.ucn.disc.dam.cenve.model.DBHelper;
import cl.ucn.disc.dam.cenve.model.Registro;
import lombok.NonNull;

/**
 * Representa la tarea de obtener los registros
 * de forma asincronica
 *
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */

public class GetRegisterTask  extends AsyncTask<Void, Void, Integer> {

    /**
     *Listener de las tareas a terminar
     */
    private TaskListener taskListener;

    private DBHelper helper;
    private Dao dao;



    public GetRegisterTask(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param voids The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Integer doInBackground(Void... voids) {


        final List<Registro> registros = getRegistros();

        // guardando
        if (registros != null && registros.size() != 0) {

            //final ModelAdapter<Registro> modelAdapter = FlowManager.getModelAdapter(Registro.class);

            // Contador de nuevas noticias
            int saved = 0;
            for (final Registro registro : registros) {

                // Si el registro ya existe, no es necesario almacenar nada.
                //if (modelAdapter.exists(registro)) {
                //    continue;
                //}

                // Inserto en la base de datos y cuento 1 mas.
                //modelAdapter.insert(article);
                saved++;

            }
            return saved;

        }

        return null;

    }

    /**
     * @return the {@link List} of {@link Registro}.
     */
    private List<Registro> getRegistros() {

        //dao = helper.getRegistroDao();

        // FIXME: Sera atributo de la clase?
        final RegisterController registerController = new RegisterController();

        try {
            // Obtengo los registros desde base de datos via controlador
            return registerController.getRegister();
        } catch (IOException e) {
            Log.e("Error", String.valueOf(e));
          return null;
        }
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param integer The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Integer integer) {

        if (taskListener != null) {
            taskListener.taskFinished(integer);
        }

    }

    /**
     *
     */
    public interface TaskListener {

        /**
         * Aviso que se termino la obtencion de los {@link Registro}.
         */
        void taskFinished(final Integer registros);

    }
}
