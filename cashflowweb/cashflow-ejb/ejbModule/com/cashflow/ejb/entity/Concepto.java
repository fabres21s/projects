package com.cashflow.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the concepto database table.
 * 
 */
@Entity
@NamedQuery(name="Concepto.findAll", query="SELECT c FROM Concepto c")
public class Concepto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONCEPTO_CONCID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONCEPTO_CONCID_GENERATOR")
	@Column(name="conc_id")
	private Integer concId;

	@Column(name="conc_esgasto")
	private Boolean concEsgasto;

	@Column(name="conc_esingreso")
	private Boolean concEsingreso;

	@Column(name="conc_estado")
	private Boolean concEstado;

	@Column(name="conc_nombre")
	private String concNombre;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="cuen_credito")
	private Cuenta cuenta1;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="cuen_debito")
	private Cuenta cuenta2;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="concepto")
	private List<Movimiento> movimientos;

	public Concepto() {
	}

	public Integer getConcId() {
		return this.concId;
	}

	public void setConcId(Integer concId) {
		this.concId = concId;
	}

	public Boolean getConcEsgasto() {
		return this.concEsgasto;
	}

	public void setConcEsgasto(Boolean concEsgasto) {
		this.concEsgasto = concEsgasto;
	}

	public Boolean getConcEsingreso() {
		return this.concEsingreso;
	}

	public void setConcEsingreso(Boolean concEsingreso) {
		this.concEsingreso = concEsingreso;
	}

	public Boolean getConcEstado() {
		return this.concEstado;
	}

	public void setConcEstado(Boolean concEstado) {
		this.concEstado = concEstado;
	}

	public String getConcNombre() {
		return this.concNombre;
	}

	public void setConcNombre(String concNombre) {
		this.concNombre = concNombre;
	}

	public Cuenta getCuenta1() {
		return this.cuenta1;
	}

	public void setCuenta1(Cuenta cuenta1) {
		this.cuenta1 = cuenta1;
	}

	public Cuenta getCuenta2() {
		return this.cuenta2;
	}

	public void setCuenta2(Cuenta cuenta2) {
		this.cuenta2 = cuenta2;
	}

	public List<Movimiento> getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Movimiento addMovimiento(Movimiento movimiento) {
		getMovimientos().add(movimiento);
		movimiento.setConcepto(this);

		return movimiento;
	}

	public Movimiento removeMovimiento(Movimiento movimiento) {
		getMovimientos().remove(movimiento);
		movimiento.setConcepto(null);

		return movimiento;
	}

}