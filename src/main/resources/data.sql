--inserção usuários
insert into usuarios(cpf,data_nascimento, email, nome) values('677.651.136-58', '1984-01-18', 'carlos@email.com', 'Carlos Almeida Junior');
insert into usuarios(cpf,data_nascimento, email, nome) values('028.982.048-03', '1994-01-18', 'maria@email.com', 'Maria Almeida');
insert into usuarios(cpf,data_nascimento, email, nome) values('064.093.263-00', '1994-01-18', 'joao@email.com', 'João Almeida');

--inserção de vacinas
insert into vacinas(nome) values ('CoronaVac');
insert into vacinas(nome) values ('Pfizer');
insert into vacinas(nome) values ('AstraZeneca');

--inserção da aplicação das vacinas
insert into aplicacao_vacinas (data , usuario_id, vacina_id) values('2021-03-01', 1, 1);
