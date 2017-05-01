import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {CorazonRest} from'../../providers/corazon-rest'
import {DetalleMediciones} from'../detalle-mediciones/detalle-mediciones';
import { AlertController } from 'ionic-angular';
import { LoadingController } from 'ionic-angular';



/**
* Generated class for the DetallePaciente page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/

@Component({
  selector: 'page-detalle-paciente',
  templateUrl: 'detalle-paciente.html',
})
export class DetallePaciente {
  paciente:any;
  authForm : FormGroup;

  constructor(public loadingCtrl: LoadingController,public navCtrl: NavController, public navParams: NavParams,public rest:CorazonRest,
  fb: FormBuilder,public alertCtrl: AlertController) {
    this.paciente=navParams.get('paciente');
    this.authForm = fb.group({
      'fechaInicio' : [null, Validators.compose([Validators.required])],
      'fechaFinal' : [null, Validators.compose([Validators.required])]
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DetallePaciente');
  }

  presentLoading(duracion) {
      let loader = this.loadingCtrl.create({
        content: "Buscando mediciones... ",
        duration: duracion
      });
      loader.present();
    }
  consultarMediciones(valor){
    console.log('consultarMediciones');
    this.presentLoading(2000);
    this.rest.getMedicionesFechas(valor,this.paciente.id).then((mediciones =>{
      let medicionesLista=mediciones;
      console.log(medicionesLista);
      this.navCtrl.push(DetalleMediciones,{medicionesLista:medicionesLista});
    })).catch((Error)=>{
      this.alertaErrorNoMediciones();
      console.log(Error);

    });

  }

  consultarHistorialMedico(){
    console.log('consultarHistorialMedico');

  }

  alertaErrorNoMediciones() {
    let alert = this.alertCtrl.create({
      title: 'Notificación',
      subTitle: 'No se encuentran mediciones en rago deseado',
      buttons: ['Aceptar']
    });
    alert.present();
  }
}
