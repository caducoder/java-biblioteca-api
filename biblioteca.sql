--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

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
-- Name: emprestimos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emprestimos (
    id integer NOT NULL,
    cliente_id integer NOT NULL,
    livro_id integer,
    emprestado_em date,
    data_devolucao date
);


ALTER TABLE public.emprestimos OWNER TO postgres;

--
-- Name: emprestimos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprestimos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprestimos_id_seq OWNER TO postgres;

--
-- Name: emprestimos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.emprestimos_id_seq OWNED BY public.emprestimos.id;


--
-- Name: financas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.financas (
    id integer NOT NULL,
    nome_arquivo character varying(100),
    valor numeric(6,2),
    data date,
    tipo_operacao character varying(10),
    assunto character varying(100)
);


ALTER TABLE public.financas OWNER TO postgres;

--
-- Name: financas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.financas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.financas_id_seq OWNER TO postgres;

--
-- Name: financas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.financas_id_seq OWNED BY public.financas.id;


--
-- Name: livros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livros (
    id integer NOT NULL,
    isbn character varying(20),
    issn character varying(20),
    doi character varying(20),
    autor character varying(50),
    titulo character varying(100),
    idioma character varying(10),
    descricao text,
    numero_paginas integer,
    ano_edicao integer,
    estado_livro character varying(10),
    editora character varying(50)
);


ALTER TABLE public.livros OWNER TO postgres;

--
-- Name: livros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livros_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.livros_id_seq OWNER TO postgres;

--
-- Name: livros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livros_id_seq OWNED BY public.livros.id;


--
-- Name: movimentacoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movimentacoes (
    id integer NOT NULL,
    id_usuario integer,
    id_cliente integer,
    id_livro integer,
    tipo_movimentacao character varying(30),
    data_hora timestamp without time zone
);


ALTER TABLE public.movimentacoes OWNER TO postgres;

--
-- Name: movimentacoes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movimentacoes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movimentacoes_id_seq OWNER TO postgres;

--
-- Name: movimentacoes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimentacoes_id_seq OWNED BY public.movimentacoes.id;


--
-- Name: reservas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservas (
    id integer NOT NULL,
    livro_id integer,
    reservado_por_cpf character varying(11),
    expira_em date
);


ALTER TABLE public.reservas OWNER TO postgres;

--
-- Name: reservas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservas_id_seq OWNER TO postgres;

--
-- Name: reservas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reservas_id_seq OWNED BY public.reservas.id;


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id integer NOT NULL,
    dtype character varying(31) NOT NULL,
    nome character varying(50),
    cpf character varying(11) NOT NULL,
    rg character varying(9),
    email character varying(50) NOT NULL,
    telefone character varying(15),
    rua character varying(30),
    numero integer,
    bairro character varying(30),
    cidade character varying(30),
    cep character varying(9),
    salario numeric(6,2),
    senha text
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO postgres;

--
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- Name: emprestimos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprestimos ALTER COLUMN id SET DEFAULT nextval('public.emprestimos_id_seq'::regclass);


--
-- Name: financas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.financas ALTER COLUMN id SET DEFAULT nextval('public.financas_id_seq'::regclass);


--
-- Name: livros id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livros ALTER COLUMN id SET DEFAULT nextval('public.livros_id_seq'::regclass);


--
-- Name: movimentacoes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimentacoes ALTER COLUMN id SET DEFAULT nextval('public.movimentacoes_id_seq'::regclass);


--
-- Name: reservas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas ALTER COLUMN id SET DEFAULT nextval('public.reservas_id_seq'::regclass);


--
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- Data for Name: emprestimos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emprestimos (id, cliente_id, livro_id, emprestado_em, data_devolucao) FROM stdin;
\.


--
-- Data for Name: financas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.financas (id, nome_arquivo, valor, data, tipo_operacao, assunto) FROM stdin;
\.


--
-- Data for Name: livros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.livros (id, isbn, issn, doi, autor, titulo, idioma, descricao, numero_paginas, ano_edicao, estado_livro, editora) FROM stdin;
54	8551001418		\N	Edmund de Waal	O Caminho Da Porcelana	PORTUGUES	\N	384	2016	DISPONIVEL	Intrínseca
38	\N	8592795656	\N	Ursula K. Le Guin	A curva do sonho	PORTUGUES	Em um mundo assolado por instabilidade climática e superpopulação, George Orr, um cidadão pacato e mundano, descobre que seus sonhos têm o poder de alterar a realidade. Quando acorda, o mundo que conhecia tornou-se um lugar estranho, quase irreconhecível, em que apenas ele tem a memória de como era antes. Sem rumo, ele busca a ajuda do Dr. William Haber, psiquiatra que logo deixa de lado o seu ceticismo e entende o poder que George possui, transformando-o em um peão de um perigoso jogo, em que o destino da humanidade fica mais ameaçado a cada instante.	224	2019	DISPONIVEL	Morro Branco
56	8521214472		\N	Luiz Fernando Grespan Setz	O Processamento Cerâmico sem Mistério	PORTUGUES	\N	256	2019	DISPONIVEL	Blucher
57	8580577837		\N	Alain Botton	Notícias: Manual do Usuário	PORTUGUES	\N	240	2015	DISPONIVEL	Intrínseca
26	8547000240	\N	\N	Carol S. Dweck	Mindset: A nova psicologia do sucesso	PORTUGUES	Carol S. Dweck, ph.D., professora de psicologia na Universidade Stanford e especialista internacional em sucesso e motivação, desenvolveu, ao longo de décadas de pesquisa, um conceito fundamental: a atitude mental com que encaramos a vida, que ela chama de “mindset”, é crucial para o sucesso.	312	2017	DISPONIVEL	Objetiva
58	6555600136		\N	Michael T. Osterholm	Inimigo Mortal: Nossa Guerra Contra os Germes Assassinos	PORTUGUES	\N	304	2020	DISPONIVEL	\N
59	8550300985			Heather Mc Manamy	Para depois que eu partir (Pocket)	PORTUGUES		192	2020	DISPONIVEL	Universo dos Livros
37	\N	B075QQRKJS	\N	Jô Soares, Matinas Suzuki Jr.	O livro de Jô - Volume 1: Uma autobiografia desautorizada	PORTUGUES	Com verve mais afiada do que nunca, Jô Soares compartilha sua trajetória de astro midiático num livro de memórias escrito para fazer rir, chorar e, sobretudo, não esquecer. O primeiro volume resgata fatos, lugares e pessoas marcantes da juventude de Jô e reconstitui seus primeiros passos no mundo dos espetáculos, nas décadas de 1950 e 1960. Entre a infância dourada no Copacabana Palace e a dura conquista do estrelato, acompanhamos o autor do nascimento aos trinta anos.	539	2017	DISPONIVEL	\N
29	856240988X	\N	\N	Charlie Donlea	A garota do lago	PORTUGUES	Summit Lake, uma pequena cidade entre montanhas, é esse tipo de lugar, bucólico e com encantadoras casas dispostas à beira de um longo trecho de água intocada.Duas semanas atrás, a estudante de direito Becca Eckersley foi brutalmente assassinada em uma dessas casas. Filha de um poderoso advogado, Becca estava no auge de sua vida. Atraída instintivamente pela notícia, a repórter Kelsey Castle vai até a cidade para investigar o caso.	296	2017	DISPONIVEL	Gutenberg Editora
24	978-8575420270	\N	\N	Eckhart Tolle	O poder do agora: Um guia para a iluminação espiritual	PORTUGUES	Nós passamos a maior parte de nossas vidas pensando no passado e fazendo planos para o futuro. Ignoramos ou negamos o presente e adiamos nossas conquistas para algum dia distante, quando conseguiremos tudo o que desejamos e seremos, finalmente, felizes.	224	2000	DISPONIVEL	Editora Sextante 
25	8576849941	\N	\N	Hal Elrod 	O milagre da manhã: O segredo para transformar sua vida (antes das 8 horas)	PORTUGUES	O segredo para transformar sua vida antes das 8 horas. O best-seller com mais de 1 milhão de exemplares vendidos no Brasil. Neste novo clássico da autoajuda, Hal Elrod explica os benefícios de acordar cedo e desenvolver todo o nosso potencial e as nossas habilidades.	196	2016	DISPONIVEL	\N
\.


--
-- Data for Name: movimentacoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movimentacoes (id, id_usuario, id_cliente, id_livro, tipo_movimentacao, data_hora) FROM stdin;
\.


--
-- Data for Name: reservas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservas (id, livro_id, reservado_por_cpf, expira_em) FROM stdin;
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id, dtype, nome, cpf, rg, email, telefone, rua, numero, bairro, cidade, cep, salario, senha) FROM stdin;
50	Administrador	admin	00000000000	\N	admin@gmail.com	\N	\N	\N	\N	\N	\N	\N	$2a$10$mEZZwuPk8pl0/cW/UCntU.3QvgQPrCa9E5SINVhINsd7vXcBE15fK
\.


--
-- Name: emprestimos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprestimos_id_seq', 29, true);


--
-- Name: financas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.financas_id_seq', 12, true);


--
-- Name: livros_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livros_id_seq', 62, true);


--
-- Name: movimentacoes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimentacoes_id_seq', 101, true);


--
-- Name: reservas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservas_id_seq', 17, true);


--
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 49, true);


--
-- Name: emprestimos emprestimos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprestimos
    ADD CONSTRAINT emprestimos_pkey PRIMARY KEY (id);


--
-- Name: financas financas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.financas
    ADD CONSTRAINT financas_pkey PRIMARY KEY (id);


--
-- Name: livros livros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livros
    ADD CONSTRAINT livros_pkey PRIMARY KEY (id);


--
-- Name: movimentacoes movimentacoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimentacoes
    ADD CONSTRAINT movimentacoes_pkey PRIMARY KEY (id);


--
-- Name: reservas reservas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas
    ADD CONSTRAINT reservas_pkey PRIMARY KEY (id);


--
-- Name: usuarios unique_cpf; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT unique_cpf UNIQUE (cpf);


--
-- Name: usuarios unique_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT unique_email UNIQUE (email);


--
-- Name: livros unique_isbn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livros
    ADD CONSTRAINT unique_isbn UNIQUE (isbn);


--
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- Name: emprestimos fk_cliente_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprestimos
    ADD CONSTRAINT fk_cliente_id FOREIGN KEY (cliente_id) REFERENCES public.usuarios(id);


--
-- Name: reservas fk_livro; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas
    ADD CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES public.livros(id);


--
-- Name: emprestimos fk_livro_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprestimos
    ADD CONSTRAINT fk_livro_id FOREIGN KEY (livro_id) REFERENCES public.livros(id);


--
-- PostgreSQL database dump complete
--

