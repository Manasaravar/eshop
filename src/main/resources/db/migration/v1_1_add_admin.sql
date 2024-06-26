INSERT INTO users (id, username, password, email, phone, archive, role)
VALUES
    (1,'admin','$2y$12$.e0qADJXLJ/XlGEJRLIIZ.mG4NiFRaopedFyn43r.Vq3zAP5xqaTW','knittingshop@list.ru','88001234567', false, 'ROLE_ADMIN');
ALTER SEQUENCE user_seq RESTART WITH 2;