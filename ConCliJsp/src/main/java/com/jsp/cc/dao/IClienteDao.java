/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsp.cc.dao;

import com.jsp.cc.model.Cliente;
import java.util.List;


public interface IClienteDao {
    
    public List<Cliente> listar();
    
    public Cliente encontrar(Cliente cliente);
    
    public int insertar(Cliente cliente);
    
    
    public int actualizar(Cliente cliente);
    
    
    public int eliminar(Cliente cliente);
    
}
