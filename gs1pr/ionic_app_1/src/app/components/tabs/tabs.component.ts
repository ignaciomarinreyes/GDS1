import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tabs',
  templateUrl: './tabs.component.html',
  styleUrls: ['./tabs.component.scss'],
})
export class TabsComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {}

  goProfile(){
    this.router.navigate(['/profile']);
  }

  goDraw(){
    this.router.navigate(['/main']);
  }

  goSearch(){
    this.router.navigate(['/search']);
  }

  goSettings(){
    this.router.navigate(['/edit']);
  }

}
