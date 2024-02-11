package br.com.rapha.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rapha.entities.Professor;
import br.com.rapha.factories.ConnectionFactory;

public class ProfessorRepository {

	public void insertProfessor(Professor professor)throws Exception{

		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO professor (id_professor, nome, telefone) VALUES (?, ?, ?)");
		
		statement.setObject(1, professor.getId_professor());
		statement.setObject(2, professor.getNome());
		statement.setObject(3, professor.getTelefone());
		
		statement.execute();
		
		
		connection.close();
		
	}
	public void updateProfessor(Professor professor)throws Exception{

		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("UPDATE professor SET nome=?, telefone=? WHERE id_professor=?");
		
		statement.setObject(1, professor.getNome());
		statement.setObject(2, professor.getTelefone());
		statement.setObject(3, professor.getId_professor());
		
		statement.execute();
		
		connection.close();
	}
	public void deleteProfessor(Professor professor)throws Exception{
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM professor WHERE id_professor=?");
		
		statement.setObject(1, professor.getId_professor());
		
		statement.execute();
		
		connection.close();
	}
	public List<Professor> findAllProfessor()throws Exception{

		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM professor ORDER BY nome");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Professor> professores = new ArrayList<Professor>();
		
		while(resultSet.next()) {
			
			Professor professor = new Professor();
			
			professor.setId_professor(UUID.fromString(resultSet.getString("id_professor")));
			professor.setNome(resultSet.getString("nome"));
			professor.setTelefone(resultSet.getString("telefone"));
			
			professores.add(professor);
			
			
		}
		
		connection.close();
		return professores;
		
	}
	public Professor findByIdProfessor(UUID id)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM professor WHERE id_professor=?");
		statement.setObject(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		Professor professor = null;
		
		if(resultSet.next()) {
			
			professor = new Professor();
			
			professor.setId_professor(UUID.fromString(resultSet.getString("id_professor")));
			professor.setNome(resultSet.getString("nome"));
			professor.setTelefone(resultSet.getString("telefone"));
			
			
			
		}
		
		
		
		connection.close();
		
		return professor;
	}
}
