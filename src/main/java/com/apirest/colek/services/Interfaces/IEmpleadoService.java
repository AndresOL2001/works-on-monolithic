package com.apirest.colek.services.Interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.apirest.colek.entity.Empleado;

public interface IEmpleadoService {

    public CompletableFuture<List<Empleado>> obtenerEmpleados();
    public CompletableFuture<Empleado> buscarEmpleadoPorId(Long id);
    public boolean eliminarEmpleado(Long id);
    public CompletableFuture<Empleado> crearEmpleado(Empleado estado);
    public CompletableFuture<List<Empleado>> crearEmpleados(ArrayList<Empleado> clientes);

}
