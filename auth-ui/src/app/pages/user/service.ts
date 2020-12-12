import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiResult, DataResult, Page, PageResult } from 'src/public/http/BaseEntity';
import { UserModel, UserData, GrantUserRole, Password } from './entity';

const api = '/user'

@Injectable({
  providedIn: 'root',
})
export class UserService {

    constructor(private http: HttpClient) { }

    /** 分页查询 */
    getPage(p: Page<string>) {
        return this.http.post<PageResult<UserData>>(api + '/page', p).toPromise();
    }
    /** 增加 */
    add(data: UserModel) {
        return this.http.post<ApiResult>(api + '/add', data).toPromise();
    }
    /** 编辑 */
    edit(data: UserModel) {
        return this.http.post<ApiResult>(api + '/edit', data).toPromise();
    }
    /** 删除 */
    del(id: number) {
        return this.http.delete<ApiResult>(api + `/${id}`).toPromise();
    }

    /** 查询用户已授权角色 */
    getUserRoleId(userId: number) {
        return this.http.get<DataResult<number>>(`/user/role/${userId}`).toPromise();
    }
    /** 用户授权角色 */
    grant(data: GrantUserRole) {
        return this.http.post<ApiResult>('/user/role', data).toPromise();
    }

    /** 我的用户信息 */
    myInfo() {
        return this.http.get<DataResult<UserData>>(api + '/me').toPromise();
    }
    /** 修改密码 */
    changePwd(data: Password) {
        return this.http.post<ApiResult>(api + '/change', data).toPromise();
    }
}
