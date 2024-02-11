--criar a tabela de alunos
create table aluno(
	id_aluno	uuid			primary key,
	nome		varchar(100)	not null,
	matricula 	varchar(10)		not null unique,
	cpf			varchar(14)		not null unique
);
--criar a tabela de professores
create table professor(
	id_professor	uuid			primary key,
	nome			varchar(100)	not null,
	telefone	 	varchar(15)		not null
);
--criar a tabela de turmas
create table turma(
	id_turma		uuid			primary key,
	nome_turma		varchar(100)	not null,
	data_inicio 	Date,
	data_fim		Date,
	professor_id	uuid			not null,
	FOREIGN key(professor_id)
		references professor(id_professor)
);
--criar a tabela matricula
create table matricula(
	id_matricula	uuid			primary key,
	turma_id		uuid			not null,
	aluno_id		uuid			not null,
	data_matricula 	Date,
	FOREIGN key(turma_id)
		references turma(id_turma),
	FOREIGN key(aluno_id)
		references aluno(id_aluno)
);
