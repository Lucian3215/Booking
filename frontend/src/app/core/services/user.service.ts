import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  apiURL = 'http://localhost:8080/api/user/';
  http = inject(HttpClient);

  private generateId() {
    var uniqid = Date.now().toString().slice(-8);
    return uniqid;
  }

  getId(): string {
    if (localStorage.getItem('token') === null) {
      let id = this.generateId();
      localStorage.setItem('token', id);
      this.http.post(this.apiURL + 'add/' + id, {}).subscribe();
    }
    return <string>localStorage.getItem('token');
  }
}
