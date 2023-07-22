CREATE TABLE refreshtoken (
                              id BIGSERIAL PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              token VARCHAR(255) NOT NULL UNIQUE,
                              expiryDate TIMESTAMP NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES "users"(id)
);
