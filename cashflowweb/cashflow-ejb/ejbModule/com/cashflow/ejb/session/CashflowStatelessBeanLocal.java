package com.cashflow.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.cashflow.ejb.entity.Cuenta;

@Local
public interface CashflowStatelessBeanLocal {

	List<Cuenta> consultarCuentas();

}
