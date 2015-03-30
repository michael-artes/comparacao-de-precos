--Altera nome da tabela
ALTER TABLE web.palavras_chaves
  RENAME TO palavras_chave;

--Altera nome da sequence  
ALTER SEQUENCE web.palavras_chaves_id_seq
  RENAME TO palavras_chave_id_seq;
  
--Altera nome de campo  
ALTER TABLE web.palavras_chave RENAME palavra_chave  TO nome_chave;
COMMENT ON COLUMN web.palavras_chave.nome_chave IS 'Irá ser o nome da palavra chave';
