
import { Component, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
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
    imdb: new FormControl(this.data.imdb, Validators.required),
    year: new FormControl(this.data.year, Validators.required),
    country: new FormControl(this.data.country, Validators.required),
    director: new FormControl(this.data.director, Validators.required),
    actors: new FormControl(this.data.actors, Validators.required),
    language: new FormControl(this.data.language, Validators.required),
    price: new FormControl(this.data.price, Validators.required),
    tickets: new FormControl(this.data.tickets, Validators.required)
  })

  public onSubmit() {
    const movieData: IMovie = {
      id: this.data.id ,
      name: this.movieForm.getRawValue().name!,
      genre: this.movieForm.getRawValue().genre!,
      description: this.movieForm.getRawValue().description!,
      img: this.movieForm.getRawValue().img!,
      imdb: this.movieForm.getRawValue().imdb!,
      year: this.movieForm.getRawValue().year!,
      country: this.movieForm.getRawValue().country!,
      director: this.movieForm.getRawValue().director!,
      actors: this.movieForm.getRawValue().actors!,
      language: this.movieForm.getRawValue().language!,
      price: this.movieForm.getRawValue().price!,
      tickets: this.movieForm.getRawValue().tickets!
    }
    if(this.data.isNew) {
      this.movieService.createMovie(movieData).subscribe(() => this.movieService.getMovies());
      this.dialogRef.close([]);
    }
    this.movieService.updateMovies(movieData).subscribe(() => this.movieService.getMovies());
    this.dialogRef.close([]);
  }

}

