import {Component, Input} from '@angular/core';
import {Lawn} from "../domain/lawn";

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent {

  @Input()
  lawn: Lawn;

  @Input()
  name: string;

  colors = ["Blue ", "Red", "Orange", "Violet", "Indigo", "Black "];

  constructor() {
  }
}
