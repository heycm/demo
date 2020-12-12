import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiResult, DataResult } from 'src/public/http/BaseEntity';
import { AppData, AppModel } from './entity';

const api = '/system';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  constructor(private http: HttpClient) {}

  /** 关键字查询 */
  list(keyword?: string) {
    const url = keyword ? `${api}?keyword=${keyword}` : api;
    return this.http.get<DataResult<AppData[]>>(url).toPromise();
  }
  /** 增加 */
  add(data: AppModel) {
    return this.http.post<ApiResult>(api + '/add', data).toPromise();
  }
  /** 编辑 */
  edit(data: AppModel) {
    return this.http.post<ApiResult>(api + '/edit', data).toPromise();
  }
  /** 删除 */
  del(id: number) {
    return this.http.delete<ApiResult>(api + `/${id}`).toPromise();
  }
}
