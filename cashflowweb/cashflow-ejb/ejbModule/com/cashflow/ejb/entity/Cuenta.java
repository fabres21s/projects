package com.cashflow.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c ORDER BY c.cuenId")
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
	@OneToMany(mappedBy="cuenta1")
	private List<Concepto> conceptos1;

	//bi-directional many-to-one association to Concepto
	@OneToMany(mappedBy="cuenta2")
	private List<Concepto> conceptos2;

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

	public List<Concepto> getConceptos1() {
		return this.conceptos1;
	}

	public void setConceptos1(List<Concepto> conceptos1) {
		this.conceptos1 = conceptos1;
	}

	public Concepto addConceptos1(Concepto conceptos1) {
		getConceptos1().add(conceptos1);
		conceptos1.setCuenta1(this);

		return conceptos1;
	}

	public Concepto removeConceptos1(Concepto conceptos1) {
		getConceptos1().remove(conceptos1);
		conceptos1.setCuenta1(null);

		return conceptos1;
	}

	public List<Concepto> getConceptos2() {
		return this.conceptos2;
	}

	public void setConceptos2(List<Concepto> conceptos2) {
		this.conceptos2 = conceptos2;
	}

	public Concepto addConceptos2(Concepto conceptos2) {
		getConceptos2().add(conceptos2);
		conceptos2.setCuenta2(this);

		return conceptos2;
	}

	public Concepto removeConceptos2(Concepto conceptos2) {
		getConceptos2().remove(conceptos2);
		conceptos2.setCuenta2(null);

		return conceptos2;
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