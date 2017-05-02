import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {CorazonRest} from'../../providers/corazon-rest';
import { AlertController } from 'ionic-angular';
import { LoadingController } from 'ionic-angular';

/**
* Generated class for the Marcapasos page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/

@Component({
  selector: 'page-marcapasos',
  templateUrl: 'marcapasos.html',
})
export class Marcapasos {

  marcapasos:any;
  idPaciente:any;
  especialista:any;
  authForm : FormGroup;
  editar=false;
  fechaImplante:any;
  finVidaUtil:any;
  constructor(public loadingCtrl: LoadingController,public rest:CorazonRest,public navCtrl: NavController, public navParams: NavParams,fb: FormBuilder,
    public alertCtrl: AlertController,) {
      this.marcapasos=navParams.get('marcapasos');
      this.fechaImplante= this.marcapasos.fechaImplante.split("T")[0];
      this.finVidaUtil= this.marcapasos.finVidaUtil.split("T")[0];
      this.idPaciente=navParams.get('idPaciente');
      this.especialista=navParams.get('especialista');
      this.authForm = fb.group({
        'voltaje' : [null, Validators.compose([Validators.required])],
        'frecuenciaElectrica' : [null, Validators.compose([Validators.required])],
      });
    }

    ionViewDidLoad() {
      console.log('ionViewDidLoad Marcapasos');
    }

    submitForm(){

      this.presentLoading(1500, "Enviando nueva configuracion");

      this.marcapasos.voltaje= this.authForm.value.voltaje;
      this.marcapasos.frecuenciaElectrica= this.authForm.value.frecuenciaElectrica;
      this.rest.putMarcapasosConfiPaciente(this.idPaciente,this.marcapasos).then(
        (s=>{
          this.editar=false;
          this.alertaErrorNoData('Valores marcapasos actualizados correctamente');
        }))

        .catch((Error)=>{
          this.alertaErrorNoData('No se actualizaron los valores');
          console.log(Error);}
        );

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

      editarCongiguracion(){
        this.editar=!this.editar;
      }

    }
