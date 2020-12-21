import { Component, OnInit, Input } from '@angular/core';
import { PopoverController, AlertController } from '@ionic/angular';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

import { DrawService } from '../../services/draw.service';
import { LocalDataService } from '../../services/local-data.service';

@Component({
  selector: 'app-add-pop-over',
  templateUrl: './add-pop-over.page.html',
  styleUrls: ['./add-pop-over.page.scss'],
})
export class AddPopOverPage implements OnInit {

  @Input() points;
  @Input() drawId;
  name;

  constructor(private popoverController: PopoverController,
    private datePipe: DatePipe,
    private drawService: DrawService,
    private router: Router,
    private alertCtrl: AlertController,
    private localDataService: LocalDataService) { }

  ngOnInit() {
  }

  async onSubmit(form: NgForm){
    console.log(form.value['name']);
    let draw;
    if (this.drawId == null) {
      draw = {
        name: form.value['name'],
        date: this.datePipe.transform(new Date(), 'dd/MM/yyyy'),
        nickNameLoggedUser: this.localDataService.token['nickName'],
        coordinates: this.points,
      };
      this.drawService.addDraw(draw)
      .subscribe(resp => {
        close();
        this.router.navigate(['/main'])
      }, err => {
        console.log('Error' + err);
      });
    }else{
      draw = {
        name: form.value['name'],
        date: this.datePipe.transform(new Date(), 'dd/MM/yyyy'),
        nickNameLoggedUser: this.localDataService.token['nickName'],
        coordinates: this.points,
        idDraw: this.drawId
      };
      this.drawService.addRoute(draw)
      .subscribe(resp => {
        this.loginAlert(resp['score']);
        close();
        this.router.navigate(['/main'])
      }, err => {
        console.log('Error' + err);
      });
    }
  }

  private async loginAlert(score) {
    const alert = await this.alertCtrl.create({
      message: 'Your score was: ' + score,
      buttons: ['Accept'],
    });
    (await alert).present();
  }

  close(){
    this.popoverController.dismiss();
  }
}
