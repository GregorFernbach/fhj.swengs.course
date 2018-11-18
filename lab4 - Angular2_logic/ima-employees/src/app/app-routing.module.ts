import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {ImaEmployeesComponent} from './ima-employees/ima-employees.component';
import {ImaStudentsComponent} from './ima-students/ima-students.component';

const routes: Routes = [
  {path: '', redirectTo: '/ima-employees', pathMatch: 'full'},
  {path: 'ima-employees', component: ImaEmployeesComponent},
  {path: 'ima-employees/:filter', component: ImaEmployeesComponent},
  {path: 'ima-students', component: ImaStudentsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
