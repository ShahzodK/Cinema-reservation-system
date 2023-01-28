import { Component, OnInit } from '@angular/core';
import { MoviesService } from 'src/app/services/movies.service';
import { IUser } from '../../models/user.model';

@Component({
  selector: 'app-users-page',
  templateUrl: './users-page.component.html',
  styleUrls: ['./users-page.component.scss']
})
export class UsersPageComponent implements OnInit {

  constructor(
              public moviesService: MoviesService
              ) { }

  ngOnInit(): void {
    this.moviesService.getUsers();
  }

  public displayedColumns = ['name', 'username', 'email', 'phone', 'purchasedMovies'];
  public clickedRows = new Set<IUser>();
}
