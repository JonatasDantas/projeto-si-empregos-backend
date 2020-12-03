DELETE FROM role;

INSERT INTO role (name) VALUES ('ROLE_USER'), ('ROLE_RECRUITER'), ('ROLE_ADMIN');

INSERT INTO qualification (name) VALUES
    ('mecânique'),
    ('eletriciste'),
    ('costureire'),
    ('bailarine');

INSERT INTO USERS (cpf, email, email_verified, gender, name, password, phone) VALUES
('00000000191','admin@admin.com','FALSE','Masculino','Ademir','$2a$10$3YLaMODYqGSv17MRIPhQ0ucap2EZpPIV1iMdd09rJpNuwuv0VtygS','11987641234');

INSERT INTO USERS_ROLES (user_id, roles_id) VALUES
(1,1),
(1,2),
(1,3);