create table voto (
	id bigint not null auto_increment,
    pauta_id bigint not null,
    associado_id bigint not null,
    resposta varchar(3),
    
    primary key(id)
);

alter table voto add constraint fk_voto_pauta
foreign key (pauta_id) references pauta (id);

alter table voto add constraint fk_voto_associado
foreign key (associado_id) references associado (id);