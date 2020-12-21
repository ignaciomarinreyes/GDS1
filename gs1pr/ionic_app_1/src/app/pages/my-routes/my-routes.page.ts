import { Component, OnInit } from '@angular/core';

import { DrawService } from '../../services/draw.service';
import { LocalDataService } from '../../services/local-data.service';

@Component({
  selector: 'app-my-routes',
  templateUrl: './my-routes.page.html',
  styleUrls: ['./my-routes.page.scss'],
})
export class MyRoutesPage implements OnInit {

  routeList;

  constructor(private localDataService: LocalDataService,
    private drawService: DrawService) { }

  ngOnInit() {
  }

  async ionViewDidEnter(){
    this.drawService.getRoutes(this.localDataService.token['id'])
    .subscribe( resp => {
      this.routeList = resp;
    }, err => {
      console.log('FALLO');
    });
  }

  delete(id){
    this.drawService.delRoute(id)
    .subscribe(resp => {
      console.log('FUNCIONO');
    });
  }
}
