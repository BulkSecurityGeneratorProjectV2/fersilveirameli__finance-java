ALTER TABLE lancamento DROP CONSTRAINT check_tipo_lancamento;
ALTER TABLE lancamento ADD CONSTRAINT check_tipo_lancamento CHECK (tipo_lancamento = ANY (ARRAY[0,1,2]));
update lancamento set tipo_lancamento = tipo_lancamento -1;
ALTER TABLE conta ADD COLUMN conta_type char;
ALTER TABLE conta ADD CONSTRAINT check_conta_type CHECK (conta_type = ANY (ARRAY['B','C']));