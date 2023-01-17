import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MoviesService } from '../../../services/movies.service';
import { IMovie } from '../../../models/movie.model';
import { MovieModalComponent } from '../../components/movie-modal/movie-modal.component';
import { ConfirmationModalComponent } from '../../components/confirmation-modal/confirmation-modal.component';
import { MatSort, Sort } from '@angular/material/sort';
import { last, skip, skipLast, take } from 'rxjs/operators';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit, AfterViewInit {

  @ViewChild(MatSort) sort!: MatSort;

  public dataSource: MatTableDataSource<IMovie> = new MatTableDataSource<IMovie>([]);

  public ELEMENT_DATA = [
    {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
    {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
    {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
    {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
    {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
    {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
    {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
    {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
    {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
    {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
  ];


  constructor(
                public moviesService: MoviesService,
                public dialog: MatDialog,
             ) { }

  ngOnInit(): void {
    this.moviesService.getMovies();
    this.moviesService.getUsers();
  }

  ngAfterViewInit() { 
    this.moviesService.movies.pipe(skip(1)).subscribe((movies: IMovie[]) => {
      this.dataSource = new MatTableDataSource(movies);
      this.dataSource.sort = this.sort;

    } )
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
