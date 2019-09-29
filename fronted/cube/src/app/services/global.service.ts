import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConstantsService } from './constant.service';
import { pipe } from '@angular/core/src/render3';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  constructor(private http: HttpClient, private constant: ConstantsService) { }

  getData(params){

    const url = `${this.constant.API_URL}private/post/generate_cube`
    return this.http.post(url, params).pipe(res => res);
  }
}
