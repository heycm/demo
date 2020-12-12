/** 应用数据 */
export class AppData {
    id!: number;
    name!: string;
    code!: string;
    url!: string;
    enable!: number;
    indez!: number;
    createAt!: string;
    remark!: string
}
/** 新增/编辑应用 */
export class AppModel {
    id?: number;
    name!: string;
    code!: string;
    enable!: number;
    indez!: number;
    url?: string;
    remark?: string
}