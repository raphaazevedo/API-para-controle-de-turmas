package br.com.rapha.repositories;

import java.sql.Connection;

import br.com.rapha.entities.Matricula;
import br.com.rapha.factories.ConnectionFactory;

public class MatriculaRepository {

	public void insertMaticula(Matricula matricula)throws Exception{

		Connection connection = ConnectionFactory.getConnection();
		// TODO
		
		connection.close();
	}
	public void deleteMatricula(Matricula matricula)throws Exception{
		// TODO
	}
}
