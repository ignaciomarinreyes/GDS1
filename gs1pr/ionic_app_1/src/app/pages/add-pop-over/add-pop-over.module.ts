import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddPopOverPageRoutingModule } from './add-pop-over-routing.module';

import { AddPopOverPage } from './add-pop-over.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddPopOverPageRoutingModule
  ],
  declarations: [AddPopOverPage]
})
export class AddPopOverPageModule {}
