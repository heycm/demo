import { Component, OnInit, ViewChild } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzFormatEmitEvent, NzTreeComponent, NzTreeNode } from 'ng-zorro-antd/tree';
import { Tree } from 'src/public/http/BaseEntity';
import { MenuData } from '../menu/entity';
import { MenuService } from '../menu/service';
import { AppData } from '../system/entity';
import { AppService } from '../system/service';
import { RoleData } from './entity';
import { RoleFormComponent } from './role-form/role-form.component';
import { RoleService } from './service';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.scss'],
})
export class RoleComponent implements OnInit {
  /** 查询关键字 */
  keyword = '';
  /** 查询结果 */
  data: RoleData[] = [];
  /** 延时搜索 */
  delay = 0;
  /** 授权抽屉 */
  drawer = false;
  /** 当前授权角色ID */
  roleId!: number;
  /** 当前授权系统ID */
  systemId!: number;
  /** 系统列表 */
  systems: AppData[] = [];
  /** 系统菜单树 */
  menuTree: Tree<MenuData>[] = [];
  /** 树实例 */
  @ViewChild('nzTreeComponent', { static: false }) nzTreeComponent!: NzTreeComponent;
  /** 已选菜单ID */
  checked: string[] = []

  constructor(
    private service: RoleService,
    private modal: NzModalService,
    private appService: AppService,
    private menuService: MenuService
  ) {}

  async ngOnInit() {
    await this.select();
  }

  /** 查询角色列表 */
  async select() {
    const res = await this.service.list(this.keyword);
    if (res.ok) {
      this.data = res.data;
    }
  }
  /** 延时搜索 */
  async search() {
    window.clearTimeout(this.delay);
    this.delay = window.setTimeout(() => this.select(), 300);
  }
  /** 新增 */
  add() {
    this.modal
      .create({
        nzTitle: '新增角色',
        nzContent: RoleFormComponent,
        nzOnOk: (p) => p.add(),
      })
      .afterClose.subscribe((x) => {
        if (x) {
          this.select();
        }
      });
  }
  /** 编辑 */
  edit(role: RoleData) {
    this.modal
      .create({
        nzTitle: '编辑角色',
        nzContent: RoleFormComponent,
        nzComponentParams: { role },
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

  /** 打开授权抽屉 */
  async addMenus(id: number) {
    this.drawer = true;
    this.roleId = id;
    // 查询系统列表
    const res = await this.appService.list();
    if (!res.ok) {
      return;
    }
    this.systems = res.data;
    // 查询已授权菜单
    if (this.roleId && this.systemId) {
      const resq = await this.service.getChecked({
        systemId: this.systemId,
        roleId: this.roleId,
        menuIds: []
      });
      if (resq.ok) {
        this.checked = resq.data;
      }
    }
  }
  /** 查询系统菜单 */
  async systemIdChange() {
    // 查询系统菜单
    const res = await this.menuService.tree(this.systemId);
    if (!res.ok) {
      return;
    }
    this.menuTree = res.data;
    // 查询已授权菜单
    const resq = await this.service.getChecked({
      systemId: this.systemId,
      roleId: this.roleId,
      menuIds: []
    });
    if (resq.ok) {
      this.checked = resq.data;
    }
  }
  /** 选择框改变回调（授权） */
  async nzTreeCheck(event: NzFormatEmitEvent) {
    let ids: number[] = [];
    const nodes = this.nzTreeComponent.getTreeNodes();
    nodes.forEach(item => {
      if (item.isChecked || item.isHalfChecked) {
        ids.push(parseInt(item.key));
      }
      if (item.children && item.children.length > 0) {
        ids = ids.concat(this.deepTree(item.children));
      }
    });
    const res = await this.service.grant({
      systemId: this.systemId,
      roleId: this.roleId,
      menuIds: ids
    });
  }
  /** 递归找全选和半选的节点 */
  deepTree(nodes: NzTreeNode[]) {
    let ids: number[] = [];
    nodes.forEach(item => {
      if (item.isChecked || item.isHalfChecked) {
        ids.push(parseInt(item.key));
      }
      if (item.children && item.children.length > 0) {
        ids = ids.concat(this.deepTree(item.children));
      }
    });
    return ids;
  }
}
