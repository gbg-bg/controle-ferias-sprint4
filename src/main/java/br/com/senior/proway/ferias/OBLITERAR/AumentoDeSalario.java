package br.com.senior.proway.ferias.model.aumento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe que modela um aumento de salario.
 * 
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
@Entity
public class AumentoDeSalario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int idAumentoSalario;
	
	double novoSalario;
	
	public AumentoDeSalario(double novoSalario) {
		this.novoSalario = novoSalario;
	}
	
	public AumentoDeSalario() {
		
	}
	
}
