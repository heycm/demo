import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { Tree } from 'src/public/http/BaseEntity';
import { AppData } from '../system/entity';
import { AppService } from '../system/service';
import { MenuData } from './entity';
import { MenuFormComponent } from './menu-form/menu-form.component';
import { MenuService } from './service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent implements OnInit {
  /** 应用列表 */
  applist: AppData[] = [];
  /** 当前选择的应用 */
  app!: AppData;

  data: Tree<MenuData>[] = [];

  mapOfExpandedData: { [key: string]: Tree<MenuData>[] } = {};

  constructor(
    private appService: AppService,
    private modal: NzModalService,
    private service: MenuService
  ) {}

  async ngOnInit() {
    const ok = await this.getApplist();
    if (!ok) {
      return;
    }
  }

  /** 查询应用列表 */
  async getApplist() {
    const res = await this.appService.list();
    if (res.ok) {
      this.applist = res.data;
    }
    return res.ok;
  }
  /** 点击选择应用 */
  async clickApp(item: AppData) {
    this.app = item;
    await this.select();
  }
  /** 查询系统菜单 */
  async select() {
    const res = await this.service.tree(this.app.id);
    if (res.ok) {
      this.data = res.data;
      this.data.forEach((item) => {
        this.mapOfExpandedData[item.key] = this.convertTreeToList(item);
      });
    }
  }

  /** 树状表格 */
  collapse(
    array: Tree<MenuData>[],
    data: Tree<MenuData>,
    $event: boolean
  ): void {
    if (!$event) {
      if (data.children) {
        data.children.forEach((d) => {
          const target = array.find((a) => a.key === d.key)!;
          target.expanded = false;
          this.collapse(array, target, false);
        });
      } else {
        return;
      }
    }
  }
  convertTreeToList(root: Tree<MenuData>): Tree<MenuData>[] {
    const stack: Tree<MenuData>[] = [];
    const array: Tree<MenuData>[] = [];
    const hashMap = {};
    stack.push({ ...root, level: 0, expanded: false });

    while (stack.length !== 0) {
      const node = stack.pop()!;
      this.visitNode(node, hashMap, array);
      if (node.children) {
        for (let i = node.children.length - 1; i >= 0; i--) {
          stack.push({
            ...node.children[i],
            level: node.level! + 1,
            expanded: false,
            parent: node,
          });
        }
      }
    }
    return array;
  }
  visitNode(
    node: Tree<MenuData>,
    hashMap: { [key: string]: boolean },
    array: Tree<MenuData>[]
  ): void {
    if (!hashMap[node.key]) {
      hashMap[node.key] = true;
      array.push(node);
    }
  }

  /** 增加根菜单 */
  add() {
    this.modal
      .create({
        nzTitle: '添加菜单',
        nzContent: MenuFormComponent,
        nzComponentParams: { systemId: this.app.id },
        nzOnOk: (p) => p.add(),
      })
      .afterClose.subscribe((x) => {
        if (x) {
          this.select();
        }
      });
  }
  /** 编辑菜单 */
  edit(menu: MenuData) {
    this.modal.create({
      nzTitle: '编辑菜单',
      nzContent: MenuFormComponent,
      nzComponentParams: { menu },
      nzOnOk: (p) => p.edit(),
    }).afterClose.subscribe((x) => {
      if (x) {
        this.select();
      }
    });
  }
  /** 增加子菜单 */
  addChild(pid: number) {
    this.modal.create({
      nzTitle: '添加菜单',
      nzContent: MenuFormComponent,
      nzComponentParams: { systemId: this.app.id, pid },
      nzOnOk: (p) => p.add(),
    }).afterClose.subscribe((x) => {
      if (x) {
        this.select();
      }
    });
  }
  /** 删除菜单 */
  async deleteRow(id: number) {
    const res = await this.service.del(id);
    if (res.ok) {
      await this.select();
    }
  }
}
