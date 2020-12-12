import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalCatch } from 'src/public/utils/localCatch';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzMessageService } from 'ng-zorro-antd/message';
import { MyInfoComponent } from './my-info/my-info.component';
import { MyPwdComponent } from './my-pwd/my-pwd.component';
import { MenuService } from '../menu/service';
import { MenuData } from '../menu/entity';
import { Tree } from 'src/public/http/BaseEntity';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  isCollapsed = false;

  nickname = '';

  menus = [
    {
      key: 1,
      title: '系统管理',
      icon: 'setting',
      path: '',
      type: 0,
      children: [
        {
          key: 2,
          title: '用户管理',
          icon: '',
          path: 'user',
          type: 1,
          children: []
        },{
          key: 3,
          title: '角色管理',
          icon: '',
          path: 'role',
          type: 1,
          children: []
        },{
          key: 4,
          title: '应用管理',
          icon: '',
          path: 'app',
          type: 1,
          children: []
        },{
          key: 5,
          title: '菜单管理',
          icon: '',
          path: 'menu',
          type: 1,
          children: []
        }
      ]
    }
  ]

  menuTree : Tree<MenuData>[] = []

  constructor(
    private router: Router,
    private modal: NzModalService,
    private message: NzMessageService,
    private service: MenuService
  ) {}

  async ngOnInit() {
    this.nickname = LocalCatch.getNickname();
    await this.myMenus();
  }

  async myMenus() {
    const res = await this.service.myMenuTree();
    if (res.ok) {
      this.menuTree = res.data;
    }
  }

  /** 注销登录 */
  logout() {
    this.modal.confirm({
      nzTitle: '确定注销登录?',
      nzOkText: '是',
      nzOkType: 'danger',
      nzOnOk: () => {
        LocalCatch.remove();
        this.router.navigateByUrl('login');
        this.message.success('注销成功');
      },
      nzCancelText: '否',
      nzOnCancel: () => {},
    });
  }

  /** 个人信息 */
  myInfo() {
    this.modal.create({
      nzTitle: '我的信息',
      nzContent: MyInfoComponent,
      nzOnOk: p => p.modify()
    }).afterClose.subscribe(x => {
      if (x) {
        this.nickname = LocalCatch.getNickname();
      }
    });
  }

  /** 修改密码 */
  changePwd() {
    this.modal.create({
      nzTitle: '修改密码',
      nzContent: MyPwdComponent,
      nzOnOk: p => p.submit()
    }).afterClose.subscribe(x => {
      if (x) {
        LocalCatch.remove();
        this.router.navigateByUrl('login');
      }
    });
  }
}
