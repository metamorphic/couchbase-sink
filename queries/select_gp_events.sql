SELECT m.customer_id_2 AS siebel_customer_number, t.event_type, e.event_ts, e.event_property
FROM (SELECT * FROM cxp.events LIMIT 10) e
JOIN cxp.customer_id_types i ON i.customer_id_type_id = e.customer_id_type_id
JOIN cxp.event_types t ON t.event_type_id = e.event_type_id
JOIN cxp.customer_id_mapping m ON m.customer_id_type_id_1 = e.customer_id_type_id AND m.customer_id_1::text = e.customer_id::text
ORDER BY e.event_ts DESC
