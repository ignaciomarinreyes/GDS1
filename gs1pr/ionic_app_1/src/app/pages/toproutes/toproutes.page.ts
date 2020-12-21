import { Component, OnInit } from '@angular/core';

import { DrawService } from '../../services/draw.service';
import { LocalDataService } from '../../services/local-data.service';


@Component({
  selector: 'app-toproutes',
  templateUrl: './toproutes.page.html',
  styleUrls: ['./toproutes.page.scss'],
})
export class ToproutesPage implements OnInit {

  routeList;

  constructor(private localDataService: LocalDataService,
    private drawService: DrawService) { }

  ngOnInit() {
  }

  async ionViewDidEnter(){
    this.drawService.getTopRoutes()
    .subscribe( resp => {
      console.log(resp);
      this.routeList = resp;
    }, err => {
      console.log('FALLO');
    });
  }

}
