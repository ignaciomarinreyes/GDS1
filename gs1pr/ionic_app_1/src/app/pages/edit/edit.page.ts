import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { LocalDataService } from '../../services/local-data.service';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.page.html',
  styleUrls: ['./edit.page.scss'],
})
export class EditPage implements OnInit {

  user = {
    idUserToUpdate: this.localDataService.token['id'],
    name: '',
    lastName: '',
    nickName: '',
    password: '',
    email: ''
  };

  currentUser;

  constructor(private localDataService: LocalDataService,
    private loginService: LoginService,
    private router: Router) { }

  ngOnInit() {
    this.currentUser = this.localDataService.token;
    console.log(this.currentUser);
  }

  onSubmit(form: NgForm){
    this.user = form.value;
    console.log(this.user);
    this.loginService.updateUser(this.user)
    .subscribe(resp => {
      console.log('FUNCIONO');
    })
  }

}