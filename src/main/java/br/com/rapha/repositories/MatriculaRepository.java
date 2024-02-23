package br.com.rapha.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rapha.entities.Aluno;
import br.com.rapha.entities.Matricula;
import br.com.rapha.entities.Turma;
import br.com.rapha.factories.ConnectionFactory;

public class MatriculaRepository {

	public void insertMaticula(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO matricula (id_matricula, turma_id, aluno_id, data_matricula) VALUES (?, ?, ?, ?)");

		statement.setObject(1, matricula.getId_matricula());
		statement.setObject(2, matricula.getTurma().getId_turma());
		statement.setObject(3, matricula.getAluno().getId_aluno());
		statement.setObject(4, new java.sql.Date(matricula.getData_matricula().getTime()));

		statement.execute();

		connection.close();
	}

	public void deleteMatricula(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("DELETE FROM matricula WHERE id_matricula = ?");

		statement.setObject(1, matricula.getId_matricula());

		statement.execute();

		connection.close();

	}

	public List<Matricula> findAllMatricula() throws Exception {
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM matricula ORDER BY data_matricula");
		ResultSet resultSet = statement.executeQuery();

		List<Matricula> matriculas = new ArrayList<Matricula>();

		while (resultSet.next()) {
			Matricula matricula = new Matricula();
			matricula.setAluno(new Aluno());
			matricula.setTurma(new Turma());

			matricula.setId_matricula(UUID.fromString(resultSet.getString("id_matricula")));
			matricula.getTurma().setId_turma(UUID.fromString(resultSet.getString("turma_id")));
			matricula.getAluno().setId_aluno(UUID.fromString(resultSet.getString("aluno_id")));
			matricula
					.setData_matricula(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data_matricula")));

			matriculas.add(matricula);

		}

		connection.close();
		return matriculas;
	}

	public Matricula findByIdMatricula(UUID id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM matricula WHERE id_matricula = ?");

		statement.setObject(1, id);

		ResultSet resultSet = statement.executeQuery();

		Matricula matricula = null;

		if (resultSet.next()) {

			matricula = new Matricula();
			matricula.setAluno(new Aluno());
			matricula.setTurma(new Turma());

			matricula.setId_matricula(UUID.fromString(resultSet.getString("id_matricula")));
			matricula.getTurma().setId_turma(UUID.fromString(resultSet.getString("turma_id")));
			matricula.getAluno().setId_aluno(UUID.fromString(resultSet.getString("aluno_id")));
			matricula
					.setData_matricula(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data_matricula")));

		}

		connection.close();
		return matricula;

	}
}
