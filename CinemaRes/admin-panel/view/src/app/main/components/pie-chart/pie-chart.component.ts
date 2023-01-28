import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { combineLatest } from 'rxjs';
import { skip } from 'rxjs/operators';
import { MoviesService } from 'src/app/services/movies.service';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.scss']
})
export class PieChartComponent implements OnInit {

  public profitByMovieChart: any;

  public chart: any

  constructor(public moviesService: MoviesService) { }

  public createChart() {
    combineLatest([this.moviesService.users, this.moviesService.movies]).pipe(skip(2)).subscribe(([user, movies]) => {
      const dataForChart = {
        movies: movies.map((movie) => movie.name),
        totalEarning:movies.map((movie) => movie.purchasedTickets! * movie.price)
      }
      this.profitByMovieChart = new Chart("profitByMovieChart", {
        type: 'doughnut', //this denotes tha type of chart
        data: {// values on X-Axis
          labels: dataForChart.movies,
          datasets: [{
            label: 'Total Profit By Movie',
            data: dataForChart.totalEarning,
            hoverOffset: 4
          }],
        },
        options: {
          aspectRatio:1.5,
          plugins: {
            title: {
                display: true,
                text: 'Total Profit By Movie',
            },
            legend: {
              display: true,
              position: 'bottom',
              labels: {
                // font: 16,
              }
            },
          },
        }

      });

      Chart.defaults.font.size = 16
    })

  }

  ngOnInit(): void {
    this.createChart();
  }

}
