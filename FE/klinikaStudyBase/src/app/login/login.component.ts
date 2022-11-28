import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SubmitButtonComponent } from '../submit-button/submit-button.component';
import {UserCredentialsService} from "../service/UserCredentialsService";
import {Credentials} from "../dto/Credentials";
import {UserTokensDto} from "../dto/UserTokensDto";
import {NotifierService} from "../service/NotifierService";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],

})
export class LoginComponent implements OnInit {

  form: FormGroup;

  @ViewChild(SubmitButtonComponent) submitButton: SubmitButtonComponent;

  private showMessage = '';
  private successfulLogin = false;

  constructor(private fb: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute,
              private userCredentialsService: UserCredentialsService,
              private credentials: Credentials,
              private notifierService: NotifierService) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    const email = this.form.get('email')?.value;
    const password = this.form.get('password')?.value;
    // @ts-ignore
    this.userCredentialsService.login({ email, password }).subscribe({
      next:  (userTokensDto: UserTokensDto) => {
        this.credentials.setAccessToken(userTokensDto.accessToken.token);
        localStorage.setItem('refreshToken', userTokensDto.refreshToken.token);
        this.showMessage = 'LOGIN.SUCCESSFUL';
        this.successfulLogin = true;
        this.submitButton.stopSpinner();
        window.location.reload()
        this.router.navigate(['fizetesiPeriodus/list']);
      },
      error: err => {
        if (err.status === 401) {
          this.showMessage = 'LOGIN.BAD_CREDENTIALS';
        } else {
          this.showMessage = err.error.message;
        }
        this.successfulLogin = false;
        this.submitButton.stopSpinner();
      }
    });
  }

  onSubmitLoading(loading: boolean): void {
    if (loading) {
      this.form.disable();
    } else {
      this.notifierService.showMessage(this.showMessage);
      this.form.enable();
      if (this.successfulLogin) {
        // @ts-ignore
        this.router.navigateByUrl(this.activatedRoute.snapshot.queryParamMap.get('redirect'));
      }
    }
  }
}
