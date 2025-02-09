-- Schema creation
DROP TABLE IF EXISTS order_items;

DROP TABLE IF EXISTS cart_items;

DROP TABLE IF EXISTS orders;

DROP TABLE IF EXISTS books;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       user_id VARCHAR(36) PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255)
);

CREATE TABLE books (
                       book_id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255),
                       genre VARCHAR(255),
                       price INT,
                       availability_status ENUM('IN_STOCK', 'OUT_OF_STOCK'),
                       origin_price INT,
                       inventory INT,
                       description TEXT,
                       image_url VARCHAR(255)
);

CREATE TABLE orders (
                        order_id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id VARCHAR(36),
                        order_date DATETIME,
                        FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE order_items (
                             order_item_id INT AUTO_INCREMENT PRIMARY KEY,
                             order_id INT,
                             book_id INT,
                             amount INT,
                             price INT,
                             FOREIGN KEY (order_id) REFERENCES orders (order_id),
                             FOREIGN KEY (book_id) REFERENCES books (book_id)
);

CREATE TABLE cart_items (
                            cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
                            user_id VARCHAR(36),
                            book_id INT,
                            amount INT,
                            FOREIGN KEY (user_id) REFERENCES users (user_id),
                            FOREIGN KEY (book_id) REFERENCES books (book_id)
);

-- Mock data insertion
INSERT INTO
    users (user_id, username, password, email)
VALUES
    (
        '550e8400-e29b-41d4-a716-446655440000',
        'Bilal Ahmed',
        '$2a$10$.RxWXAF86INbwBxSAcPiPurhUqQmPdvp1EemKbGnxXe86FhqZ736O',
        'info@bilal-ahmed-dev.com'
    ),
    (
        '6ba7b810-9dad-11d1-80b4-00c04fd430c8',
        'John Doe',
        '$2a$10$.RxWXAF86INbwBxSAcPiPurhUqQmPdvp1EemKbGnxXe86FhqZ736O',
        'user@example.com'
    ),
    (
        '6ba7b811-9dad-11d1-80b4-00c04fd430c9',
        'Dev Test',
        '$2a$10$.RxWXAF86INbwBxSAcPiPurhUqQmPdvp1EemKbGnxXe86FhqZ736O',
        'dev@test.com'
    );

INSERT INTO
    books (
    title,
    author,
    genre,
    price,
    availability_status,
    origin_price,
    inventory,
    description,
    image_url
)
VALUES
    (
        'Clean Code',
        'Robert C. Martin',
        'Programming',
        45,
        'IN_STOCK',
        50,
        100,
        'Software engineering best practices',
        'http://example.com/clean-code.jpg'
    ),
    (
        'Design Patterns',
        'Gamma et al.',
        'Computer Science',
        55,
        'OUT_OF_STOCK',
        60,
        0,
        'Classic patterns reference',
        'http://example.com/patterns.jpg'
    ),
    (
        'The Pragmatic Programmer',
        'Andrew Hunt',
        'Software Development',
        40,
        'IN_STOCK',
        45,
        75,
        'Developer career handbook',
        'http://example.com/pragmatic.jpg'
    );

INSERT INTO
    orders (user_id, order_date)
VALUES
    (
        '550e8400-e29b-41d4-a716-446655440000',
        '2024-03-15 10:30:00'
    ),
    (
        '6ba7b810-9dad-11d1-80b4-00c04fd430c8',
        '2024-03-16 14:45:00'
    );

INSERT INTO
    order_items (order_id, book_id, amount, price)
VALUES
    (1, 1, 2, 45),
    (1, 3, 1, 40),
    (2, 2, 3, 55);

INSERT INTO
    cart_items (user_id, book_id, amount)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 3, 2),
    ('6ba7b810-9dad-11d1-80b4-00c04fd430c8', 1, 1);