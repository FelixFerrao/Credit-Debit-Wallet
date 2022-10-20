import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TransactDTO } from '../dto/transactDTO';
import { Transactions } from './transaction';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  url = 'http://localhost:8080/transaction';
  constructor(private http: HttpClient) {}

  creditTransaction(trans: TransactDTO, userId: number) {
    return this.http.post(this.url + '/credit/' + userId, trans);
  }

  debitTransaction(trans: TransactDTO, userId: number) {
    return this.http.post(this.url + '/debit/' + userId, trans);
  }

  getAllTransactions(userId: number): Observable<Transactions[]> {
    return this.http.get<Transactions[]>(this.url + '/' + userId);
  }
}
