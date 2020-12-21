import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MyRoutesPageRoutingModule } from './my-routes-routing.module';

import { MyRoutesPage } from './my-routes.page';

import { ComponentsModule } from '../../components/components.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MyRoutesPageRoutingModule,
    ComponentsModule
  ],
  declarations: [MyRoutesPage]
})
export class MyRoutesPageModule {}
