--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-06-12 15:12:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16828)
-- Name: alunos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alunos (
    id integer NOT NULL,
    cpf character varying(14) NOT NULL,
    nome character varying(100) NOT NULL,
    idade integer NOT NULL,
    email character varying(100) NOT NULL,
    plano_id integer NOT NULL,
    data_inicio_plano character varying(10) NOT NULL,
    data_termino_plano character varying(10),
    valor_pago numeric(10,2),
    tipo_plano character varying,
    CONSTRAINT alunos_email_check CHECK ((POSITION(('@'::text) IN (email)) > 0)),
    CONSTRAINT alunos_idade_check CHECK ((idade > 0))
);


ALTER TABLE public.alunos OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16835)
-- Name: alunos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alunos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.alunos_id_seq OWNER TO postgres;

--
-- TOC entry 4839 (class 0 OID 0)
-- Dependencies: 216
-- Name: alunos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alunos_id_seq OWNED BY public.alunos.id;


--
-- TOC entry 217 (class 1259 OID 16836)
-- Name: dados_funcionarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dados_funcionarios (
    funcionario_id integer NOT NULL,
    login character varying(100) NOT NULL,
    senha character varying(100) NOT NULL,
    nome character varying(100) NOT NULL,
    cpf character varying(11) NOT NULL,
    telefone character varying(9) NOT NULL,
    cargo character varying(100) NOT NULL
);


ALTER TABLE public.dados_funcionarios OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16839)
-- Name: dados_funcionarios_funcionario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dados_funcionarios_funcionario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.dados_funcionarios_funcionario_id_seq OWNER TO postgres;

--
-- TOC entry 4842 (class 0 OID 0)
-- Dependencies: 218
-- Name: dados_funcionarios_funcionario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dados_funcionarios_funcionario_id_seq OWNED BY public.dados_funcionarios.funcionario_id;


--
-- TOC entry 219 (class 1259 OID 16840)
-- Name: dados_funcionarios_id_funcionario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dados_funcionarios_id_funcionario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.dados_funcionarios_id_funcionario_seq OWNER TO postgres;

--
-- TOC entry 4844 (class 0 OID 0)
-- Dependencies: 219
-- Name: dados_funcionarios_id_funcionario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dados_funcionarios_id_funcionario_seq OWNED BY public.dados_funcionarios.funcionario_id;


--
-- TOC entry 220 (class 1259 OID 16841)
-- Name: planos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planos (
    id integer NOT NULL,
    tipo character varying(10) NOT NULL,
    data_inicio character varying(10) NOT NULL,
    valor numeric(10,2) NOT NULL,
    CONSTRAINT planos_tipo_check CHECK (((tipo)::text = ANY (ARRAY[('MENSAL'::character varying)::text, ('TRIMESTRAL'::character varying)::text, ('SEMESTRAL'::character varying)::text])))
);


ALTER TABLE public.planos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16845)
-- Name: planos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.planos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.planos_id_seq OWNER TO postgres;

--
-- TOC entry 4847 (class 0 OID 0)
-- Dependencies: 221
-- Name: planos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.planos_id_seq OWNED BY public.planos.id;


--
-- TOC entry 222 (class 1259 OID 16846)
-- Name: registros_horarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registros_horarios (
    id integer NOT NULL,
    cpf character varying(11) NOT NULL,
    nome character varying(100) NOT NULL,
    entrada timestamp without time zone,
    saida timestamp without time zone,
    data date
);


ALTER TABLE public.registros_horarios OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16849)
-- Name: registros_horarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.registros_horarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.registros_horarios_id_seq OWNER TO postgres;

--
-- TOC entry 4850 (class 0 OID 0)
-- Dependencies: 223
-- Name: registros_horarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.registros_horarios_id_seq OWNED BY public.registros_horarios.id;


--
-- TOC entry 224 (class 1259 OID 16850)
-- Name: treinos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.treinos (
    id integer NOT NULL,
    aluno_id integer NOT NULL,
    funcionario_id integer NOT NULL,
    data date NOT NULL,
    descricao text NOT NULL,
    duracao integer,
    nome_aluno character varying(100),
    nome_funcionario character varying(100)
);


ALTER TABLE public.treinos OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16855)
-- Name: treinos_com_nomes; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.treinos_com_nomes AS
 SELECT t.id,
    t.aluno_id,
    t.funcionario_id,
    t.data,
    t.descricao,
    t.duracao,
    a.nome AS nome_aluno,
    f.nome AS nome_funcionario
   FROM ((public.treinos t
     JOIN public.alunos a ON ((t.aluno_id = a.id)))
     JOIN public.dados_funcionarios f ON ((t.funcionario_id = f.funcionario_id)));


ALTER VIEW public.treinos_com_nomes OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16860)
-- Name: treinos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.treinos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.treinos_id_seq OWNER TO postgres;

--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 226
-- Name: treinos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.treinos_id_seq OWNED BY public.treinos.id;


--
-- TOC entry 4659 (class 2604 OID 16861)
-- Name: alunos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos ALTER COLUMN id SET DEFAULT nextval('public.alunos_id_seq'::regclass);


--
-- TOC entry 4660 (class 2604 OID 16862)
-- Name: dados_funcionarios funcionario_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dados_funcionarios ALTER COLUMN funcionario_id SET DEFAULT nextval('public.dados_funcionarios_id_funcionario_seq'::regclass);


--
-- TOC entry 4661 (class 2604 OID 16863)
-- Name: planos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planos ALTER COLUMN id SET DEFAULT nextval('public.planos_id_seq'::regclass);


--
-- TOC entry 4662 (class 2604 OID 16864)
-- Name: registros_horarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registros_horarios ALTER COLUMN id SET DEFAULT nextval('public.registros_horarios_id_seq'::regclass);


--
-- TOC entry 4663 (class 2604 OID 16865)
-- Name: treinos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinos ALTER COLUMN id SET DEFAULT nextval('public.treinos_id_seq'::regclass);


--
-- TOC entry 4821 (class 0 OID 16828)
-- Dependencies: 215
-- Data for Name: alunos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alunos (id, cpf, nome, idade, email, plano_id, data_inicio_plano, data_termino_plano, valor_pago, tipo_plano) FROM stdin;
3	09910358441	Ramon	21	diegoalbuquerque11@hotmail.com	2	2021-04-25	2021-07-27	100.00	Trimestral
\.


--
-- TOC entry 4823 (class 0 OID 16836)
-- Dependencies: 217
-- Data for Name: dados_funcionarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dados_funcionarios (funcionario_id, login, senha, nome, cpf, telefone, cargo) FROM stdin;
3	teste	teste	teste	14523698752	123456789	teste
\.


--
-- TOC entry 4826 (class 0 OID 16841)
-- Dependencies: 220
-- Data for Name: planos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.planos (id, tipo, data_inicio, valor) FROM stdin;
\.


--
-- TOC entry 4828 (class 0 OID 16846)
-- Dependencies: 222
-- Data for Name: registros_horarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.registros_horarios (id, cpf, nome, entrada, saida, data) FROM stdin;
\.


--
-- TOC entry 4830 (class 0 OID 16850)
-- Dependencies: 224
-- Data for Name: treinos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.treinos (id, aluno_id, funcionario_id, data, descricao, duracao, nome_aluno, nome_funcionario) FROM stdin;
5	3	3	2024-06-05	teste	30	\N	\N
6	3	3	2024-06-05	x1	50	\N	\N
7	3	3	2024-06-05	teste	1	\N	\N
8	3	3	2024-06-05	teste	4	\N	\N
\.


--
-- TOC entry 4856 (class 0 OID 0)
-- Dependencies: 216
-- Name: alunos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alunos_id_seq', 3, true);


--
-- TOC entry 4857 (class 0 OID 0)
-- Dependencies: 218
-- Name: dados_funcionarios_funcionario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dados_funcionarios_funcionario_id_seq', 1, false);


--
-- TOC entry 4858 (class 0 OID 0)
-- Dependencies: 219
-- Name: dados_funcionarios_id_funcionario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dados_funcionarios_id_funcionario_seq', 3, true);


--
-- TOC entry 4859 (class 0 OID 0)
-- Dependencies: 221
-- Name: planos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planos_id_seq', 1, true);


--
-- TOC entry 4860 (class 0 OID 0)
-- Dependencies: 223
-- Name: registros_horarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.registros_horarios_id_seq', 2, true);


--
-- TOC entry 4861 (class 0 OID 0)
-- Dependencies: 226
-- Name: treinos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.treinos_id_seq', 8, true);


--
-- TOC entry 4668 (class 2606 OID 16867)
-- Name: alunos alunos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_pkey PRIMARY KEY (id);


--
-- TOC entry 4670 (class 2606 OID 16869)
-- Name: dados_funcionarios dados_funcionarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dados_funcionarios
    ADD CONSTRAINT dados_funcionarios_pkey PRIMARY KEY (funcionario_id);


--
-- TOC entry 4672 (class 2606 OID 16871)
-- Name: planos planos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planos
    ADD CONSTRAINT planos_pkey PRIMARY KEY (id);


--
-- TOC entry 4674 (class 2606 OID 16873)
-- Name: registros_horarios registros_horarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registros_horarios
    ADD CONSTRAINT registros_horarios_pkey PRIMARY KEY (id);


--
-- TOC entry 4676 (class 2606 OID 16875)
-- Name: treinos treinos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinos
    ADD CONSTRAINT treinos_pkey PRIMARY KEY (id);


--
-- TOC entry 4837 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

GRANT ALL ON SCHEMA public TO user_admin;


--
-- TOC entry 4838 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE alunos; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.alunos TO user_admin;


--
-- TOC entry 4840 (class 0 OID 0)
-- Dependencies: 216
-- Name: SEQUENCE alunos_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.alunos_id_seq TO user_admin;


--
-- TOC entry 4841 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE dados_funcionarios; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.dados_funcionarios TO user_admin;


--
-- TOC entry 4843 (class 0 OID 0)
-- Dependencies: 218
-- Name: SEQUENCE dados_funcionarios_funcionario_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.dados_funcionarios_funcionario_id_seq TO user_admin;


--
-- TOC entry 4845 (class 0 OID 0)
-- Dependencies: 219
-- Name: SEQUENCE dados_funcionarios_id_funcionario_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.dados_funcionarios_id_funcionario_seq TO user_admin;


--
-- TOC entry 4846 (class 0 OID 0)
-- Dependencies: 220
-- Name: TABLE planos; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.planos TO user_admin;


--
-- TOC entry 4848 (class 0 OID 0)
-- Dependencies: 221
-- Name: SEQUENCE planos_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.planos_id_seq TO user_admin;


--
-- TOC entry 4849 (class 0 OID 0)
-- Dependencies: 222
-- Name: TABLE registros_horarios; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.registros_horarios TO user_admin;


--
-- TOC entry 4851 (class 0 OID 0)
-- Dependencies: 223
-- Name: SEQUENCE registros_horarios_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.registros_horarios_id_seq TO user_admin;


--
-- TOC entry 4852 (class 0 OID 0)
-- Dependencies: 224
-- Name: TABLE treinos; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.treinos TO user_admin;


--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 225
-- Name: TABLE treinos_com_nomes; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.treinos_com_nomes TO user_admin;


--
-- TOC entry 4855 (class 0 OID 0)
-- Dependencies: 226
-- Name: SEQUENCE treinos_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.treinos_id_seq TO user_admin;


-- Completed on 2024-06-12 15:12:19

--
-- PostgreSQL database dump complete
--

