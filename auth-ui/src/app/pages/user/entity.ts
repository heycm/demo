/** 用户分页数据 */
export class UserData {
    id!: number;
    username!: string;
    nickname!: string;
    enable!: number;
    createAt!: string;
}
/** 新增/编辑用户/编辑个人信息 */
export class UserModel {
    id?: number;
    username?: string;
    nickname?: string;
    enable?: number;
}
/** 用户授权角色 */
export class GrantUserRole {
    userId!: number;
    roleId!: number;
}
/** 修改密码 */
export class Password {
    oldPwd!: string;
    newPwd!: string;
}