--Tabelas

ALTER TABLE web.anuncio
  OWNER TO "comparison-shopp";

  ALTER TABLE web.empresa
  OWNER TO "comparison-shopp";

ALTER TABLE web.item_orcamento
  OWNER TO "comparison-shopp";

ALTER TABLE web.orcamento
  OWNER TO "comparison-shopp";

ALTER TABLE web.palavras_chaves
  OWNER TO "comparison-shopp";

ALTER TABLE web.usuario
  OWNER TO "comparison-shopp";


--Sequences

ALTER SEQUENCE web.anuncio_id_seq
  OWNER TO "comparison-shopp";

  ALTER SEQUENCE web.empresa_id_seq
  OWNER TO "comparison-shopp";

ALTER SEQUENCE web.item_orcamento_id_seq
  OWNER TO "comparison-shopp";

ALTER SEQUENCE web.orcamento_id_seq
  OWNER TO "comparison-shopp";

ALTER SEQUENCE web.palavras_chaves_id_seq
  OWNER TO "comparison-shopp";

ALTER SEQUENCE web.usuario_id_seq
  OWNER TO "comparison-shopp";  