package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IVehiculoService;
import com.example.demo.service.to.VehiculoDTO_TO;
import com.example.demo.service.to.VehiculoTO;

@CrossOrigin
@RestController
@RequestMapping(path = "/vehiculos")
public class Vehiculo1ControllerRestful {

	@Autowired
	private IVehiculoService iVehiculoService;

	// Insertar
	// http://localhost:8080/API/v1.0/Concesionario/vehiculos POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> guardar(@RequestBody VehiculoTO vehiculoTO) {
		 int resultado = iVehiculoService.guardar(vehiculoTO);
		    return ResponseEntity.ok(resultado);
	}

	// CONSULTAR TODOS DTO
	// http://localhost:8080/API/v1.0/Concesionario/vehiculos GET
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehiculoDTO_TO>> consultarTodosDTO() {
		var lista = this.iVehiculoService.consultarTodoDTO();
		for (VehiculoDTO_TO vh : lista) {
			Link link = linkTo(
					methodOn(Vehiculo1ControllerRestful.class).consultarPorPlaca(vh.getPlaca()))
					.withRel("enlaces");
			vh.add(link);
		}
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	// CONSULTAR POR Placa
		// http://localhost:8080/API/v1.0/Concesionario/vehiculos/{placa} GET
		@GetMapping(path = "/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<VehiculoTO> consultarPorPlaca(@PathVariable String placa) {
		    VehiculoTO vehiculo = iVehiculoService.consultarPorPlaca(placa);
		    return ResponseEntity.ok(vehiculo);

		}
	
		// ACTUALIZAR PARCIAL
		// http://localhost:8080/API/v1.0/Concesionario/vehiculos/{placa} PATCH
		@PatchMapping(path = "/{placa}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Integer> actualizarParcial(@PathVariable String placa,
				@RequestBody VehiculoTO vehiculoTO) {
			vehiculoTO.setPlaca(placa);
			var vh = this.iVehiculoService.actualizarParcial(vehiculoTO);
			return ResponseEntity.status(HttpStatus.OK).body(vh);
		}

		// ELIMINAR
		// http://localhost:8080/API/v1.0/Concesionario/vehiculos/{placa} DELETE
		@DeleteMapping(path = "/{placa}")
		public ResponseEntity<Integer> eliminar(@PathVariable String placa) {
			var p = this.iVehiculoService.eliminar(placa);
			return (ResponseEntity<Integer>) ResponseEntity.status(HttpStatus.OK).body(p);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	



}
