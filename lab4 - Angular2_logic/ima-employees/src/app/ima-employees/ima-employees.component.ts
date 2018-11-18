import { Component, OnInit } from '@angular/core';

import imaEmployeesJSONFile from './imaEmployees.json';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-ima-employees',
  templateUrl: './ima-employees.component.html',
  styleUrls: ['./ima-employees.component.scss']
})
export class ImaEmployeesComponent implements OnInit {

  filter;
  imaEmployeeArray;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    const filterParam = this.route.snapshot.paramMap.get('filter');
    this.filter = filterParam ? filterParam : '';
    this.filterEmployees(this.filter);

    this.route.params.subscribe(params => {
      this.filter = params['filter'];
      this.filterEmployees(this.filter);
    });
  }

  filterEmployees(filterValue) {
    if (filterValue) {
      this.imaEmployeeArray = imaEmployeesJSONFile.filter((employee) => {
        return employee.name.toLowerCase().indexOf(filterValue.toLowerCase()) !== -1 ||
          employee.position.toLowerCase().indexOf(filterValue.toLowerCase()) !== -1;
      });
    } else {
      this.imaEmployeeArray = imaEmployeesJSONFile;
    }
      }
}
