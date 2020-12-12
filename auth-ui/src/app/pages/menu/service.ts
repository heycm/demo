import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiResult, DataResult, Tree } from 'src/public/http/BaseEntity';
import { MenuData, MenuModel } from './entity';
import { environment } from 'src/environments/environment';

const api = '/menu';

@Injectable({
  providedIn: 'root',
})
export class MenuService {
  constructor(private http: HttpClient) {}

  /** 我的菜单 */
  myMenuTree() {
    return this.http.get<DataResult<Tree<MenuData>[]>>(api + `/${environment.systemId}/tree/my`).toPromise();
  }
  /** 菜单树 */
  tree(systemId: number) {
    return this.http.get<DataResult<Tree<MenuData>[]>>(`${api}/${systemId}/tree`).toPromise();
  }
  /** 增加 */
  add(data: MenuModel) {
    return this.http.post<ApiResult>(api + '/add', data).toPromise();
  }
  /** 编辑 */
  edit(data: MenuModel) {
    return this.http.post<ApiResult>(api + '/edit', data).toPromise();
  }
  /** 删除 */
  del(id: number) {
    return this.http.delete<ApiResult>(api + `/${id}`).toPromise();
  }

}
