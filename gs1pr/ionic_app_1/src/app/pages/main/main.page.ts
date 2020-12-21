import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

import { DrawService } from '../../services/draw.service';
import { LocalDataService } from '../../services/local-data.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.page.html',
  styleUrls: ['./main.page.scss'],
})
export class MainPage implements OnInit {

  drawList;

  constructor(
    private router: Router,
    private drawService: DrawService,
    private localDataService: LocalDataService) { }

  ngOnInit() {
  }

  async ionViewDidEnter(){
    this.drawService.getDraws(this.localDataService.token['id'])
    .subscribe( resp => {
      this.drawList = resp;
    }, err => {
      console.log('FALLO');
    });
  }

  selfDraw(){
    this.router.navigate(['/draw']);
  }

  baseDrawTransfer(id){
    this.router.navigate(['/base-draw', id]);
  }

  myRoutes(){
    this.router.navigate(['/my-routes']);
  }

  topRoutes(){
    this.router.navigate(['/toproutes']);
  }

}
