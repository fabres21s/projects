package com.cashflow.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the movimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MOVIMIENTO_MOVIID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOVIMIENTO_MOVIID_GENERATOR")
	@Column(name="movi_id")
	private Integer moviId;

	@Column(name="movi_descripcion")
	private String moviDescripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="movi_fecha")
	private Date moviFecha;

	//bi-directional many-to-one association to Detalle
	@OneToMany(mappedBy="movimiento")
	private List<Detalle> detalles;

	//bi-directional many-to-one association to Concepto
	@ManyToOne
	@JoinColumn(name="conc_id")
	private Concepto concepto;

	public Movimiento() {
	}

	public Integer getMoviId() {
		return this.moviId;
	}

	public void setMoviId(Integer moviId) {
		this.moviId = moviId;
	}

	public String getMoviDescripcion() {
		return this.moviDescripcion;
	}

	public void setMoviDescripcion(String moviDescripcion) {
		this.moviDescripcion = moviDescripcion;
	}

	public Date getMoviFecha() {
		return this.moviFecha;
	}

	public void setMoviFecha(Date moviFecha) {
		this.moviFecha = moviFecha;
	}

	public List<Detalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public Detalle addDetalle(Detalle detalle) {
		getDetalles().add(detalle);
		detalle.setMovimiento(this);

		return detalle;
	}

	public Detalle removeDetalle(Detalle detalle) {
		getDetalles().remove(detalle);
		detalle.setMovimiento(null);

		return detalle;
	}

	public Concepto getConcepto() {
		return this.concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

}