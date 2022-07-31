/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asignacion.datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author jonat
 */
public class SQLUsuarios extends  conexionSQL {
    
    public boolean registrar(usuarios usr)
    {
      
        PreparedStatement ps = null;
        conexionSQL cc=new conexionSQL();
        Connection con=cc.Conexion();
        
        String sql = "INSERT INTO usuarios(usuario,pass,cod,nombre,id_tipo) VALUES(?,?,?,?,?)";
        
        try{
        ps = con.prepareStatement(sql);
        ps.setString(1, usr.getUsuario());
        ps.setString(2, usr.getPass());
        ps.setString(3, usr.getCod());
        ps.setString(4, usr.getNombre());
        ps.setString(5, usr.getId_tipo());
        ps.execute();
        return true;
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        return false;
        }
    
    }
    
     public boolean login(usuarios usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        conexionSQL cc=new conexionSQL();
        Connection con=cc.Conexion();
        
        String sql = "SELECT u.id, u.usuario, u.pass , u.nombre , u.id_tipo  t.nombre FROM ususarios As u INNER JOIN tipo_ususario As t on u.id_tipo = t.id WHERE ususario = ? ";
       
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,usr.getUsuario());
            rs = ps.executeQuery();
            
            if(rs.next()) {
                if(usr.getPass().equals(rs.getString(3))) {
                   
                   usr.setId(rs.getInt (1));
                   usr.setNombre(rs.getString(4));
                   usr.setId_tipo(rs.getString (5));
                   usr.setNombre_tipo(rs.getString(6));
                                 
                   return true;
            }else {
                   return false;
            }
          }
            return false;
        
            
            
            } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        return  false;
        
    }

    }
}


    
