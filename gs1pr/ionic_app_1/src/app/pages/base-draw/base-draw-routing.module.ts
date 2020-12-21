import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BaseDrawPage } from './base-draw.page';

const routes: Routes = [
  {
    path: '',
    component: BaseDrawPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BaseDrawPageRoutingModule {}
