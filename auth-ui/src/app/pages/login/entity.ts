
/** 登录表单 */
export class LoginModel {
    username!: string;
    password!: string;
}

/** 登录成功返回数据 */
export class LoginData {
    id!: number;
    username!: string;
    nickname!: string;
    token!: string;
}