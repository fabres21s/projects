package com.cashflow.ejb.entityReport;

public class ReportData {

	private String mes;
	private String cuenta;
	private Double ingresos;
	private Double egresos;
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public Double getIngresos() {
		return ingresos;
	}
	public void setIngresos(Double ingresos) {
		this.ingresos = ingresos;
	}
	public Double getEgresos() {
		return egresos;
	}
	public void setEgresos(Double egresos) {
		this.egresos = egresos;
	}
}
