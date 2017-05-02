import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import {CorazonRest} from'../../providers/corazon-rest';
import {DetallePaciente} from'../detalle-paciente/detalle-paciente';


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
  idMedico:any;
  especialista:any;

  constructor(public storage:Storage,public navCtrl: NavController, public navParams: NavParams
     ,public rest:CorazonRest) {
    this.storage.get('medico').then((medico=>{

      if(medico!==undefined){
        this.pacientes=medico.pacientes;
        this.idMedico=medico.id;
        this.especialista=medico.especialista;
      }
      console.log('id medico');
      console.log(this.idMedico);
    }));

  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad Pacientes');
    this.storage.get('medico').then((medico=>{
      if(medico!==undefined){
        this.pacientes=medico.pacientes;
        this.idMedico=medico.id;
      }
    }));
  }

  verDetallePaciente(paciente){
    this.navCtrl.push(DetallePaciente,{paciente:paciente,idMedico:this.idMedico,especialista:this.especialista})
  }

}
