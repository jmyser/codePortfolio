/* Order Tables from DWP Chapter 9, by Paul Wang
 * revised by Jay Myser
 * to use member table instead of customer table
/* drop table IF EXISTS order_item, orders, customer, product; */

/* replace customer table with member table, using PRIMARY KEY (uid) */
/*
CREATE TABLE customer
(  id VARCHAR(10),
   lastname VARCHAR(25),
   firstname VARCHAR(15),
   email VARCHAR(35),
   address VARCHAR(35),
   city VARCHAR(15),
   state CHAR(2),
   zip5 CHAR(5),
   zip4 CHAR(4),
   PRIMARY KEY (id)
)  ENGINE = InnoDB;
*/

CREATE TABLE product
(  id VARCHAR(12),
   description  VARCHAR(256),
   price DECIMAL(7,2),
   PRIMARY KEY (id)
)  ENGINE = InnoDB;


CREATE TABLE orders
(  id       VARCHAR(10),
   customer VARCHAR(10),
   order_date DATE NOT NULL,
   PRIMARY KEY (id),
   /* FOREIGN KEY (customer) REFERENCES customer (id) */
   FOREIGN KEY (customer) REFERENCES member (uid)
)  ENGINE = InnoDB;

CREATE TABLE order_item
(  order_id VARCHAR(10),
   prod_id  VARCHAR(12),
   qty      SMALLINT DEFAULT 1,
   FOREIGN KEY (order_id) REFERENCES orders (id) 
      ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (prod_id)  REFERENCES product (id),
   PRIMARY KEY (order_id, prod_id)
)  ENGINE = InnoDB;
