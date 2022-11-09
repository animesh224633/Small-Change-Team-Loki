import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ClientIdService } from 'src/app/client-id.service';
import { WalletUpdateValues } from 'src/app/models/walletUpdateValues';
import { SmallChangeWalletService } from 'src/app/small-change-wallet.service';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  
  public ngOnInit() {
  }

  constructor(public dialog: MatDialog) {}

  openDialog() {
    const dialogRef = this.dialog.open(DialogContentExampleDialog);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}

@Component({
  selector: 'dialog-content-example-dialog',
  templateUrl: 'dialog-content-example-dialog.html',
})
export class DialogContentExampleDialog implements OnInit{
  constructor(public smallChangeWalletService: SmallChangeWalletService, private clientIdService: ClientIdService) {}

  public ngOnInit() {
    this.callWalletServiceToFetchAccounts();
  }

  displayedColumns: string[] = ['accountName', 'accountBalance'];
  dataSource: any;
  selectedAccountName: any;
  rechargeAmount!: number;
  walletUpdateValues!: WalletUpdateValues;
  showSuccessMessage = false;

  executeWalletRecharge(){
    this.walletUpdateValues = new WalletUpdateValues(this.clientIdService.clientId,this.selectedAccountName,this.rechargeAmount);
    this.smallChangeWalletService.updateRechargeDetails(this.walletUpdateValues).subscribe(data => {
      console.log('response ',data);
      this.ngOnInit();
      if(data!=null){
        this.showSuccessMessage = true;
      }
    })
    console.log('selected from dropdown', this.walletUpdateValues);
  }

  callWalletServiceToFetchAccounts() {
    this.smallChangeWalletService.getBankAccountDetails(this.clientIdService.clientId).subscribe((data) => {
      console.log('aaaaaaaaaaaaaa', data);
      this.dataSource = data;
    });
  }
}