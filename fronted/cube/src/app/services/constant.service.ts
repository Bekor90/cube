import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})

export class ConstantsService {
    API_URL = 'http://localhost:8080/Cube/';

    constructor() {}
}