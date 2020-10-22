/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsp.cc.dao;

import com.jsp.cc.datos.Conexion;
import com.jsp.cc.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ClienteDaoImpl implements IClienteDao {

    private final String SQL_SELECT = "Select id, nombre, apellido, email, telefono, saldo "
            + "from clientes ";

    private static final String SQL_SELECT_BY_ID = "select c.id, c.nombre, c.apellido, c.email, c.telefono, c.saldo "
            + "from clientes c where c.id = ?";

    private static final String SQL_INSERT = "insert into clientes(nombre, apellido, email , telefono, saldo) "
            + "values(?,?,?,?,?)";

    private static final String SQL_UPDATE = "update c "
            + "set c.nombre = ?, c.apellido = ?, c.email = ?, c.telefono = ?, c. saldo = ? "
            + "from clientes c where c.id = ?";

    private static final String SQL_DELETE = "delete from cliente c where c.id = ?";

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Cliente cliente = null;
            conn = Conexion.getConnecion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                Double saldo = rs.getDouble("saldo");
                cliente = new Cliente(id, nombre, apellido, email, telefono, saldo);
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                Conexion.close(rs);
            }
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }

        }
        return clientes;
    }

    @Override
    public Cliente encontrar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnecion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getId());
            rs = stmt.executeQuery();

            rs.absolute(1); //en dado caso que exista el registro nos posicionamos en el primer registro
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            Double saldo = rs.getDouble("saldo");
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setSaldo(saldo);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                Conexion.close(rs);
            }
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        
        return cliente;

    }

    @Override
    public int insertar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rows=0;
        try {
            conn = Conexion.getConnecion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            
            rows = stmt.executeUpdate();

            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                Conexion.close(rs);
            }
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
       return rows;
     
    }

    @Override
    public int actualizar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rows=0;
        try {
            conn = Conexion.getConnecion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setDouble(6, cliente.getId());
            rows = stmt.executeUpdate();

            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                Conexion.close(rs);
            }
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
       return rows;    
    }

    @Override
    public int eliminar(Cliente cliente) {
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rows=0;
        try {
            conn = Conexion.getConnecion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, cliente.getId());
            rows = stmt.executeUpdate();

            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                Conexion.close(rs);
            }
            if (stmt != null) {
                Conexion.close(stmt);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
       return rows;       
    }
}
