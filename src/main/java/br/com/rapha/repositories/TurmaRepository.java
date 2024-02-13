package br.com.rapha.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rapha.entities.Professor;
import br.com.rapha.entities.Turma;
import br.com.rapha.factories.ConnectionFactory;

public class TurmaRepository {
	public void insertTurma(Turma turma, Professor professor)throws Exception{


		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSET INTO turma (id_turma, nome_turma, data_inicio, data_fim, id_professor) VALUES (?,?,?,?,?)");
		statement.setObject(1, turma.getId_turma());
		statement.setObject(2, turma.getNome_turma());
		statement.setObject(3, turma.getData_inicio());
		statement.setObject(4, turma.getData_termino());
		statement.setObject(5, turma.getProfessor().getId_professor());
		
		statement.execute();
		
		connection.close();
	}
	public void updateTurma(Turma turma, Professor professor)throws Exception{
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement
				("UPDATE turma SET nome_turma=?, data_inicio=?, data_fim=?, id_professor=? WHERE id_professor=?");
			
		statement.setObject(1, turma.getNome_turma());
		statement.setObject(2, turma.getData_inicio());
		statement.setObject(1, turma.getData_termino());
		statement.setObject(1, turma.getProfessor().getId_professor());
		statement.setObject(1, turma.getId_turma());
		
		
		statement.execute();
		
		connection.close();
	}
	public void deleteTurma(Turma turma)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM turma WHERE id_turma=?");
		
		statement.setObject(1, turma.getId_turma());
		
		statement.execute();
		
		
		
		connection.close();
		
	}
	public List<Turma> findAllTurma()throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM turma ORDEER BY nome_turma");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Turma> turmas = new ArrayList<Turma>();
		
		while(resultSet.next()) {
			
			Turma turma = new Turma();
			turma.setProfessor(new Professor());
			
			turma.setId_turma(UUID.fromString(resultSet.getString("id_turma")));
			turma.setNome_turma(resultSet.getString("nome_turma"));
			turma.setData_inicio(resultSet.getDate("data_inicio"));
			turma.setData_termino(resultSet.getDate("data_fim"));
			turma.getProfessor().setId_professor(UUID.fromString(resultSet.getString("professor_id")));
			
			turmas.add(turma);
		}
		
		
		connection.close();
		return turmas;
		
	}
	public Turma findByIdTurma(UUID id)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM turma WHERE id_turma=?");
		statement.setObject(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		Turma turma = null;
		
		if(resultSet.next()) {
			
			turma = new Turma();
			turma.setProfessor(new Professor());
			
			turma.setId_turma(UUID.fromString(resultSet.getString("id_turma")));
			turma.setNome_turma(resultSet.getString("nome_turma"));
			turma.setData_inicio(resultSet.getDate("data_inicio"));
			turma.setData_termino(resultSet.getDate("data_fim"));
			turma.getProfessor().setId_professor(UUID.fromString(resultSet.getString("professor_id")));
			
			
		}
		
		connection.close();
		return turma;
	}
}
