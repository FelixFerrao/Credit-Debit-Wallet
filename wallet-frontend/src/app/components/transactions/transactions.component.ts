import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Transactions } from 'src/app/services/transaction';
import { TransactionService } from 'src/app/services/transaction.service';
import { User } from 'src/app/services/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css'],
})
export class TransactionsComponent implements OnInit {
  transactions: Transactions[];
  user = new User()
  constructor(
    private transactionService: TransactionService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user = this.userService.getUserData();
    if (this.userService.getUserData().id) {
      this.transactionService
        .getAllTransactions(this.userService.getUserData().id)
        .subscribe(
          (data) => {
            this.transactions = data;
          },
          (err) => {
            console.log('An error occured: ' + err);
          }
        );
    } else {
      this.router.navigate(['/login']);
    }
  }
}
