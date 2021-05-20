package br.com.senior.proway.ferias.model.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.senior.proway.ferias.model.entity.Colaborador;
import br.com.senior.proway.ferias.model.entity.Ferias;
import br.com.senior.proway.ferias.model.entity.Requerimento;
import br.com.senior.proway.ferias.model.entity.Saldo;

public class DBConnection {

	private static SessionFactory sessionFactory;

	private static Session session;

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
					.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/ferias")
					.setProperty("hibernate.connection.username", "postgres")
					.setProperty("hibernate.connection.password", "admin")
					.setProperty("hibernate.jdbc.time_zone", "UTC")
					.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
					.setProperty("hibernate.show_sql", "true").setProperty("hibernate.format_sql", "false")
					.setProperty("hibernate.hbm2ddl.auto", "update")
					.setProperty("hibernate.connection.autocommit", "true")
					.addAnnotatedClass(Ferias.class)
					.addAnnotatedClass(Saldo.class)
					.addAnnotatedClass(Colaborador.class)
					.addAnnotatedClass(Requerimento.class)
					.buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}

	public static void shutdown() {
		session.close();
		getSessionFactory().close();
	}

	public static Session getSession() {
		getSessionFactory();
		if (session == null)
			session = sessionFactory.openSession();
		return session;
	}
}
