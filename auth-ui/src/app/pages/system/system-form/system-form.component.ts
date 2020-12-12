import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Checker } from 'src/public/utils/checker';
import { AppData } from '../entity';
import { AppService } from '../service';

@Component({
  selector: 'app-system-form',
  templateUrl: './system-form.component.html',
  styleUrls: ['./system-form.component.scss']
})
export class SystemFormComponent implements OnInit {
  /** 表单 */
  group!: FormGroup;
  /** 编辑 */
  app!:AppData;
  
  get id() {return this.group.get('id');}
  get name() {return this.group.get('name');}
  get code() {return this.group.get('code');}
  get enable() {return this.group.get('enable');}
  get remark() {return this.group.get('remark');}
  get indez() {return this.group.get('indez');}
  get url() {return this.group.get('url');}

  constructor(fb: FormBuilder, private service: AppService) {
    this.group = fb.group({
      id: [],
      name: [null, Validators.required],
      code: [null, Validators.required],
      enable: [0, Validators.required],
      indez: [1, Validators.required],
      url: [],
      remark: []
    });
  }

  ngOnInit() {
    if(this.app) {
      this.group.patchValue({
        id: this.app.id,
        name: this.app.name,
        code: this.app.code,
        enable: this.app.enable,
        remark: this.app.remark,
        indez: this.app.indez,
        url: this.app.url
      });
      this.code?.disable();
    }
  }

   /** 新增 */
   add() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    return this.service.add({
      name: this.name?.value.trim(),
      code: this.code?.value.trim(),
      indez: this.indez?.value,
      url: this.url?.value,
      enable: this.enable?.value,
      remark: this.remark?.value
    }).then(x => x.ok);
  }

  /** 更新 */
  edit() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    return this.service.edit({
      id: this.id?.value,
      name: this.name?.value.trim(),
      code: this.code?.value.trim(),
      indez: this.indez?.value,
      url: this.url?.value,
      enable: this.enable?.value,
      remark: this.remark?.value
    }).then(x => x.ok);
  }
}
