--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

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

DROP DATABASE IF EXISTS swabian;
--
-- Name: swabian; Type: DATABASE; Schema: -; Owner: swabian
--

CREATE DATABASE swabian WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'German_Germany.1252';


ALTER DATABASE swabian OWNER TO swabian;

\connect swabian

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
-- Name: citizen; Type: TABLE; Schema: public; Owner: swabian
--

CREATE TABLE public.citizen (
    id uuid NOT NULL,
    handle text NOT NULL
);


ALTER TABLE public.citizen OWNER TO swabian;

--
-- Data for Name: citizen; Type: TABLE DATA; Schema: public; Owner: swabian
--



--
-- Name: citizen citizen_pk; Type: CONSTRAINT; Schema: public; Owner: swabian
--

ALTER TABLE ONLY public.citizen
    ADD CONSTRAINT citizen_pk PRIMARY KEY (id);


--
-- Name: citizen citizen_un; Type: CONSTRAINT; Schema: public; Owner: swabian
--

ALTER TABLE ONLY public.citizen
    ADD CONSTRAINT citizen_un UNIQUE (handle);


--
-- PostgreSQL database dump complete
--

