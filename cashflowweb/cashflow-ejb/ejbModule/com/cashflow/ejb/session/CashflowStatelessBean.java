package com.cashflow.ejb.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cashflow.ejb.entity.Concepto;
import com.cashflow.ejb.entity.Cuenta;
import com.cashflow.ejb.entityReport.Reporte;

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
		query = em.createQuery("SELECT COUNT(c) FROM " + table + " c");
		return ((Long) query.getResultList().get(0)).intValue();
	}

	@Override
	public List<Reporte> consultarSaldos() {

		List<Reporte> reportes = new ArrayList<>();

		try {
			query = em.createQuery(" SELECT c.cuenId, c.cuenNombre, SUM(d.detaDebito) - SUM(d.detaCredito) FROM "
					+ "Detalle d " 
					+ "INNER JOIN d.cuenta as c " 
					+ "WHERE c.cuenEssaldo = :esSaldo "
					+ "GROUP BY c.cuenId,c.cuenNombre "
					+ "ORDER BY c.cuenNombre");
			query.setParameter("esSaldo", true);
			List<Object[]> list = query.getResultList();
			for (Object[] array : list) {
				Reporte r = new Reporte();
				r.setId(Integer.valueOf(array[0].toString()));
				r.setDescripcion(array[1].toString());
				r.setSaldo(Long.valueOf(array[2].toString()));
				reportes.add(r);
			}
		} catch (Exception exc) {
			reportes = new ArrayList<Reporte>();
			exc.printStackTrace();
		}
		return reportes;

	}

	@Override
	public long getSaldo(String field, String value) {
		Long saldo = (long) 0;
        try {

            query = em.createQuery(" SELECT SUM(d." + value + ") FROM "
                    + "Detalle d "
                    + "INNER JOIN d.movimiento as m  "
                    + "INNER JOIN m.concepto   as c  "
                    + "INNER JOIN d.cuenta     as cu "
                    + "WHERE c." + field + " = :" + field );
            query.setParameter(field, true);
            saldo = Long.valueOf(query.getSingleResult().toString());
        } catch (Exception exc) {
        	exc.printStackTrace();
        }
		return saldo;
	}

	@Override
	public List<Concepto> consultarConceptosActivos() {
		query = em.createQuery("SELECT c FROM Concepto c "
				+ "WHERE c.concEstado = :estado "
				+ "ORDER BY c.concNombre ");
        query.setParameter("estado", true);
         return query.getResultList();
	}

}
