/* Order Tables from DWP Chapter 9, by Paul Wang
 * revised by Jay Myser
 *
 * new version of orders and order_item to use AUTO_INCREMENT
 * for order id, to allow new orders to be created with unique id
 */

CREATE TABLE orders
(  /* id VARCHAR(10), replaced by AUTO_INCREMENT */
   id INT NOT NULL AUTO_INCREMENT,
   customer VARCHAR(10),
   order_date DATE NOT NULL,
   PRIMARY KEY (id),
   /* FOREIGN KEY (customer) REFERENCES customer (id) */
   FOREIGN KEY (customer) REFERENCES member (uid)
)  ENGINE = InnoDB;

CREATE TABLE order_item
(  /* order_id VARCHAR(10), replaced by INT to reference orders.id */
   order_id INT NOT NULL,
   prod_id  VARCHAR(12),
   qty      SMALLINT DEFAULT 1,
   FOREIGN KEY (order_id) REFERENCES orders (id) 
      ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (prod_id)  REFERENCES product (id),
   PRIMARY KEY (order_id, prod_id)
)  ENGINE = InnoDB;
