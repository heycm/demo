import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Checker } from 'src/public/utils/checker';
import { LocalCatch } from 'src/public/utils/localCatch';
import { LoginData } from './entity';
import { LoginService } from './service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  group!: FormGroup;

  get username() {
    return this.group.get('username');
  }
  get password() {
    return this.group.get('password');
  }

  constructor(
    private fb: FormBuilder,
    private service: LoginService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.group = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((p) => {
      const token = p.get('token');
      if (token) {
        this.sso(token);
      }
    });
  }

  /** 登录 */
  async submitForm(): Promise<void> {
    if (!Checker.verifyForm(this.group)) return;
    const res = await this.service.login(this.group.value);
    if (res.ok) {
      await this.loginSuccess(res.data);
    } else {
      this.password?.setValue('');
    }
  }

  /** 单点登录 */
  async sso(token: string) {
    LocalCatch.setToken(token);
    const res = await this.service.sso();
    if (res.ok) {
      await this.loginSuccess(res.data);
    } else {
      LocalCatch.remove();
      this.router.navigateByUrl('login');
    }
  }

  /** 登录成功 */
  async loginSuccess(data: LoginData) {
    LocalCatch.setCatch(data);
    this.router.navigateByUrl('home');
  }
}
