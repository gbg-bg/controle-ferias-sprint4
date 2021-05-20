package br.com.senior.proway.ferias.model.DAO;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.senior.proway.ferias.model.db.DBConnection;
import br.com.senior.proway.ferias.model.entity.Saldo;

public class SaldoDAO extends DAO<Saldo> implements ICRUD<Saldo> {
	private static SaldoDAO instance;

	public static SaldoDAO getInstance() {
		if (instance == null) {
			instance = new SaldoDAO();
		}
		return instance;
	}

	private SaldoDAO() {
		session = DBConnection.getSession();
	}

	public Saldo consultarPorId(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return session.get(Saldo.class, id);
	}

	public List<Saldo> consultarTodos() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Saldo> criteria = builder.createQuery(Saldo.class);
		Root<Saldo> root = criteria.from(Saldo.class);
		CriteriaQuery<Saldo> rootQuery = criteria.select(root);
		Query query = session.createQuery(rootQuery);
		List<Saldo> selectedSaldo = query.getResultList();
		return selectedSaldo;
	}

}
