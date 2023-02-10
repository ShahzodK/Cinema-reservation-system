import { SharedModule } from './../shared/shared.module';
import { OrdersRoutingModule } from './orders-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdersPageComponent } from './pages/orders-page/orders-page.component';



@NgModule({
  declarations: [
    OrdersPageComponent
  ],
  imports: [
    CommonModule,
    OrdersRoutingModule,
    SharedModule
  ]
})
export class OrdersModule { }
