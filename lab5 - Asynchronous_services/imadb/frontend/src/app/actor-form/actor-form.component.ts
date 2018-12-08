import {Component, OnInit} from '@angular/core';
import {Actor} from '../api/actor';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {ActorService} from '../actor.service';

@Component({
  selector: 'app-actor-form',
  templateUrl: './actor-form.component.html',
  styleUrls: ['./actor-form.component.scss']
})
export class ActorFormComponent implements OnInit {

  actor: Actor;
  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private actorService: ActorService) {
  }

  ngOnInit() {
    this.actor = {
      firstName: '',
      lastName: ''
    };
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.http.get('/api/actors/' + id)
        .subscribe((response) => {
          this.actor = <Actor>response;
        });
    }
  }
  createActor(actor: Actor) {
    if (this.actor.id) {
      this.actorService.updateActor(this.actor)
      .subscribe(() => {
        alert('Update Funktioniert!');
        this.router.navigateByUrl('/actor-list');
      });
    } else {
      this.actorService.createActor(this.actor)
        .subscribe(() => {
          alert('Create Funktioniert!');
          this.router.navigateByUrl('/actor-list');
        });
    }
  }

}
