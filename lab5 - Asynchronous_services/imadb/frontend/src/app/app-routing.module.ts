import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ActorFormComponent} from './actor-form/actor-form.component';
import {ActorListComponent} from './actor-list/actor-list.component';

const routes: Routes = [
  {path: 'actor-form', component: ActorFormComponent},
  {path: 'actor-list', component: ActorListComponent},
  {path: 'actor-form/:id', component: ActorFormComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})

export class AppRoutingModule {
}
