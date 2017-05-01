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

  ip='192.168.0.4';


  constructor(public http: Http,public storage:Storage) {
    console.log('Hello CorazonRest Provider');
  }

  getToken(credenciales){
    return new Promise((resolve,reject)=>{
      var autenticacion = new Headers(autenticacion);
      autenticacion.append('Content-Type',"application/json");
      this.http.post('http://'+this.ip+':8080/corazon-web/api/auth/login',credenciales,{headers:autenticacion}).
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
      this.storage.get('token').then((token=>{
        var autenticacion = new Headers(autenticacion);
        autenticacion.append('Content-Type',"application/json");
        autenticacion.append('x_rest_user',token);
        this.http.get('http://'+this.ip+':8080/corazon-web/api/medicos/cedula/'+id,{headers:autenticacion}).
        subscribe(medico=>{
          console.log("respuesta get medico")
          console.log(medico.json())
          if(medico.json().id!==0){

            resolve(medico.json());
          }else{
            reject(new Error("Credenciales no correctas"));
          }
        })
      }));
    })
  }

  getMedicionesFechas(fechas,idPaciente ){
    return new Promise((resolve,reject)=>{
      var autenticacion = new Headers(autenticacion);
      this.storage.get('token').then((token=>{
        autenticacion.append('Content-Type',"application/json");
        autenticacion.append('x_rest_user',token);
        this.http.get('http://'+this.ip+':8080/corazon-web/api/resources/pacientes/'+idPaciente+'/mediciones/?fecha_inicio='+
        fechas.fechaInicio.year+'-'+fechas.fechaInicio.month+'-'+fechas.fechaInicio.day+
        '&fecha_fin='+fechas.fechaFinal.year+'-'+fechas.fechaFinal.month+'-'+fechas.fechaFinal.day,{headers:autenticacion}).
        subscribe(mediciones=>{
          if(mediciones.json().length!==0){
            resolve(mediciones.json());
          }else{
            reject(new Error("No mediciones que cumplan el criterio"));
          }
        })
      }));
    })
  }

  getConsejosPaciente(idPaciente ){
    return new Promise((resolve,reject)=>{
      var autenticacion = new Headers(autenticacion);
      this.storage.get('token').then((token=>{
        autenticacion.append('Content-Type',"application/json");
        autenticacion.append('x_rest_user',token);
        this.http.get('http://'+this.ip+':8080/corazon-web/api/consejo/pacientes/'+idPaciente+'/consejos',{headers:autenticacion}).
        subscribe(consejos=>{
          if(consejos.json().length!==0){
            resolve(consejos.json());
          }else{
            reject(new Error("No se pudieron recuperar las mediciones"));
          }
        })
      }));
    })
  }
  postConsejosPaciente(idPaciente,medicoId,consejo ){
    return new Promise((resolve,reject)=>{
      var autenticacion = new Headers(autenticacion);
      this.storage.get('token').then((token=>{
        autenticacion.append('Content-Type',"application/json");
        autenticacion.append('x_rest_user',token);
        this.http.post('http://'+this.ip+':8080/corazon-web/api/consejo/medicos/'+medicoId+'/consejos/'+idPaciente,consejo,{headers:autenticacion}).
        subscribe(consejos=>{
          if(consejos.json().id!==0){
            resolve(consejos.json());
          }else{
            reject(new Error("No se pudo enviar la recomendacion"));
          }
        })
      }));
    })

  }

}
