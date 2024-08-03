package com.example.demo.service;

import java.util.List;


import com.example.demo.service.to.VehiculoDTO_TO;
import com.example.demo.service.to.VehiculoTO;

public interface IVehiculoService {

	public int guardar(VehiculoTO vehiculoTO);

	public List<VehiculoDTO_TO> consultarTodoDTO();

	public VehiculoTO consultarPorPlaca(String placa);

	public int actualizarParcial(VehiculoTO vehiculoTO);

	public int eliminar(String placa);
}
