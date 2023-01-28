import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MoviesService } from '../../../services/movies.service';
import { IMovie } from '../../../models/movie.model';
import { MovieModalComponent } from '../../components/movie-modal/movie-modal.component';
import { ConfirmationModalComponent } from '../../components/confirmation-modal/confirmation-modal.component';
import { MatSort } from '@angular/material/sort';
import { skip } from 'rxjs/operators';
import { IUser } from 'src/app/users/models/user.model';
import { combineLatest } from 'rxjs';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit, AfterViewInit {

  @ViewChild(MatSort) sort!: MatSort;

  public dataSource: MatTableDataSource<IMovie> = new MatTableDataSource<IMovie>([]);


  constructor(
                public moviesService: MoviesService,
                public dialog: MatDialog,
             ) { }

  ngOnInit(): void {
    this.moviesService.getMovies();
    this.moviesService.getUsers();
    combineLatest([this.moviesService.users, this.moviesService.movies]).pipe(skip(2)).subscribe(([users, movies]) => {
      const cumulativePurchasedMovies: { [key: string]: number } = {};

      users.map((user: IUser) => {
      const purchasedMovies = JSON.parse(user.purchasedMovies);
        for (const key in purchasedMovies) {
          if (cumulativePurchasedMovies.hasOwnProperty(key)) {
            cumulativePurchasedMovies[key] += purchasedMovies[key];
          }
          else cumulativePurchasedMovies[key] = purchasedMovies[key];
        }

      movies.map((movie) => {
          if (cumulativePurchasedMovies[movie.name]) movie.purchasedTickets = cumulativePurchasedMovies[movie.name];
        });
      })
    })
  }

  ngAfterViewInit() {
    this.moviesService.movies.pipe(skip(1)).subscribe((movies) => {
      this.dataSource = new MatTableDataSource(movies);
      this.dataSource.sort = this.sort;
    })
}

  public displayedColumns = ['name', 'image', 'genre', 'description', 'price', 'purchasedTickets', 'tickets', 'update', 'delete'];
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
          tickets: movie?.tickets
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
