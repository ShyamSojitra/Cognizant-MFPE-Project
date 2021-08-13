import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MemberService } from 'src/app/member.service';

@Component({
  selector: 'app-submitclaim',
  templateUrl: './submitclaim.component.html',
  styleUrls: ['./submitclaim.component.css']
})
export class SubmitclaimComponent implements OnInit {

  form: FormGroup;
  public loginInvalid = false;
  private formSubmitAttempt = false;

  constructor(
    private fb: FormBuilder,
    private service: MemberService
  ) {

    this.form = this.fb.group({
      memberId: ['', Validators.required],
      policyId: ['', Validators.required],
      amount: ['', Validators.required]
    });
  }

  ngOnInit() { }

  async onSubmit(): Promise<void> {
    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    if (this.form.valid) {
      try {
        const mid = this.form.get('memberId')?.value;
        const pid = this.form.get('policyId')?.value;
        const amount = this.form.get('amount')?.value;
        console.log(this.service.getToket());
        let claim = {
          "claimId": "",
          "memberId": "",
          "status": "",
          "description": "",
          "hospital": "",
          "amountClaimed": amount,
          "setteled": ""
        }
        await this.service
          .submitClaim(this.service.getToket(), mid, pid, claim)
          .subscribe(data => console.log(data))
      } catch (err) {
        this.loginInvalid = true;
      }
    } else {
      this.formSubmitAttempt = true;
    }
  }

}

