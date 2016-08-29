package com.cashflow.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cashflow.ejb.entity.Cuenta;

/**
 * Session Bean implementation class CashflowStatelessBean
 */
@Stateless
public class CashflowStatelessBean implements CashflowStatelessBeanLocal {

    /**
     * Default constructor. 
     */
    public CashflowStatelessBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName = "PersistenceUnit")
	protected EntityManager em;
    
    private Query query;

	@Override
	public List<Cuenta> consultarCuentas(int firstRow, int maxResults) {
		query = em.createNamedQuery("Cuenta.findAll");
		query.setFirstResult(firstRow);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	@Override
	public int countRecords(String table) {
		query = em.createQuery("SELECT COUNT(c) FROM "+table+" c");
		return ((Long)query.getResultList().get(0)).intValue();
	}

}
