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
  constructor(private http: HttpClient,
    private userService: UserService,
    private transactionService: TransactionService,
    private router: Router) {}
  transaction = new Transactions();
  user = new User();
  transactionDTO = new TransactDTO();

  ngOnInit(): void {}

  amount: number;
  makeTransaction(data: any) {
    this.user = this.userService.getUserData();
    if(data.amount > this.user.balance) {
      alert('Insufficient Balance');
    } else {
      this.transactionDTO.amount = data.amount;
      this.transactionDTO.description = data.description;
      this.amount = this.user.balance - data.amount;
      this.transactionService.createTransaction(this.transactionDTO, this.user.id)
    }
    console.log(this.transactionDTO);
  }
}
