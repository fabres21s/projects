package com.cashflow.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c ORDER BY c.cuenNombre")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUENTA_CUENID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUENTA_CUENID_GENERATOR")
	@Column(name="cuen_id")
	private Integer cuenId;

	@Column(name="cuen_essaldo")
	private Boolean cuenEssaldo;

	@Column(name="cuen_nombre")
	private String cuenNombre;

	//bi-directional many-to-one association to Concepto
	@OneToMany(mappedBy="cuentaCredito")
	private List<Concepto> conceptosCredito;

	//bi-directional many-to-one association to Concepto
	@OneToMany(mappedBy="cuentaDebito")
	private List<Concepto> conceptosDebito;

	//bi-directional many-to-one association to Detalle
	@OneToMany(mappedBy="cuenta")
	private List<Detalle> detalles;

	public Cuenta() {
	}

	public Integer getCuenId() {
		return this.cuenId;
	}

	public void setCuenId(Integer cuenId) {
		this.cuenId = cuenId;
	}

	public Boolean getCuenEssaldo() {
		return this.cuenEssaldo;
	}

	public void setCuenEssaldo(Boolean cuenEssaldo) {
		this.cuenEssaldo = cuenEssaldo;
	}

	public String getCuenNombre() {
		return this.cuenNombre;
	}

	public void setCuenNombre(String cuenNombre) {
		this.cuenNombre = cuenNombre;
	}

	public List<Concepto> getConceptosCredito() {
		return this.conceptosCredito;
	}

	public void setConceptosCredito(List<Concepto> conceptosCredito) {
		this.conceptosCredito = conceptosCredito;
	}


	public List<Concepto> getConceptosDebito() {
		return this.conceptosDebito;
	}

	public void setConceptos2(List<Concepto> conceptosDebito) {
		this.conceptosDebito = conceptosDebito;
	}

	public List<Detalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public Detalle addDetalle(Detalle detalle) {
		getDetalles().add(detalle);
		detalle.setCuenta(this);

		return detalle;
	}

	public Detalle removeDetalle(Detalle detalle) {
		getDetalles().remove(detalle);
		detalle.setCuenta(null);

		return detalle;
	}

}