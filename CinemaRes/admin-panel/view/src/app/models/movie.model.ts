export interface IMovie {
  id: number;
  name: string;
  genre: string;
  description: string;
  price: number;
  img: string;
  purchasedTickets?: number;
  tickets: number;
}
