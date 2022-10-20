import { User } from "../services/user";

export class TransactDTO {
  amount: number;
  description: string;
  user: User;
}
