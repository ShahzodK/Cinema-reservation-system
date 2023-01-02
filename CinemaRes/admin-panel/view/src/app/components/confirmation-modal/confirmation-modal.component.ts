import { IMovie } from './../../models/movie.model';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MoviesService } from 'src/app/services/movies.service';
import { MovieModalComponent } from '../movie-modal/movie-modal.component';

@Component({
  selector: 'app-confirmation-modal',
  templateUrl: './confirmation-modal.component.html',
  styleUrls: ['./confirmation-modal.component.scss']
})
export class ConfirmationModalComponent implements OnInit {

  constructor(
              public dialogRef: MatDialogRef<MovieModalComponent>,
              @Inject(MAT_DIALOG_DATA) public data: {id: string},
              public movieService: MoviesService,
              ) { }

  ngOnInit(): void {
  }

  public deleteMovie() {
    this.movieService.deleteMovie(this.data.id).subscribe(() => this.movieService.getMovies())
    this.dialogRef.close([]);
  }

}
