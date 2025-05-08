CREATE TABLE tarefas
(
    id                     SERIAL PRIMARY KEY,
    descricao              TEXT    NOT NULL,
    projeto_id             INTEGER NOT NULL,
    data_inicio            DATE,
    data_fim               DATE,
    tarefa_predecessora_id INTEGER,
    status                 VARCHAR(20) CHECK (status IN ('Concluída', 'Não Concluída')),
    CONSTRAINT fk_projeto FOREIGN KEY (projeto_id) REFERENCES projetos (id),
    CONSTRAINT fk_tarefa_predecessora FOREIGN KEY (tarefa_predecessora_id) REFERENCES tarefas (id)
);
