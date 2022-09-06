import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-paratext',
  templateUrl: './paratext.component.html',
  styleUrls: ['./paratext.component.css']
})
export class ParatextComponent implements OnInit {
   paratext="Did you know that SmallChange™ has no investment fees on portfolios with balances under $5,000? After that, only a 0.25% fee applies monthly."
  constructor() { }

  ngOnInit(): void {
  }

}
