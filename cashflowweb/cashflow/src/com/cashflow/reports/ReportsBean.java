package com.cashflow.reports;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.cashflow.ejb.session.CashflowStatelessBeanLocal;
import com.google.gson.Gson;

@ManagedBean
@ViewScoped
public class ReportsBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6030768777676931847L;
	
	@EJB(lookup = "java:global/cashflow-ejb/CashflowStatelessBean!com.cashflow.ejb.session.CashflowStatelessBeanLocal")
	private CashflowStatelessBeanLocal mainPersistenceManager;
	
	private List<Data> dataList;
    private String xValues;
    private String data;
	
	@PostConstruct
	public void init() {
		consultarReporteGeneral();
	}

	private void consultarReporteGeneral() {
		List<Object[]> reportDataList = mainPersistenceManager.consultarReporteGeneralCuentasMes(50);
		
		
		 List<String> xValues = new ArrayList<String>();
		 
		 List<Double> yValuesIngresos = new ArrayList<Double>();
		 List<Double> yValuesTotalIngresos = new ArrayList<Double>();
		 List<Double> yValuesPromedioIngresos = new ArrayList<Double>();
		 
		 List<Double> yValuesEgresos = new ArrayList<Double>();
		 List<Double> yValuesTotalEgresos = new ArrayList<Double>();
		 List<Double> yValuesPromedioEgresos = new ArrayList<Double>();
		 
		 Double totalIngresos = 0.0, totalEgresos = 0.0;
		 Double averageIngresos = 0.0, averageEgresos = 0.0;
		 int count = 0;
		 Double valueIngresos = 0.0, valueEgresos= 0.0;
		 
		 String name = "";
		 for (Object[] reportData : reportDataList) {
			 
			 valueIngresos = Double.valueOf(String.valueOf(reportData[2]));
			 valueEgresos = Double.valueOf(String.valueOf(reportData[3]));
			 
			 xValues.add(("'"+String.valueOf(reportData[0]))+"'");
			 
			 yValuesIngresos.add(valueIngresos);
			 yValuesEgresos.add(valueEgresos);
			 
			 name = String.valueOf(reportData[1]);
			 
			 totalIngresos += valueIngresos;
			 totalEgresos += valueEgresos;
			 count ++;
			 averageIngresos = totalIngresos / count;
			 averageEgresos = totalEgresos / count;
			 
			 yValuesTotalIngresos.add(totalIngresos);
			 yValuesPromedioIngresos.add(averageIngresos);
			 yValuesTotalEgresos.add(totalEgresos);
			 yValuesPromedioEgresos.add(averageEgresos);
		 }

	        dataList = new ArrayList<>();

	        dataList.add(new Data(name +" Ingresos", yValuesIngresos, "column"));
	        dataList.add(new Data("Promedio Ingresos", yValuesPromedioIngresos, "spline"));
	        dataList.add(new Data("Total ingresos", yValuesTotalIngresos, "spline"));
	        
	        dataList.add(new Data(name +" Egresos", yValuesEgresos, "column"));
	        dataList.add(new Data("Promedio egresos", yValuesPromedioEgresos, "spline"));
	        dataList.add(new Data("Total egresos", yValuesTotalEgresos, "spline"));


	        this.xValues = xValues.toString();
	        data = new Gson().toJson(dataList);
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

}
