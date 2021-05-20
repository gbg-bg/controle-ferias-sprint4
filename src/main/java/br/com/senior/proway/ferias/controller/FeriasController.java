package br.com.senior.proway.ferias.controller;

import java.time.LocalDate;
import java.util.List;

import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.ferias.IFerias;

/**
 * Classe controller de objeto {@link Ferias}, metodos CRUD e get
 * por atributos especificos.
 * 
 * @author Sprint 5
 * @see FeriasDAO
 */
public class FeriasController {

	// Metodos do DAO

	/**
	 * Metodo que retorna uma lista de objetos {@link IFerias} do banco de dados.
	 * @return List IFerias
	 */
	public List<> pegarTodos() {
		return feriasDAO.pegarTodos();
	}

	/**
	 * Metodo que retorna objeto especifico {@link IFerias} por Id do banco de dados.
	 * @param int id
	 * @return {@link IFerias}
	 */
	public IFerias pegarFeriasPorId(int id) {

		return feriasDAO.pegarFeriasPorID(id);
	}

	/**
	 * Metodo que cadastra novo objeto {@link IFerias} no banco de dados.
	 * @param IFerias
	 * @return boolean se cadastrado com sucesso.
	 */
	public boolean cadastrar(kfoaskdsaodksad) {
		return feriasDAO.cadastrar(ferias);
	}

	/**
	 * Metodo que altera objeto {@link IFerias} existente no banco de dados.
	 * @param IFerias
	 * @return boolean se alterado com sucesso.
	 */
	public boolean alterar(IFerias novaFerias) {
		return feriasDAO.alterar(novaFerias);
	}

	/**
	 * Metodo que deleta objeto {@link IFerias} existente no banco de dados.
	 * @param IFerias
	 * @return boolean se deletado com sucesso.
	 */
	public boolean deletar(IFerias ferias) {
		return feriasDAO.deletar(ferias);
	}

	/**
	 * Metodo que retorna uma lista de objetos {@link IFerias} pelo Id do usuario.
	 * @param idUsuarioEntrada
	 * @return List IFerias
	 */
	public List<IFerias> pegarTodasAsFeriasPorIDColaborador(int idUsuarioEntrada) {
		return feriasDAO.pegarTodasAsFeriasPorIDColaborador(idUsuarioEntrada);
	}
	
	/**
	 * Metodo que retorna uma lista de objetos {@link IFerias} pelo {@link TiposFerias}.
	 * @param TiposFerias
	 * @return List IFerias
	 */
	public List<IFerias> pegarTodasAsFeriasPorTipo(TiposFerias tipo) {
		return feriasDAO.pegarTodasAsFeriasPorTipo(tipo);
	}

	/**
	 * Metodo que retorna uma lista de objetos {@link IFerias} pela {@link dataRecebida}.
	 * @param dataRecebida
	 * @return List IFerias
	 */
	public List<IFerias> pegarTodasAsFeriasPorDataInicio(LocalDate dataRecebida) {
		return feriasDAO.pegarTodasAsFeriasPorDataInicio(dataRecebida);
	}

	/**
	 * Metodo que limpa a tabela do banco de dados.
	 * 
	 */
	public void limpartabela() {
		feriasDAO.limparTabela();
	}
}
