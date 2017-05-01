import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import {CorazonRest} from'../../providers/corazon-rest'
import {DetallePaciente} from'../detalle-paciente/detalle-paciente'


/**
 * Generated class for the Pacientes page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */

@Component({
  selector: 'page-pacientes',
  templateUrl: 'pacientes.html',
})
export class Pacientes {

  pacientes:any;

  constructor(public storage:Storage,public navCtrl: NavController, public navParams: NavParams
     ,public rest:CorazonRest) {
    this.storage.get('medico').then((medico=>{
      console.log(medico.pacientes);
      console.log("buscando pacientes");
      console.log(this.pacientes);
      if(medico!==undefined){
        this.pacientes=medico.pacientes;
      }
    }));

  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad Pacientes');
    this.storage.get('medico').then((medico=>{
      if(medico!==undefined){
        this.pacientes=medico.pacientes;
      }
    }));
  }

  verDetallePaciente(paciente){
    this.navCtrl.push(DetallePaciente,{paciente:paciente})
  }

}
