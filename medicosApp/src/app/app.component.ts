import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { ModalController} from 'ionic-angular';

import { Storage } from '@ionic/storage';

import { HomePage } from '../pages/home/home';
import { ListPage } from '../pages/list/list';
import { Pacientes } from '../pages/pacientes/pacientes';
import {Autenticacion} from'../pages/autenticacion/autenticacion'
import {CorazonRest} from'../providers/corazon-rest'

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = HomePage;

  autenticado:boolean=false;

  pages: Array<{title: string, component: any}>;

  constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen,
    public storage:Storage, public modalCtrl: ModalController, public rest:CorazonRest) {
      if(this.autenticado==false){
        //mostrar login
        let loginModal = this.modalCtrl.create(Autenticacion);
        loginModal.onDidDismiss(data => {
          console.log("justo antes de entrar hacer autenticacion");
          console.log(data);

          if(data.username!==undefined){
            rest.getToken(data).then((token =>{
              this.storage.set('token',token);
              console.log("token guardado");
              this.storage.get('token').then((val)=>{
                console.log(val);
              });
            })).catch((Error)=>{
              console.log("no se pudo ");
              console.log(Error);
            });
          }
        });
        loginModal.present();
      }
      this.initializeApp();

      // used for an example of ngFor and navigation
      this.pages = [
        { title: 'Inicio', component: HomePage },
        { title: 'Pacientes', component:Pacientes },
        { title: 'List', component: ListPage }
      ];

    }

    initializeApp() {
      this.platform.ready().then(() => {
        // Okay, so the platform is ready and our plugins are available.
        // Here you can do any higher level native things you might need.
        this.statusBar.styleDefault();
        this.splashScreen.hide();
      });
    }

    openPage(page) {
      // Reset the content nav to have just this page
      // we wouldn't want the back button to show in this scenario
      this.nav.setRoot(page.component);
    }
  }
