import { User } from './user';

export class Transactions {
  description: string | undefined;
  transactions: Array<User> = [];
  transactionDate: Date | undefined;
}
