package cl.ucn.disc.dam.cenve.tasks;


import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import android.content.Context;
import cl.ucn.disc.dam.cenve.controller.RegisterController;
import cl.ucn.disc.dam.cenve.model.Registro;

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
    Context context;

    public GetRegisterTask(TaskListener taskListener, Context context) {
        this.taskListener = taskListener;
        this.context = context;
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

        return null;

    }

    /**
     * @return the {@link List} of {@link Registro}.
     */
    private List<Registro> getRegistros() {

        // FIXME: Sera atributo de la clase?
        final RegisterController registerController = new RegisterController(context);

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
