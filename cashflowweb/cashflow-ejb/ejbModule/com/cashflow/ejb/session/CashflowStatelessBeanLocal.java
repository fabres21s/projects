package com.cashflow.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.cashflow.ejb.entity.Concepto;
import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.entity.Detalle;
import com.cashflow.ejb.entityReport.Reporte;
import com.cashflow.ejb.filter.DetalleFilter;

@Local
public interface CashflowStatelessBeanLocal {

	List<Cuenta> consultarCuentas(int firsRow, int maxResults);

	int countRecords(String table);

	List<Reporte> consultarSaldos();

	long getSaldo(String field, String value);

	List<Concepto> consultarConceptosActivos();

	Object findRecord(Object table, Integer id);

	void persist(Object object);

	List<Detalle> consultarDetalles(DetalleFilter detalleFilter);

}
