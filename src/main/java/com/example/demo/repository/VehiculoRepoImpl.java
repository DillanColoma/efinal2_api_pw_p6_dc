package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modelo.Vehiculo;
import com.example.demo.modelo.dto.VehiculoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
@Transactional
public class VehiculoRepoImpl implements IVehiculoRepo {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public int guardar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		try {
			this.entityManager.persist(vehiculo);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public List<VehiculoDTO> consultarTodoDTO() {
		// TODO Auto-generated method stub
		Query query = this.entityManager.createQuery(
				"SELECT NEW com.example.demo.modelo.dto.VehiculoDTO(v.placa, v.marca,v.modelo) FROM Vehiculo v");
		return query.getResultList();
	}


	@Override
	public Vehiculo consultarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		Query query = this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.placa = :placa");
		query.setParameter("placa", placa);
		return  (Vehiculo) query.getSingleResult();
	}

	@Override
	public int actualizarParcial(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		try {
			this.entityManager.merge(vehiculo);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public int eliminar(String placa) {
		// TODO Auto-generated method stub
		try {
			Vehiculo vehiculo = this.consultarPorPlaca(placa);
			this.entityManager.remove(vehiculo);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}




}
