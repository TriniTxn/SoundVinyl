-- USER
INSERT INTO tb_users (id, username, name, email, password, created_at)
VALUES (
           1,
           'giovanni',
           'Giovanni',
           'giovanni@mail.com',
           null,
           CURRENT_TIMESTAMP
       );

-- ARTIST
INSERT INTO artist (id, name, country, formed_in, mbid)
VALUES (
           1,
           'Daft Punk',
           'FR',
           1993,
           null
       );

-- ALBUM
INSERT INTO album (
    id,
    title,
    release_year,
    cover_url,
    type,
    artist_id,
    rating_avg,
    rating_count,
    mbid
)
VALUES (
           1,
           'Discovery',
           2001,
           'https://i.imgur.com/Qx8lcYE.jpeg',
           'SYNTHWAVE',
           1,
           4.5,
           1,
           null
       );

-- REVIEW (so stats make sense)
INSERT INTO review (
    id,
    user_id,
    album_id,
    rating,
    text,
    created_at,
    updated_at
)
VALUES (
           1,
           1,
           1,
           4.5,
           'Electronic classics.',
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP
       );
