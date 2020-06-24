package br.mack.persistencia;

import br.mack.entidade.HeartRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class HeartRateDAOMySQL implements HeartRateDAO{
    private final MySQLConnection mysql = new MySQLConnection();
    String createSQL = "INSERT INTO HeartRate VALUES (?, ?)";
    private String updateSQL = "UPDATE HeartRate SET bpm=?, day=? WHERE id=?";
    String deleteSQL = "DELETE FROM HeartRate WHERE id=?";
    private String readSQL = "SELECT * FROM HeartRate";

    public HeartRateDAOMySQL(){

    }

    // C       R       E       A       T
    @Override
    public boolean create(HeartRate heartRate) {
        Connection conexao = mysql.getConnection();

        try {
            PreparedStatement stm = conexao.prepareStatement(createSQL);

            stm.setInt(1, heartRate.getId());
            stm.setInt(2, heartRate.getBpm());
            stm.setDate(3, new java.sql.Date(heartRate.getDay().getTime()));


            int registros = stm.executeUpdate();

            return  registros > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    // D       E       L       E       T       E
    @Override
    public boolean delete(int opc) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(deleteSQL);

            stm.setInt(1, opc);

            int registros = stm.executeUpdate();

            return registros > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }


    // U        P       D       A       T       E
    @Override
    public boolean update(HeartRate heartRate) {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(updateSQL);

            stm.setInt(1, heartRate.getBpm());
            stm.setDate(2, new java.sql.Date(heartRate.getDay().getTime()));

            int registros = stm.executeUpdate();

            return  registros > 0 ? true : false;

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }


    // R        E       A       D
    @Override
    public void read() {
        Connection conexao = mysql.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(readSQL);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int bpm = rs.getInt("bpm");
                Date day = rs.getDate("day");
                System.out.println("\nId: " + id + "\nBPM: " + bpm + "\nData: " + day);
            }
            rs.close();
            conexao.close();

        } catch (final SQLException ex) {
            System.out.println("Falha de conexão com a base de dados!");
            ex.printStackTrace();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public MySQLConnection getAllHearts() {
        return this.mysql;
    }
}
