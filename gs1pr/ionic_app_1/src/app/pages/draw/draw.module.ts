import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DrawPageRoutingModule } from './draw-routing.module';

import { DrawPage } from './draw.page';

import { AgmCoreModule } from '@agm/core';
import { AgmDrawingModule } from '@agm/drawing';
import { ComponentsModule } from '../../components/components.module';

import {} from 'googlemaps';

@NgModule({
  imports: [
  CommonModule,
  FormsModule,
  IonicModule,
  DrawPageRoutingModule,
  ComponentsModule,
  AgmCoreModule.forRoot({
    apiKey: 'AIzaSyBjmCrmrVx64G6_dbQrBCmxgqj79G5HWkA',
    libraries: ['drawing'],
  }),
  AgmDrawingModule
  ],
  declarations: [DrawPage]
})
export class DrawPageModule {}
