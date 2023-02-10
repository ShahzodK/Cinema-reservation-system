import { Pipe, PipeTransform } from '@angular/core';
import { IUser } from './../../users/models/user.model';
import { IMovie } from './../../models/movie.model';

  @Pipe({
    name: 'sortByColumn'
  })

  export class SortByColumnPipe implements PipeTransform {

    transform<T>(arr: T[], column: string ) {

      // return arr.sort((a: T, b: T) => {
      //   if (a[column] < b[column])  return -1;
      //   if (a[column] > b[column])  return 1;
      //   return 0;
      // })
    }

  }
