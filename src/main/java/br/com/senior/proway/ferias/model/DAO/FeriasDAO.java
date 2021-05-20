package br.com.senior.proway.ferias.model.DAO;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.senior.proway.ferias.model.db.DBConnection;
import br.com.senior.proway.ferias.model.entity.Ferias;

/**
 * Classe DAO do objeto {@link Ferias} implementacao de CRUD.
 * 
 * Classe que faz comunicacao com banco de dados: criacao, leitura, atualizacao
 * e remocao.
 * 
 * @author Sprint 5
 *
 */
public final class FeriasDAO extends DAO<Ferias> implements ICRUD<Ferias> {

	private static FeriasDAO instance;

	public static FeriasDAO getInstance() {
		if (instance == null) {
			instance = new FeriasDAO();
		}
		return instance;
	}

	private FeriasDAO() {
		session = DBConnection.getSession();
	}

	/**
	 * Retorna um objeto do tipo {@link Ferias} de acordo com o id do objeto.
	 * 
	 * @author Janaina, Vitor, Bruna, Jonata, Daniella.
	 * @param id int Id do objeto a ser consultado.
	 * @return IFerias Um objeto do tipo IFerias.
	 * 
	 */
	public Ferias consultarPorId(int id) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return session.get(Ferias.class, id);
	}

	/**
	 * Metodo que realiza a busca de todas as ferias existentes no banco de dados.
	 * 
	 * Posteriormente armazena as ferias em uma lista (listaFerias) retornando os
	 * dados obtidos.
	 * 
	 * @author Sprint5
	 * @return listaFerias.
	 * 
	 */
	public List<Ferias> consultarTodos() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Ferias> criteria = builder.createQuery(Ferias.class);
		Root<Ferias> root = criteria.from(Ferias.class);
		CriteriaQuery<Ferias> rootQuery = criteria.select(root);
		Query query = session.createQuery(rootQuery);
		List<Ferias> selectedFerias = query.getResultList();
		return selectedFerias;
	}

//	/**
//	 * Retorna uma lista de objetos do tipo {@link IFerias} com id do usuario
//	 * passado como parametro.
//	 * 
//	 * @author Janaina
//	 * @param idColaborador int Identificador do usuário.
//	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
//	 * 
//	 */
//	public List<Ferias> pegarTodasAsFeriasPorIDColaborador(int idUsuarioEntrada) {
//		if (!session.getTransaction().isActive()) {
//			session.beginTransaction();
//		}
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<Ferias> criteria = builder.createQuery(Ferias.class);
//
//		Root<Ferias> root = criteria.from(Ferias.class);
//
//		criteria.select(root).where(builder.equal(root.get("identificadorUsuario"), idUsuarioEntrada));
//		Query query = session.createQuery(criteria);
//		List<Ferias> todasFerias = query.getResultList();
//		return todasFerias;
//	}
//
//	/**
//	 * Retorna uma lista de objetos do tipo {@link IFerias} com {@link TipoFerias}i
//	 * passado como parametro.
//	 * 
//	 * @author Sprint5
//	 * @param {@link TiposFerias}
//	 * @return List IFerias
//	 */
//	public List<Ferias> pegarTodasAsFeriasPorTipo(TiposFerias tipoEntrada) {
//		if (!session.getTransaction().isActive()) {
//			session.beginTransaction();
//		}
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<Ferias> criteria = builder.createQuery(Ferias.class);
//
//		Root<Ferias> root = criteria.from(Ferias.class);
//
//		criteria.select(root).where(builder.equal(root.get("tipoFerias"), tipoEntrada.getValor()));
//		Query query = session.createQuery(criteria);
//		List<Ferias> todasFerias = query.getResultList();
//		return todasFerias;
//
//	}
//
//	/**
//	 * Retorna uma lista de objetos do tipo {@link IFerias} de acordo com
//	 * {@link dataRecebida} passada com parametro.
//	 * 
//	 * @author Sprint5
//	 * @param LocalDate
//	 * @return List IFerias
//	 */
//	public List<Ferias> pegarTodasAsFeriasPorDataInicio(LocalDate dataRecebida) {
//		if (!session.getTransaction().isActive()) {
//			session.beginTransaction();
//		}
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<Ferias> criteria = builder.createQuery(Ferias.class);
//
//		Root<Ferias> root = criteria.from(Ferias.class);
//
//		criteria.select(root).where(builder.equal(root.get("dataInicio"), dataRecebida));
//		Query query = session.createQuery(criteria);
//		List<Ferias> todasFerias = query.getResultList();
//		return todasFerias;
//
//	}
//
//	/**
//	 * Metodo que limpa tabela {@link Ferias} do banco de dados.
//	 * 
//	 * @return boolean se limpo com sucesso.
//	 */
//	public boolean limparTabela() {
//		if (!session.getTransaction().isActive()) {
//			session.beginTransaction();
//		}
//		try {
//
//			CriteriaBuilder builder = session.getCriteriaBuilder();
//			CriteriaDelete<Ferias> criteriaDelete = builder.createCriteriaDelete(Ferias.class);
//			criteriaDelete.from(Ferias.class);
//			Query query = session.createQuery(criteriaDelete);
//			query.executeUpdate();
//
//			session.getTransaction().commit();
//			return true;
//		} catch (Exception e) {
//			e.getMessage();
//			return false;
//		}
//	}
}
