import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WalletComponent } from './components/wallet/wallet.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { RegisterComponent } from './components/register/register.component';
// import { RouterModule, Routes } from '@angular/router';

@NgModule({
  declarations: [AppComponent, WalletComponent, LoginComponent, TransactionsComponent, RegisterComponent],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
