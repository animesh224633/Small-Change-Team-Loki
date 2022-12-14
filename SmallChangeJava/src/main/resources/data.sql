INSERT INTO CLIENT (CLIENT_ID, CLIENT_NAME, CLIENT_MAIL,CLIENT_SMALLCHANGE_WALLET,PASSWORD)  VALUES('1','TEAMLOKI','teamloki@gmail.com',3444.6,'teamloki');
INSERT INTO CLIENT (CLIENT_ID, CLIENT_NAME, CLIENT_MAIL,CLIENT_SMALLCHANGE_WALLET,PASSWORD)  VALUES('2','LOKI','loki@gmail.com',78444.6,'loki');

INSERT INTO CLIENT_FINANCE_DETAILS(CLIENT_ID, ACCOUNT_NUMBER, ACCOUNT_BALANCE)  VALUES('1','122354D',45645444.6);
INSERT INTO CLIENT_FINANCE_DETAILS(CLIENT_ID, ACCOUNT_NUMBER, ACCOUNT_BALANCE)  VALUES('2','83783AA',5645444.6);
INSERT INTO CLIENT_FINANCE_DETAILS(CLIENT_ID, ACCOUNT_NUMBER, ACCOUNT_BALANCE)  VALUES('1','IIGG776',7776.99);

INSERT INTO INSTRUMENT(CODE,CURRENT_PRICE,NAME,CATEGORY) VALUES ('AMZN',100.8,'AMAZON','STOCK');
INSERT INTO INSTRUMENT(CODE,CURRENT_PRICE,NAME,CATEGORY) VALUES ('APL',121.3,'APPLE','STOCK');
INSERT INTO INSTRUMENT(CODE,CURRENT_PRICE,NAME,CATEGORY) VALUES ('SBIMF',111.7,'SBI MUTUAL FUND','MUTUALFUND');
INSERT INTO INSTRUMENT(CODE,CURRENT_PRICE,NAME,CATEGORY) VALUES ('LMF',1141.7,'LEAP MUTUAL FUND','MUTUALFUND');

INSERT INTO ORDERS(CODE,QUANTITY,BUY_PRICE,CLIENT_ID,ORDER_ID,TIMESTAMP,DIRECTION) VALUES('AMZN',10,5694.7,'1','101',TO_DATE('2022-10-12','YYYY-MM-DD'),'BUY');
INSERT INTO ORDERS(CODE,QUANTITY,BUY_PRICE,CLIENT_ID,ORDER_ID,TIMESTAMP,DIRECTION) VALUES('APL',12,54.7,'2','102',TO_DATE('2022-10-12','YYYY-MM-DD'),'BUY');
INSERT INTO ORDERS(CODE,QUANTITY,BUY_PRICE,CLIENT_ID,ORDER_ID,TIMESTAMP,DIRECTION) VALUES('LMF',5,232.6,'1','103',TO_DATE('2022-10-12','YYYY-MM-DD'),'BUY');
INSERT INTO ORDERS(CODE,QUANTITY,BUY_PRICE,CLIENT_ID,ORDER_ID,TIMESTAMP,DIRECTION) VALUES('APL',12,54.7,'2','104',TO_DATE('2022-10-12','YYYY-MM-DD'),'SELL');

insert into holdings(holding_id,client_id,code,quantity,buyprice) values ('1101','1','AMZN',1,5694.7);
insert into holdings(holding_id,client_id,code,quantity,buyprice) values ('1102','1','LMF',1,232.6);
insert into holdings(holding_id,client_id,code,quantity,buyprice) values ('1103','2','APL',1,54.7);
commit;