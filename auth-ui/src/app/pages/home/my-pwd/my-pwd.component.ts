import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Checker } from 'src/public/utils/checker';
import { UserService } from '../../user/service';
import { LocalCatch } from 'src/public/utils/localCatch';

@Component({
  selector: 'app-my-pwd',
  templateUrl: './my-pwd.component.html',
  styleUrls: ['./my-pwd.component.scss']
})
export class MyPwdComponent implements OnInit {
  /** 表单 */
  group!: FormGroup;

  get oldPwd() {return this.group.get('oldPwd');}
  get newPwd() {return this.group.get('newPwd');}
  get confirm() {return this.group.get('confirm');}

  n_tip = '';
  c_tip = '';
  validConfirm = (control: FormControl) => {
    if (!this.group) {
      return null;
    }
    if (!control.value) {
      this.n_tip = '请设置新密码！';
      this.c_tip = '请确认新密码！';
      return {required: true}
    }
    if (this.newPwd?.value && this.newPwd?.value !== control.value) {
      this.n_tip = '两次密码不一致！';
      this.c_tip = '两次密码不一致！';
      return {confirm: true}
    }
    if (this.confirm?.value && this.confirm?.value !== control.value) {
      this.n_tip = '两次密码不一致！';
      this.c_tip = '两次密码不一致！';
      return {confirm: true}
    }
    return null;
  }

  constructor(fb: FormBuilder, private service: UserService) {
    this.group = fb.group({
      oldPwd: [null, Validators.required],
      newPwd: [null, this.validConfirm],
      confirm: [null, this.validConfirm],
    });
   }

  ngOnInit() {
  }

  submit() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    return this.service.changePwd({
      oldPwd: this.oldPwd?.value.trim(),
      newPwd: this.newPwd?.value.trim()
    }).then(x => x.ok);
  }
}
