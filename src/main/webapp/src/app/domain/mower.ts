import { Position } from './position';
import { Orientation } from './orientation';


export interface Mower{
    
    id: number;
    position: Position;
    previous: Position[];
    orientation: Orientation;
}