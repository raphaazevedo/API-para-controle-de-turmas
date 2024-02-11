package br.com.rapha.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rapha.entities.Aluno;
import br.com.rapha.factories.ConnectionFactory;

public class AlunoRepository {

	public void insertAluno(Aluno aluno)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("INSERT INTO aluno (id_aluno, nome, matricula, cpf)VALUES (?, ?, ?, ?)");
		
		statement.setObject(1, aluno.getId_aluno());
		statement.setObject(2, aluno.getNome());
		statement.setObject(3, aluno.getMatricula());
		statement.setObject(4, aluno.getCpf());
		
		statement.execute();
		
		connection.close();
		
	}
	public void updateAluno(Aluno aluno)throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("UPDATE aluno SET nome=?, matricula=?, cpf=? WHERE id_aluno=?");

		statement.setObject(1, aluno.getNome());
		statement.setObject(2, aluno.getMatricula());
		statement.setObject(3, aluno.getCpf());
		statement.setObject(4, aluno.getId_aluno());
		
		statement.execute();
		
		connection.close();
	}
	public void deleteAluno(Aluno aluno)throws Exception{

		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("DELETE FROM aluno WHERE id_aluno = ?");
		
		statement.setObject(1, aluno.getId_aluno());
		statement.execute();
		
		connection.close();
	}
	public List<Aluno> findAllAluno()throws Exception{

		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM aluno ORDER BY nome");
		ResultSet resultSet = statement.executeQuery();
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		while (resultSet.next()) {
			
			Aluno aluno = new Aluno();
			
			aluno.setId_aluno(UUID.fromString(resultSet.getString("id_aluno")));
			aluno.setNome(resultSet.getString("nome"));
			aluno.setMatricula(resultSet.getString("matricula"));
			aluno.setCpf(resultSet.getString("cpf"));

			alunos.add(aluno);
		}
		
		connection.close();
		return alunos;
	}
	public Aluno findByIdAluno(UUID id)throws Exception{

		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("SELECT * FROM aluno WHERE id_aluno=?");
		
		statement.setObject(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		Aluno aluno = null;
		
		if(resultSet.next()) {
			
			aluno = new Aluno();
			
			aluno.setId_aluno(UUID.fromString(resultSet.getString("id_aluno")));
			aluno.setNome(resultSet.getString("nome"));
			aluno.setMatricula(resultSet.getString("matricula"));
			aluno.setCpf(resultSet.getString("cpf"));
			
			
		}
			
		connection.close();
		return aluno;
	}
}
