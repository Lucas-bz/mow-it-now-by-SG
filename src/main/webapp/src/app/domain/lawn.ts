import { Cell } from './cell';
import * as _ from "lodash";
import { Mower } from './mower';

export class Lawn {

    cells: Cell[][] = [];
    mowerSet: Mower[]


    constructor(x: number, y: number, mowerSet: Mower[]) {
      this.mowerSet = mowerSet;
        for (let i = 0; i <= y; i++) {
          this.cells[i] = [];
          for (let j = 0; j <= x; j++) {
            this.cells[i][j] = new Cell(i, j);
            const mower =_.find(mowerSet, o => {return o.position.x == j && y - o.position.y == i})
            if( mower !== undefined){
              this.cells[i][j].mowIsHere = true;
              this.cells[i][j].mower = mower;

            }
          }
        }



    }



}
