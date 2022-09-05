import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-link',
  templateUrl: './nav-link.component.html',
  styleUrls: ['./nav-link.component.css']
})
export class NavLinkComponent implements OnInit {

  @Input() NavbarText:string = "";
  @Input() NavbarLink:string = ""

  constructor() {
    this.NavbarText = "Portfolio"
    this.NavbarLink = "#"
   }

  ngOnInit(): void {
  }

}
