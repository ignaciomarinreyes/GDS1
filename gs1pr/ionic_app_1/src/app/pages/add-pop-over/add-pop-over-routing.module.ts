import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddPopOverPage } from './add-pop-over.page';

const routes: Routes = [
  {
    path: '',
    component: AddPopOverPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddPopOverPageRoutingModule {}
