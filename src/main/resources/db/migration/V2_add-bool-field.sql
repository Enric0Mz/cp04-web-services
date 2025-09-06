ALTER TABLE instructors ADD COLUMN ativo BOOLEAN DEFAULT TRUE;
UPDATE instructors SET ativo = TRUE;
