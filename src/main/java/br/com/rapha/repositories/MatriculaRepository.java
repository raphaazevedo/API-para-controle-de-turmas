package br.com.rapha.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

import br.com.rapha.entities.Matricula;
import br.com.rapha.factories.ConnectionFactory;

public class MatriculaRepository {

	public void insertMaticula(Matricula matricula, UUID turma_id, UUID aluno_id)throws Exception{

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO matricula (id_matricula, turma_id, aluno_id, data_matricula) VALUES (?, ?, ?, ?)");
		
		statement.setObject(1, matricula.getId_matricula());
		statement.setObject(2, turma_id);
		statement.setObject(3, aluno_id);
		statement.setObject(4, matricula.getData_matricula());
		
		statement.execute();
		
		
		connection.close();
	}
	public void deleteMatricula(Matricula matricula)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM matricula WHERE id_matricula = ?");
		
		statement.setObject(1, matricula.getId_matricula());
		
		statement.execute();
		
		connection.close();
		
	}
}
