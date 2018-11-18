import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-alert-button',
  templateUrl: './alert-button.component.html',
  styleUrls: ['./alert-button.component.css']
})
export class AlertButtonComponent implements OnInit {

  @Input('initialAlert')
  initialAlert: String = '';

  constructor() { }

  ngOnInit() {
  }

  showAlert(alterTextInput : HTMLInputElement) {
    alert(alterTextInput.value);
  }
}
