export interface IMovie {
  id: number;
  name: string;
  genre: string;
  description: string;
  price: number;
  img: string;
  imdb: number,
  year: number,
  country: string,
  director: string,
  actors: string,
  language: string,
  purchasedTickets?: number;
  tickets: number;
}
