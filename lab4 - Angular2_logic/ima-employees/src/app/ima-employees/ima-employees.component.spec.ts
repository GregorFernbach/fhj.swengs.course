import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {RouterTestingModule} from '@angular/router/testing';
import { ImaEmployeesComponent } from './ima-employees.component';

describe('ImaEmployeesComponent', () => {
  let component: ImaEmployeesComponent;
  let fixture: ComponentFixture<ImaEmployeesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      // Router Testing Module muss noch eingebunden werden
      imports: [
        RouterTestingModule
      ],
      declarations: [ ImaEmployeesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImaEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('There should be 36 Lehrbeauftragte', () => {
    expect(component.imaEmployeeArray.length).toBe(36);
  });

  it('There should be 17 Nebenberufliche', () => {
    component.filter = 'nebenberuflich lehrend';
    component.filterEmployees(component.filter);
    expect(component.imaEmployeeArray.length).toBe(17);
  });

  it('There should be 1 Institutsleiter', () => {
    component.filter = 'Institutsleiter Informationsmanagement';
    component.filterEmployees(component.filter);
    expect(component.imaEmployeeArray.length).toBe(1);
  });

  it('In the frontenf Institutsleiter Werner Fritz should appear', () => {
    component.filter = 'Institutsleiter Informationsmanagement';
    component.filterEmployees(component.filter);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Werner Fritz');
  });
});
