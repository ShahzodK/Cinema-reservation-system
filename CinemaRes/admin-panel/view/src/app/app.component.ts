import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MoviesService } from './services/movies.service';
import { IMovie } from './models/movie.model';
import { MovieModalComponent } from './components/movie-modal/movie-modal.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(
              public moviesService: MoviesService,
              public dialog: MatDialog
              ) {}

  ngOnInit(): void {
    this.moviesService.getMovies();

  }

  public displayedColumns = ['name', 'image', 'genre', 'description', 'price', 'update'];
  public clickedRows = new Set<IMovie>();

  public showModal(movie: IMovie) {
    const dialogRef = this.dialog.open(MovieModalComponent, {
      data: {
        isNew: false,
        id: movie?.id,
        name: movie?.name,
        genre: movie?.genre,
        description: movie?.description,
        img: movie?.img,
        price: movie?.price,
      }
    })
  }

}
