package br.com.senior.proway.ferias.model.requerimento.tipos;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;
import br.com.senior.proway.ferias.model.ferias.Ferias;
import br.com.senior.proway.ferias.model.requerimento.IRequerimento;
import br.com.senior.proway.ferias.model.requerimento.RequerimentoFactory;

/**
 * Classe RequerimentoFerias
 * 
 * A classe RequerimentoFerias é um objeto requerimento que e gerenciado pelas
 * Classes RequerimentoController e RequerimentoDAO. E responsavel por fazer a
 * padronizacao dos requerimentos de ferias no programa. Recebe um objeto
 * {@link Ferias}, que contem as informacoes pertinentes as ferias em si.
 *
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
@Entity
public class RequerimentoFerias extends RequerimentoFactory<Ferias> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRequerimentoFerias;

	@Transient
	public static short PRAZO_MINIMO_SOLICITACAO_FERIAS = 10;

	/**
	 * Construtor vazio, para uso pelo Hibernate
	 */
	public RequerimentoFerias() {
		super();
	}

	public RequerimentoFerias(Ferias objetoRequerimento, EstadoRequerimento estadoRequerimento,
			int idVerificadorRequerimento, int idCriadorRequerimento, LocalDate prazoParaAnaliseRequerimento,
			NivelUrgencia nivelUrgencia, String mensagemRequerimento) {
		super(objetoRequerimento, estadoRequerimento, idVerificadorRequerimento, idCriadorRequerimento,
				prazoParaAnaliseRequerimento, nivelUrgencia, mensagemRequerimento);
	}

	public int getIdRequerimentoFerias() {
		return idRequerimentoFerias;
	}

	public void setIdRequerimentoFerias(int idRequerimentoFerias) {
		this.idRequerimentoFerias = idRequerimentoFerias;
	}
}
