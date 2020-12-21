import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { LocalDataService } from '../../services/local-data.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {

  @Input() title: string = "";

  constructor(private localdataService: LocalDataService,
    private router: Router) { }

  ngOnInit() {}

  back(){
    this.router.navigate(['/main']);
  }

  logOut(){
    this.localdataService.eraseToken();
    this.router.navigate(['/login']);
  }
}
