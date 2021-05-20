package br.com.senior.proway.ferias.model.entity;

import org.junit.Test;

import br.com.senior.proway.ferias.model.db.DBConnection;

public class SaldoTest {

	@Test
	public void test() {
		Colaborador c = new Colaborador();
		DBConnection.getSession().beginTransaction();
	}

}
