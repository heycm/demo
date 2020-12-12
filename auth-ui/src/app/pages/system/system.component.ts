import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { AppData } from './entity';
import { AppService } from './service';
import { SystemFormComponent } from './system-form/system-form.component';

@Component({
  selector: 'app-system',
  templateUrl: './system.component.html',
  styleUrls: ['./system.component.scss']
})
export class SystemComponent implements OnInit {

  /** 查询关键字 */
  keyword = '';
  /** 查询结果 */
  data:AppData[] = [];
  /** 延时搜索 */
  delay = 0;

  constructor(private service: AppService, private modal: NzModalService) { }

  async ngOnInit() {
    await this.select();
  }

  /** 查询应用列表 */
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
    this.modal.create({
      nzTitle: '新增应用',
      nzContent: SystemFormComponent,
      nzOnOk: p => p.add()
    }).afterClose.subscribe(x => {
      if (x) {
        this.select();
      }
    })
  }
  /** 编辑 */
  edit(app: AppData) {
    this.modal.create({
      nzTitle: '编辑应用',
      nzContent: SystemFormComponent,
      nzComponentParams: {app},
      nzOnOk: p => p.edit()
    }).afterClose.subscribe(x => {
      if (x) {
        this.select();
      }
    })
  }
  /** 删除 */
  async deleteRow(id: number) {
    const res = await this.service.del(id);
    if (res.ok) {
      await this.select();
    }
  }

}
