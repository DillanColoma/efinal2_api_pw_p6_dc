package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.dto.VehiculoDTO;
import com.example.demo.repository.IVehiculoRepo;
import com.example.demo.service.to.VehiculoDTO_TO;
import com.example.demo.service.to.VehiculoTO;

@Service
public class VehServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepo iVehiculoRepo;

	public Vehiculo convertirAProducto(VehiculoTO vehiculoTO) {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(vehiculoTO.getPlaca());
		vehiculo.setMarca(vehiculoTO.getMarca());
		vehiculo.setModelo(vehiculoTO.getModelo());
		vehiculo.setColor(vehiculoTO.getColor());
		vehiculo.setPrecio(vehiculoTO.getPrecio());
		return vehiculo;
	}

	public VehiculoDTO_TO convertirADTO_TO(VehiculoDTO vehiculoDTO) {
		VehiculoDTO_TO vehiculoDTO_TO = new VehiculoDTO_TO();
		vehiculoDTO_TO.setPlaca(vehiculoDTO.getPlaca());
		vehiculoDTO_TO.setMarca(vehiculoDTO.getMarca());
		vehiculoDTO_TO.setModelo(vehiculoDTO.getModelo());
		return vehiculoDTO_TO;
	}

	public VehiculoTO convertirATO(Vehiculo vehiculo) {
		VehiculoTO vehiculoTO = new VehiculoTO();
		vehiculoTO.setPlaca(vehiculo.getPlaca());
		vehiculoTO.setMarca(vehiculo.getMarca());
		vehiculoTO.setModelo(vehiculo.getModelo());
		vehiculoTO.setColor(vehiculo.getColor());
		vehiculoTO.setPrecio(vehiculo.getPrecio());
		return vehiculoTO;
	}

	@Override
	public int guardar(VehiculoTO vehiculoTO) {
		return this.iVehiculoRepo.guardar(this.convertirAProducto(vehiculoTO));
	}

	@Override
	public List<VehiculoDTO_TO> consultarTodoDTO() {
		List<VehiculoDTO> vh = this.iVehiculoRepo.consultarTodoDTO();
		List<VehiculoDTO_TO> listaConvertirDtoATo = new ArrayList<>();
		for (VehiculoDTO vehiculoDTO : vh) {
			listaConvertirDtoATo.add(this.convertirADTO_TO(vehiculoDTO));
		}
		return listaConvertirDtoATo;
	}

	@Override
	public VehiculoTO consultarPorPlaca(String placa) {
		return this.convertirATO(this.iVehiculoRepo.consultarPorPlaca(placa));
	}

	@Override
	public int actualizarParcial(VehiculoTO vehiculoTO) {
		Vehiculo vh = this.iVehiculoRepo.consultarPorPlaca(vehiculoTO.getPlaca());
		vh.setMarca(vehiculoTO.getMarca());
		vh.setModelo(vehiculoTO.getModelo());
		vh.setColor(vehiculoTO.getColor());
		vh.setPrecio(vehiculoTO.getPrecio());
		return this.iVehiculoRepo.actualizarParcial(vh);
	}

	@Override
	public int eliminar(String placa) {
		return this.iVehiculoRepo.eliminar(placa);
	}
}