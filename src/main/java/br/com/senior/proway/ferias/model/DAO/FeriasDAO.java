package br.com.senior.proway.ferias.model.DAO;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class FeriasDAO implements Icrud<IFerias>, IConsultaDeFeriasPorTipoDAO, IConsultaPorColaboradorDAO {

	/**
	 * 
	 * Metodo que realiza a busca de todas as ferias existentes no banco de dados.
	 * 
	 * Posteriormente armazena as ferias em uma lista (listaFerias) retornando os dados obtidos.  
	 * 
	 * @author Jonata Cardoso Caetano.
	 * @return listaFerias.
	 * 
	 */
	public ArrayList<IFerias> pegarTodos() {
		ArrayList<IFerias> listaFerias = new ArrayList<IFerias>();
		try {

			PostgresConector.conectar();
			String query = "SELECT *FROM ferias";
			ResultSet resultSet = PostgresConector.executarQuery(query);

			while (resultSet.next()) {
				Ferias ferias = new Ferias();

				ferias.setId(resultSet.getInt("id"));

				ferias.setIdentificadorUsuario(resultSet.getString("id_colaborador"));

				LocalDate localDateInicio = resultSet.getDate("dataInicio").toLocalDate();
				ferias.setDataInicio(localDateInicio);

				LocalDate localDateFim = resultSet.getDate("dataFim").toLocalDate();
				ferias.setDataInicio(localDateFim);

				ferias.setTipo(TiposFerias.pegarPorValor(resultSet.getInt("id_tipoferias")));

				listaFerias.add(ferias);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaFerias;
	}

	/**
	 * Metodo que busca e retorna um objeto do tipo IFerias, atraves do id.
	 * 
	 * @param int id Id do objeto a ser consultado.
	 * @return IFerias - Um objeto do tipo IFerias.
	 * @author Janaina, Vitor, Bruna, Jonata, Daniella.
	 */

	public IFerias pegarPorID(int id) {
		IFerias ferias = new Ferias();

		try {
			PostgresConector.conectar();
			String query = "SELECT * FROM ferias WHERE id = " + id;
			ResultSet resultSet = PostgresConector.executarQuery(query);

			while (resultSet.next()) {
				ferias.setId(resultSet.getInt("id"));

				ferias.setIdentificadorUsuario(resultSet.getString("id_colaborador"));

				LocalDate localDateInicio = resultSet.getDate("dataInicio").toLocalDate();
				ferias.setDataInicio(localDateInicio);

				LocalDate localDateFim = resultSet.getDate("dataFim").toLocalDate();
				ferias.setDataFim(localDateFim);

				ferias.setDiasVendidos(resultSet.getShort("diasVendidos"));

				int tipoFerias = resultSet.getInt("id_tipoferias");
				ferias.setTipo(TiposFerias.pegarPorValor(tipoFerias));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ferias;
	}

	/**
	 * Metodo que cadastra um objeto do tipo IFerias.
	 * 
	 * @param IFerias ferias
	 * @return boolean Retorna se o metodo foi executado com sucesso.
	 * @author Janaina
	 */

	public boolean cadastrar(IFerias _ferias) {
		IFerias ferias = _ferias;
		boolean cadastrou = false;
		try {
			PostgresConector.conectar();

			String query = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES("
					+ ferias.getIdentificadorUsuario() + ", '" + Date.valueOf(ferias.getDataInicio()) + "', '"
					+ Date.valueOf(ferias.getDataFim()) + "', " + ferias.getDiasVendidos() + ", "
					+ ferias.getTipo().getValor() + ")";
			PostgresConector.executarUpdateQuery(query);
			cadastrou = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadastrou;
	}

	/**
	 * Atualiza um objeto do tipo IFerias atraves do id. Busca dentro da lista
	 * ferias, um objeto do tipo IFerias atraves de um Id. Localizando faz a
	 * subscricao.
	 * 
	 * @return boolean Retorna se o método foi executado com sucesso.
	 * @author Janaina
	 */
	public boolean alterar(int id, IFerias novaFerias) {
		boolean sucesso = false;
		try {
			PostgresConector.conectar();
			String query = "UPDATE ferias SET datainicio = '" + Date.valueOf(novaFerias.getDataInicio())
					+ "', datafim = '" + Date.valueOf(novaFerias.getDataFim()) + "', diasvendidos = "
					+ novaFerias.getDiasVendidos() + ", id_tipoferias = " + novaFerias.getTipo().getValor()
					+ " WHERE id = " + id;
			PostgresConector.executarUpdateQuery(query);
			sucesso = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucesso;
	}

	/**
	 * Deleta objeto do tipo IFerias atraves onde o id é igual ao id passado no
	 * parâmetro.
	 * 
	 * @param int Id Id do objeto a ser deletado.
	 * @return boolean Retorna se o metodo foi executado com sucesso.
	 * @author Janaina
	 */
	public boolean deletar(int id) {
		boolean sucesso = false;
		try {
			String query = "DELETE FROM ferias WHERE id = " + id;
			PostgresConector.conectar();
			PostgresConector.executarUpdateQuery(query);
			sucesso = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucesso;
	}

	
	/**
	 * 
	 * Metodo que realiza a busca de todas as ferias do tipo TOTAL existentes no banco de dados.
	 * 
	 * Posteriormente armazena as ferias em uma lista (listaTodasAsFeriasTotais) retornando os dados obtidos.  
	 * 
	 * @author Jonata Cardoso Caetano.
	 * 
	 * @return listaTodasAsFeriasParciais.
	 * 
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasTotais() {
		ArrayList<IFerias> listaTodasAsFeriasTotais = new ArrayList<IFerias>();

		try {
			PostgresConector.conectar();
			String query = "SELECT * FROM ferias WHERE id_tipoferias = 2";
			ResultSet resultSet = PostgresConector.executarQuery(query);

			while (resultSet.next()) {
				Ferias ferias = new Ferias();

				ferias.setId(resultSet.getInt("id"));
				ferias.setIdentificadorUsuario(resultSet.getString("id_colaborador"));

				LocalDate localDataInicio = resultSet.getDate("DataInicio").toLocalDate();
				ferias.setDataInicio(localDataInicio);

				LocalDate localDataFim = resultSet.getDate("DataFim").toLocalDate();
				ferias.setDataFim(localDataFim);

				ferias.setDiasVendidos((short) resultSet.getInt("diasvendidos"));

				ferias.setTipo(TiposFerias.pegarPorValor(resultSet.getInt("id_tipoferias")));

				listaTodasAsFeriasTotais.add(ferias);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return listaTodasAsFeriasTotais;
	}

	/***
	 * Retorna uma lista de Ferias Invalidas. Retorna uma lista com objetos do tipo
	 * IFerias onde o TipoDeFerias eh igual a INVALIDA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasInvalidas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasInvalidas = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getTipo().equals(TiposFerias.INVALIDA)) {
				feriasInvalidas.add(umaFerias);
			}
		}
		return feriasInvalidas;
	}

	/**
	 * 
	 * Metodo que realiza a busca de todas as ferias do tipo Parcial existentes no banco de dados.
	 * 
	 * Posteriormente armazena as ferias em uma lista (listaTodasAsFeriasParciais) retornando os dados obtidos.  
	 * 
	 * @author Jonata Cardoso Caetano.
	 * 
	 * @return listaTodasAsFeriasParciais.
	 * 
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasParciais() {
		ArrayList<IFerias> listaTodasAsFeriasParciais = new ArrayList<IFerias>();

		try {
			PostgresConector.conectar();
			String query = "SELECT *FROM ferias WHERE id_tipoferias = 3";
			ResultSet resultSet = PostgresConector.executarQuery(query);

			while (resultSet.next()) {
				Ferias ferias2 = new Ferias();

				ferias2.setId(resultSet.getInt("id"));
				ferias2.setIdentificadorUsuario(resultSet.getString("id_colaborador"));

				LocalDate localDataInicio = resultSet.getDate("DataInicio").toLocalDate();
				ferias2.setDataInicio(localDataInicio);

				LocalDate localDataFim = resultSet.getDate("DataFim").toLocalDate();
				ferias2.setDataFim(localDataFim);

				ferias2.setDiasVendidos((short) resultSet.getInt("diasvendidos"));

				ferias2.setTipo(TiposFerias.pegarPorValor(resultSet.getInt("id_tipoferias")));

				listaTodasAsFeriasParciais.add(ferias2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTodasAsFeriasParciais;

	}

	/***
	 * Retorna uma lista de Ferias Fracionadas. Retorna uma lista com objetos do
	 * tipo IFerias onde o TipoDeFerias eh igual a FRACIONADA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasFracionadas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasFracionadas = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getTipo().equals(TiposFerias.FRACIONADA)) {
				feriasFracionadas.add(umaFerias);
			}
		}
		return feriasFracionadas;
	}

	/***
	 * Retorna uma lista de Ferias Vendidas. Retorna uma lista com objetos do tipo
	 * IFerias onde o TipoDeFerias eh igual a VENDIDA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasVendidas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasVendidas = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getTipo().equals(TiposFerias.VENDIDA)) {
				feriasVendidas.add(umaFerias);
			}
		}
		return feriasVendidas;
	}

	/***
	 * Retorna uma lista de Ferias correspondente ao idUsuario. Retorna uma lista
	 * com objetos do tipo IFerias onde o idUsuario eh igual ao ao parametro
	 * idUsuario.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarFeriasPorIDColaborador(String idUsuario) {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasDoUsuario = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getIdentificadorUsuario().equals(idUsuario)) {
				feriasDoUsuario.add(umaFerias);
			}
		}
		return feriasDoUsuario;
	}

}
