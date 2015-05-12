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
-- Popular Driver Category -------------------------------
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