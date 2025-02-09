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

INSERT INTO books (
    title,
    author,
    genre,
    price,
    availability_status,
    origin_price,
    inventory,
    description,
    image_url
) VALUES
      -- Programming (3)
      ('Clean Code', 'Robert C. Martin', 'Programming', 45, 'IN_STOCK', 50, 100, 'Software engineering best practices', '/images/clean-code.jpg'),
      ('Code Complete', 'Steve McConnell', 'Programming', 50, 'IN_STOCK', 55, 80, 'Practical construction techniques', '/images/code-complete.jpg'),
      ('Refactoring', 'Martin Fowler', 'Programming', 40, 'OUT_OF_STOCK', 45, 0, 'Improving code structure', '/images/refactoring.jpg'),

      -- Algorithms (3)
      ('Introduction to Algorithms', 'Cormen et al.', 'Algorithms', 60, 'IN_STOCK', 65, 50, 'Comprehensive algorithm guide', '/images/algo-intro.jpg'),
      ('Algorithm Design Manual', 'Steven Skiena', 'Algorithms', 55, 'IN_STOCK', 60, 30, 'Practical algorithm approaches', '/images/algo-manual.jpg'),
      ('Grokking Algorithms', 'Aditya Bhargava', 'Algorithms', 35, 'OUT_OF_STOCK', 40, 0, 'Visual algorithm guide', '/images/grokking-algo.jpg'),

      -- Web Development (3)
      ('Eloquent JavaScript', 'Marijn Haverbeke', 'Web Development', 30, 'IN_STOCK', 35, 120, 'Modern JS fundamentals', '/images/eloquent-js.jpg'),
      ('You Don''t Know JS', 'Kyle Simpson', 'Web Development', 25, 'IN_STOCK', 30, 90, 'Deep JS concepts', '/images/ydkjs.jpg'),
      ('HTTP: The Definitive Guide', 'David Gourley', 'Web Development', 40, 'OUT_OF_STOCK', 45, 0, 'Web protocol deep dive', '/images/http-guide.jpg'),

      -- Databases (3)
      ('Designing Data-Intensive Apps', 'Martin Kleppmann', 'Databases', 50, 'IN_STOCK', 55, 60, 'Modern database patterns', '/images/ddia.jpg'),
      ('SQL Performance Explained', 'Markus Winand', 'Databases', 45, 'IN_STOCK', 50, 40, 'Query optimization guide', '/images/sql-perf.jpg'),
      ('Redis in Action', 'Josiah Carlson', 'Databases', 35, 'OUT_OF_STOCK', 40, 0, 'Redis practical guide', '/images/redis-action.jpg'),

      -- DevOps (3)
      ('The Phoenix Project', 'Gene Kim', 'DevOps', 40, 'IN_STOCK', 45, 70, 'DevOps novel approach', '/images/phoenix-project.jpg'),
      ('Site Reliability Engineering', 'Betsy Beyer', 'DevOps', 55, 'IN_STOCK', 60, 50, 'Google SRE practices', '/images/sre-book.jpg'),
      ('Kubernetes Up & Running', 'Kelsey Hightower', 'DevOps', 50, 'OUT_OF_STOCK', 55, 0, 'K8s practical guide', '/images/k8s-book.jpg'),

      -- Security (3)
      ('Hacking: The Art of Exploitation', 'Jon Erickson', 'Security', 45, 'IN_STOCK', 50, 30, 'Low-level security concepts', '/images/hacking-art.jpg'),
      ('Web Application Security', 'Andrew Hoffman', 'Security', 50, 'IN_STOCK', 55, 40, 'Modern web security', '/images/web-app-sec.jpg'),
      ('Cryptography Engineering', 'Bruce Schneier', 'Security', 60, 'OUT_OF_STOCK', 65, 0, 'Practical cryptography', '/images/crypto-eng.jpg'),

      -- Software Architecture (3)
      ('Domain-Driven Design', 'Eric Evans', 'Software Architecture', 55, 'IN_STOCK', 60, 45, 'Strategic design patterns', '/images/ddd-book.jpg'),
      ('Building Microservices', 'Sam Newman', 'Software Architecture', 50, 'IN_STOCK', 55, 35, 'Distributed system design', '/images/microservices-book.jpg'),
      ('Patterns of Enterprise Application Architecture', 'Martin Fowler', 'Software Architecture', 60, 'OUT_OF_STOCK', 65, 0, 'Architectural patterns', '/images/poeaa.jpg');

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