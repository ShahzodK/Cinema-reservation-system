import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, combineLatest, skip } from 'rxjs';
import { IMovie } from './../models/movie.model';
import { IUser } from '../users/models/user.model';
import { IOrder } from '../orders/models/order.model';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  public host = 'http://localhost:5000/';

  public movies: BehaviorSubject<IMovie[]> = new BehaviorSubject<IMovie[]>([]);

  public users: BehaviorSubject<IUser[]> = new BehaviorSubject<IUser[]>([]);

  public orders: BehaviorSubject<IOrder[]> = new BehaviorSubject<IOrder[]>([]);

  constructor(private http: HttpClient) {}

  public getMovies() {
    return this.http.get<IMovie[]>(this.host).subscribe((movies) => this.movies.next(movies));
  }

  public createMovie(movie: IMovie) {
    return this.http.post<IMovie>(this.host, movie)
  }

  public updateMovies(movie: IMovie): Observable<IMovie[]> {
    return this.http.put<IMovie[]>(`${this.host}${movie.id}`, movie);
  }

  public deleteMovie(id: string) {
    return this.http.delete(`${this.host}${id}`);
  }

  public getUsers() {
    return this.http.get<IUser[]>(`${this.host}users`).subscribe((users) => this.users.next(users));
  }

  public getOrders() {
    return this.http.get<IOrder[]>(`${this.host}orders`).subscribe((orders) => this.orders.next(orders))
  }

}
