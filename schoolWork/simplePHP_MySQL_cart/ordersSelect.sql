-- Sample select
SELECT product.id as Product_ID, product.description
AS Description, product.price AS Price, qty AS Quantity,
qty*product.price AS Amt FROM order_item, product
WHERE order_id='ord_01009' && prod_id=product.id
