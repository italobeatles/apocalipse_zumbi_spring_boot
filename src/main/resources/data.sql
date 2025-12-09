INSERT INTO tbrecursos (id, descricao, pontos) VALUES (1, 'Agua', 4) ON CONFLICT DO NOTHING;
INSERT INTO tbrecursos (id, descricao, pontos) VALUES (2, 'Comida', 3) ON CONFLICT DO NOTHING;
INSERT INTO tbrecursos (id, descricao, pontos) VALUES (3, 'Medicamento', 2) ON CONFLICT DO NOTHING;
INSERT INTO tbrecursos (id, descricao, pontos) VALUES (4, 'Municao', 1) ON CONFLICT DO NOTHING;
