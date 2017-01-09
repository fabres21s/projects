package com.cashflow.bean;

import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.cashflow.ejb.entity.Concepto;
import com.cashflow.ejb.entity.Movimiento;
import com.cashflow.ejb.entityReport.Reporte;

@ManagedBean
@ViewScoped
public class MovimientosBean {



	private List<Reporte> saldos;
	
	private List<SelectItem> conceptosItems = new ArrayList<SelectItem>();
	
	private long ingresos;
	private long gastos;
	private Integer conceptoSelected;
	
	private Movimiento movimiento;
	private String moviValor;
	
	@PostConstruct
	public void init() {
		saldos 		= AccessDatabase.getInstance().consultarSaldos();
		ingresos	= AccessDatabase.getInstance().getSaldo("concEsingreso", "detaDebito");
		gastos 		= AccessDatabase.getInstance().getSaldo("concEsgasto", "detaCredito");
		movimiento = new Movimiento();
		cargarConceptos();
	}
	
	
	public void guardarMovimiento() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String fecha = request.getParameter("fechaMov");
		
		
		System.out.println(fecha + " "+conceptoSelected);
	}


	private void cargarConceptos() {
		List<Concepto> conceptos = AccessDatabase.getInstance().consultarConceptosActivos();
		for (Concepto c : conceptos) {
			conceptosItems.add(new SelectItem(c.getConcId(), c.getConcNombre()));
		}
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





	public List<SelectItem> getConceptosItems() {
		return conceptosItems;
	}





	public void setConceptosItems(List<SelectItem> conceptosItems) {
		this.conceptosItems = conceptosItems;
	}





	public Integer getConceptoSelected() {
		return conceptoSelected;
	}





	public void setConceptoSelected(Integer conceptoSelected) {
		this.conceptoSelected = conceptoSelected;
	}





	public Movimiento getMovimiento() {
		return movimiento;
	}





	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}





	public String getMoviValor() {
		return moviValor;
	}





	public void setMoviValor(String moviValor) {
		this.moviValor = moviValor;
	}
}
