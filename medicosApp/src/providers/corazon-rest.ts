import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

/*
Generated class for the CorazonRest provider.

See https://angular.io/docs/ts/latest/guide/dependency-injection.html
for more info on providers and Angular 2 DI.
*/
@Injectable()
export class CorazonRest {

  constructor(public http: Http,public storage:Storage) {
    console.log('Hello CorazonRest Provider');
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

  getMedicoId(id){
    return new Promise((resolve,reject)=>{
      var autenticacion = new Headers(autenticacion);
      autenticacion.append('Content-Type',"application/json");
      autenticacion.append('x_rest_user',this.storage.get('token'));
      this.http.get('http://localhost:8080/corazon-web/api/medicos/cedula/'+id,{headers:autenticacion}).
      subscribe(medico=>{
        console.log("respuesta get medico")
        console.log(medico.json())
        if(medico.json().id!==0){
          resolve(medico.json());
        }else{
          reject(new Error("Credenciales no correctas"));
        }
      })
    })

  }

}
