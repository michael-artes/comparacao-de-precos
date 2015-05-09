ALTER TABLE web.item_orcamento
  ADD COLUMN id_empresa integer NOT NULL;
ALTER TABLE web.item_orcamento
  ADD CONSTRAINT empresa_item_orcamento_fk FOREIGN KEY (id_empresa) REFERENCES web.empresa (id) ON UPDATE RESTRICT ON DELETE RESTRICT;
COMMENT ON COLUMN web.item_orcamento.id_empresa IS 'id da Empresa';