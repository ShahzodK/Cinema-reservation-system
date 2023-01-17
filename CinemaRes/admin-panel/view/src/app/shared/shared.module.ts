import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SortByColumnPipe } from './pipes/sort-by-column.pipe';
import { MatSortModule } from '@angular/material/sort';


@NgModule({
  declarations: [
    SortByColumnPipe,
  ],
  imports: [
    CommonModule,
    MatSortModule
  ],
  exports: [
    SortByColumnPipe,
    MatSortModule
  ]
})
export class SharedModule { }
