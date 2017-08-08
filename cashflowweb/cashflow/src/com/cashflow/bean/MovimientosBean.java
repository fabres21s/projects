package com.cashflow.bean;

import javax.faces.bean.ManagedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.cashflow.ejb.entity.Concepto;
import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.entity.Detalle;
import com.cashflow.ejb.entity.Movimiento;
import com.cashflow.ejb.entityReport.Reporte;
import com.cashflow.ejb.filter.DetalleFilter;
import com.cashflow.ejb.paginator.Paginator;
import com.cashflow.ejb.paginator.PaginatorInterface;

@ManagedBean
@ViewScoped
public class MovimientosBean implements PaginatorInterface{



	private List<Reporte> saldos;
	private List<Detalle> detalles;
	
	private List<SelectItem> conceptosItems = new ArrayList<SelectItem>();
	private List<SelectItem> cuentasItems = new ArrayList<SelectItem>();
	
	private long ingresos;
	private long gastos;
	private Integer conceptoSelected;
	
	private Movimiento movimiento;
	private String moviValor;
	
	private Paginator paginator;
	
	@PostConstruct
	public void init() {
		paginator = new Paginator(10, "d.movimiento.moviFecha", false, this);
		reset();
	}
	
	
	public void guardarMovimiento() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String fecha = request.getParameter("fechaMov");
		String valor = request.getParameter("valorMov");
	    
	    Concepto concepto = (Concepto)AccessDatabase.getInstance().findRecord(new Concepto(), conceptoSelected);
        movimiento.setConcepto(concepto);
        movimiento.setMoviFecha(Utils.getInstance().getDate(fecha));
        
      
        List<Detalle> detalles = new ArrayList<Detalle>();
        Detalle detalle_debito = new Detalle();
        
        detalle_debito.setCuenta(concepto.getCuentaDebito());
        detalle_debito.setDetaDebito(BigDecimal.valueOf(Double.valueOf(valor)));
        detalle_debito.setDetaCredito(BigDecimal.ZERO);
        detalle_debito.setMovimiento(movimiento);
        detalles.add(detalle_debito);
        
        Detalle detalle_credito = new Detalle();
        detalle_credito.setCuenta(concepto.getCuentaCredito());
        detalle_credito.setDetaCredito(BigDecimal.valueOf(Double.valueOf(valor)));
        detalle_credito.setDetaDebito(BigDecimal.ZERO);
        detalle_credito.setMovimiento(movimiento);
        detalles.add(detalle_credito);
        
        movimiento.setDetalles(detalles);
        AccessDatabase.getInstance().persist(movimiento);
		reset();
	}


	private void reset() {
		saldos 		= AccessDatabase.getInstance().consultarSaldos();
		ingresos	= AccessDatabase.getInstance().getSaldo("concEsingreso", "detaDebito") ;
		gastos 		= AccessDatabase.getInstance().getSaldo("concEsgasto", "detaCredito") ;
		movimiento = new Movimiento();
		cargarConceptos();
		cargarCuentas();
		paginator.setTotalRecords(AccessDatabase.getInstance().countRecords("Detalle"));
	}


	private void cargarCuentas() {
		List<Cuenta> cuentas = AccessDatabase.getInstance().consultarCuentas();
		for (Cuenta cuenta: cuentas) {
			cuentasItems.add(new SelectItem(cuenta.getCuenId(), cuenta.getCuenNombre()));
		}
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

	@Override
	public void updatetable() {
		
		
		detalles = AccessDatabase.getInstance().consultarDetalles(new DetalleFilter(paginator));
	}


	public Paginator getPaginator() {
		return paginator;
	}


	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}


	public List<Detalle> getDetalles() {
		return detalles;
	}


	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}


	public List<SelectItem> getCuentasItems() {
		return cuentasItems;
	}


	public void setCuentasItems(List<SelectItem> cuentasItems) {
		this.cuentasItems = cuentasItems;
	}
}
