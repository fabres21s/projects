package com.cashflow.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.entityReport.Reporte;

@Local
public interface CashflowStatelessBeanLocal {

	List<Cuenta> consultarCuentas(int firsRow, int maxResults);

	int countRecords(String table);

	List<Reporte> consultarSaldos();

	long getSaldo(String field, String value);

}
