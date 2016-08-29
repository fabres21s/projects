package com.cashflow.bean;

import java.util.List;

import com.cashflow.ejb.entity.Cuenta;
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

		
	public List<Cuenta> consultarCuentas(Paginator paginator) {
		return mainPersistenceManager.consultarCuentas(paginator.getFirstRow(), paginator.getSizePage());
	}


	public void setMainpersistence(CashflowStatelessBeanLocal mainPersistenceManager2) {
		this.mainPersistenceManager = mainPersistenceManager2;
	}


	public int countRecords(String table) {
		return mainPersistenceManager.countRecords(table);
	}
	
}
