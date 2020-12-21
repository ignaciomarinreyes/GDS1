import { Component, OnInit } from '@angular/core';
import { LocalDataService } from '../../services/local-data.service';
import { LoginService } from '../../services/login.service';
import { DrawService } from '../../services/draw.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  user;
  routeList;
  friendList;

  constructor(private localDataService: LocalDataService,
      private loginService: LoginService,
      private drawService: DrawService,
      private router: Router) { }

  ngOnInit() {
    this.loginService.searchId(this.localDataService.token['id'])
    .subscribe(resp => {
      this.user = resp;
      console.log(resp);
    });
    this.drawService.getRoutes(this.localDataService.token['id'])
    .subscribe(resp => {
      console.log("RUTAS");
      console.log(resp);
      this.routeList = resp;
    });
    this.loginService.getFriends(this.localDataService.token['nickName'])
    .subscribe(resp => {
      this.friendList = resp;
    });
  }

  seeUser(id){
    this.router.navigate(['/user', id])
  }
}
