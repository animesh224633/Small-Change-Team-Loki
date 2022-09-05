import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  
  active=1;
 
  isActive=true;
  public ngOnInit(){
  console.log(this.isActive)
  this.isActive=!this.isActive
  }
  
}
