package com.cashflow.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle database table.
 * 
 */
@Entity
@NamedQuery(name="Detalle.findAll", query="SELECT d FROM Detalle d")
public class Detalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="deta_id")
	private Integer detaId;

	@Column(name="deta_credito")
	private BigDecimal detaCredito;

	@Column(name="deta_debito")
	private BigDecimal detaDebito;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="cuen_id")
	private Cuenta cuenta;

	//bi-directional many-to-one association to Movimiento
	@ManyToOne
	@JoinColumn(name="movi_id")
	private Movimiento movimiento;

	public Detalle() {
	}

	public Integer getDetaId() {
		return this.detaId;
	}

	public void setDetaId(Integer detaId) {
		this.detaId = detaId;
	}

	public BigDecimal getDetaCredito() {
		return this.detaCredito;
	}

	public void setDetaCredito(BigDecimal detaCredito) {
		this.detaCredito = detaCredito;
	}

	public BigDecimal getDetaDebito() {
		return this.detaDebito;
	}

	public void setDetaDebito(BigDecimal detaDebito) {
		this.detaDebito = detaDebito;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Movimiento getMovimiento() {
		return this.movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

}