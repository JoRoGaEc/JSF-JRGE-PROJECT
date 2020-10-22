package com.jsp.cc.web;

import com.jsp.cc.dao.ClienteDaoImpl;
import com.jsp.cc.dao.IClienteDao;
import com.jsp.cc.model.Cliente;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Inject
    private IClienteDao clienteDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    editarCliente(request, resp);
                    break;
                default:
                    this.accionDefault(request, resp);
                    break;
            }
        } else {
            this.accionDefault(request, resp);
        }

    }
    
    
    private void editarCliente(HttpServletRequest  request, HttpServletResponse resp) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("id"));
        Cliente cliente   = clienteDao.encontrar(new Cliente(idCliente));
        request.setAttribute("cliente",cliente);
        String jspEditar  = "clientes/editar.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    insertarCliente(request, resp);
                    break;
                default:
                    this.accionDefault(request, resp);
                    break;
            }
        } else {
            this.accionDefault(request, resp);
        }

    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        //clienteDao = new ClienteDaoImpl();
        List<Cliente> clientes = clienteDao.listar();

        System.out.println(clientes);
        HttpSession session = request.getSession();
        session.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        session.setAttribute("clientes", clientes);        
        //request.getRequestDispatcher("/clientes/clientes.jsp").forward(request, resp);
       resp.sendRedirect("clientes/clientes.jsp");
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        
        String nombre =  request.getParameter("nombre");
        String apellido  = request.getParameter("apellido");
        String email  = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo  =0;
        String saldoString  = request.getParameter("saldo");
        if(saldoString!= null && !"".equals(saldoString)){
            saldo =  Double.parseDouble(saldoString);
        }
        
        /*Crear el modelo*/
        Cliente cliente =  new Cliente(nombre, apellido, email ,telefono, saldo);
        //insertarlo a la BD
        int registrosModificados = clienteDao.insertar(cliente);
        System.out.println("Se han modificado " + registrosModificados + " registros");
        this.accionDefault(request, resp);

    }

    private double calcularSaldoTotal(List<Cliente> clientes) {

        Double total = clientes.stream()
                .filter(c -> c != null
                && c.getSaldo() != null).mapToDouble(Cliente::getSaldo)
                .sum();

        return total;

    }

}
