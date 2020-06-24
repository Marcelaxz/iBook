package br.mack.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    String url = "jdbc:mysql://192.168.99.100:32777/Heart";
    String usuario = "root";
    String senha = "root";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection(url, usuario, senha);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
