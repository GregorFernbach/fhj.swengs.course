import { Component, OnInit } from '@angular/core';
import {Actor} from '../api/actor';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.scss']
})
export class ActorListComponent implements OnInit {

  actors: Array<Actor>;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get('/api/actors')
      .subscribe((response: any) => {
        this.actors = response._embedded.actors;
    });
  }
  deleteActor(actor: Actor) {
    this.http.delete('/api/actors/' + actor.id)
      .subscribe(() => {
        this.ngOnInit();
      });
  }

}
