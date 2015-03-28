--Criação inicial das tabelas

CREATE SEQUENCE web.usuario_id_seq;

CREATE TABLE web.usuario (
                id INTEGER NOT NULL DEFAULT nextval('web.usuario_id_seq'),
                login VARCHAR(50) NOT NULL,
                nome VARCHAR(100) NOT NULL,
                senha VARCHAR(25) NOT NULL,
                perfil INTEGER NOT NULL,
                ativo BOOLEAN DEFAULT false NOT NULL,
                data_cadastro TIMESTAMP NOT NULL,
                data_alteracao TIMESTAMP,
                CONSTRAINT usuario_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN web.usuario.id IS 'id do usuário';
COMMENT ON COLUMN web.usuario.login IS 'Login do usuário';
COMMENT ON COLUMN web.usuario.nome IS 'Nome do usuário';
COMMENT ON COLUMN web.usuario.senha IS 'Senha do usuário';
COMMENT ON COLUMN web.usuario.perfil IS 'Ira indicar o perfil de cada usuario no sistema.';
COMMENT ON COLUMN web.usuario.ativo IS 'Campo que irá verificar se o usuário está ativo no sistema';
COMMENT ON COLUMN web.usuario.data_cadastro IS 'Informa a data que foi cadastrado o usuário';
COMMENT ON COLUMN web.usuario.data_alteracao IS 'Data em que o usuario alterou o seu cadastro';


ALTER SEQUENCE web.usuario_id_seq OWNED BY web.usuario.id;

CREATE UNIQUE INDEX usuario_idx
 ON web.usuario USING BTREE
 ( id );

CREATE SEQUENCE web.empresa_id_seq;

CREATE TABLE web.empresa (
                id INTEGER NOT NULL DEFAULT nextval('web.empresa_id_seq'),
                id_usuario INTEGER NOT NULL,
                cnpj VARCHAR(20) NOT NULL,
                nome_fantasia VARCHAR(150) NOT NULL,
                razao_social VARCHAR(150) NOT NULL,
                descricao VARCHAR(250) NOT NULL,
                ativo BOOLEAN NOT NULL,
                data_cadastro TIMESTAMP NOT NULL,
                data_alteracao TIMESTAMP,
                CONSTRAINT empresa_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN web.empresa.id IS 'id da tabela';
COMMENT ON COLUMN web.empresa.id_usuario IS 'Id do usuario em que a empresa estara relacionada';
COMMENT ON COLUMN web.empresa.cnpj IS 'Cnpj da empresa';
COMMENT ON COLUMN web.empresa.nome_fantasia IS 'Nome fantasia da empresa';
COMMENT ON COLUMN web.empresa.razao_social IS 'Razão social da empresa';
COMMENT ON COLUMN web.empresa.descricao IS 'Descrição: exemplo endereço';
COMMENT ON COLUMN web.empresa.ativo IS 'Empresa está ativa?';
COMMENT ON COLUMN web.empresa.data_cadastro IS 'Data do cadastro da empresa';
COMMENT ON COLUMN web.empresa.data_alteracao IS 'Data em que a empresa alterou o seu cadastro';


ALTER SEQUENCE web.empresa_id_seq OWNED BY web.empresa.id;

CREATE UNIQUE INDEX empresa_idx
 ON web.empresa USING BTREE
 ( id );

CREATE SEQUENCE web.anuncio_id_seq;

CREATE TABLE web.anuncio (
                id INTEGER NOT NULL DEFAULT nextval('web.anuncio_id_seq'),
                id_empresa INTEGER NOT NULL,
                nome VARCHAR(50) NOT NULL,
                descricao VARCHAR(250) NOT NULL,
                valor NUMERIC(10,2) NOT NULL,
                data_cadastro TIMESTAMP NOT NULL,
                data_alteracao TIMESTAMP,
                imagem BYTEA,
                CONSTRAINT anuncio_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN web.anuncio.id IS 'id da tabela anuncio';
COMMENT ON COLUMN web.anuncio.id_empresa IS 'Informará o id da tabela empresa';
COMMENT ON COLUMN web.anuncio.nome IS 'Nome do anuncio';
COMMENT ON COLUMN web.anuncio.descricao IS 'Descrição do anuncio';
COMMENT ON COLUMN web.anuncio.valor IS 'Valor do anúncio';
COMMENT ON COLUMN web.anuncio.data_cadastro IS 'Data que foi cadastrado o anúncio';
COMMENT ON COLUMN web.anuncio.data_alteracao IS 'Data em que o anúncio foi alterado';
COMMENT ON COLUMN web.anuncio.imagem IS 'Onde será gravado os bytes das imagens.';


ALTER SEQUENCE web.anuncio_id_seq OWNED BY web.anuncio.id;

CREATE UNIQUE INDEX anuncio_idx
 ON web.anuncio USING BTREE
 ( id );

CREATE SEQUENCE web.palavras_chaves_id_seq;

CREATE TABLE web.palavras_chaves (
                id INTEGER NOT NULL DEFAULT nextval('web.palavras_chaves_id_seq'),
                id_anuncio INTEGER NOT NULL,
                palavra_chave VARCHAR(100) NOT NULL,
                CONSTRAINT palavras_chaves_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN web.palavras_chaves.id IS 'Id da tabela palavras chave';
COMMENT ON COLUMN web.palavras_chaves.id_anuncio IS 'Indica o id do anuncio';
COMMENT ON COLUMN web.palavras_chaves.palavra_chave IS 'Irá ser o nome da palavra chave';


ALTER SEQUENCE web.palavras_chaves_id_seq OWNED BY web.palavras_chaves.id;

CREATE UNIQUE INDEX palavras_chaves_idx
 ON web.palavras_chaves USING BTREE
 ( id );

CREATE SEQUENCE web.orcamento_id_seq;

CREATE TABLE web.orcamento (
                id INTEGER NOT NULL DEFAULT nextval('web.orcamento_id_seq'),
                id_usuario INTEGER NOT NULL,
                status INTEGER NOT NULL,
                data_cadastro TIMESTAMP NOT NULL,
                CONSTRAINT orcamento_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN web.orcamento.id IS 'id da tabela orcamento';
COMMENT ON COLUMN web.orcamento.id_usuario IS 'id usuário, que define para qual usuário o orçamento foi gerado';
COMMENT ON COLUMN web.orcamento.status IS 'Status do orçamento';
COMMENT ON COLUMN web.orcamento.data_cadastro IS 'Data em que o orçamento foi gerado';


ALTER SEQUENCE web.orcamento_id_seq OWNED BY web.orcamento.id;

CREATE UNIQUE INDEX orcamento_idx
 ON web.orcamento USING BTREE
 ( id );

CREATE SEQUENCE web.item_orcamento_id_seq;

CREATE TABLE web.item_orcamento (
                id INTEGER NOT NULL DEFAULT nextval('web.item_orcamento_id_seq'),
                id_orcamento INTEGER NOT NULL,
                descricao VARCHAR(250) NOT NULL,
                valor NUMERIC(10,2) NOT NULL,
                CONSTRAINT item_orcamento_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN web.item_orcamento.id IS 'id da tabela item_orcamento';
COMMENT ON COLUMN web.item_orcamento.id_orcamento IS 'id do orçamento';
COMMENT ON COLUMN web.item_orcamento.descricao IS 'descrição do item';
COMMENT ON COLUMN web.item_orcamento.valor IS 'Valor do item';


ALTER SEQUENCE web.item_orcamento_id_seq OWNED BY web.item_orcamento.id;

CREATE UNIQUE INDEX item_orcamento_idx
 ON web.item_orcamento USING BTREE
 ( id );

ALTER TABLE web.orcamento ADD CONSTRAINT usuario_orcamento_fk
FOREIGN KEY (id_usuario)
REFERENCES web.usuario (id)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE web.empresa ADD CONSTRAINT usuario_empresa_fk
FOREIGN KEY (id_usuario)
REFERENCES web.usuario (id)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE web.anuncio ADD CONSTRAINT empresa_anuncio_fk
FOREIGN KEY (id_empresa)
REFERENCES web.empresa (id)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE web.palavras_chaves ADD CONSTRAINT anuncio_palavras_chaves_fk
FOREIGN KEY (id_anuncio)
REFERENCES web.anuncio (id)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE web.item_orcamento ADD CONSTRAINT orcamento_item_orcamento_fk
FOREIGN KEY (id_orcamento)
REFERENCES web.orcamento (id)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;
