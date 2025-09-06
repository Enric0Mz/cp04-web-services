CREATE TABLE agendamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    aluno_id BIGINT NOT NULL,
    instrutor_id BIGINT NOT NULL,
    data DATETIME NOT NULL,
    motivo_cancelamento VARCHAR(100),
    data_cancelamento DATETIME,

    PRIMARY KEY(id),
    CONSTRAINT fk_agendamentos_aluno_id FOREIGN KEY (aluno_id) REFERENCES alunos(id),
    CONSTRAINT fk_agendamentos_instrutor_id FOREIGN KEY (instrutor_id) REFERENCES instructors(id)
);
