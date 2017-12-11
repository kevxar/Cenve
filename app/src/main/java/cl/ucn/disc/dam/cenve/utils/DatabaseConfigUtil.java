package cl.ucn.disc.dam.cenve.utils;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil{
    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt");
    }
}
