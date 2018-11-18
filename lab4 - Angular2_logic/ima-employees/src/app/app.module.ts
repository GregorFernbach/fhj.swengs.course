import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ImaEmployeesComponent } from './ima-employees/ima-employees.component';
import { ImaStudentsComponent } from './ima-students/ima-students.component';

@NgModule({
  declarations: [
    AppComponent,
    ImaEmployeesComponent,
    ImaStudentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
