import { LoginData } from 'src/app/pages/login/entity';

export class LocalCatch {

    /** 缓存 */
    static setCatch(data: LoginData) {
        localStorage.setItem('id', data.id + '');
        localStorage.setItem('username', data.username);
        localStorage.setItem('nickname', data.nickname);
        localStorage.setItem('token', data.token);
    }

    /** 取ID */
    static getId() {
        return parseInt(localStorage.getItem('token') || '-1');
    }

    /** 取用户名 */
    static getUsername() {
        return localStorage.getItem('username') || '';
    }

    /** 取昵称 */
    static getNickname() {
        return localStorage.getItem('nickname') || '';
    }

    /** 存昵称(更新个人信息时用到) */
    static setNickname(nickname: string) {
        localStorage.setItem('nickname', nickname);
    }

    /** 取token */
    static getToken() {
        return localStorage.getItem('token');
    }

    /** 存token(单点登录时用到) */
    static setToken(token: string) {
        localStorage.setItem('token', token);
    }

    /** 清缓存 */
    static remove() {
        localStorage.removeItem('id');
        localStorage.removeItem('username');
        localStorage.removeItem('nickname');
        localStorage.removeItem('token');
    }
}