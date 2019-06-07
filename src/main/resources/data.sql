insert into user (email, password, username) values ('admin@gmail.com', '$2a$10$3K0woKoq2A/5xwL4XX.cjuy6hAjyUAHO.4t3Q.JVKXheEORMDIaza', 'admin');

insert into todo_list (owner_user_id, title) values (1, 'List 1');

insert into todo_item (depndnt_todo_item, description, done, title, todo_list_id) values (null, 'Todo 1 Desc', false, 'Todo 1 Title', 1);