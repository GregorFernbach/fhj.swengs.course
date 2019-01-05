import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Actor} from '../api/actor';
import {ActorService} from '../service/actor.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-actor-list',
  templateUrl: './actor-list.component.html',
  styleUrls: ['./actor-list.component.scss']
})
export class ActorListComponent implements OnInit {

  actors: Array<Actor>;

  constructor(private actorService: ActorService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {

    const actorsData = this.route.snapshot.data;
    const acTors = actorsData.actors;
    this.actors = acTors;

/*
    this.actorService.getAll()
      .subscribe((actors: any) => {
        this.actors = actors;
      });
  */
  }


  deleteActor(actor: Actor) {

    this.actorService.delete(actor)
      .subscribe(() => {
        this.ngOnInit();
      });

  }

  createActor() {
    this.router.navigate(['/actor-form']);
  }
}
