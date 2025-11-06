-- FINTECH - Seeds (dados de exemplo)

INSERT INTO USUARIO (NOME, EMAIL, SENHA)
VALUES ('Usuário Teste', 'teste@fiap.com', '123456');

DECLARE
  v_user_id NUMBER;
BEGIN
  SELECT ID INTO v_user_id FROM USUARIO WHERE EMAIL = 'teste@fiap.com';

  INSERT INTO CONTA (ID_USUARIO, TIPO, AGENCIA, NUMERO, SALDO)
  VALUES (v_user_id, 'corrente', '0001', '12345-6', 1500.00);

  INSERT INTO DESPESA (ID_USUARIO, DESCRICAO, VALOR, CATEGORIA)
  VALUES (v_user_id, 'Supermercado', 250.75, 'Alimentação');

  INSERT INTO DESPESA (ID_USUARIO, DESCRICAO, VALOR, CATEGORIA)
  VALUES (v_user_id, 'Internet', 99.90, 'Serviços');
END;
/
COMMIT;
