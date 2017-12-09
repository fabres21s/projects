package com.cashflow.reports;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.cashflow.bean.CashflowBean;
import com.cashflow.bean.MovimientosBean;
import com.cashflow.ejb.entity.Concepto;
import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.session.CashflowStatelessBeanLocal;
import com.google.gson.Gson;

@ManagedBean
@ViewScoped
public class ReportsBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6030768777676931847L;

	private List<Data> dataList;
	private String xValues;
	private String data;

	private Cuenta accountSelected;
	private List<SelectItem> conceptosItems;
	private Concepto conceptSelected;

	@ManagedProperty("#{movimientosBean}")
	private MovimientosBean movimientosBean;

	@ManagedProperty("#{cashflowBean}")
	private CashflowBean cashflowBean;

	private CashflowStatelessBeanLocal mainPersistenceManager;

	@PostConstruct
	public void init() {
		setAccountSelected(new Cuenta());
		getAccountSelected().setCuenId(1);
		getAccountSelected().setCuenNombre("EFECTIVO");

		mainPersistenceManager = getCashflowBean().getMainPersistenceManager();

		conceptSelected = new Concepto();
		conceptSelected.setConcNombre("Todos los conceptos");
		conceptSelected.setConcId(-1);

		consultarConceptos();

		consultarReporteGeneral();
	}

	public void consultarConceptos() {
		conceptosItems = new ArrayList<SelectItem>();
		conceptosItems.add(new SelectItem(-1, "Todos los conceptos"));

		List<Concepto> conceptos = mainPersistenceManager.consultarConceptosPorCuentaId(accountSelected.getCuenId());
		for (Concepto concepto : conceptos) {
			conceptosItems.add(new SelectItem(concepto.getConcId(), concepto.getConcNombre()));
		}
	}

	public void consultarReporteGeneral() {
		List<Object[]> reportDataList = new ArrayList<Object[]>();

		
		for (SelectItem selectItem: conceptosItems) {
			if (selectItem.getValue().equals(conceptSelected.getConcId())) {
				conceptSelected.setConcNombre(selectItem.getLabel());
				break;
			}
		}
		
		if (conceptSelected.getConcId() == -1) {
			//TODO Cambio por periodo
			reportDataList = mainPersistenceManager.consultarReporteGeneralCuentasMes(getAccountSelected().getCuenId());
		} else {
			reportDataList = mainPersistenceManager.consultarReportePorConcepto(conceptSelected.getConcId());
		}

		List<String> xValues = new ArrayList<String>();

		List<Double> yValuesIngresos = new ArrayList<Double>();
		List<Double> yValuesTotalIngresos = new ArrayList<Double>();
		List<Double> yValuesPromedioIngresos = new ArrayList<Double>();

		List<Double> yValuesEgresos = new ArrayList<Double>();
		List<Double> yValuesTotalEgresos = new ArrayList<Double>();
		List<Double> yValuesPromedioEgresos = new ArrayList<Double>();

		Double totalIngresos = 0.0, totalEgresos = 0.0;
		Double averageIngresos = 0.0, averageEgresos = 0.0;
		int countIngresos = 0, countEgresos = 0;
		Double valueIngresos = 0.0, valueEgresos = 0.0;

		String name = "";
		for (Object[] reportData : reportDataList) {

			valueIngresos = Double.valueOf(String.valueOf(reportData[2]));
			valueEgresos = Double.valueOf(String.valueOf(reportData[3]));

			xValues.add(("'" + String.valueOf(reportData[0])) + "'");

			yValuesIngresos.add(valueIngresos);
			yValuesEgresos.add(valueEgresos);

			name = String.valueOf(reportData[1]);

			totalIngresos += valueIngresos;
			totalEgresos += valueEgresos;

			if (valueIngresos > 0) {
				countIngresos++;
				averageIngresos = totalIngresos / countIngresos;

			}

			if (valueEgresos > 0) {
				countEgresos++;
				averageEgresos = totalEgresos / countEgresos;

			}

			yValuesTotalIngresos.add(totalIngresos);
			yValuesPromedioIngresos.add(averageIngresos);
			yValuesTotalEgresos.add(totalEgresos);
			yValuesPromedioEgresos.add(averageEgresos);

		}

		dataList = new ArrayList<>();

		if (totalIngresos > 0) {
			dataList.add(new Data(name + " Ingresos", yValuesIngresos, "column"));
			dataList.add(new Data("Promedio Ingresos", yValuesPromedioIngresos, "spline"));
			dataList.add(new Data("Total ingresos", yValuesTotalIngresos, "spline"));
		}

		if (totalEgresos > 0) {
			dataList.add(new Data(name + " Egresos", yValuesEgresos, "column"));
			dataList.add(new Data("Promedio egresos", yValuesPromedioEgresos, "spline"));
			dataList.add(new Data("Total egresos", yValuesTotalEgresos, "spline"));
		}

		this.xValues = xValues.toString();
		data = new Gson().toJson(dataList);

		for (SelectItem selectItem : getMovimientosBean().getCuentasItems()) {
			if (selectItem.getValue().equals(getAccountSelected().getCuenId())) {
				getAccountSelected().setCuenNombre(selectItem.getLabel());
				break;
			}
		}

	}

	public List<Data> getDataList() {
		return dataList;
	}

	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}

	public String getxValues() {
		return xValues;
	}

	public void setxValues(String xValues) {
		this.xValues = xValues;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public MovimientosBean getMovimientosBean() {
		return movimientosBean;
	}

	public void setMovimientosBean(MovimientosBean movimientosBean) {
		this.movimientosBean = movimientosBean;
	}

	public Cuenta getAccountSelected() {
		return accountSelected;
	}

	public void setAccountSelected(Cuenta accountSelected) {
		this.accountSelected = accountSelected;
	}

	public CashflowBean getCashflowBean() {
		return cashflowBean;
	}

	public void setCashflowBean(CashflowBean cashflowBean) {
		this.cashflowBean = cashflowBean;
	}

	public List<SelectItem> getConceptosItems() {
		return conceptosItems;
	}

	public void setConceptosItems(List<SelectItem> conceptosItems) {
		this.conceptosItems = conceptosItems;
	}

	public Concepto getConceptSelected() {
		return conceptSelected;
	}

	public void setConceptSelected(Concepto conceptSelected) {
		this.conceptSelected = conceptSelected;
	}

}
