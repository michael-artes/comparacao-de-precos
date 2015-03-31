--ALTERACAO DO NOME NA TABELA PALAVRAS_CHAVE
ALTER TABLE web.palavras_chaves
  RENAME TO palavras_chave;

--ALTERACAO DE NOME NA SEQUENCE PALAVRAS_CHAVE_ID_SEQ
ALTER SEQUENCE web.palavras_chaves_id_seq
  RENAME TO palavras_chave_id_seq;
  
--ALTERACAO DE CAMPO NA TABELA PALAVRAS_CHAVE  
ALTER TABLE web.palavras_chave RENAME palavra_chave TO nome_chave;
COMMENT ON COLUMN web.palavras_chave.nome_chave IS 'Irá ser o nome da palavra chave';



--CRIACAO DE CONSTRAINTS NA TABELA USUARIO
ALTER TABLE web.palavras_chave
  ADD CONSTRAINT uk_palavras_chave_id_anuncio_nome_chave UNIQUE (id_anuncio, nome_chave);
  
  

--CRIACAO DE CONSTRAINTS NA TABELA EMPRESA
ALTER TABLE web.empresa
  ADD CONSTRAINT uk_empresa_id_usuario UNIQUE (id_usuario);
ALTER TABLE web.empresa
  ADD CONSTRAINT uk_empresa_cnpj UNIQUE (cnpj);
  

  
--CRIACAO DE CONSTRAINTS NA TABELA USUARIO
ALTER TABLE web.usuario
  ADD CONSTRAINT uk_usuario_login UNIQUE (login);
  
