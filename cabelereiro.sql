create database cabelereiro;
use cabelereiro;

create table cliente(
	codCliente int not null auto_increment,
    nome varchar(50) not null,
    telefone varchar(15) not null,
    endereco varchar(255) not null,
    horario datetime not null,
    -- keys
	primary key(codCliente)
);
alter table cliente modify column horario varchar(50);
-- esqueci do valor do valor do corte

select * from cliente;
