import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IMovie } from './../models/movie.model';
import { Observable, switchMap } from 'rxjs';
import { IUser } from '../users/models/user.model';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  public host = 'http://localhost:5000/';

  public movies: IMovie[] = [];

  public users: IUser[] = [];

  constructor(private http: HttpClient) {}

  public getMovies() {
    return this.http.get<IMovie[]>(this.host).subscribe((movies) => this.movies = movies)
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
    return this.http.get<IUser[]>(`${this.host}users`).subscribe((users) => this.users = users)
  }

}