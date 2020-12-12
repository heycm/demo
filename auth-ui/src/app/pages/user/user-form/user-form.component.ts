import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Checker } from 'src/public/utils/checker';
import { UserModel } from '../entity';
import { UserService } from '../service';
@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss'],
})
export class UserFormComponent implements OnInit {
  /** 表单 */
  group!: FormGroup;

  /** 编辑 */
  user!: UserModel

  get id() {return this.group.get('id');}
  get username() {return this.group.get('username');}
  get nickname() {return this.group.get('nickname');}
  get enable() {return this.group.get('enable');}

  validUsername = (control: FormControl) => {
    if (!this.group) {
      return null;
    }
    if (!control.value || !control.value.trim()) {
      return {required: false};
    }
    return null;
  }

  constructor(fb: FormBuilder, private service: UserService) {
    this.group = fb.group({
      id: [],
      username: [null, this.validUsername],
      nickname: [null, Validators.required],
      enable: [0, Validators.required]
    });
  }

  ngOnInit() {
    if(this.user) {
      this.group.patchValue({
        id: this.user.id,
        username: this.user.username,
        nickname: this.user.nickname,
        enable: this.user.enable
      });
      this.username?.disable();
    }
  }

  /** 新增 */
  add() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    return this.service.add({
      username: this.username?.value.trim(),
      nickname: this.nickname?.value.trim(),
      enable: this.enable?.value
    }).then(x => x.ok);
  }

  /** 更新 */
  edit() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    return this.service.edit({
      id: this.id?.value,
      username: this.username?.value.trim(),
      nickname: this.nickname?.value.trim(),
      enable: this.enable?.value
    }).then(x => x.ok);
  }
}
