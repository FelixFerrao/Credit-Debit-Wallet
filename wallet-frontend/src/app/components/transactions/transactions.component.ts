import { Component, OnInit } from '@angular/core';
import { Transactions } from 'src/app/services/transaction';
import { TransactionService } from 'src/app/services/transaction.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css'],
})
export class TransactionsComponent implements OnInit {
  transactions: Transactions[];
  constructor(
    private transactionService: TransactionService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.transactionService.getAllTransactions(2).subscribe(
      (data) => {
        this.transactions = data;
      },
      (err) => {
        console.log('An error occured: ' + err);
      }
    );
  }
}
