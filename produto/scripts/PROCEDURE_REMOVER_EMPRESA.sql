-- PROCEDURE PARA EXCLUSAO DE EMPRESA. ANTES DE REMOVER A EMPRESA, ELE DEVE EXCLUIR TODAS AS DEPENDENCIAS.
CREATE OR REPLACE PROCEDURE EXCLUIR_EMPRESA 
	(P_CODEMP INTEGER)
AS 
	V_CODEMP INTEGER;
BEGIN
	/*REMOCAO DOS DADOS DA TABELA DE FUN_RESPONSAVEL.*/
	DELETE FROM FUN_RESPONSAVEL
	  WHERE CODEMP = P_CODEMP;
	/*REMOCAO DOS DADOS DA TABELA DE LEMBRETE*/  
	DELETE FROM LEMBRETE
	  WHERE EXISTS (
	    SELECT 1
	    FROM ATIVIDADE ATIV
	    WHERE ATIV.CODATI = LEMBRETE.CODATI
	      AND ATIV.CODEMP = P_CODEMP
	);
	/*REMOCAO DOS DADOS DA TABELA DE CONVIDADO.*/  
	DELETE FROM CONVIDADO
	WHERE EXISTS (
	  SELECT 1
	  FROM ATIVIDADE ATIV
	  WHERE ATIV.CODATI = CONVIDADO.CODATI
	    AND ATIV.CODEMP = P_CODEMP
	);
	/*REMOCAO DOS DADOS DA TABELA DE CLIENTE.*/
	DELETE FROM CLIENTE
	WHERE CODEMP = P_CODEMP;
	/*REMOCAO DOS DADOS DA TABELA DE ATIVIDADE.*/
	DELETE FROM ATIVIDADE
	  WHERE CODEMP = P_CODEMP;
	/*REMOCAO DOS DADOS DA TABELA DE CONTATO.*/
	DELETE FROM CONTATO
	  WHERE  EXISTS (
	  SELECT 1
	  FROM CLIENTE CLI
	  WHERE CLI.CODCLI = CONTATO.CODCLI
	    AND CLI.CODEMP = P_CODEMP
	);
	/*REMOCAO DOS DADOS DA TABELA DE FUNCIONARIO.*/
	DELETE FROM FUNCIONARIO
	  WHERE CODEMP = P_CODEMP;
	/*REMOCAO DOS DADOS DA TABELA DE EMPRESA*/  
	DELETE FROM EMPRESA
	  WHERE CODEMP = P_CODEMP;
END;
/