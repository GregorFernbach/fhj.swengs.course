import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Actor} from './api/actor';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  constructor(private http: HttpClient) {
  }

  createActor(actor: Actor) {
    return this.http.post('/api/actors/', actor);
  }

  updateActor(actor: Actor) {
    return this.http.put('/api/actors/' + actor.id, actor);
  }
}
