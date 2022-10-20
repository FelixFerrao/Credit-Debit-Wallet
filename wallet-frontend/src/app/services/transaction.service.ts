import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransactDTO } from '../dto/transactDTO';
import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  url = 'http://localhost:8080/transaction';
  constructor(private http: HttpClient) {}

  createTransaction(trans: TransactDTO, userId: number) {
    return this.http.post(this.url + '/' + userId, trans)
  }
}
