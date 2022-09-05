import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor() { }
   footer='Copyright 1998-2021 FMR LLC. All Rights Reserved.'
  ngOnInit(): void {
  }

}
