package com.example.demo.repository;

import java.util.List;

import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.dto.VehiculoDTO;


public interface IVehiculoRepo {

	public int guardar(Vehiculo vehiculo);

	public List<VehiculoDTO> consultarTodoDTO();

	public Vehiculo consultarPorPlaca(String placa);

	public int actualizarParcial(Vehiculo vehiculo);

	public int eliminar(String placa);
}
