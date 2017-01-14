package com.cashflow.bean;

import javax.faces.bean.ManagedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.entityReport.Reporte;
import com.cashflow.ejb.paginator.Paginator;
import com.cashflow.ejb.paginator.PaginatorInterface;
import com.cashflow.ejb.session.CashflowStatelessBeanLocal;

@ManagedBean
@ViewScoped
public class CashflowBean implements PaginatorInterface{


	@EJB(lookup = "java:global/cashflow-ejb/CashflowStatelessBean!com.cashflow.ejb.session.CashflowStatelessBeanLocal")
	private CashflowStatelessBeanLocal mainPersistenceManager;
	
	private AccessDatabase accessDatabase;

	private List<Cuenta> cuentas;
	private List<Reporte> saldos;
	private Places places;
	
	private String actualPage = "dashboard.xhtml";
	private Paginator paginator;
	
	@PostConstruct
	public void init() {
		accessDatabase = AccessDatabase.getInstance();
		accessDatabase.setMainpersistence(mainPersistenceManager);
		
		places = Places.getInstance();
		
		paginator = new Paginator(7,"id",true, this);
		paginator.setTotalRecords(accessDatabase.countRecords("Cuenta"));
		updatetable();
		
	}
	
	public void test(Cuenta cuenta) {
		//System.out.println("CashflowBean.test() :: "+cuenta.getCuenNombre());
	}
	
	public void goTo(String page) {
		actualPage = Places.getInstance().get(page);
	}
	
	public void updatetable() {
		//setCuentas(accessDatabase.consultarCuentas(paginator));
	}
	

	public String getActualPage() {
		return actualPage;
	}

	public void setActualPage(String actualPage) {
		this.actualPage = actualPage;
	}



	public List<Cuenta> getCuentas() {
		return cuentas;
	}



	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}



	public Paginator getPaginator() {
		return paginator;
	}



	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public List<Reporte> getSaldos() {
		return saldos;
	}

	public void setSaldos(List<Reporte> saldos) {
		this.saldos = saldos;
	}

	public Places getPlaces() {
		return places;
	}

	public void setPlaces(Places places) {
		this.places = places;
	}
}
