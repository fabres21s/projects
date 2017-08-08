package com.cashflow.bean;

import java.util.List;

import com.cashflow.ejb.entity.Concepto;
import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.entity.Detalle;
import com.cashflow.ejb.entity.Movimiento;
import com.cashflow.ejb.entityReport.Reporte;
import com.cashflow.ejb.filter.DetalleFilter;
import com.cashflow.ejb.session.CashflowStatelessBeanLocal;

import sun.applet.Main;

public class AccessDatabase {
	
	public static AccessDatabase instance;
	private CashflowStatelessBeanLocal mainPersistenceManager;

	
	public static AccessDatabase getInstance() {
		if (instance == null) {
			instance = new AccessDatabase();
			
		}
		return instance;
	}

		
	public void setMainpersistence(CashflowStatelessBeanLocal mainPersistenceManager2) {
		this.mainPersistenceManager = mainPersistenceManager2;
	}


	public int countRecords(String table) {
		return mainPersistenceManager.countRecords(table);
	}


	public List<Reporte> consultarSaldos() {
		return mainPersistenceManager.consultarSaldos();
	}


	public long getSaldo(String field, String value) {
		
		return mainPersistenceManager.getSaldo(field, value);
	}


	public List<Concepto> consultarConceptosActivos() {
		return mainPersistenceManager.consultarConceptosActivos();
	}


	public Object findRecord(Object table, Integer id) {
		return mainPersistenceManager.findRecord(table, id);
	}


	public void persist(Object object) {
		mainPersistenceManager.persist(object);
	}


	public List<Detalle> consultarDetalles(DetalleFilter detalleFilter) {
		return mainPersistenceManager.consultarDetalles(detalleFilter);
	}


	public List<Cuenta> consultarCuentas() {
		return mainPersistenceManager.consultarCuentas();
	}
	
}
