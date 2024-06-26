INSERT INTO users (id, username, password, email, phone, archive, role)
VALUES
    (nextval('user_seq'),'user','$2y$12$OQgDHihmxZytQ8yUTu7iDOr3vxPf8eYwacD19/nLN3V1/NmqpQy5O','user@list.ru','89991234567', false, 'ROLE_CLIENT');

INSERT INTO users (id, username, password, email, phone, archive, role)
VALUES
(nextval('user_seq'),'manager','$2y$12$2vwt1eFl6/CJre2CKIXc2O3fyOUAJuO1ja3MtfQ3Sru4SMrbibR2a','manager@list.ru','89991234567', false, 'ROLE_MANAGER');
