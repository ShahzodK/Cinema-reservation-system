import { Component, Input, OnInit } from '@angular/core';
import { IMovie } from './../../models/movie.model';

@Component({
  selector: 'app-movie-item',
  templateUrl: './movie-item.component.html',
  styleUrls: ['./movie-item.component.scss']
})
export class MovieItemComponent implements OnInit {

  @Input() public movie: IMovie | undefined

  constructor() { }

  ngOnInit(): void {
  }


}
