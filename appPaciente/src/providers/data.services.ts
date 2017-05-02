import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import * as data from './data';

import 'rxjs/add/operator/toPromise';

/*
  Generated class for the MyData provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class AppServices {

  header = new Headers();
  private url = 'http://localhost:8080/corazon-web/api/';
  user = this.storage.get('user');

  constructor(public http: Http, public storage: Storage) {
    this.header.append('x_rest_user', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IkJ1aXRyYWdvIiwicGFzc3dvcmQiOiJCdWl0cmFnbzI3IiwibGV2ZWxBY2Nlc3MiOiJ1c2VyIn0.Bh6_BjImaskzYYk_MOgeIHVeUKs-_Lm7tx8KorDhl5I');
    console.log('Hello MyData Provider');
  }

  getToken(credenciales){
    return new Promise((resolve,reject)=>{
      var autenticacion = new Headers(autenticacion);
      autenticacion.append('Content-Type',"application/json");
      this.http.post('http://localhost:8080/corazon-web/api/auth/login',credenciales,{headers:autenticacion}).
      subscribe(token=>{
        console.log("respuesta")
        console.log(token.json())
        if(token.status==200){
          resolve(token.json());
        }else{
          reject(new Error("Credenciales no correctas"));
        }
      })
    })

  }

  getMediciones(): Promise<data.Medicion[]>{
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json().data as data.Medicion[])
               .catch(this.handleError);
  }

  getTratamientos(): Promise<data.Tratamiento[]>{
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json().data as data.Tratamiento[])
               .catch(this.handleError);
  }

  getDiagnosticos(): Promise<data.Diagnostico[]>{
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json().data as data.Diagnostico[])
               .catch(this.handleError);
  }

  getExamenes(): Promise<data.Tratamiento[]>{
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json().data as data.Examen[])
               .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
