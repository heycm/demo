import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Checker } from 'src/public/utils/checker';
import { MenuData } from '../entity';
import { MenuService } from '../service';

@Component({
  selector: 'app-menu-form',
  templateUrl: './menu-form.component.html',
  styleUrls: ['./menu-form.component.scss'],
})
export class MenuFormComponent implements OnInit {
  /** 表单 */
  group!: FormGroup;
  /** 归属系统ID */
  systemId!: number;
  /** 编辑 */
  menu!: MenuData;
  /** 新增下级时PID */
  pid!: number;

  get f_id() {return this.group.get('id');}
  get f_systemId() {return this.group.get('systemId');}
  get f_pid() {return this.group.get('pid');}
  get f_type() {return this.group.get('type');}
  get f_title() {return this.group.get('title');}
  get f_indez() {return this.group.get('indez');}
  get f_path() {return this.group.get('path');}
  get f_icon() {return this.group.get('icon');}
  get f_remark() {return this.group.get('remark');}
  get f_enable() {return this.group.get('enable');}

  validPath = (control: FormControl) => {
    if (!this.group) {
      return null;
    }
    if (!control.value && this.f_type?.value === 1) {
      return {required: false};
    }
    return null;
  }

  constructor(fb: FormBuilder, private service: MenuService) {
    this.group = fb.group({
      id: [],
      systemId: [null, Validators.required],
      pid: [],
      type: [0, Validators.required],
      title: [null, Validators.required],
      path: [null, this.validPath],
      icon: [],
      indez: [1, Validators.required],
      remark: [],
      enable: [0, Validators.required],
    });
  }

  ngOnInit() {
    if (this.systemId) {
      this.group.patchValue({
        systemId: this.systemId,
      });
    }
    if (this.menu) {
      this.group.patchValue({
        id: this.menu.id,
        systemId: this.menu.systemId,
        pid: this.menu.pid,
        type: this.menu.type,
        title: this.menu.title,
        path: this.menu.path,
        icon: this.menu.icon,
        indez: this.menu.indez,
        remark: this.menu.remark,
        enable: this.menu.enable
      });
    }
    if (this.pid) {
      this.group.patchValue({
        pid: this.pid,
        type: 1,
      });
    }
  }

  add() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    return this.service.add(this.group.value).then(x => x.ok);
  }

  edit() {
    if (!Checker.verifyForm(this.group)) {
      return false;
    }
    return this.service.edit(this.group.value).then(x => x.ok);
  }
}
