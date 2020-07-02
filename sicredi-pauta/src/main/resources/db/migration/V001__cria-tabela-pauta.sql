create table pauta (
	id bigint not null auto_increment,
    assunto varchar(255) not null,
    horario_abertura datetime,
    
    primary key (id)
);