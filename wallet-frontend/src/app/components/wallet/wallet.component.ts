import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TransactDTO } from 'src/app/dto/transactDTO';
import { Transactions } from 'src/app/services/transaction';
import { TransactionService } from 'src/app/services/transaction.service';
import { User } from 'src/app/services/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css'],
})
export class WalletComponent implements OnInit {
  constructor(
    private http: HttpClient,
    private userService: UserService,
    private transactionService: TransactionService,
    private router: Router
  ) {}
  transaction = new Transactions();
  user = new User();
  transactionDTO = new TransactDTO();

  ngOnInit(): void {
    // Getting the user data
    this.user = this.userService.getUserData();
    if (this.user.id == null) {
      this.router.navigate(['/login']);
    }
  }

  amount: number;
  // Credit Operation
  creditTransaction(userTransData: any) {
    this.transactionDTO.amount = userTransData.amount;
    this.transactionDTO.reason = userTransData.reason;
    this.amount = this.user.balance + userTransData.amount;
    this.transactionService
      .creditTransaction(this.transactionDTO, this.user.id)
      .subscribe((data) => {
        alert('Transaction successful');
        this.userService.getUserDataByEmail(this.user.email).subscribe((data) => {
          this.userService.storeUserData(data);
        });
        this.amount = 0;
        this.router.navigate(['/transact']);
      });
    // Updating the user balance after the transaction
  }

  // Debit Operation
  debitTransaction(userTransData: any) {
    if (userTransData.amount > this.user.balance) {
      alert('Insufficient Balance');
    } else {
      this.transactionDTO.amount = userTransData.amount;
      this.transactionDTO.reason = userTransData.reason;
      this.amount = this.user.balance - userTransData.amount;
      this.transactionService
        .debitTransaction(this.transactionDTO, this.user.id)
        .subscribe((data) => {
          console.log('Transaction details ' + data);
          alert('Transaction successful');
          this.userService.getUserDataByEmail(this.user.email).subscribe((data) => {
            this.userService.storeUserData(data);
            console.log('Updated user data: ' + data)
          });
          this.amount = 0;
          this.router.navigate(['/transact']);
        });
    }
    // console.log(this.transactionDTO);
  }
}
