import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {CorazonRest} from'../../providers/corazon-rest'
import {DetalleMediciones} from'../detalle-mediciones/detalle-mediciones';
import {Recomendaciones} from'../recomendaciones/recomendaciones';
import {EnviarConsejo} from'../enviar-consejo/enviar-consejo';
import { AlertController } from 'ionic-angular';
import { LoadingController } from 'ionic-angular';
import { ModalController } from 'ionic-angular';



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
  idMedico:any;
  authForm : FormGroup;

  constructor(public loadingCtrl: LoadingController,public navCtrl: NavController, public navParams: NavParams,public rest:CorazonRest,
    fb: FormBuilder,public alertCtrl: AlertController,public modalCtrl: ModalController) {
      this.paciente=navParams.get('paciente');
      this.idMedico=navParams.get('idMedico');
      this.authForm = fb.group({
        'fechaInicio' : [null, Validators.compose([Validators.required])],
        'fechaFinal' : [null, Validators.compose([Validators.required])]
      });
    }

    ionViewDidLoad() {
      console.log('ionViewDidLoad DetallePaciente');
    }

    presentLoading(duracion, mensaje) {
      let loader = this.loadingCtrl.create({
        content: mensaje,
        duration: duracion
      });
      loader.present();
    }
    consultarMediciones(valor){
      console.log('consultarMediciones');
      this.presentLoading(2000,"Buscando mediciones... ");
      this.rest.getMedicionesFechas(valor,this.paciente.id).then((mediciones =>{
        let medicionesLista=mediciones;
        console.log(medicionesLista);
        this.navCtrl.push(DetalleMediciones,{medicionesLista:medicionesLista});
      })).catch((Error)=>{
        this.alertaErrorNoData('No se encuentran mediciones en rago deseado');
        console.log(Error);

      });

    }

    consultarHistorialMedico(){
      console.log('consultarHistorialMedico');

    }

    consultarConsejos(){
      console.log('consultarRecomencacinones');
      this.presentLoading(300,"Buscando recomendaciones... ");
      this.rest.getConsejosPaciente(this.paciente.id).then((recomenciones =>{
        let recomencionesLista=recomenciones;
        console.log(recomenciones);
        this.navCtrl.push(Recomendaciones,{recomencionesLista:recomencionesLista});
      })).catch((Error)=>{
        this.alertaErrorNoData('Paciente no cuenta con consejos');
        console.log(Error);
      });
    }

    enviarConsejos(){
      let favoritoListaModal = this.modalCtrl.create(EnviarConsejo, {});
      favoritoListaModal.onDidDismiss(data => {
        console.log(data);
        if(data.descripcion!==undefined){
          this.rest.postConsejosPaciente(this.paciente.id,this.idMedico,data).then((recomendacion=>{
            this.alertaErrorNoData('Consejo enviado con exito');
          })).catch((Error)=>{
            this.alertaErrorNoData('No se pudo enviar el consejo');
            console.log(Error);
          });
        }
        else{
          console.log("no ir a favorito");
        }
      });
      favoritoListaModal.present();
    }

    alertaErrorNoData(mensaje) {
      let alert = this.alertCtrl.create({
        title: 'Notificación',
        subTitle: mensaje,
        buttons: ['Aceptar']
      });
      alert.present();
    }
  }
