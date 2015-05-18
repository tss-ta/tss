-------------------------------------------------------
-- Popular Car Category -------------------------------
-------------------------------------------------------
SELECT cc.name AS "Category", count(txo.car_category) AS "Orders Amount"
  FROM taxi_order AS txo
	JOIN car_category AS cc
		ON txo.car_category = cc.id
	GROUP BY txo.car_category, cc.name
	ORDER BY count(txo.car_category) DESC;


-------------------------------------------------------
-- Popular Driver Category ----------------------------
-------------------------------------------------------
SELECT dct.name AS "Category", count(d.category) AS "Orders Amount"
  FROM driver AS d
	JOIN driver_car AS dc
		ON d.driver_id = dc.driver_id
	JOIN taxi_order AS o
		ON dc.id = o.driver_car_id
	JOIN driver_category AS dct
		ON dct.id = d.category
	GROUP BY d.category, dct.name
	ORDER BY count(d.category) DESC;


-------------------------------------------------------
-- Popular Car Options By User ------------------------
-------------------------------------------------------
SELECT t.email AS "User Email", t."Option Name", t."Select Amount"
	FROM (
		SELECT c.email, 'Animal Transport' AS "Option Name", sum(animal_transport::int) AS "Orders with option"
			FROM taxi_order
			JOIN contacts AS c
				ON c.id = contacts_id
			GROUP BY c.email
		UNION
		SELECT c.email, 'WI-FI' AS "Option Name", sum(wifi::int) AS "Orders with option"
			FROM taxi_order
			JOIN contacts AS c
				ON c.id = contacts_id
			GROUP BY c.email
		UNION
		SELECT c.email, 'Conditioner' AS "Option Name", sum(conditioner::int) AS "Orders with option"
		FROM taxi_order
		JOIN contacts AS c
				ON c.id = contacts_id
			GROUP BY c.email
	) AS t
	ORDER BY t.email, t."Orders with option" DESC LIMIT ? OFFSET ?;

-- Count Query ----------------------------------------

SELECT count(t."Option Name")
	FROM (
		SELECT c.email, 'Animal Transport' AS "Option Name", sum(animal_transport::int) AS "Orders with option"
			FROM taxi_order
			JOIN contacts AS c
				ON c.id = contacts_id
			GROUP BY c.email
		UNION
		SELECT c.email, 'WI-FI' AS "Option Name", sum(wifi::int) AS "Orders with option"
			FROM taxi_order
			JOIN contacts AS c
				ON c.id = contacts_id
			GROUP BY c.email
		UNION
		SELECT c.email, 'Conditioner' AS "Option Name", sum(conditioner::int) AS "Orders with option"
		FROM taxi_order
		JOIN contacts AS c
				ON c.id = contacts_id
			GROUP BY c.email
	) AS t;

-------------------------------------------------------
-- Popular Car Options --------------------------------
-------------------------------------------------------
SELECT *
	FROM (
		SELECT 'Animal Transport' AS "Option Name", sum(animal_transport::int) AS "Select Amount"
			FROM taxi_order
		UNION
		SELECT 'WI-FI' AS "Option Name", sum(wifi::int) AS "Select Amount"
			FROM taxi_order
		UNION
		SELECT 'Conditioner' AS "Option Name", sum(conditioner::int) AS "Select Amount"
		FROM taxi_order
	) AS T
	ORDER BY "Select Amount" DESC;


-------------------------------------------------------
-- Service profitability by... ------------------------
-------------------------------------------------------
WITH last_order as (
	SELECT id, price FROM taxi_order
		WHERE order_time >  CURRENT_DATE - INTERVAL '6 month'
)
SELECT t.service_type AS "Service", t.total_price AS "Total price", t.orders_amount AS "Orders amount"
	FROM (
	SELECT 'Convey corporation' AS service_type, ceiling(sum(lo.price)) as total_price, count(s.service_name) as orders_amount
		FROM service AS s
		JOIN last_order AS lo
			ON s.order_id = lo.id
		WHERE s.service_name = 'conveycorp'
		GROUP BY s.service_name
	UNION
	SELECT 'Sober driver' AS service_type, ceiling(sum(lo.price)) as total_price, count(s.service_name) as orders_amount
		FROM service AS s
		JOIN last_order AS lo
			ON s.order_id = lo.id
		WHERE s.service_name = 'sober'
		GROUP BY s.service_name
	UNION
	SELECT 'Meet my guest' AS service_type, ceiling(sum(lo.price)) as total_price, count(s.service_name) as orders_amount
		FROM service AS s
		JOIN last_order AS lo
			ON s.order_id = lo.id
		WHERE s.service_name = 'meetMyGuest'
		GROUP BY s.service_name
	UNION
	SELECT 'Celebration' AS service_type, ceiling(sum(lo.price)) as total_price, count(lo.id) as orders_amount
		FROM celebration AS c
		JOIN last_order AS lo
			ON c.order_id = lo.id) t
	ORDER BY t.total_price, t.orders_amount;
