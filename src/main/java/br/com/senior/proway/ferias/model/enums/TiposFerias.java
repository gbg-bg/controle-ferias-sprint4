package br.com.senior.proway.ferias.model.enums;

public enum TiposFerias {
	INVALIDA(0), // Erro
	TOTAL(1), // Todos os dias sao utilizados nas ferias
	PARCIAL(2), // Ferias com uma fracao dos dias disponiveis, o restante e automaticamente
				// vendido
	FRACIONADA(3), // Ferias com uma fracao dos dias disponiveis, o restante continua como credito
	VENDIDA(4); // Vende todos os dias disponveis

	private int valor;

	TiposFerias(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static TiposFerias pegarPorValor(int valor) {
		switch (valor) {
		case 0:
			return TiposFerias.INVALIDA;
		case 1:
			return TiposFerias.TOTAL;
		case 2:
			return TiposFerias.PARCIAL;
		case 3:
			return TiposFerias.FRACIONADA;
		case 4:
			return TiposFerias.VENDIDA;
		default:
			return null;
		}
	}

}
