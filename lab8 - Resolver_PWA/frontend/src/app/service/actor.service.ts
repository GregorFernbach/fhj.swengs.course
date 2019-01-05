import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Actor} from '../api/actor';
import {catchError, map, retry} from 'rxjs/operators';
import {of, throwError} from 'rxjs';
import {OnlineStatusService, OnlineStatusType} from './online-status.service';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  constructor(private http: HttpClient, private onlineStatusService: OnlineStatusService, private router: Router) {
    this.onlineStatusService.status.subscribe((status: OnlineStatusType) => {
      if (status === OnlineStatusType.ONLINE) {
        const offlineActors = this.getOfflineActors();
        while (offlineActors.length > 0) {
          const offlineActor = offlineActors.pop();
          const offlineActorId = offlineActor.id;
          offlineActor.id = null;
          this.create(offlineActor).subscribe((response: any) => {
            console.log('saved successfully');
            if (this.router.url === '/actor-form/' + offlineActorId) {
              this.router.navigate(['/actor-form', response.id]);
            }
          });
          this.setOfflineActors(offlineActors);
        }
      }
    });
  }

  getById(id: number) {
    if (id < 0) {
      return of(this.getOfflineActors().find(offlineActor => offlineActor.id === id));
    }
    return this.http.get('/api/dto/actors/' + id).pipe(
      map((res: any) => {
        if (res.dayOfBirth) {
          res.dayOfBirth = new Date(res.dayOfBirth);
        }
        return res;
      }));
  }

  getAll() {
    return this.http.get('/api/actors').pipe(
      map((response: any) => {
        return response._embedded.actors;
      }),
      catchError((err: HttpErrorResponse) => {
        return of(this.getOfflineActors());
      })
    );
  }

  delete(actor) {
    return this.http.delete('/api/actors/' + actor.id).pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  update(actor: Actor) {
    return this.http.put('/api/dto/actors/' + actor.id, actor).pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  create(actor: Actor) {
    return this.http.post('/api/dto/actors', actor)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          if (!navigator.onLine) {
            actor.id = new Date().getUTCMilliseconds() * -1;
            const offlineActors = this.getOfflineActors();
            offlineActors.push(actor);
            this.setOfflineActors(offlineActors);
          }
          return of(actor);
        })
      );
  }

  setOfflineActors(offlinceActors) {
    localStorage.setItem('offlineActors', JSON.stringify(offlinceActors));
  }

  getOfflineActors(): Array<any> {
    return localStorage.getItem('offlineActors') ? <Array<any>>JSON.parse(localStorage.getItem('offlineActors')) : [];
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }

}
