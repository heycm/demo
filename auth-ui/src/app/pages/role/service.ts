import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiResult, DataResult } from 'src/public/http/BaseEntity';
import { GrantRoleMenu, RoleData, RoleModel } from './entity';

const api = '/role';

@Injectable({
  providedIn: 'root',
})
export class RoleService {
  constructor(private http: HttpClient) {}

  /** 关键字查询 */
  list(keyword?: string) {
    const url = keyword ? `${api}?keyword=${keyword}` : api;
    return this.http.get<DataResult<RoleData[]>>(url).toPromise();
  }
  /** 增加 */
  add(data: RoleModel) {
    return this.http.post<ApiResult>(api + '/add', data).toPromise();
  }
  /** 编辑 */
  edit(data: RoleModel) {
    return this.http.post<ApiResult>(api + '/edit', data).toPromise();
  }
  /** 删除 */
  del(id: number) {
    return this.http.delete<ApiResult>(api + `/${id}`).toPromise();
  }
  /** 授权菜单 */
  grant(data: GrantRoleMenu) {
    return this.http.post<ApiResult>('/role/menu', data).toPromise();
  }
  /** 查询已授权菜单ID集合 */
  getChecked(param: GrantRoleMenu) {
    return this.http.post<DataResult<string[]>>('/role/menu/checked', param).toPromise();
  }
}
