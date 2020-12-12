import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';
import { MyInfoComponent } from './my-info/my-info.component';
import { MyPwdComponent } from './my-pwd/my-pwd.component';
import { UserComponent } from '../user/user.component';
import { UserFormComponent } from '../user/user-form/user-form.component';
import { RoleComponent } from '../role/role.component';
import { RoleFormComponent } from '../role/role-form/role-form.component';
import { SystemComponent } from '../system/system.component';
import { SystemFormComponent } from '../system/system-form/system-form.component';
import { MenuComponent } from '../menu/menu.component';
import { MenuFormComponent } from '../menu/menu-form/menu-form.component';

import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzSpaceModule } from 'ng-zorro-antd/space';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzPopconfirmModule } from 'ng-zorro-antd/popconfirm';
import { NzTagModule } from 'ng-zorro-antd/tag';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { NzInputNumberModule } from 'ng-zorro-antd/input-number';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzTreeModule } from 'ng-zorro-antd/tree';

import { NzModalService } from 'ng-zorro-antd/modal';
import { NzMessageService } from 'ng-zorro-antd/message';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      { path: 'user', component: UserComponent },
      { path: 'role', component: RoleComponent },
      { path: 'app', component: SystemComponent },
      { path: 'menu', component: MenuComponent }
    ],
  },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    ReactiveFormsModule,
    NzLayoutModule,
    NzMenuModule,
    NzCardModule,
    NzIconModule,
    NzDropDownModule,
    NzSpaceModule,
    NzGridModule,
    NzButtonModule,
    NzInputModule,
    NzTableModule,
    NzDividerModule,
    NzPopconfirmModule,
    NzTagModule,
    NzFormModule,
    NzRadioModule,
    NzInputNumberModule,
    NzListModule,
    NzDrawerModule,
    NzSelectModule,
    NzTreeModule
  ],
  declarations: [
    HomeComponent,
    MyInfoComponent,
    MyPwdComponent,
    UserComponent,
    UserFormComponent,
    RoleComponent,
    RoleFormComponent,
    SystemComponent,
    SystemFormComponent,
    MenuComponent,
    MenuFormComponent
  ],
  providers: [NzModalService, NzMessageService],
})
export class HomeModule {}
