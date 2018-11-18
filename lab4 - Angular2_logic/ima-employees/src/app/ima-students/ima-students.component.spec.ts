import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImaStudentsComponent } from './ima-students.component';

describe('ImaStudentsComponent', () => {
  let component: ImaStudentsComponent;
  let fixture: ComponentFixture<ImaStudentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImaStudentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImaStudentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
