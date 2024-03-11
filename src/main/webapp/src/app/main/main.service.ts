import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import { Response } from '../domain/Response';

@Injectable({
  providedIn: 'root'
})
export class MainService {

  private static INIT_LAWN_API = 'http://localhost:8080/uploadFile'

  constructor(private http:HttpClient) { }



  public loadInitialLawn(params: any): Observable<Response>{
    return this.http.post<Response>(MainService.INIT_LAWN_API, params, {});
  }



}
