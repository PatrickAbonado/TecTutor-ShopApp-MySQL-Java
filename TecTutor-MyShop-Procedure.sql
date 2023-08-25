DELIMITER //

CREATE PROCEDURE concession_calculate(IN input_code VARCHAR(255), OUT calculated_price DECIMAL(10, 2))
BEGIN
    DECLARE price DECIMAL(10, 2);
    
    SELECT item_price INTO price FROM item WHERE item_code = input_code;
    
    IF price < 10 THEN
        SET calculated_price = item_price;
    ELSEIF price >= 10 AND price <= 100 THEN
        SET calculated_price = price * 0.9; -- 10% concession
    ELSE
        SET calculated_price = price * 0.8; -- 20% concession
    END IF;
END;

//

DELIMITER ;