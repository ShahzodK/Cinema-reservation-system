import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IMovie } from './../models/movie.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  public host = 'http://localhost:5000/';
  
  public movies: Observable<IMovie[]>;

  constructor(private http: HttpClient) { 
    this.movies = this.http.get<IMovie[]>(this.host);
  }

  public getMovies() {
    this.movies = this.http.get<IMovie[]>(this.host);
  }


}
