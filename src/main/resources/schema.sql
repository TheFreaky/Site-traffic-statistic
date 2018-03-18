CREATE TABLE public.visits (
  id      SERIAL PRIMARY KEY,
  date    DATE NOT NULL,
  page_id VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX visits_date_page_id_user_id_uindex
  ON public.visits (date, page_id, user_id);