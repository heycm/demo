/** 角色信息 */
export class RoleData {
    id!: number;
    name!: string;
    nameZh!: string;
    enable!: number;
    remark!: string;
    type!: number;
    createAt!: string;
}
/** 角色新增/编辑 */
export class RoleModel {
    id?: number;
    name!: string;
    nameZh!: string;
    enable!: number;
    remark?: string;
}
/** 角色授权菜单 */
export class GrantRoleMenu {
    systemId!: number;
    roleId!: number;
    menuIds!: number[];
}