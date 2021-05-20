package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.entity.Saldo;
import br.com.senior.proway.ferias.model.saldoferias.SaldoFeriasBuilder;
import br.com.senior.proway.ferias.model.saldoferias.SaldoFeriasDirector;

public class SaldoFeriasTeste {

	@Test
	public void calculaProximasFeriasdoUsuario() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		Saldo saldo = saldoFeriasBuilder.build();
	
		assertEquals(dataAdmissao.plusYears(1), saldo.getProximasFerias());
		assertEquals(userID, saldo.getIdentificadorUsuario());
	}
	
	@Test 
	public void atualizarDataProximasFerias() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		Saldo saldo = saldoFeriasBuilder.build();
		// Ao criarmos o objeto saldo, a ProximasFerias ja foi atualizada para dataAdmissao+1
		
		GroundControl.atualizarProximasFerias(saldo);
		// Logo, devemos esperar que apos atualizar essa data novamente, teremos:
		
		assertEquals(dataAdmissao.plusYears(2), saldo.getProximasFerias());
	}
	
	@Test 
	public void CreditarDiasDasFeriasSemFaltas() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 0;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		Saldo saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.DIAS_DISPONIVEIS_PARA_FERIAS, saldo.getDiasDisponiveisDeFerias());	
	}
	
	@Test 
	public void CreditarDiasDasFeriasComFaltas1() { //INTERVALO_FALTAS_1
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 8;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		Saldo saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.CREDITOS_FALTAS_1, saldo.getDiasDisponiveisDeFerias());	
	}
	
	@Test 
	public void CreditarDiasDasFeriasComFaltas2() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 18;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		Saldo saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.CREDITOS_FALTAS_2, saldo.getDiasDisponiveisDeFerias());	
	}
	
	@Test 
	public void CreditarDiasDasFeriasComFaltas3() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 28;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		Saldo saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.CREDITOS_FALTAS_3, saldo.getDiasDisponiveisDeFerias());	
	}
	
	@Test 
	public void CreditarDiasDasFeriasComFaltas4() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 38;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		Saldo saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.CREDITOS_FALTAS_4, saldo.getDiasDisponiveisDeFerias());	
	}
	@Test 
	public void getESetIdentificadorUsuario() {
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)5, new ArrayList<Ferias>(), new ArrayList<Requerimento>());
		saldoFerias.setIdentificadorUsuario("1");
		assertEquals("1", saldoFerias.getIdentificadorUsuario());
	}
	
	@Test 
	public void GetESetHistoricoFerias() {
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)5, new ArrayList<Ferias>(), new ArrayList<Requerimento>());
		ArrayList<Ferias> historicoFerias = new ArrayList<Ferias>();
		saldoFerias.setHistoricoFerias(historicoFerias);
		assertEquals(0, saldoFerias.getHistoricoFerias().size());
	}
	@Test 
	public void GetESetHistoricoRequerimento() {
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)5, new ArrayList<Ferias>(), new ArrayList<Requerimento>());
		ArrayList<Requerimento> historicoFerias = new ArrayList<Requerimento>();
		saldoFerias.setHistoricoRequerimentos(historicoFerias);
		assertEquals(0, saldoFerias.getHistoricoRequerimentos().size());
	}
	@Test 
	public void TesteAdicionarHistoricoDeFerias() {
		Ferias ferias = new Ferias();
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)0, new ArrayList<Ferias>(), new ArrayList<Requerimento>());
		saldoFerias.adicionarHistoricoFerias(ferias);	
		assertEquals(1, saldoFerias.getHistoricoFerias().size());
	}
	
	@Test 
	public void TesteRemoverHistoricoDeFerias() {
		Ferias ferias = new Ferias();
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)0, new ArrayList<Ferias>(), new ArrayList<Requerimento>());
		saldoFerias.removerHistoricoFerias(ferias);	
		assertEquals(0, saldoFerias.getHistoricoFerias().size());
	}
	
	@Test 
	public void TesteVerificaQuantidadeHistoricoFerias() {
		ArrayList<Ferias> historicoFerias = new ArrayList<Ferias>();
		historicoFerias.add(new Ferias());
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)1, historicoFerias, new ArrayList<Requerimento>());
		saldoFerias.verificaQuantidadeHistoricoFerias();	
		assertEquals(1, saldoFerias.verificaQuantidadeHistoricoFerias());
	}
	
	@Test 
	public void TesteAdicionarHistoricoRequerimentos() {
		Requerimento requerimento = new Requerimento();
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)0, new ArrayList<Ferias>(), new ArrayList<Requerimento>());
		saldoFerias.adicionarHistoricoRequerimentos(requerimento);;	
		assertEquals(1, saldoFerias.getHistoricoRequerimentos().size());
	}
	
	@Test 
	public void TesteRemoverHistoricoRequerimentos() {
		Requerimento requerimento = new Requerimento();
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)0, new ArrayList<Ferias>(), new ArrayList<Requerimento>());
		saldoFerias.removerHistoricoRequerimentos(requerimento);;	
		assertEquals(0, saldoFerias.getHistoricoRequerimentos().size());
	}
	
	@Test 
	public void TesteVerificaQuantiaRequerimentos() {
		ArrayList<Requerimento> historicoRequerimentos = new ArrayList<Requerimento>();
		historicoRequerimentos.add(new Requerimento());
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)1, new ArrayList<Ferias>(), historicoRequerimentos);
		saldoFerias.verificaQuantiaRequerimentos();	
		assertEquals(1, saldoFerias.verificaQuantiaRequerimentos());
	}
	
	public void TesteChecarSaldoPositivo() {
		ArrayList<Requerimento> historicoRequerimentos = new ArrayList<Requerimento>();
		historicoRequerimentos.add(new Requerimento());
		Saldo saldoFerias = new Saldo("1", LocalDate.of(2021, 5, 5), (short)1, new ArrayList<Ferias>(), new ArrayList<Requerimento>());
		saldoFerias.checarSaldoPositivo();	
		assertEquals(1, saldoFerias.checarSaldoPositivo());
	}
	
	
}
