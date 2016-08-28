package com.cashflow.bean;

import javax.faces.bean.ManagedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.session.CashflowStatelessBeanLocal;

@ManagedBean
@ViewScoped
public class CashflowBean {

	@EJB(lookup = "java:global/cashflow-ejb/CashflowStatelessBean!com.cashflow.ejb.session.CashflowStatelessBeanLocal")
	private CashflowStatelessBeanLocal mainPersistenceManager;
	
	@PostConstruct
	public void init() {
		System.out.println("CashflowBean.init() ::: inicializando el bean xyz!!!");
		
		List<Cuenta> cuentas = mainPersistenceManager.consultarCuentas();
		for (Cuenta cuenta : cuentas) {
			System.out.println("CashflowBean.init() ::: "+cuenta.getCuenNombre());
		}
	}
	
	private String actualPage = "list";

	public String getActualPage() {
		return actualPage;
	}

	public void setActualPage(String actualPage) {
		this.actualPage = actualPage;
	}
}
