import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { LoginService } from '../../services/login.service';
import { DrawService } from '../../services/draw.service';
import { LocalDataService } from '../../services/local-data.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.page.html',
  styleUrls: ['./user.page.scss'],
})
export class UserPage implements OnInit {

  userId;
  user;
  routeList;
  likes = [];

  constructor(private router: ActivatedRoute,
    private loginService: LoginService,
    private drawService: DrawService,
    private localDataService: LocalDataService) { }

  ngOnInit() {
  }

  async ionViewDidEnter(){
    this.getId();
    this.loginService.searchId(this.userId)
    .subscribe( resp => {
      this.user = resp;
    });
    this.drawService.getRoutes(this.userId)
    .subscribe(resp => {
      console.log(resp);
      this.routeList = resp;
      for (var i = 0; i < this.routeList.length; ++i) {
        this.likes.push(this.liked(this.routeList[i].id));
      }
    });

  }

  liked(id){
    this.drawService.getLiked(this.localDataService.token['id'], id)
    .subscribe(resp => {
      return resp['isLiked'];
    })
  }

  like(id){
    this.drawService.addLike(this.localDataService.token['id'], id)
    .subscribe(resp => {
    });
    this.drawService.getRoutes(this.userId)
    .subscribe(resp => {
      this.routeList = resp;
      for (var i = 0; i < this.routeList.length; ++i) {
        this.likes.push(this.liked(this.routeList[i].id));
      }
    });
  }

  async getId(){
    this.userId = this.router.snapshot.paramMap.get('id');
  }

  addFriend(userF){
    this.loginService.addFriend(this.localDataService.token['nickName'], userF)
    .subscribe(resp => {
      console.log('FUNCIONO');
    });
  }

}
