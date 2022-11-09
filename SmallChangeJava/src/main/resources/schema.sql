-- Schema definition for the departments and employees




DROP TABLE HOLDINGS;
DROP TABLE ORDERS;
DROP TABLE INSTRUMENT;
DROP TABLE CLIENT_FINANCE_DETAILS;
DROP TABLE CLIENT;

CREATE TABLE CLIENT (    
    CLIENT_ID VARCHAR(100),
    CLIENT_NAME VARCHAR(100),
    CLIENT_MAIL VARCHAR(100),
    CLIENT_SMALLCHANGE_WALLET FLOAT,
    PASSWORD VARCHAR(100),
CONSTRAINT CLIENT_ID_PK PRIMARY KEY(CLIENT_ID)
);

CREATE TABLE CLIENT_FINANCE_DETAILS (    
    CLIENT_ID VARCHAR(100),
    ACCOUNT_NUMBER VARCHAR(100),
    ACCOUNT_BALANCE FLOAT,
CONSTRAINT CLIENT_FINANCE_DETAILS_PK PRIMARY KEY(CLIENT_ID,ACCOUNT_NUMBER)
);

ALTER TABLE CLIENT_FINANCE_DETAILS ADD CONSTRAINT CLIENT_FINANCE_FK FOREIGN KEY (CLIENT_ID)
REFERENCES CLIENT(CLIENT_ID);

CREATE TABLE INSTRUMENT (    
    CODE VARCHAR(100),  
    CURRENT_PRICE FLOAT,
    NAME VARCHAR(100) UNIQUE,
    CATEGORY VARCHAR(100),
CONSTRAINT INSTRUMENT_PK PRIMARY KEY(CODE)
);

CREATE TABLE ORDERS (    
    CODE VARCHAR(100),
   	QUANTITY INTEGER,
    BUY_PRICE FLOAT,
    CLIENT_ID VARCHAR(100),
    ORDER_ID VARCHAR(100) PRIMARY KEY,
    TIMESTAMP DATE,
    DIRECTION VARCHAR(100),
     FOREIGN KEY (CODE) REFERENCES INSTRUMENT(CODE),
     FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT(CLIENT_ID)
);

CREATE TABLE HOLDINGS (    
    HOLDING_ID VARCHAR(100) unique,
    CLIENT_ID VARCHAR(100),
    CODE VARCHAR(100),
    QUANTITY integer,
    BUYPRICE float,
     FOREIGN KEY (CODE) REFERENCES INSTRUMENT(CODE),
     FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT(CLIENT_ID)
);
