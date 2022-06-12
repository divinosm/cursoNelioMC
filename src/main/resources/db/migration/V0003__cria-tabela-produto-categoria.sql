CREATE TABLE IF NOT EXISTS produto_categoria(
      produto_id bigint not null,
      categoria_id bigint not null
);
ALTER TABLE `nelio`.`produto_categoria` 
CHANGE COLUMN `categoria_id` `categoria_id` BIGINT NOT NULL FIRST,
ADD PRIMARY KEY (`categoria_id`, `produto_id`);
;
alter table produto_categoria
add constraint fk_produto
foreign key (produto_id)
references produto(id);

alter table produto_categoria
add constraint fk_categoria
foreign key (categoria_id)
references categoria(id);

