-- Usuário "logado"
INSERT INTO tb_users (id, username, name, email, password, created_at)
VALUES (1, 'giovanni', 'Giovanni', 'giovanni@mail.com', null, CURRENT_TIMESTAMP);

-- Artista + álbum
INSERT INTO artist (id, name, country, formed_in, mbid) VALUES
    (1, 'Daft Punk', 'FR', 1993, null);

INSERT INTO album (id, title, release_year, cover_url, type, artist_id, rating_avg, rating_count, mbid) VALUES
    (1, 'Discovery', 2001, 'https://i.imgur.com/8Km9tLL.jpg', 'ALBUM', 1, 0.0, 0, null);