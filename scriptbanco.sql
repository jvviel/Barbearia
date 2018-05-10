create table tipo (
id bigint(20) primary key auto_increment,
descricao varchar(50) not null
) default charset=utf8;

create table produto_servico (
id_servico bigint(20) primary key auto_increment,
descricao varchar(50) not null,
valor float not null,
pontuacao int,
id_tipo bigint(20),
foreign key(id_tipo) references tipo.id
) default charset=utf8;

create table usuario (
id bigint(20) primary key auto_increment,
nome varchar(60) not null,
email varchar(70) not null,
telefone varchar(20),
senha varchar(100) not null,
pontuacao int
) default charset=utf8;

create table consumo (
id bigint(20) primary key auto_increment,
data_consumo date,
valor_total float,
pago varchar(1),
id_usuario bigint(20),
foreign key(id_usuario)
) default charset=utf8;

create table agenda (
id bigint(20) primary key auto_increment,
data_operacao date;
data_agendamento date;
hora_agendamento time
) default charset=utf8;

create table agenda_itens (
id_agenda bigint(20) not null,
id_servico bigint(20) not null,
primary key(id_agenda, id_servico),
foreign key(id_agenda) references agenda.id_agenda,
foreign key(id_servico) references produto_servico.id_servico
);

create table consumo_itens (
id bigint(20) primary key auto_increment,
valor float,
pontuacao int,
id_servico bigint(20),
id_consumo bigint(20),
foreign key(id_servico) references produto_servico.id_servico,
foreign key(id_consumo) references consumo.id
);











