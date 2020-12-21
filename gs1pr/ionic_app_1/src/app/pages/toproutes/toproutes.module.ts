import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ToproutesPageRoutingModule } from './toproutes-routing.module';

import { ToproutesPage } from './toproutes.page';

import { ComponentsModule } from '../../components/components.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ToproutesPageRoutingModule,
    ComponentsModule
  ],
  declarations: [ToproutesPage]
})
export class ToproutesPageModule {}
