import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { LoginService } from '../../services/login.service';
import { LocalDataService } from '../../services/local-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  user = {
    nickname: '',
    password: ''
  }

  constructor(    
    private router: Router,
    private loginService: LoginService,
    private alertCtrl: AlertController,
    private localDataService: LocalDataService) { }

  ngOnInit() {
    if (this.localDataService.token != null) {
      this.router.navigate(['/main']);
    }
  }

  async onSubmit(form: NgForm){
    this.user = form.value;
    this.loginService.logIn(this.user.nickname, this.user.password)
    .subscribe( resp => {
      console.log('DESPUES-DEL-LOGIN');
      console.log(resp);
      this.localDataService.saveToken(resp);
      this.router.navigate(['/main']);
    }, err => {
   
      form.reset();
      this.loginAlert();
      
    });
  }

    private async loginAlert() {
    const alert = await this.alertCtrl.create({
      message: `Wrong user or password`,
      buttons: ['Accept'],
    });
    (await alert).present();
  }
}
