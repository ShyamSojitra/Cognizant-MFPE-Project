import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private httpClient: HttpClient) { }
  msg: string = '';
  loggedIn = false;
  setToken(tkn: string) { this.msg = tkn }
  getToket() { return this.msg }
  setLog(log: boolean) { this.loggedIn = log }
  getLog() { return this.loggedIn }
  public generateToken(request: any) {
    let url = "http://localhost:8400/auth/authenticate";
    return this.httpClient.post<string>(url, request, { responseType: 'text' as 'json' });
  }

  public viewBills(token: any, memberId: number, policyId: number,) {
    let tokenStr = 'Bearer ' + token;
    let url = `http://localhost:8100/viewbills/${memberId}/${policyId}`;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.httpClient.get<any>(url, { headers });
  }

  public getClaimStatus(token: any, memberId: number, policyId: number, claimId: number) {
    let tokenStr = 'Bearer ' + token;
    let url = `http://localhost:8100/getclaimstatus/${memberId}/${policyId}/${claimId}`;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    console.log(token);

    return this.httpClient.get<any>(url, { headers });
  }

  public submitClaim(token: any, memberId: number, policyId: number, claim: any) {
    let tokenStr = 'Bearer ' + token;
    console.log(claim);
    let url = `http://localhost:8100/submitclaim/${memberId}/${policyId}`;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.httpClient.post<any>(url, claim.amountClaimed, { headers });
  }
}