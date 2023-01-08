
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { IMovie } from '../../../models/movie.model';
import { MoviesService } from '../../../services/movies.service';



@Component({
  selector: 'app-movie-modal',
  templateUrl: './movie-modal.component.html',
  styleUrls: ['./movie-modal.component.scss']
})

export class MovieModalComponent {

  constructor(
    public dialogRef: MatDialogRef<MovieModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IMovie & {isNew: string},
    private movieService: MoviesService,
    ) {}

  public movieForm = new FormGroup({
    name: new FormControl(this.data.name, Validators.required),
    genre: new FormControl(this.data.genre, Validators.required),
    description: new FormControl(this.data.description, Validators.required),
    img: new FormControl(this.data.img, Validators.required),
    price: new FormControl(this.data.price, Validators.required)
  })

  public onSubmit() {
    const movieData: IMovie = {
      id: this.data.id ,
      name: this.movieForm.getRawValue().name!,
      genre: this.movieForm.getRawValue().genre!,
      description: this.movieForm.getRawValue().description!,
      img: this.movieForm.getRawValue().img!,
      price: this.movieForm.getRawValue().price!,
    }
    if(this.data.isNew) {
      this.movieService.createMovie(movieData).subscribe(() => this.movieService.getMovies());
      this.dialogRef.close([]);
    }
    this.movieService.updateMovies(movieData).subscribe(() => this.movieService.getMovies());
    this.dialogRef.close([]);
  }

}


