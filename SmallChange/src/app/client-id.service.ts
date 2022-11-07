import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClientIdService {

  constructor() { }

  clientId!: string;
}
