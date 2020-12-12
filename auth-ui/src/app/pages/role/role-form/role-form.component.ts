import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Checker } from 'src/public/utils/checker';
import { RoleData } from '../entity';
import { RoleService } from '../service';

@Component({
  selector: 'app-role-form',
  templateUrl: './role-form.component.html',
  styleUrls: ['./role-form.component.scss']
})
export class RoleFormComponent implements OnInit {
  /** 表单 */
  group!: FormGroup;
  /** 编辑 */
  role!:RoleData;

  get id() {return this.group.get('id');}
  get name() {return this.group.get('name');}
  get nameZh() {return this.group.get('nameZh');}
  get enable() {return this.group.get('enable');}
  get remark() {return this.group.get('remark');}

  constructor(fb: FormBuilder, private service: RoleService) {
    this.group = fb.group({
      id: [],
      name: [null, Validators.required],
      nameZh: [null, Validators.required],
      enable: [0, Validators.required],
      remark: []
    });
  }

  ngOnInit() {
    if(this.role) {
      this.group.patchValue({
        id: this.role.id,
        name: this.role.name,
        nameZh: this.role.nameZh,
        enable: this.role.enable,
        remark: this.role.remark
      });
      this.name?.disable();
    }
  }

  /** 新增 */
  add() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    return this.service.add({
      name: this.name?.value.trim(),
      nameZh: this.nameZh?.value.trim(),
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
      nameZh: this.nameZh?.value.trim(),
      enable: this.enable?.value,
      remark: this.remark?.value
    }).then(x => x.ok);
  }
}
