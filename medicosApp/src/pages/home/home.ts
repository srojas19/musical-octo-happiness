import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import {CorazonRest} from'../../providers/corazon-rest'
import { ModalController} from 'ionic-angular';
import {Autenticacion} from'../autenticacion/autenticacion'

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  medico:any;
  pacientesCuidado:any;

  constructor(public navCtrl: NavController,public storage:Storage, public navParams: NavParams,
    public rest:CorazonRest,public modalCtrl: ModalController) {
      console.log("contructor home");
      console.log(navParams);
      if(navParams.get('medico')!==undefined){
        this.medico=navParams.get('medico');
      }
      else{
        this.medico=this.storage.get('medico').then((medico=>{
          if(medico!==undefined){
            this.medico=medico;
          }else{
            this.navCtrl.setRoot(Autenticacion,{},{});
          }
        }));
      }
    }

    ionViewDidLoad(){
      console.log("  ionViewDidLoad home");

      this.medico=this.storage.get('medico').then((medico=>{
        if(medico!==undefined){
          this.medico=medico;
        }else{
          this.navCtrl.setRoot(Autenticacion,{},{});
        }
      }));
      this.medico=this.storage.get('pacientesCuidado').then((pacientes=>{
        if(pacientes!==undefined){
          this.pacientesCuidado=pacientes;
        }
      }));
    }

  }
