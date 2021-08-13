import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { HomeComponent } from './home/home.component';
import { ClaimstatusComponent, ClaimstatusResult } from './member/claimstatus/claimstatus.component';
import { SubmitclaimComponent } from './member/submitclaim/submitclaim.component';
import { ViewbillComponent, ViewbillResult } from './member/viewbill/viewbill.component';

const routes: Routes = [
  { path: '', redirectTo: 'auth', pathMatch: 'full' },
  { path: 'auth', component: AuthComponent },
  { path: 'home', component: HomeComponent },
  { path: 'claimstatus', component: ClaimstatusComponent },
  { path: 'submitclaim', component: SubmitclaimComponent },
  { path: 'viewbill', component: ViewbillComponent },
  { path: 'claimstatus/:mid/:pid/:cid', component: ClaimstatusResult },
  { path: 'submitclaim/:mid/:pid', component: SubmitclaimComponent },
  { path: 'viewbill/:mid/:pid', component: ViewbillResult }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
