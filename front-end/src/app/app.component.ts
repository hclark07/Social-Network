import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { slider } from "./route-animation";
import { Router } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';

import {trigger, animate, style, group, animateChild, query, stagger, transition, state} from '@angular/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [
    slider
  ]
})

export class AppComponent {

  constructor(public router: Router) {

  }

  title = 'front-end';

  //calls what route transition we want
  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation'];
  }

}
