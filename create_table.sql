--CREATE TABLES

CREATE TABLE tipostelefones
(
    tpt_id SERIAL NOT NULL ,
    tpt_descricao character varying(30) NOT NULL,
    CONSTRAINT pk_tpt PRIMARY KEY (tpt_id)
);

CREATE TABLE telefones
(
    tel_id SERIAL NOT NULL ,
    tel_ddd character(5) NOT NULL,
    tel_numero character varying(10)  NOT NULL,
    tel_tpt_id integer NOT NULL,
    CONSTRAINT pk_tel PRIMARY KEY (tel_id),
    CONSTRAINT fk_tel_tpt FOREIGN KEY (tel_tpt_id)
        REFERENCES tipostelefones (tpt_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE statuspedidos
(
    stp_id SERIAL NOT NULL ,
    stp_descricao character varying(25),
    CONSTRAINT pk_stp PRIMARY KEY (stp_id)
);

CREATE TABLE tiposcupons
(
    tpc_id SERIAL NOT NULL,
    tpc_descricao character varying(20) NOT NULL,
    CONSTRAINT pk_tpc PRIMARY KEY (tpc_id)
);

CREATE TABLE tiposlogradouros
(
    tpl_id SERIAL NOT NULL ,
    tpl_descricao character varying(50) NOT NULL,
    CONSTRAINT pk_tpl PRIMARY KEY (tpl_id)
);

CREATE TABLE usuarios
(
    usr_id SERIAL NOT NULL ,
    usr_email character varying(100) NOT NULL,
    usr_senha character(64) NOT NULL,
    usr_isadmin boolean NOT NULL,
    CONSTRAINT pk_usr PRIMARY KEY (usr_id)
);

CREATE TABLE autores
(
    atr_id SERIAL NOT NULL,
    atr_nome character varying(50),
    CONSTRAINT pk_atr PRIMARY KEY (atr_id)
);

CREATE TABLE categorias
(
    cat_id SERIAL NOT NULL,
    cat_descricao character varying(50),
    CONSTRAINT pk_cat PRIMARY KEY (cat_id)
);

CREATE TABLE bandeiras
(
    ban_id SERIAL NOT NULL ,
    ban_descricao character varying(25) NOT NULL,
    CONSTRAINT pk_ban PRIMARY KEY (ban_id)
);

CREATE TABLE cartoescompras
(
    ccc_id SERIAL NOT NULL ,
    ccc_nometitular character varying(255) NOT NULL,
    ccc_numero character varying(25) NOT NULL,
    ccc_cvv character varying(5) NOT NULL,
    ccc_validade character varying(25) NOT NULL,
    ccc_ban_id integer NOT NULL,
    ccc_pdd_id integer,
    ccc_valor character varying(20),
    CONSTRAINT pk_ccc PRIMARY KEY (ccc_id),
    CONSTRAINT fk_ccc_ban FOREIGN KEY (ccc_ban_id)
        REFERENCES bandeiras (ban_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT fk_ccc_pdd FOREIGN KEY (ccc_pdd_id)
        REFERENCES pedidos (pdd_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE autores_livros
(
    atl_atr_id integer NOT NULL,
    atl_lvr_id integer NOT NULL,
    CONSTRAINT pk_atl PRIMARY KEY (atl_atr_id, atl_lvr_id),
    CONSTRAINT fk_atl_atr FOREIGN KEY (atl_atr_id)
        REFERENCES autores (atr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_atl_lvr FOREIGN KEY (atl_lvr_id)
        REFERENCES livros (lvr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE carrinhos
(
    crr_id SERIAL NOT NULL,
    crr_cli_id integer NOT NULL,
    CONSTRAINT pk_crr PRIMARY KEY (crr_id),
    CONSTRAINT fk_crr_cli FOREIGN KEY (crr_cli_id)
        REFERENCES clientes (cli_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE cartoescredito
(
    ctc_id SERIAL NOT NULL ,
    ctc_nomeidentificacao character varying(255) NOT NULL,
    ctc_nometitular character varying(255) NOT NULL,
    ctc_numero character varying(25) NOT NULL,
    ctc_cvv character varying(5) NOT NULL,
    ctc_validade character varying(25) NOT NULL,
    ctc_ban_id integer NOT NULL,
    ctc_cli_id integer NOT NULL,
    CONSTRAINT pk_ctc PRIMARY KEY (ctc_id),
    CONSTRAINT fk_ctc_ban FOREIGN KEY (ctc_ban_id)
        REFERENCES bandeiras (ban_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_ctc_cli FOREIGN KEY (ctc_cli_id)
        REFERENCES clientes (cli_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE categorias_livros
(
    ctl_cat_id integer NOT NULL,
    ctl_lvr_id integer NOT NULL,
    CONSTRAINT pk_ctl PRIMARY KEY (ctl_cat_id, ctl_lvr_id),
    CONSTRAINT fk_ctl_cat FOREIGN KEY (ctl_cat_id)
        REFERENCES categorias (cat_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_ctl_lvr FOREIGN KEY (ctl_lvr_id)
        REFERENCES livros (lvr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE clientes
(
    cli_id SERIAL NOT NULL ,
    cli_nome character varying(100) NOT NULL,
    cli_dtnascimento character varying(10) NOT NULL,
    cli_cpf character varying(14) NOT NULL,
    cli_gen_id integer NOT NULL,
    cli_tel_id integer NOT NULL,
    cli_usr_id integer NOT NULL,
    CONSTRAINT pk_cli PRIMARY KEY (cli_id),
    CONSTRAINT fk_cli_gen FOREIGN KEY (cli_gen_id)
        REFERENCES generos (gen_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cli_tel FOREIGN KEY (cli_tel_id)
        REFERENCES telefones (tel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cli_usr FOREIGN KEY (cli_usr_id)
        REFERENCES usuarios (usr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE cupons
(
    cpm_id SERIAL NOT NULL,
    cpm_codigo character varying(20) ,
    cpm_valor character varying(10) ,
    cpm_pdd_id integer,
    cpm_isresgatado boolean DEFAULT false,
    cpm_validade date,
    cpm_cli_id integer,
    cpm_tpc_id integer,
    CONSTRAINT fk_cpm_cli FOREIGN KEY (cpm_cli_id)
        REFERENCES clientes (cli_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cpm_pdd FOREIGN KEY (cpm_pdd_id)
        REFERENCES pedidos (pdd_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cpm_tcp FOREIGN KEY (cpm_tpc_id)
        REFERENCES tiposcupons (tpc_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE enderecos
(
    end_id SERIAL NOT NULL ,
    end_logradouro character varying(100)  NOT NULL,
    end_numero character varying(10)  NOT NULL,
    end_bairro character varying(50) NOT NULL,
    end_cep character varying(10) NOT NULL,
    end_cidade character varying(100) NOT NULL,
    end_estado character varying(50) NOT NULL,
    end_pais character varying(50)  NOT NULL,
    end_nome_identificacao character varying(200) NOT NULL,
    end_complemento character varying(200) ,
    end_cli_id integer NOT NULL,
    end_tpr_id integer NOT NULL,
    end_tpl_id integer NOT NULL,
    CONSTRAINT pk_end PRIMARY KEY (end_id),
    CONSTRAINT fk_end_cli FOREIGN KEY (end_cli_id)
        REFERENCES clientes (cli_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_end_tpl FOREIGN KEY (end_tpl_id)
        REFERENCES tiposlogradouros (tpl_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_end_tpr FOREIGN KEY (end_tpr_id)
        REFERENCES tiposresidencias (tpr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE enderecosentregas
(
    ede_id SERIAL NOT NULL,
    ede_logradouro character varying(100) NOT NULL,
    ede_numero character varying(10) NOT NULL,
    ede_bairro character varying(50) NOT NULL,
    ede_cep character varying(10) NOT NULL,
    ede_cidade character varying(100) NOT NULL,
    ede_estado character varying(50) NOT NULL,
    ede_pais character varying(50) NOT NULL,
    ede_complemento character varying(200) ,
    ede_tpr_id integer NOT NULL,
    ede_tpl_id integer NOT NULL,
    ede_prazo_entrega integer,
    ede_valor_frete character varying(10) ,
    CONSTRAINT pk_ede PRIMARY KEY (ede_id),
    CONSTRAINT fk_ede_tpl FOREIGN KEY (ede_tpl_id)
        REFERENCES tiposlogradouros (tpl_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_ede_tpr FOREIGN KEY (ede_tpr_id)
        REFERENCES tiposresidencias (tpr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE estoque
(
    etq_id SERIAL NOT NULL ,
    etq_lvr_id integer NOT NULL,
    etq_quantidade integer NOT NULL,
    etq_status boolean NOT NULL,
    etq_justificativa character varying(255) ,
    etq_cgs_id integer,
    etq_valor_venda character varying(10) ,
    etq_preco_custo character varying(10) ,
    CONSTRAINT pk_etq PRIMARY KEY (etq_id),
    CONSTRAINT fk_etq_cgs FOREIGN KEY (etq_cgs_id)
        REFERENCES categoriastatus (cgs_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_etq_lvr FOREIGN KEY (etq_lvr_id)
        REFERENCES livros (lvr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE generos
(
    gen_id SERIAL NOT NULL ,
    gen_descricao character varying(20) NOT NULL,
    CONSTRAINT pk_gen PRIMARY KEY (gen_id)
);

CREATE TABLE itenscarrinhos
(
    itc_id SERIAL NOT NULL ,
    itc_crr_id integer NOT NULL,
    itc_lvr_id integer NOT NULL,
    itc_quantidade integer NOT NULL,
    itc_valor_venda character varying(10),
    CONSTRAINT pk_itc PRIMARY KEY (itc_id),
    CONSTRAINT fk_itc_crr FOREIGN KEY (itc_crr_id)
        REFERENCES carrinhos (crr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_itc_lvr FOREIGN KEY (itc_lvr_id)
        REFERENCES livros (lvr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE itenspedidos
(
    itp_id SERIAL NOT NULL,
    itp_lvr_id integer,
    itp_quantidade integer,
    itp_pdd_id integer,
    itp_valor_unitario character varying(10),
    CONSTRAINT pk_itp PRIMARY KEY (itp_id),
    CONSTRAINT fk_itp_lvr FOREIGN KEY (itp_lvr_id)
        REFERENCES livros (lvr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_itp_pdd FOREIGN KEY (itp_pdd_id)
        REFERENCES pedidos (pdd_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
	
CREATE TABLE notificacoes
(
    ntf_id SERIAL NOT NULL ,
    ntf_mensagem character varying(200) NOT NULL,
    ntf_islida boolean NOT NULL DEFAULT false,
    ntf_usr_id integer NOT NULL,
    ntf_data_cadastro date DEFAULT CURRENT_DATE,
    CONSTRAINT pk_ntf PRIMARY KEY (ntf_id),
    CONSTRAINT fk_ntf_cli FOREIGN KEY (ntf_usr_id)
        REFERENCES usuarios (usr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE pedidostrocas
(
    pdt_id SERIAL NOT NULL DEFAULT ,
    pdt_cli_id integer NOT NULL,
    pdt_data date NOT NULL DEFAULT CURRENT_DATE,
    pdt_pdd_id integer NOT NULL,
    CONSTRAINT pk_pdt PRIMARY KEY (pdt_id),
	CONSTRAINT fk_pdt_cli FOREIGN KEY (pdt_cli_id)
        REFERENCES clientes (cli_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_pdt_pdd FOREIGN KEY (pdt_pdd_id)
        REFERENCES pedidos (pdd_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE itenstrocas
(
    itt_id SERIAL NOT NULL ,
    itt_pdt_id integer NOT NULL,
    itt_lvr_id integer NOT NULL,
    itt_quantidade integer NOT NULL,
    itt_valor_unitario character varying(10)NOT NULL,
    itt_data date NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT pk_itt PRIMARY KEY (itt_id),
    CONSTRAINT fk_itt_lvr FOREIGN KEY (itt_lvr_id)
        REFERENCES livros (lvr_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_itt_pdt FOREIGN KEY (itt_pdt_id)
        REFERENCES pedidostrocas (pdt_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE livros
(
    lvr_id SERIAL NOT NULL ,
    lvr_titulo character varying(255) NOT NULL,
    lvr_ano integer,
    lvr_editora character varying(255) NOT NULL,
    lvr_edicao integer,
    lvr_isbn character(13) NOT NULL,
    lvr_numero_paginas integer,
    lvr_sinopse character varying(3000) NOT NULL,
    lvr_altura numeric NOT NULL,
    lvr_largura numeric NOT NULL,
    lvr_profundidade numeric NOT NULL,
    lvr_peso numeric NOT NULL,
    lvr_grupo_precificacao integer NOT NULL,
    lvr_codigo_barras character(13) NOT NULL,
    lvr_link_capa character varying(255) NOT NULL,
    CONSTRAINT pk_lvr PRIMARY KEY (lvr_id)
);

CREATE TABLE pedidos
(
    pdd_id SERIAL NOT NULL ,
    pdd_valor_total character varying(10) ,
    pdd_data date NOT NULL DEFAULT CURRENT_DATE,
    pdd_cli_id integer,
    pdd_stp_id integer,
    pdd_ede_id integer,
    CONSTRAINT pk_pdd PRIMARY KEY (pdd_id),
    CONSTRAINT fk_pdd_cli FOREIGN KEY (pdd_cli_id)
        REFERENCES clientes (cli_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_pdd_ede FOREIGN KEY (pdd_ede_id)
        REFERENCES enderecosentregas (ede_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_pdd_stp FOREIGN KEY (pdd_stp_id)
        REFERENCES statuspedidos (stp_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE tiposresidencias
(
    tpr_id SERIAL NOT NULL ,
    tpr_descricao character varying(50) NOT NULL,
    CONSTRAINT pk_tpr PRIMARY KEY (tpr_id)
);

CREATE TABLE categoriastatus
(
    cgs_id SERIAL NOT NULL DEFAULT ,
    cgs_descricao character varying(50) ,
    CONSTRAINT pk_cgs PRIMARY KEY (cgs_id)
)

--INSERT DATA

INSERT INTO AUTORES (atr_nome) VALUES ('J.K. Rowling');
INSERT INTO AUTORES (atr_nome) VALUES ('E.L. James');

INSERT INTO LIVROS (lvr_titulo, lvr_ano, lvr_editora, lvr_edicao, lvr_isbn, lvr_numero_paginas, lvr_sinopse, lvr_altura, lvr_largura, lvr_profundidade, lvr_peso, lvr_grupo_precificacao, lvr_codigo_barras, lvr_link_capa) values (
	"Harry Potter e a Pedra Filosofal", 2017, "Rocco", 1, "9788532530783", 208, "Harry Potter ?? um garoto cujos pais, feiticeiros, foram assassinados por um poderos??ssimo bruxo quando ele ainda era um beb??. Ele foi levado, ent??o, para a casa dos tios que nada tinham a ver com o sobrenatural. Pelo contr??rio. At?? os 10 anos, Harry foi uma esp??cie de gata borralheira: maltratado pelos tios, herdava roupas velhas do primo gorducho, tinha ??culos remendados e era tratado como um estorvo. No dia de seu anivers??rio de 11 anos, entretanto, ele parece deslizar por um buraco sem fundo, como o de Alice no pa??s das maravilhas, que o conduz a um mundo m??gico. Descobre sua verdadeira hist??ria e seu destino: ser um aprendiz de feiticeiro at?? o dia em que ter?? que enfrentar a pior for??a do mal, o homem que assassinou seus pais. O menino de olhos verde, magricela e desengon??ado, t??o habituado ?? rejei????o, descobre, tamb??m, que ?? um her??i no universo dos magos. Potter fica sabendo que ?? a ??nica pessoa a ter sobrevivido a um ataque do tal bruxo do mal e essa ?? a causa da marca em forma de raio que ele carrega na testa. Ele n??o ?? um garoto qualquer, ele sequer ?? um feiticeiro qualquer ele ?? Harry Potter, s??mbolo de poder, resist??ncia e um l??der natural entre os sobrenaturais. A f??bula, recheada de fantasmas, paredes que falam, caldeir??es, sapos, unic??rnios, drag??es e gigantes, n??o ??, entretanto, apenas um passatempo.", 23, 16, 1.8, 260,2, "9788532530783", "HP_Pedra_Filosofal.jpg" 
);

INSERT INTO LIVROS (lvr_titulo, lvr_ano, lvr_editora, lvr_edicao, lvr_isbn, lvr_numero_paginas, lvr_sinopse, lvr_altura, lvr_largura, lvr_profundidade, lvr_peso, lvr_grupo_precificacao, lvr_codigo_barras, lvr_link_capa) values (
	"Harry Potter e a C??mara Secreta", 2017, "Rocco", 1, "9788532530790", 224, "Os Dursley estavam t??o anti-sociais naquele ver??o, que tudo o que Harry queria era voltar ??s aulas da Escola de Bruxarias de Hogwarts. No entanto, quando j?? terminava de fazer suas malas, Harry recebe um aviso de um estranho chamado Dobby, que diz que um desastre acontecer?? caso Potter decida voltar ?? Hogwarts. Harry n??o liga para aquela mensagem e o desastre realmente acontece. Naquele segundo ano estudando em Hogwarts, novos horrores surgem para atormentar Harry, incluindo o novo professor Gilderoy Lockhart e um esp??rito chamado Moaning Myrtle, que assombra o banheiro feminino, al??m de olhares indesejados da irm?? mais nova de Ron Weasley, Ginny. Todos esses problemas, no entanto, parecem menores quando o verdadeiro problema come??a e algo transforma os alunos de Hogwarts em pedra. Dentre os suspeitos: o pr??prio Harry. Descubra o fim desta aventura emocionante.", 23, 16, 1.8, 270,2, "9788532530790", "HP_Camera_Secreta.jpg" 
);

INSERT INTO LIVROS (lvr_titulo, lvr_ano, lvr_editora, lvr_edicao, lvr_isbn, lvr_numero_paginas, lvr_sinopse, lvr_altura, lvr_largura, lvr_profundidade, lvr_peso, lvr_grupo_precificacao, lvr_codigo_barras, lvr_link_capa) values (
	"Harry Potter e o Prisioneiro de Azkaban", 2017, "Rocco", 1, "9788532530806", 288, "Durante 12 anos o forte de Azkaban guardou o prisioneiro Sirius Black, acusado de matar 13 pessoas e ser o principal ajudante de Voldemort, o Senhor das Trevas. Agora ele conseguiu escapar, deixando apenas uma pista: seu destino ?? a escola de Hogwarts, em busca de Harry Potter. Neste livro o leitor estar?? mais uma vez mergulhando no mundo m??gico de Hogwarts e participando de aventuras repletas de imagina????o, humor e emo????o, que repetem o encantamento proporcionado pelos livros anteriores dessa maravilhosa s??rie de J K Rowling.", 23, 16, 2, 280,2, "9788532530806", "HP_Prisioneiro_Azkaban.jpg" 
);

INSERT INTO LIVROS (lvr_titulo, lvr_ano, lvr_editora, lvr_edicao, lvr_isbn, lvr_numero_paginas, lvr_sinopse, lvr_altura, lvr_largura, lvr_profundidade, lvr_peso, lvr_grupo_precificacao, lvr_codigo_barras, lvr_link_capa) values (
	"Cinquenta Tons de Cinza", 2012, "Intr??seca", 1, "9788580572186", 480, "Quando Anast??sia Steele entrevista o jovem empres??rio Christian Grey, descobre nele um homem atraente, brilhante e profundamente dominador Ing??nua e inocente, Ana se surpreende ao perceber que, a despeito da enigm??tica reserva de Grey, est?? desesperadamente atra??da por ele Incapaz de resistir ?? beleza discreta, ?? timidez e ao esp??rito independente de Ana, Grey admite que tamb??m a deseja - mas em seus pr??prios termosChocada e ao mesmo tempo seduzida pelas estranhas prefer??ncias de Grey, Ana hesita Por tr??s da fachada de sucesso - os neg??cios multinacionais, a vasta fortuna, a amada fam??lia -, Grey ?? um homem atormentado por dem??nios do passado e consumido pela necessidade de controle Quando eles embarcam num apaixonado e sensual caso de amor, Ana n??o s?? descobre mais sobre seus pr??prios desejos, como tamb??m sobre os segredos obscuros que Grey tenta manter escondidos", 23, 16, 2.7, 640,1, "9788580572186", "Cinquenta_Tons_Cinza.png" 
);

INSERT INTO CATEGORIAS (cat_descricao) values("Aventura");
INSERT INTO CATEGORIAS (cat_descricao) values("Fantasia");
INSERT INTO CATEGORIAS (cat_descricao) values("Fic????o");
INSERT INTO CATEGORIAS (cat_descricao) values("Infantojuvenil");
INSERT INTO CATEGORIAS (cat_descricao) values("Literatura Estrangeira");
INSERT INTO CATEGORIAS (cat_descricao) values("Romance");
INSERT INTO CATEGORIAS (cat_descricao) values("Suspense e Mist??rio");
INSERT INTO CATEGORIAS (cat_descricao) values("Er??tico");

INSERT INTO AUTORES_LIVROS(atl_atr_id, atl_lvr_id) values(1,1);
INSERT INTO AUTORES_LIVROS(atl_atr_id, atl_lvr_id) values(1,2);
INSERT INTO AUTORES_LIVROS(atl_atr_id, atl_lvr_id) values(1,3);
INSERT INTO AUTORES_LIVROS(atl_atr_id, atl_lvr_id) values(2,4);

INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(1,1);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(2,1);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(3,1);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(4,1);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(5,1);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(6,1);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(7,1);

INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(1,2);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(2,2);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(3,2);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(4,2);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(5,2);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(6,2);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(7,2);

INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(1,3);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(2,3);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(3,3);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(4,3);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(5,3);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(6,3);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(7,3);

INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(5,4);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(6,4);
INSERT INTO CATEGORIAS_LIVROS (ctl_cat_id, ctl_lvr_id) values(8,4);

INSERT INTO CATEGORIASTATUS (cgs_descricao) VALUES("Em estoque");
INSERT INTO CATEGORIASTATUS (cgs_descricao) VALUES("Fora de mercado");
INSERT INTO CATEGORIASTATUS (cgs_descricao) VALUES("Sem estoque");

INSERT INTO ESTOQUE (etq_lvr_id, etq_quantidade, etq_status, etq_justificativa, etq_cgs_id, etq_valor_venda, etq_preco_custo) values (
	1, 25, true, 'Primeira entrada', 1, '29.00', '19.90'
);

INSERT INTO ESTOQUE (etq_lvr_id, etq_quantidade, etq_status, etq_justificativa, etq_cgs_id, etq_valor_venda, etq_preco_custo) values (
	2, 25, true, 'Primeira entrada', 1, '29.00', '18.90'
);

INSERT INTO ESTOQUE (etq_lvr_id, etq_quantidade, etq_status, etq_justificativa, etq_cgs_id, etq_valor_venda, etq_preco_custo) values (
	3, 25, true, 'Primeira entrada', 1, '34.50', '22.90'
);

INSERT INTO ESTOQUE (etq_lvr_id, etq_quantidade, etq_status, etq_justificativa, etq_cgs_id, etq_valor_venda, etq_preco_custo) values (
	4, 25, true, 'Primeira entrada', 1, '42.60', '37.90'
);

INSERT INTO TIPOSLOGRADOUROS (tpl_descricao) values ("Rua");
INSERT INTO TIPOSLOGRADOUROS (tpl_descricao) values ("Avenida");
INSERT INTO TIPOSLOGRADOUROS (tpl_descricao) values ("Outro");

INSERT INTO TIPOSRESIDENCIAS (tpr_descricao) values ("Casa");
INSERT INTO TIPOSRESIDENCIAS (tpr_descricao) values ("apartamento");
INSERT INTO TIPOSRESIDENCIAS (tpr_descricao) values ("outro");

INSERT INTO BANDEIRAS (ban_descricao) values ("Visa");
INSERT INTO BANDEIRAS (ban_descricao) values ("Mastercard");

INSERT INTO TIPOSCUPONS (tpc_descricao) values ("Troca");
INSERT INTO TIPOSCUPONS (tpc_descricao) values ("Promocional");

INSERT INTO GENEROS (gen_descricao) values ("masculino");
INSERT INTO GENEROS (gen_descricao) values ("feminino");
INSERT INTO GENEROS (gen_descricao) values ("nao binario");

INSERT INTO STATUSPEDIDOS (stp_descricao) values ("Em Processamento");
INSERT INTO STATUSPEDIDOS (stp_descricao) values ("Em Tr??nsito");
INSERT INTO STATUSPEDIDOS (stp_descricao) values ("Entregue");
INSERT INTO STATUSPEDIDOS (stp_descricao) values ("Em Troca");
INSERT INTO STATUSPEDIDOS (stp_descricao) values ("Troca Autorizada");
INSERT INTO STATUSPEDIDOS (stp_descricao) values ("Troca Recusada");
INSERT INTO STATUSPEDIDOS (stp_descricao) values ("Troca Finalizada");

INSERT INTO TIPOSTELEFONES (tpt_descricao) values ("fixo");
INSERT INTO TIPOSTELEFONES (tpt_descricao) values ("celular");
