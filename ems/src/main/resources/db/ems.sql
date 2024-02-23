--
-- PostgreSQL database dump
--

-- Dumped from database version 14.10 (Ubuntu 14.10-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.10 (Ubuntu 14.10-0ubuntu0.22.04.1)

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
-- Name: department; Type: TABLE; Schema: public; Owner: hawk
--

CREATE TABLE public.department (
    id bigint NOT NULL,
    creation_date timestamp(6) without time zone,
    department_head_id bigint,
    name character varying(255)
);


ALTER TABLE public.department OWNER TO hawk;

--
-- Name: department_id_seq; Type: SEQUENCE; Schema: public; Owner: hawk
--

CREATE SEQUENCE public.department_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.department_id_seq OWNER TO hawk;

--
-- Name: department_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hawk
--

ALTER SEQUENCE public.department_id_seq OWNED BY public.department.id;


--
-- Name: employee; Type: TABLE; Schema: public; Owner: hawk
--

CREATE TABLE public.employee (
    id bigint NOT NULL,
    address character varying(255),
    date_of_birth timestamp(6) without time zone,
    department_id bigint,
    joining_date timestamp(6) without time zone,
    name character varying(255),
    reporting_manager_id bigint,
    role character varying(255),
    salary double precision,
    yearly_bonus_percentage double precision
);


ALTER TABLE public.employee OWNER TO hawk;

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: hawk
--

CREATE SEQUENCE public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_id_seq OWNER TO hawk;

--
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hawk
--

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- Name: department id; Type: DEFAULT; Schema: public; Owner: hawk
--

ALTER TABLE ONLY public.department ALTER COLUMN id SET DEFAULT nextval('public.department_id_seq'::regclass);


--
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: hawk
--

ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);


--
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: hawk
--

COPY public.department (id, creation_date, department_head_id, name) FROM stdin;
1	2024-02-21 22:36:06.539	1	admin
2	2024-02-21 22:36:06.539	1	Engineering
4	2024-02-21 22:36:06.539	1	Developement MA
3	2024-02-21 22:36:06.539	1	Developement MA
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: hawk
--

COPY public.employee (id, address, date_of_birth, department_id, joining_date, name, reporting_manager_id, role, salary, yearly_bonus_percentage) FROM stdin;
1	654 Cherry St	\N	\N	2024-02-21 22:36:06.539	Arun	\N	admin	50000	22
2	123 Main St	\N	1	2023-01-10 14:30:00	John	2	Developer	50000	20
3	456 Oak St	\N	2	2022-07-05 16:00:00	Alice	4	Tester	60000	15
4	789 Elm St	\N	3	2021-04-15 16:30:00	Bob	3	QA	55000	18
5	789 Elm St	\N	3	2021-04-15 16:30:00	Bob	25	QA	55000	18
6	101 Pine St	1988-03-25 19:30:00	1	2024-02-01 15:00:00	Eva	\N	Developer	48000	22
7	202 Maple St	1995-06-12 18:15:00	2	2023-09-20 15:30:00	Alex	\N	Tester	55000	18
8	303 Cedar St	1990-12-08 16:45:00	3	2022-05-15 15:15:00	Grace	1	QA	60000	20
9	404 Birch St	1987-04-30 20:00:00	1	2021-11-10 14:00:00	Charlie	2	Developer	52000	15
10	505 Oak St	1993-09-17 16:00:00	2	2023-08-05 16:45:00	Olivia	1	Developer	58000	19
11	606 Elm St	1989-05-08 14:15:00	3	2022-04-12 19:30:00	Daniel	5	Tester	50000	17
12	707 Pine St	1984-12-21 18:45:00	1	2021-10-18 15:00:00	Sophie	3	QA	54000	21
13	707 Pine St	1984-12-21 18:45:00	1	2021-10-18 15:00:00	Sophie	3	QA	54000	21
14	808 Maple St	1991-07-14 15:15:00	2	2023-06-08 14:00:00	Max	4	Developer	56000	20
15	909 Cedar St	1986-02-03 17:30:00	3	2022-03-25 15:45:00	Isabella	2	Tester	49000	18
16	1010 Birch St	1983-11-27 17:00:00	1	2021-09-12 14:30:00	Owen	1	QA	52000	22
17	111 Pine St	1994-04-19 19:15:00	2	2023-11-15 14:15:00	Liam	5	QA	57000	18
18	222 Oak St	1997-01-28 15:30:00	3	2022-08-10 15:00:00	Ava	2	Tester	49000	21
19	333 Elm St	1989-09-03 16:45:00	1	2021-06-05 19:30:00	Noah	3	Developer	53000	20
20	444 Cedar St	1992-12-09 20:00:00	2	2023-10-15 13:30:00	Emma	4	Tester	55000	17
21	555 Maple St	1986-06-26 14:30:00	3	2022-07-01 15:45:00	Mason	1	Developer	51000	19
22	666 Oak St	1990-03-14 17:15:00	1	2021-04-20 20:00:00	Aria	5	QA	48000	22
23	777 Pine St	1996-08-22 17:30:00	2	2023-05-18 17:00:00	Ethan	3	QA	59000	20
24	888 Elm St	1993-02-11 16:15:00	3	2022-09-15 14:30:00	Avery	2	Developer	52000	18
25	999 Oak St	1985-11-05 18:45:00	1	2021-12-01 14:15:00	Jackson	4	Tester	54000	21
26	111 Birch St	1997-04-30 20:00:00	2	2023-02-10 15:30:00	Lily	1	Developer	58000	19
\.


--
-- Name: department_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hawk
--

SELECT pg_catalog.setval('public.department_id_seq', 4, true);


--
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hawk
--

SELECT pg_catalog.setval('public.employee_id_seq', 26, true);


--
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: hawk
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (id);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: hawk
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

