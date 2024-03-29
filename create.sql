create table categoria (id int8 generated by default as identity, nome varchar(255) not null, primary key (id));
create table cidade (id int8 generated by default as identity, nome varchar(255), estado_id int8, primary key (id));
create table cliente (id int8 generated by default as identity, cpfoucnpj varchar(255), email varchar(255), nome varchar(255) not null, tipocliente int4, primary key (id));
create table endereco (id int8 generated by default as identity, bairro varchar(255), cep varchar(255), complemento varchar(255), logradouro varchar(255) not null, numero varchar(255), cidade_id int8, cliente_id int8, primary key (id));
create table estado (id int8 generated by default as identity, nome varchar(255), primary key (id));
create table item_pedido (desconto float8, preco float8, quantidade int4, pedido_id int8 not null, produto_id int8 not null, primary key (pedido_id, produto_id));
create table pagamento (pedido_id int8 not null, estado_pagamento_int int4, primary key (pedido_id));
create table pagamento_boleto (data_pagamento timestamp, data_vencimento timestamp, pedido_id int8 not null, primary key (pedido_id));
create table pagamento_cartao (numero_parcelas int4, pedido_id int8 not null, primary key (pedido_id));
create table pedido (id int8 generated by default as identity, instante timestamp, cliente_id int8, endereco_entrega_id int8, primary key (id));
create table pessoa (id int8 generated by default as identity, email varchar(255) not null, nome varchar(255) not null, primary key (id));
create table produto (id int8 generated by default as identity, nome varchar(255) not null, preco float8 not null, primary key (id));
create table produto_categoria (produto_id int8 not null, categoria_id int8 not null);
create table telefone (cliente_id int8 not null, telefones varchar(255));
alter table if exists cliente add constraint UK_cmxo70m08n43599l3h0h07cc6 unique (email);
alter table if exists cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado;
alter table if exists endereco add constraint FK8b1kcb3wucapb8dejshyn5fsx foreign key (cidade_id) references cidade;
alter table if exists endereco add constraint FK8s7ivtl4foyhrfam9xqom73n9 foreign key (cliente_id) references cliente;
alter table if exists item_pedido add constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido;
alter table if exists item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto;
alter table if exists pagamento add constraint FKthad9tkw4188hb3qo1lm5ueb0 foreign key (pedido_id) references pedido;
alter table if exists pagamento_boleto add constraint FKdqk2a6n9it7vodk056daxe4jy foreign key (pedido_id) references pagamento;
alter table if exists pagamento_cartao add constraint FK936sknk6dwq1oa3p2y7d4s8ol foreign key (pedido_id) references pagamento;
alter table if exists pedido add constraint FK30s8j2ktpay6of18lbyqn3632 foreign key (cliente_id) references cliente;
alter table if exists pedido add constraint FKcrxxe5rpllxbh0sfi4a6rwphb foreign key (endereco_entrega_id) references endereco;
alter table if exists produto_categoria add constraint FKq3g33tp7xk2juh53fbw6y4y57 foreign key (categoria_id) references categoria;
alter table if exists produto_categoria add constraint FK1c0y58d3n6x3m6euv2j3h64vt foreign key (produto_id) references produto;
alter table if exists telefone add constraint FK8aafha0njkoyoe3kvrwsy3g8u foreign key (cliente_id) references cliente;
