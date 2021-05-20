package br.com.senior.proway.ferias.model.DAO;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.senior.proway.ferias.model.db.DBConnection;
import br.com.senior.proway.ferias.model.entity.Colaborador;

public final class ColaboradorDAO extends DAO<Colaborador> implements ICRUD<Colaborador> {

	private static ColaboradorDAO instance;

	public static ColaboradorDAO getInstance() {
		if (instance == null) {
			instance = new ColaboradorDAO();
		}
		return instance;
	}

	private ColaboradorDAO() {
		session = DBConnection.getSession();
	}

	public Colaborador consultarPorId(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return session.get(Colaborador.class, id);
	}

	public List<Colaborador> consultarTodos() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Colaborador> criteria = builder.createQuery(Colaborador.class);
		Root<Colaborador> root = criteria.from(Colaborador.class);
		CriteriaQuery<Colaborador> rootQuery = criteria.select(root);
		Query query = session.createQuery(rootQuery);
		List<Colaborador> selectedColaborador = query.getResultList();
		return selectedColaborador;
	}

}
