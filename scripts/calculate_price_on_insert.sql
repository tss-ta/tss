CREATE OR REPLACE FUNCTION calculate_price() RETURNS TRIGGER AS $$
DECLARE
  order_price FLOAT;
  distance_price FLOAT;

  route_distance FLOAT;


BEGIN
  order_price = 0;
  SELECT route.distance INTO route_distance
  FROM route
  WHERE route.route_id = NEW.route_id;

    order_price = order_price + (route_distance + (SELECT plus_coef
                                    FROM tariff
                                    WHERE tariff_name = 'per_km')) *
                                   (SELECT multiple_coef
                                    FROM tariff
                                    WHERE tariff_name = 'per_km');

  IF EXTRACT(HOUR FROM NEW.order_time) > 22 AND EXTRACT(HOUR FROM NEW.order_time) < 7 THEN
    order_price = (order_price + (SELECT plus_coef
                                     FROM tariff
                                     WHERE tariff_name = 'night')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'night');
  END IF;

  IF EXTRACT(HOUR FROM NEW.order_time) > 9 AND EXTRACT(HOUR FROM NEW.order_time) < 11 THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'rush_hour')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'rush_hour');
  END IF;

  IF NEW.wifi IS NOT NULL THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'wi-fi')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'wi-fi');
  END IF;

  IF NEW.car_category = 1 THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'business')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'business');
  END IF;

  IF NEW.car_category = 2 THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'economy')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'economy');
  END IF;

  IF NEW.car_category = 3 THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'van')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'van');
  END IF;

  IF NEW.car_category = 4 THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'cargo')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'cargo');
  END IF;

  IF NEW.smoke = TRUE THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'no_smoked')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'no_smoked');
  END IF;

  IF NEW.animal_transport = TRUE THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'animal')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'animal');
  END IF;

  IF NEW.conditioner = TRUE THEN
    order_price = (order_price + (SELECT plus_coef
                                  FROM tariff
                                  WHERE tariff_name = 'conditioner')) *
                  (SELECT multiple_coef
                   FROM tariff
                   WHERE tariff_name = 'conditioner');
  END IF;

  UPDATE taxi_order SET price = round( CAST(order_price AS NUMERIC), 2)
  WHERE id = NEW.id;
  RETURN NULL;
  END;
$$ LANGUAGE 'plpgsql';


CREATE TRIGGER t_price
AFTER INSERT ON taxi_order FOR EACH ROW EXECUTE
PROCEDURE calculate_price();

