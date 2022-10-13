import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Preferences } from '../models/preferences';


@Component({
  selector: 'app-preferences-page',
  templateUrl: './preferences-page.component.html',
  styleUrls: ['./preferences-page.component.css']
})
export class PreferencesPageComponent implements OnInit  {
  
  investmentList: any[] = ['College Fund','Retirement',];
  riskToleranceList: any[]=['Conservative', 'Below Average',' Average', 'Above Average',' Aggressive']
  incomeCategoryList: any[]=['0-20,000','20,001 – 40,000','40,001-60,000','60,001-80,000','80,001-100,000','100,001-150,000','150,000+']
  lengthOfinvestmentList: any=['0-5 years',' 5-7 years','7-10 years','10-15years','16+ years']
  constructor( private router: Router){}
  ngOnInit() { }

  onChange(event: any){
    console.log(event.value);
  }
  PreferenceSubmit(){
    //this.router.navigate(['preferencesSubmitPage']);
    alert(" Preferences Added Successfully")
  }
}
