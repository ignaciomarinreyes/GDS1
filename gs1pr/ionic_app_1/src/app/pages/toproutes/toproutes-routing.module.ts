import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ToproutesPage } from './toproutes.page';

const routes: Routes = [
  {
    path: '',
    component: ToproutesPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ToproutesPageRoutingModule {}
