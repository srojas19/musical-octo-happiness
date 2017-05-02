import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {CorazonRest} from'../../providers/corazon-rest';
import {DetalleMediciones} from'../detalle-mediciones/detalle-mediciones';
import {Recomendaciones} from'../recomendaciones/recomendaciones';
import {EnviarConsejo} from'../enviar-consejo/enviar-consejo';
import { AlertController } from 'ionic-angular';
import { LoadingController } from 'ionic-angular';
import { ModalController } from 'ionic-angular';
import { Marcapasos } from '../marcapasos/marcapasos';
import { Diagnosticos } from '../diagnosticos/diagnosticos';
import { Examenes } from '../examenes/examenes';
import { Tratamientos } from '../tratamientos/tratamientos';
import { Storage } from '@ionic/storage';

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
  especialista:any;
  observado=false;

  constructor(public loadingCtrl: LoadingController,public navCtrl: NavController, public navParams: NavParams,public rest:CorazonRest,
    fb: FormBuilder,public alertCtrl: AlertController,public modalCtrl: ModalController,public storage:Storage) {
      this.paciente=navParams.get('paciente');
      this.idMedico=navParams.get('idMedico');
      this.especialista=navParams.get('especialista');
      if(navParams.get('observado')!==undefined){
        this.observado=true;
      }

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

    consultarDiagnosticos(){
      this.presentLoading(1500,"Buscando diagnósticos... ");
      console.log('consultarDiagnosticos');
      this.rest.getHistoriaClinicaPaciente(this.paciente.id).then((historia =>{
        let historiaLista=historia;
        console.log(historiaLista);
        this.navCtrl.push(Diagnosticos,{historia:historiaLista,idPaciente:this.paciente.id});
      })).catch((Error)=>{
        this.alertaErrorNoData('No se encuentran diagnósticos');
        console.log(Error);

      });

    }
    consultarExamenes(){
      this.presentLoading(1500,"Buscando exámenes... ");
      console.log('consultarDiagnosticos');
      this.rest.getHistoriaClinicaPaciente(this.paciente.id).then((historia =>{
        let historiaLista=historia;
        console.log(historiaLista);
        this.navCtrl.push(Examenes,{historia:historiaLista,idPaciente:this.paciente.id});
      })).catch((Error)=>{
        this.alertaErrorNoData('No se encuentran exámenes');
        console.log(Error);

      });

    }
    consultarTratamientos(){
      this.presentLoading(1500,"Buscando tratamientos... ");
      console.log('consultarDiagnosticos');
      this.rest.getHistoriaClinicaPaciente(this.paciente.id).then((historia =>{
        let historiaLista=historia;
        console.log(historiaLista);
        this.navCtrl.push(Tratamientos,{historia:historiaLista,idPaciente:this.paciente.id});
      })).catch((Error)=>{
        this.alertaErrorNoData('No se encuentran tratamientos');
        console.log(Error);

      });

    }

    configurarMarcapados(){

      this.presentLoading(2000,"Buscando información marcapasos... ");
      console.log('consultarDiagnosticos');
      this.rest.getMarcapsosPaciente(this.paciente.id).then((historia =>{
        let historiaLista=historia;
        console.log("el marcapadod que se pidio");
        console.log(historiaLista);
        this.navCtrl.push(Marcapasos,{marcapasos:historiaLista,idPaciente:this.paciente.id,especialista:this.especialista});
      })).catch((Error)=>{
        this.alertaErrorNoData('No se encuentran tratamientos');
        console.log(Error);

      });

    }

    agregarObservacion(){
      this.observado=true;
      this.storage.get('observacion').then((lista=>{
        if(lista===undefined||lista===null){
          this.storage.set('observacion',[this.paciente]);
        }
        else{
          let listad =lista.push(this.paciente);
          this.storage.set('observacion',listad);
        }
        this.alertaErrorNoData('Paciente agregado a observación');
      }))

    }

    eliminarObservacion(){

      this.observado=false;
      this.storage.get('observacion').then((lista=>{
        var listad =lista
        for (var i = 0; i < lista.length; i++){
          if(lista[i].id==this.paciente.id){
            lista.splice(i,1);
            console.log("quito de la lista observacion");
          }
          this.alertaErrorNoData('Paciente eliminado de observación');
          this.storage.set('observacion',listad);
        }

      }))

    }
  }
