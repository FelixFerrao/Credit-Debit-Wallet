import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { WalletComponent } from './components/wallet/wallet.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'wallet', component: WalletComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
