import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component'
import { TabsComponent } from './tabs/tabs.component';
import { IonicModule } from '@ionic/angular';

@NgModule({
  declarations: [
      HeaderComponent,
      TabsComponent
  ],
  exports: [
      HeaderComponent,
      TabsComponent
  ],
  imports: [
    CommonModule,
    IonicModule
  ]
})
export class ComponentsModule { }