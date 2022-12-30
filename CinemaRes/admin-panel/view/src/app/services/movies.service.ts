import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IMovie } from './../models/movie.model';
import { Observable, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  public host = 'http://localhost:5000/';

  public movies$: Observable<IMovie[]> = this.http.get<IMovie[]>(this.host);

  constructor(private http: HttpClient) {}

  public updateMovies(movie: IMovie) {
    // should changed
    this.movies$ =  this.http.put<IMovie[]>(`${this.host}${movie.id}`, movie); 
  }


}
