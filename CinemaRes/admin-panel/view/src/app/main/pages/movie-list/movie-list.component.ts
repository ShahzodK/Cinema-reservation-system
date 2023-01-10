import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MoviesService } from '../../../services/movies.service';
import { IMovie } from '../../../models/movie.model';
import { MovieModalComponent } from '../../components/movie-modal/movie-modal.component';
import { ConfirmationModalComponent } from '../../components/confirmation-modal/confirmation-modal.component';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit {

  constructor(
                public moviesService: MoviesService,
                public dialog: MatDialog
             ) { }

  ngOnInit(): void {
    this.moviesService.getMovies();
  }

  public displayedColumns = ['name', 'image', 'genre', 'description', 'price', 'update', 'delete'];
  public clickedRows = new Set<IMovie>();

  public showModal(isNew: boolean, movie?: IMovie) {
    if(!isNew) {
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
    } else {
      const dialogRef = this.dialog.open(MovieModalComponent, {
        data: {
          isNew: true
        }
      })
    }
  }

  public showConfirmationModal(id: string) {
    const dialogRef = this.dialog.open(ConfirmationModalComponent, {
      data: {
        id
      }
    })
  }

}