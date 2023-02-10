import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../shared/shared.module';
import { MainRoutingModule } from './main-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { MovieListComponent } from './pages/movie-list/movie-list.component';
import { MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table'
import { CdkTableModule } from '@angular/cdk/table';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';


import { MovieModalComponent } from './components/movie-modal/movie-modal.component';
import { ConfirmationModalComponent } from './components/confirmation-modal/confirmation-modal.component';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';



@NgModule({
  declarations: [
    MovieListComponent,
    MovieModalComponent,
    ConfirmationModalComponent,
    PieChartComponent
    ],
  imports: [
    CommonModule,
    SharedModule,
    MainRoutingModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    CdkTableModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
  ]
})
export class MainModule { }
