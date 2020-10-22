<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar cliente</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar" method="POST" class="was-validated">
                <div class="modal-body" >
                    <div class="form-group">
                        <label for="nombre">NOMBRE</label>
                        <input  class="form-control" type="text" name="nombre" required>                                               
                    </div>
                    <div class="form-group">
                        <label for="apellido">APELLIDO</label>
                        <input  class="form-control" type="text" name="apellido" required>                                               
                    </div>
                    <div class="form-group">
                        <label for="email">EMAIL</label>
                        <input  class="form-control" type="email" name="email" required>                                               
                    </div>  
                     <div class="form-group">
                        <label for="telefono">TELEFONO</label>
                        <input  class="form-control" type="text" name="telefono" required>                                               
                    </div>    
                    <div class="form-group">
                        <label for="saldo">SALDO</label>
                        <input  class="form-control" type="number" name="saldo" required>                                               
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>
            </form>

        </div>
    </div>
</div>