import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MyRoutesPage } from './my-routes.page';

const routes: Routes = [
  {
    path: '',
    component: MyRoutesPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MyRoutesPageRoutingModule {}
