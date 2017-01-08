package com.cashflow.bean;

import javax.faces.bean.ManagedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.entityReport.Reporte;
import com.cashflow.ejb.session.CashflowStatelessBeanLocal;

@ManagedBean
@ViewScoped
public class MovimientosBean {



	private List<Reporte> saldos;
	
	private long ingresos;
	private long gastos;
	
	@PostConstruct
	public void init() {
		saldos 		= AccessDatabase.getInstance().consultarSaldos();
		ingresos	= AccessDatabase.getInstance().getSaldo("concEsingreso", "detaDebito");
		gastos 		= AccessDatabase.getInstance().getSaldo("concEsgasto", "detaCredito"); 
	}
	
	
	


	public List<Reporte> getSaldos() {
		return saldos;
	}

	public void setSaldos(List<Reporte> saldos) {
		this.saldos = saldos;
	}





	public long getIngresos() {
		return ingresos;
	}





	public void setIngresos(long ingresos) {
		this.ingresos = ingresos;
	}





	public long getGastos() {
		return gastos;
	}





	public void setGastos(long gastos) {
		this.gastos = gastos;
	}
}
