import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  intro = 'Wunderful welcome to '
  title = 'testcomponents';
  calculatedValue;

  ngOnInit(): void {
    this.calculatedValue = this.add(1, 2);
}

  add(a, b) {
    return a + b;
  }
}
