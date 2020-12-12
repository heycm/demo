import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Checker } from 'src/public/utils/checker';
import { UserService } from '../../user/service';
import { LocalCatch } from 'src/public/utils/localCatch';

@Component({
  selector: 'app-my-info',
  templateUrl: './my-info.component.html',
  styleUrls: ['./my-info.component.scss'],
})
export class MyInfoComponent implements OnInit {
  /** 表单 */
  group!: FormGroup;

  get id() {return this.group.get('id');}
  get username() {return this.group.get('username');}
  get nickname() {return this.group.get('nickname');}

  constructor(fb: FormBuilder, private service: UserService) {
    this.group = fb.group({
      id: [],
      username: [],
      nickname: [null, Validators.required],
    });
  }

  async ngOnInit() {
    this.username?.disable();
    await this.getMyInfo();
  }

  /** 获取个人信息 */
  async getMyInfo() {
    const res = await this.service.myInfo();
    if (res.ok) {
      this.group.patchValue({
        id: res.data.id,
        username: res.data.username,
        nickname: res.data.nickname
      });
    }
  }

  /** 更新个人信息 */
  async modify() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    const res = await this.service.edit({
      id: this.id?.value,
      nickname: this.nickname?.value.trim()
    });
    if (res.ok) {
      LocalCatch.setNickname(this.nickname?.value.trim());
    }
    return res.ok;
  }
}
