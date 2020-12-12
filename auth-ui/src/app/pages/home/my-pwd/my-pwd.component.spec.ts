/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MyPwdComponent } from './my-pwd.component';

describe('MyPwdComponent', () => {
  let component: MyPwdComponent;
  let fixture: ComponentFixture<MyPwdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyPwdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPwdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
