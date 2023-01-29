import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SortByColumnPipe } from './pipes/sort-by-column.pipe';
import { MatSortModule } from '@angular/material/sort';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { CdkTableModule } from '@angular/cdk/table';


@NgModule({
  declarations: [
    SortByColumnPipe,
  ],
  imports: [
    CommonModule,
    MatSortModule,
    MatDialogModule,
    MatTableModule,
    CdkTableModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  exports: [
    SortByColumnPipe,
    MatSortModule,
    MatSortModule,
    MatDialogModule,
    MatTableModule,
    CdkTableModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
  ]
})
export class SharedModule { }
