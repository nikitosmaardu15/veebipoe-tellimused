INSERT INTO orders (order_number, status, created_at)
VALUES ('ORD-1001', 'PAID', '2026-10-28 10:15:00+02'),
       ('ORD-1002', 'NEW', '2026-10-29 12:15:00+02');

INSERT INTO order_items (order_id, name, quantity, unit_price)
VALUES ((SELECT id FROM orders WHERE order_number = 'ORD-1001'), 'Klaviatuur', 1, 49.90),
       ((SELECT id FROM orders WHERE order_number = 'ORD-1001'), 'Hiir', 2, 19.99),
       ((SELECT id FROM orders WHERE order_number = 'ORD-1002'), 'Monitor', 1, 199.00);