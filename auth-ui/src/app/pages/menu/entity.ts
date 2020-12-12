/** 菜单数据 */
export class MenuData {
    id!: number;
    systemId!: number;
    pid!: number;
    type!: number;
    title!: string;
    path!: string;
    icon!: string;
    indez!: number;
    remark!: string;
    enable!: number;
}
/** 菜单新增/编辑 */
export class MenuModel {
    id?: number;
    systemId!: number;
    pid?: number;
    type!: number;
    title!: string;
    path?: string;
    icon?: string;
    indez!: number;
    remark?: string;
    enable!: number;
}