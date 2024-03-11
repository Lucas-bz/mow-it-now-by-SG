import { Mower } from './mower';


export class Cell {

    mowed: boolean; 
    mowIsHere: boolean;
    mower: Mower;

    constructor(public row: number, public column: number) {}

}