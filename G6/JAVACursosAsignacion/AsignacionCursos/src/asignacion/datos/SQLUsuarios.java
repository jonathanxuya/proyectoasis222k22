/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asignacion.datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author jonat
 */
public class SQLUsuarios extends Conexion {
    
    public boolean registrar(usuarios usr)
    {
      
        PreparedStatement ps = null;
        Connection con = getConexion();
       
        
        String sql = "INSERT INTO usuarios(usuario,password,nombre,correo,id_tipo) VALUES(?,?,?,?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getCorreo());
            ps.setInt(5, usr.getId_tipo());
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SQLUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
           
    
}
       public boolean login(usuarios usr) {
           
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        
        String sql = "SELECT u.id, u.usuario, u.password, u.nombre, u.id_tipo, t.nombre FROM usuarios As u INNER JOIN tipo_usuario As t ON u.id_tipo =t.id WHERE usuario = ? ";
       
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,usr.getUsuario());
            rs = ps.executeQuery();
            
            if(rs.next()) {
                if(usr.getPassword().equals(rs.getString(3))) {
                   
                   usr.setId(rs.getInt (1));
                   usr.setNombre(rs.getString(4));
                   usr.setId_tipo(rs.getInt (5));
                   usr.setNombre_tipo(rs.getString(6));
                           
                                 
                   return true;
            }else {
                   return false;
            }
          }
            return false;
        
            
            
            } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        return  false;
        
    }

    }
}
