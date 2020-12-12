import { Component, OnInit } from '@angular/core';
import { Page } from 'src/public/http/BaseEntity';
import { UserData } from './entity';
import { UserService } from './service';
import { NzModalService } from 'ng-zorro-antd/modal';
import { UserFormComponent } from './user-form/user-form.component';
import { RoleData } from '../role/entity';
import { RoleService } from '../role/service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  /** 分页参数 */
  query = new Page<string>();
  /** 分页结果 */
  page = 1;
  size = 10;
  total = 0;
  data: UserData[] = [];
  /** 延时搜索 */
  delay = 0;

  /** 授权抽屉 */
  drawer = false;
  /** 当前授权用户ID */
  userId!: number;
  /** 角色列表 */
  roles: RoleData[] = [];
  /** 授权角色 */
  roleId!: number;

  constructor(
    private service: UserService,
    private modal: NzModalService,
    private roleService: RoleService
  ) {}

  async ngOnInit() {
    await this.select();
  }

  /** 分页查询 */
  async select() {
    const res = await this.service.getPage(this.query);
    if (res.ok) {
      this.page = res.page;
      this.size = res.size;
      this.total = res.total;
      this.data = res.data;
    }
  }
  /** 延时搜索 */
  async search() {
    this.query.page = 1;
    window.clearTimeout(this.delay);
    this.delay = window.setTimeout(() => this.select(), 300);
  }
  /** 表格页码改变回调 */
  async pageChange(val: number) {
    this.query.page = val;
    await this.select();
  }
  /** 新增 */
  add() {
    this.modal
      .create({
        nzTitle: '新增用户',
        nzContent: UserFormComponent,
        nzOnOk: (p) => p.add(),
      })
      .afterClose.subscribe((x) => {
        if (x) {
          this.select();
        }
      });
  }
  /** 编辑 */
  edit(user: UserData) {
    this.modal
      .create({
        nzTitle: '编辑用户',
        nzContent: UserFormComponent,
        nzComponentParams: { user },
        nzOnOk: (p) => p.edit(),
      })
      .afterClose.subscribe((x) => {
        if (x) {
          this.select();
        }
      });
  }
  /** 删除 */
  async deleteRow(id: number) {
    const res = await this.service.del(id);
    if (res.ok) {
      await this.select();
    }
  }

  /** 打开授权弹窗 */
  async addRole(id: number) {
    this.userId = id;
    this.drawer = true;
    // 查询角色列表
    const res = await this.roleService.list();
    if (!res.ok) {
      return;
    }
    this.roles = res.data;
    // 查询已授权角色ID
    const resq = await this.service.getUserRoleId(id);
    if (resq.ok) {
      this.roleId = resq.data;
    }
  }
  /** 角色授权 */
  async roleIdChange() {
    const res = await this.service.grant({
      roleId: this.roleId,
      userId: this.userId
    });
  }
}
