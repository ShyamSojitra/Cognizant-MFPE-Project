import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MemberService } from '../member.service';
@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  form: FormGroup;
  public loginInvalid = false;
  private formSubmitAttempt = false;
  public token: any;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private service: MemberService
  ) {

    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.service.setToken(" ");
    this.service.setLog(false);
  }

  async onSubmit(): Promise<void> {
    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    if (this.form.valid) {
      try {
        const username = this.form.get('username')?.value;
        const password = this.form.get('password')?.value;
        let jwt = {
          "userName": username,
          "password": password
        };


        await this.service.generateToken(jwt).subscribe(
          data => {
            this.token = data;
            console.log(this.token),
              this.service.setToken(this.token),
              this.service.setLog(true);
            this.router.navigate(['/home']);
          },
          error => this.loginInvalid = true);
      } catch (err) {
        this.loginInvalid = true;
      }
    } else {
      this.formSubmitAttempt = true;
    }
  }
}
