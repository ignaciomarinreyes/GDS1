import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';

@Injectable({
  providedIn: 'root'
})
export class LocalDataService {

  token = null

  constructor(private storage: Storage) {
    this.getToken();
  }

  saveToken(token){
    this.token = token;
    this.storage.set('token', token);
  }

  async getToken(){
    const token = await this.storage.get('token');

    if (token) {
      this.token = token;
    }
  }

  async eraseToken(){
    this.token = null;
    this.storage.set('token', this.token);
  }
}
