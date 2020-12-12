import { FormGroup } from '@angular/forms';

/**
 * 校验器
 */
export class Checker {

    static verifyForm(group: FormGroup): boolean {
        if (!group) return false;
        for (const i in group.controls) {
            group.controls[i].markAsDirty();
            group.controls[i].updateValueAndValidity();
        }
        return group.valid;
    }

}