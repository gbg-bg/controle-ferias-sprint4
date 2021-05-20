package br.com.senior.proway.ferias.model.DAO;

import java.util.List;

public interface ICRUD<T> {

	public T criar(T objeto);

	public T alterar(T objeto);

	public boolean deletar(T objeto);

	public T consultarPorId(int id);

	public List<T> consultarTodos();

}