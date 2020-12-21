import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { BaseDrawPageRoutingModule } from './base-draw-routing.module';

import { BaseDrawPage } from './base-draw.page';

import { AgmCoreModule } from '@agm/core';
import { AgmDrawingModule } from '@agm/drawing';
import { ComponentsModule } from '../../components/components.module';

import {} from 'googlemaps';

@NgModule({
  imports: [
  CommonModule,
  FormsModule,
  IonicModule,
  BaseDrawPageRoutingModule,
  ComponentsModule,
  AgmCoreModule.forRoot({
    apiKey: 'AIzaSyBjmCrmrVx64G6_dbQrBCmxgqj79G5HWkA',
    libraries: ['drawing'],
  }),
  AgmDrawingModule
  ],
  declarations: [BaseDrawPage]
})
export class BaseDrawPageModule {}
