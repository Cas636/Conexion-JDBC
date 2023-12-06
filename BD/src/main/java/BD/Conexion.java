package BD;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conn = null;
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user ="postgres";
    String pass = "root";
    
    public void conectar(){
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,user,pass);
            //JOptionPane.showMessageDialog(null, "conectado a la BD"); 
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "error al conectar "+ e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cerrar(){
        try{
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error al desconectar "+ e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public String Select() {
        String texto = "";
        try {
            Statement ejecutor = conn.createStatement();
            ResultSet respuesta = ejecutor.executeQuery("Select * from paciente");

            while (respuesta.next()) {
                texto = texto + respuesta.getString("cod_paciente") + " | " + respuesta.getString("documentopaciente") + " | " + respuesta.getString("nombres")+ "\n";
            }
        } catch (Exception e) {

        }
        return texto;

    }
    
    public String Selectto(String qr) {
        
        String texto = "";
        try {
            Statement ejecutor = conn.createStatement();
            ResultSet respuesta = ejecutor.executeQuery(qr);

            while (respuesta.next()) {
                texto = texto + respuesta.getString("cod_paciente") +" | " + respuesta.getString("nombres")+ " | " + respuesta.getString("documentopaciente")+ " | " + respuesta.getString("nombre_farmacia")+" | " + respuesta.getString("direccion_farmacia")+" | " + respuesta.getString("telefono_farmacia")+" | " + respuesta.getString("horario_farmacia")+ "\n";
            }
        } catch (Exception e) {

        }
        return texto;

    }
    
    
    public String Selectfromquery(String Query,String columna) {
        String texto = "";
        try {
            Statement ejecutor = conn.createStatement();
            ResultSet respuesta = ejecutor.executeQuery(Query);
            while (respuesta.next()) {
               texto = respuesta.getString(columna);
               
            }
        } catch (Exception e) {

        }
        return texto;

    }
}