import { Component, OnInit } from '@angular/core';
import { combineLatest } from 'rxjs';
import { skip } from 'rxjs/operators';
import { MoviesService } from 'src/app/services/movies.service';
import { IOrder } from '../../models/order.model';

@Component({
  selector: 'app-orders-page',
  templateUrl: './orders-page.component.html',
  styleUrls: ['./orders-page.component.scss']
})
export class OrdersPageComponent implements OnInit {

  constructor(public moviesService: MoviesService) { }

  ngOnInit(): void {
    this.moviesService.getOrders();
  }

  public displayedColumns = ['orderId', 'username', 'movie', 'ticket', 'totalPrice', 'status'];
  public clickedRows = new Set<IOrder>();
}
