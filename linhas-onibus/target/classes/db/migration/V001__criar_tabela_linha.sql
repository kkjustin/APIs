CREATE TABLE linha (

	id int not null auto_increment primary key,
    codigo varchar(10) not null,
    nome varchar(100) not null,
    unique key (codigo)
    
);

CREATE TABLE itinerario (

	id int not null auto_increment,
    linha_id int not null,
    lat varchar(30) not null,
    lng varchar(30) not null,
    
    primary key (id),
    unique key (linha_id, lat, lng),
    foreign key (linha_id) references linha (id)
    
);