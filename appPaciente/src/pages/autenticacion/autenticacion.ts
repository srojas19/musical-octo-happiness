import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { AlertController } from 'ionic-angular';

import { HomePage } from '../home/home';
import { TabsPage } from '../tabs/tabs';

import {AppServices} from'../../providers/data.services'

/**
* Generated class for the Autenticacion page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/

@Component({
  selector: 'page-autenticacion',
  templateUrl: 'autenticacion.html',
})
export class Autenticacion {
  authForm : FormGroup;
  constructor(public navCtrl: NavController, public navParams: NavParams,fb: FormBuilder,
    public viewCtrl: ViewController,public storage:Storage,
    public services: AppServices,public alertCtrl: AlertController) {
      this.authForm = fb.group({
        'username' : [null, Validators.compose([Validators.required])],
        'password' : [null, Validators.compose([Validators.required])],
        'levelAccess':"user",
        'id':[null, Validators.compose([Validators.required])]
      });
      this.storage.get('user').then((user=>{
        console.log("fue a traer datos paciente");
        console.log(user);
        if(user!==undefined&&user!==null){
          // this.navCtrl.setRoot(HomePage,{medico},{});
          this.navCtrl.setRoot(TabsPage,{},{});
        }
      }));
    }

    ionViewDidLoad() {
      console.log('ionViewDidLoad Autenticacion');
    }

    submitForm():void{
      console.log("inicio de sesion");
      this.services.getToken(this.authForm.value).then((token =>{
        this.storage.set('token',token);
        // this.services.getMedicoId(this.authForm.value.cedula).then((medico =>{
          this.storage.set('user',this.authForm.get('id'));
          console.log("paciente guardado");
          // this.navCtrl.setRoot(TabsPage,{},{});
          // this.navCtrl.setRoot(HomePage,{},{});
          this.navCtrl.setRoot(TabsPage);
        // })).catch((Error)=>{
        //   this.alertaErrorIdentificacion();
        //   console.log(Error);
        //
        // });

      })).catch((Error)=>{
        this.alertaErrorAutenticacion();
        console.log(Error);
      });
    }

    alertaErrorIdentificacion() {
      let alert = this.alertCtrl.create({
        title: 'Advertencia',
        subTitle: 'Identificación invalida, intente nuevamente',
        buttons: ['Aceptar']
      });
      alert.present();
    }
    alertaErrorAutenticacion() {
      let alert = this.alertCtrl.create({
        title: 'Advertencia',
        subTitle: 'Credenciales invalidas, intente nuevamente',
        buttons: ['Aceptar']
      });
      alert.present();
    }

  }
