DROP TABLE invoices IF EXISTS;

CREATE TABLE invoices (
  id          INTEGER IDENTITY PRIMARY KEY,
  pet_id      INTEGER NOT NULL,
  invoice_date  DATE,
  description VARCHAR(8192)
);

CREATE INDEX invoices_pet_id ON invoices (pet_id);
