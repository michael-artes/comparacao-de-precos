--CRIAR E-MAIL PARA O USUARIO
ALTER TABLE web.usuario
  ADD COLUMN email character varying(100) NOT NULL DEFAULT 'sem@email.com.br';
COMMENT ON COLUMN web.usuario.email IS 'E-mail do usuario';

--CRIAR E-MAIL PARA A EMPRESA
ALTER TABLE web.empresa
  ADD COLUMN email character varying(100) NOT NULL DEFAULT 'sem@email.com.br';
COMMENT ON COLUMN web.empresa.email IS 'E-mail do usuario';