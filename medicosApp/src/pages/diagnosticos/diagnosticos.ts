import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {CorazonRest} from'../../providers/corazon-rest';
import { AlertController } from 'ionic-angular';
import { LoadingController } from 'ionic-angular';
/**
* Generated class for the Diagnosticos page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/
@Component({
  selector: 'page-diagnosticos',
  templateUrl: 'diagnosticos.html',
})

export class Diagnosticos {
  historia:any;
  authForm : FormGroup;
  date:any;
  idPaciente:any;
  generarDiagnostico=true;
  constructor(public loadingCtrl: LoadingController,public alertCtrl: AlertController,public navCtrl: NavController, public navParams: NavParams,public fb: FormBuilder,
  public rest:CorazonRest) {
    console.log(navParams);
    this.historia=navParams.get('historia');
    this.idPaciente=navParams.get('idPaciente');
    this.date = new Date().toISOString();
    this.authForm = fb.group({
      'descripcion' : [null, Validators.compose([Validators.required])],
      'fecha':this.date.split("T")[0]
    })


  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad Diagnosticos');
  }

  mostarForm(){
    if(this.generarDiagnostico==false){
      this.generarDiagnostico=true;
    }
    else{
      this.generarDiagnostico=false;
    }
  }

  submitForm(){
    console.log('Enviar Diagnostico');
    console.log(this.authForm.value);
    this.presentLoading(1000,"Enviando Diagnostico... ");
    this.rest.postDiagnosticoPaciente(this.idPaciente,this.authForm.value).then((recomendacion=>{
      this.alertaErrorNoData('Diagnóstico generado con exito');
      this.navCtrl.pop();
    })).catch((Error)=>{
      this.alertaErrorNoData('El diagnóstico no fue generado con exito');
      console.log(Error);
    });

  }

  alertaErrorNoData(mensaje) {
    let alert = this.alertCtrl.create({
      title: 'Notificación',
      subTitle: mensaje,
      buttons: ['Aceptar']
    });
    alert.present();
  }
  presentLoading(duracion, mensaje) {
    let loader = this.loadingCtrl.create({
      content: mensaje,
      duration: duracion
    });
    loader.present();
  }

}
