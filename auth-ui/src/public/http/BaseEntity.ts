/**
 * 三种基本返回数据类型
 */

 /** 基本接口返回 */
export class ApiResult {
    ok!: boolean;
    message!: string;
}

/** 数据接口返回 */
export class DataResult<T> extends ApiResult {
    data!: T
}

/** 分页数据接口返回 */
export class PageResult<T> extends ApiResult {
    page!: number;
    pages!: number;
    size!: number;
    total!: number;
    data!: T[];
}

/**
 * 分页查询统一参数类型
 */
export class Page<T> {
    page? = 1;
    size? = 10;
    param?: T


    constructor(param?: {
        page?: number,
        size?: number,
        param?: T
    }) {
        if (param) {
            this.page = param.page ? param.page : 1;
            this.size = param.size ? param.size : 10;
            if (param.param) {
                this.param = param.param;
            }
        } else {
            this.page = 1;
            this.size = 10;
        }
        
    }
}

/**
 * 树模型
 */
export class Tree<T> {
    key!: string;
    title!: string;
    isLeaf?: boolean;
    expanded?: boolean;
    disabled?: boolean;
    level?: number;
    parent?: Tree<T>;
    origin!: T;
    children!: Tree<T>[]
}