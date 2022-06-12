CREATE TABLE IF NOT EXISTS produto(
	id bigint not null auto_increment,
    nome varchar(60) not null,
    preco decimal (11,2),
    
    primary key (id)
);