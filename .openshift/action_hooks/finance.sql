CREATE TABLE banco (
    id bigint NOT NULL,
    ativo boolean,
    codigo character varying(255),
    nome character varying(255),
    site character varying(255),
    instancia bigint
);

CREATE TABLE categoria (
    id bigint NOT NULL,
    nome character varying(80) NOT NULL,
    categoria_superior bigint,
    instancia bigint
);

CREATE TABLE cedente_sacado (
    id bigint NOT NULL,
    descricao text,
    nome character varying(80) NOT NULL,
    instancia bigint
);

CREATE TABLE conta (
    id bigint NOT NULL,
    nome character varying(30) NOT NULL,
    instancia bigint,
    limite numeric(18,2),
    saldo numeric(18,2)
);

CREATE TABLE conta_bancaria (
    agencia bigint,
    conta bigint,
    tipo_conta integer NOT NULL,
    id bigint NOT NULL,
    banco bigint
);

CREATE TABLE conta_cartao_credito (
    dia_vencimento integer NOT NULL,
    id bigint NOT NULL,
    conta_bancaria bigint,
    CONSTRAINT conta_cartao_credito_dia_vencimento_check CHECK (((dia_vencimento >= 1) AND (dia_vencimento <= 31)))
);

CREATE TABLE feriado (
    id bigint NOT NULL,
    data date NOT NULL,
    descricao character varying(100) NOT NULL,
    instancia bigint
);

CREATE TABLE instancia (
    id bigint NOT NULL,
    fundo_caixa numeric(18,2) NOT NULL,
    nome character varying(40) NOT NULL
);

CREATE TABLE lancamento (
    id bigint NOT NULL,
    descricao character varying(60),
    historico character varying(255),
    tipo_lancamento integer,
    categoria bigint,
    cedente_sacado bigint,
    instancia bigint,
    CONSTRAINT check_tipo_lancamento CHECK ((tipo_lancamento = ANY (ARRAY[1, 2, 3])))
);

CREATE TABLE parcela_lancamento (
    id bigint NOT NULL,
    data_pgto date,
    data_vencimento date,
    parcela integer,
    valor numeric(18,2),
    conta bigint,
    lancamento bigint
);

CREATE SEQUENCE seq_banco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_categoria
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_cedente_sacado
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_conta
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_feriado
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_instancia
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_lancamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_parcela_lancamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE usuario (
    id bigint NOT NULL,
    email character varying(80) NOT NULL,
    nome character varying(120) NOT NULL,
    senha character varying(40) NOT NULL,
    instancia bigint
);

ALTER TABLE ONLY banco    ADD CONSTRAINT banco_codigo_nome_instancia_key UNIQUE (codigo, nome, instancia);
ALTER TABLE ONLY banco    ADD CONSTRAINT banco_pkey PRIMARY KEY (id);
ALTER TABLE ONLY categoria    ADD CONSTRAINT categoria_nome_instancia_categoria_superior_key UNIQUE (nome, instancia, categoria_superior);
ALTER TABLE ONLY categoria    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);
ALTER TABLE ONLY cedente_sacado    ADD CONSTRAINT cedente_sacado_nome_instancia_key UNIQUE (nome, instancia);
ALTER TABLE ONLY cedente_sacado    ADD CONSTRAINT cedente_sacado_pkey PRIMARY KEY (id);
ALTER TABLE ONLY conta_bancaria    ADD CONSTRAINT conta_bancaria_pkey PRIMARY KEY (id);
ALTER TABLE ONLY conta_cartao_credito    ADD CONSTRAINT conta_cartao_credito_pkey PRIMARY KEY (id);
ALTER TABLE ONLY conta    ADD CONSTRAINT conta_pkey PRIMARY KEY (id);
ALTER TABLE ONLY feriado    ADD CONSTRAINT feriado_descricao_key UNIQUE (descricao);
ALTER TABLE ONLY feriado    ADD CONSTRAINT feriado_pkey PRIMARY KEY (id);
ALTER TABLE ONLY instancia    ADD CONSTRAINT instancia_nome_key UNIQUE (nome);
ALTER TABLE ONLY instancia    ADD CONSTRAINT instancia_pkey PRIMARY KEY (id);
ALTER TABLE ONLY lancamento    ADD CONSTRAINT lancamento_pkey PRIMARY KEY (id);
ALTER TABLE ONLY parcela_lancamento    ADD CONSTRAINT parcela_lancamento_pkey PRIMARY KEY (id);
ALTER TABLE ONLY usuario    ADD CONSTRAINT usuario_email_instancia_key UNIQUE (email, instancia);
ALTER TABLE ONLY usuario    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
ALTER TABLE ONLY banco    ADD CONSTRAINT fk_banco_x_instancia FOREIGN KEY (instancia) REFERENCES instancia(id);
ALTER TABLE ONLY categoria    ADD CONSTRAINT fk_categoria_x_categoria_superior FOREIGN KEY (categoria_superior) REFERENCES categoria(id);
ALTER TABLE ONLY categoria    ADD CONSTRAINT fk_categoria_x_instancia FOREIGN KEY (instancia) REFERENCES instancia(id);
ALTER TABLE ONLY cedente_sacado    ADD CONSTRAINT fk_cedente_sacado_x_instancia FOREIGN KEY (instancia) REFERENCES instancia(id);
ALTER TABLE ONLY conta_bancaria    ADD CONSTRAINT fk_conta_bancaria_x_banco FOREIGN KEY (banco) REFERENCES banco(id);
ALTER TABLE ONLY conta_bancaria    ADD CONSTRAINT fk_conta_bancaria_x_conta FOREIGN KEY (id) REFERENCES conta(id);
ALTER TABLE ONLY conta_cartao_credito    ADD CONSTRAINT fk_conta_cartao_credito_x_conta FOREIGN KEY (id) REFERENCES conta(id);
ALTER TABLE ONLY conta_cartao_credito    ADD CONSTRAINT fk_conta_cartao_credito_x_conta_bancaria FOREIGN KEY (conta_bancaria) REFERENCES conta_bancaria(id);
ALTER TABLE ONLY conta    ADD CONSTRAINT fk_conta_x_instancia FOREIGN KEY (instancia) REFERENCES instancia(id);
ALTER TABLE ONLY feriado    ADD CONSTRAINT fk_feriado_x_instancia FOREIGN KEY (instancia) REFERENCES instancia(id);
ALTER TABLE ONLY lancamento    ADD CONSTRAINT fk_lancamento_x_categoria FOREIGN KEY (categoria) REFERENCES categoria(id);
ALTER TABLE ONLY lancamento    ADD CONSTRAINT fk_lancamento_x_cedente_sacado FOREIGN KEY (cedente_sacado) REFERENCES cedente_sacado(id);
ALTER TABLE ONLY lancamento    ADD CONSTRAINT fk_lancamento_x_instancia FOREIGN KEY (instancia) REFERENCES instancia(id);
ALTER TABLE ONLY parcela_lancamento    ADD CONSTRAINT fk_parcela_lancamento_x_conta FOREIGN KEY (conta) REFERENCES conta(id);
ALTER TABLE ONLY parcela_lancamento    ADD CONSTRAINT fk_parcela_lancamento_x_lancamento FOREIGN KEY (lancamento) REFERENCES lancamento(id);
ALTER TABLE ONLY usuario    ADD CONSTRAINT fk_usuario_x_instancia FOREIGN KEY (instancia) REFERENCES instancia(id);

insert into instancia (id, fundo_caixa, nome) values (1, 500.00, 'Geral');
insert into usuario (id, email, nome, senha, instancia) values (1, 'fernando@fsilveira.com.br', 'Fernando Silveira', '123456', 1);

insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '246', 'Banco ABC Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '025', 'Banco Alfa S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '641', 'Banco Alvorada S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '029', 'Banco Banerj S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '038', 'Banco Banestado S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '000', 'Banco Bankpar S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '740', 'Banco Barclays S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '107', 'Banco BBM S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '031', 'Banco Beg S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '096', 'Banco BM&F de Serviços de Liquidação e Custódia S.A');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '318', 'Banco BMG S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '752', 'Banco BNP Paribas Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '248', 'Banco Boavista Interatlântico S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '036', 'Banco Bradesco BBI S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '204', 'Banco Bradesco Cartões S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '237', 'Banco Bradesco S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '225', 'Banco Brascan S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '044', 'Banco BVA S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '263', 'Banco Cacique S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '473', 'Banco Caixa Geral - Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '222', 'Banco Calyon Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '040', 'Banco Cargill S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '745', 'Banco Citibank S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M08', 'Banco Citicard S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M19', 'Banco CNH Capital S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '215', 'Banco Comercial e de Investimento Sudameris S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '756', 'Banco Cooperativo do Brasil S.A. - BANCOOB');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '748', 'Banco Cooperativo Sicredi S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '505', 'Banco Credit Suisse (Brasil) S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '229', 'Banco Cruzeiro do Sul S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '---', 'Banco CSF S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '003', 'Banco da Amazônia S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '083', 'Banco da China Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '707', 'Banco Daycoval S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M06', 'Banco de Lage Landen Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '024', 'Banco de Pernambuco S.A. - BANDEPE');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '456', 'Banco de Tokyo-Mitsubishi UFJ Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '214', 'Banco Dibens S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '001', 'Banco do Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '047', 'Banco do Estado de Sergipe S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '037', 'Banco do Estado do Pará S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '041', 'Banco do Estado do Rio Grande do Sul S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '004', 'Banco do Nordeste do Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '265', 'Banco Fator S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M03', 'Banco Fiat S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '224', 'Banco Fibra S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '626', 'Banco Ficsa S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '---', 'Banco Fidis S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '394', 'Banco Finasa BMC S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M18', 'Banco Ford S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '233', 'Banco GE Capital S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '734', 'Banco Gerdau S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M07', 'Banco GMAC S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '612', 'Banco Guanabara S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M22', 'Banco Honda S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '063', 'Banco Ibi S.A. Banco Múltiplo');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M11', 'Banco IBM S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '604', 'Banco Industrial do Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '320', 'Banco Industrial e Comercial S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '653', 'Banco Indusval S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '630', 'Banco Intercap S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '249', 'Banco Investcred Unibanco S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '184', 'Banco Itaú BBA S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '341', 'Banco Itaú S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '479', 'Banco ItaúBank S.A');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '---', 'Banco Itaucard S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M09', 'Banco Itaucred Financiamentos S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '376', 'Banco J. P. Morgan S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '074', 'Banco J. Safra S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '217', 'Banco John Deere S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '065', 'Banco Lemon S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '600', 'Banco Luso Brasileiro S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '389', 'Banco Mercantil do Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '755', 'Banco Merrill Lynch de Investimentos S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '746', 'Banco Modal S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '151', 'Banco Nossa Caixa S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '045', 'Banco Opportunity S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '623', 'Banco Panamericano S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '611', 'Banco Paulista S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '643', 'Banco Pine S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '---', 'Banco Porto Real de Investimentos S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '638', 'Banco Prosper S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '747', 'Banco Rabobank International Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '356', 'Banco Real S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '633', 'Banco Rendimento S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M16', 'Banco Rodobens S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '072', 'Banco Rural Mais S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '453', 'Banco Rural S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '422', 'Banco Safra S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '033', 'Banco Santander (Brasil) S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '250', 'Banco Schahin S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '749', 'Banco Simples S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '366', 'Banco Société Générale Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '637', 'Banco Sofisa S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '012', 'Banco Standard de Investimentos S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '464', 'Banco Sumitomo Mitsui Brasileiro S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '082', 'Banco Topázio S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M20', 'Banco Toyota do Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '634', 'Banco Triângulo S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '208', 'Banco UBS Pactual S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M14', 'Banco Volkswagen S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, 'M23', 'Banco Volvo (Brasil) S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '655', 'Banco Votorantim S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '610', 'Banco VR S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '370', 'Banco WestLB do Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '---', 'Banco Yamaha Motor S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '021', 'BANESTES S.A. Banco do Estado do Espírito Santo');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '719', 'Banif-Banco Internacional do Funchal (Brasil)S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '073', 'BB Banco Popular do Brasil S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '078', 'BES Investimento do Brasil S.A.-Banco de Investimento');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '069', 'BPN Brasil Banco Múltiplo S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '070', 'BRB - Banco de Brasília S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '104', 'Caixa Econômica Federal');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '477', 'Citibank N.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '081', 'Concórdia Banco S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '487', 'Deutsche Bank S.A. - Banco Alemão');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '751', 'Dresdner Bank Brasil S.A. - Banco Múltiplo');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '062', 'Hipercard Banco Múltiplo S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '399', 'HSBC Bank Brasil S.A. - Banco Múltiplo');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '492', 'ING Bank N.V.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '652', 'Itaú Unibanco Holding S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '488', 'JPMorgan Chase Bank');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '409', 'UNIBANCO - União de Bancos Brasileiros S.A.');
insert into banco (id, ativo, instancia, site, codigo, nome) values (nextval('seq_banco'), true, 1, null, '230', 'Unicard Banco Múltiplo S.A.');

INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'), 'Abastecimento', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'), 'Educação', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'), 'Farmácia', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'), 'Salário', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'), 'Mercado', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'), 'Emprestimos', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Sem Categoria', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Transferência', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Alimentação', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Churrasco/Festa', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Lanche', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Padaria', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Restaurante', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Supermercado', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Bancos', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Empréstimos', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Encargos', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Estorno', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Juros', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Tarifas', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Cópias/Impressões', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'CPMF', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Educação', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Outros Gastos', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Universidade', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Hospedagem Site', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Impostos', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Investimento', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Capitalização', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Poupança', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'IOF', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'IRRF', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Lazer', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Viagem', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Licenciamento Anual', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Mensalidade Internet', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Moradia', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Aluguel', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Condomínio', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Internet', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'IPTU', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Luz', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Manutenção', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Mobiliário', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Telefone', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Celular', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Fixo', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'TV à Cabo', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Utensílios', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Pagamento Cartão', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Receita', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Férias', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Salário - 13º', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Saúde', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Dentista', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Farmácia', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Vestuário', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Financiamento', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Multa', null, 1);
INSERT INTO categoria(id, nome, categoria_superior, instancia) VALUES (nextval('seq_categoria'),'Seguro DPVAT', null, 1);

INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Giassi', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Posto Falcão', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Angeloni', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Big', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Unisul', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'JExperts', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Banco do Brasil', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Cetelem', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'BV Financeira', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Não informado', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Detran/SC', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'DEPTO DE POLICIA RODOVIARIA FEDERAL', 1);

INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Valetão Pneus ', 1);
INSERT INTO cedente_sacado(id, descricao, nome, instancia) VALUES (nextval('seq_cedente_sacado'), '', 'Havan', 1);


INSERT INTO banco(
            id,  nome, instancia)
    VALUES (nextval('seq_banco'), 'Credifiesc', 1);



insert into conta(id, nome, instancia, limite, saldo) values (nextval('seq_conta'), 'C/C Capoeiras', 1, 100.00, -100.00);	
INSERT INTO conta_bancaria(id, agencia, conta, tipo_conta, banco) VALUES ((select id from conta where nome = 'Ag Capoeiras'), 30473, 78417,  1, (select id from banco where nome = 'Banco do Brasil S.A.'));

insert into conta(id, nome, instancia, limite, saldo) values (nextval('seq_conta'), 'C/C Integração', 1, 0, -100.00);	
INSERT INTO conta_bancaria(id, agencia, conta, tipo_conta, banco) VALUES ((select id from conta where nome = 'C/C Integração'), 34207, 89868-6,  1, (select id from banco where nome = 'Banco do Brasil S.A.'));

insert into conta(id, nome, instancia, limite, saldo) values (nextval('seq_conta'), 'C/C', 1, 0, -100.00);	
INSERT INTO conta_bancaria(id, agencia, conta, tipo_conta, banco) VALUES ((select id from conta where nome = 'C/C'), 0, 32875,  1, (select id from banco where nome = 'Credifiesc'));


insert into conta(id, nome, instancia, limite, saldo) values (nextval('seq_conta'), 'Master Submarino', 1, 100.00, -100.00);	
INSERT INTO conta_cartao_credito(id, dia_vencimento, conta_bancaria) VALUES ((select id from conta where nome = 'Master Submarino'), 15, null);

insert into conta(id, nome, instancia, limite, saldo) values (nextval('seq_conta'), 'Visa Integração', 1, 100.00, -100.00);	
INSERT INTO conta_cartao_credito(id, dia_vencimento, conta_bancaria) VALUES ((select id from conta where nome = 'Visa Integração'), 10, (select id from conta where nome = 'C/C Integração'));


ALTER TABLE parcela_lancamento ADD COLUMN valor_real numeric(18,2);
UPDATE parcela_lancamento SET valor_real=valor;

