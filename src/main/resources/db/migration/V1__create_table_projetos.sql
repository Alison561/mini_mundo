CREATE TABLE projetos
(
    id        SERIAL PRIMARY KEY,
    nome      VARCHAR(255) NOT NULL UNIQUE,
    descricao TEXT,
    status    VARCHAR(10)  NOT NULL CHECK (status IN ('Ativo', 'Inativo')),
    orcamento NUMERIC(12, 2)
);