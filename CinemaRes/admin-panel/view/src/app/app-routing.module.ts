import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'main',
    loadChildren: () => import('./main/main.module')
      .then((mod) => mod.MainModule),
  },
  {
    path: 'users',
    loadChildren: () => import('./users/users.module')
      .then((mod) => mod.UsersModule),
  },
  {
    path: 'orders',
    loadChildren: () => import('./orders/orders.module')
      .then((mod) => mod.OrdersModule),
  },
  { path: '', redirectTo: 'main', pathMatch:'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    anchorScrolling: 'enabled',
  })],
  exports: [RouterModule],
})
export class AppRoutingModule { }
