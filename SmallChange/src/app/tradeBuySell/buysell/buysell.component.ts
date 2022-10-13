import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-buysell',
  templateUrl: './buysell.component.html',
  styleUrls: ['./buysell.component.css']
})
export class BuysellComponent implements OnInit {
value:number=0;
  constructor() { }
clickBuy(){
  this.value=1;
}
clickSell(){
  this.value=2;
}
  ngOnInit(): void {
  }

}
