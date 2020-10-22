<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control de clientes</title>
    </head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <body>
        <jsp:include page="/WEB-INF/plantilla/navbar.jsp"/>
        <section id="actions" class="py-4 mb-4 bg-light">
            <div class="container">
            </div>   
        </section>
        <div class="container">
            <br>
            <div class="row">
                <div class="col-md-3">
                    <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#agregarClienteModal"> Nuevo</a>
                </div>
            </div>
            <div  class="card">
                <div class="card card-header bg-dark text-white">
                    Clientes
                </div>    
                <div  class="card card-body">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Email</th>
                                <th>Telefono</th>
                                <th>Saldo</th>   
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="obj" items="${clientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${obj.nombre}</td> 
                                    <td>${obj.apellido}</td>
                                    <td>${obj.email}</td>
                                    <td>${obj.telefono}</td>
                                    <td style="text-align: right;"><fmt:formatNumber value="${obj.saldo}" type="currency"/></td>
                                    <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&id=${obj.id}"
                                           class="btn btn-secondary btn-sm">Editar</a>
                                    <a href="${pageContext.request.contextPath}/ServletControlador?accion=elimimnar&id=${obj.id}"
                                           class="btn btn-danger btn-sm">Eliminar</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card card-footer">

                </div> 
            </div
        </div>

    </div>
    <jsp:include page="/clientes/agregar.jsp"/>
    
    <jsp:include page="/WEB-INF/plantilla/footer.jsp"/>  
</body>
</html>
