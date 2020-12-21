import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.page.html',
  styleUrls: ['./search.page.scss'],
})
export class SearchPage implements OnInit {

  items;

  constructor(private loginService: LoginService,
    private router: Router) { }

  ngOnInit() {
  }

  setFilteredItems(event){
    this.loginService.searchUser(event.detail.value)
    .subscribe(resp => {
      this.items = resp;
    });
  }

  seeUser(){
    this.router.navigate(['/user', this.items.id])
  }

}
