import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { AlertController } from 'ionic-angular';

import { Storage } from '@ionic/storage';

import { HomePage } from '../pages/home/home';
import { ListPage } from '../pages/list/list';
import { Pacientes } from '../pages/pacientes/pacientes';


import {Autenticacion} from'../pages/autenticacion/autenticacion'



@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = Autenticacion;

  pages: Array<{title: string, component: any}>;

  constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen,
    public storage:Storage,public alertCtrl: AlertController ) {
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

    logOut(){
      this.storage.clear();
      this.nav.setRoot(Autenticacion);
    }

    openPage(page) {
      // Reset the content nav to have just this page
      // we wouldn't want the back button to show in this scenario
      this.nav.setRoot(page.component);
    }
    confirmacionLogOut() {
    let confirm = this.alertCtrl.create({
      title: '¿Desea cerrar sesión?',
      message: 'Al cerrar sesión tendra que intruducir sus credenciales nuevamente',
      buttons: [
        {
          text: 'Cancelar',
          handler: () => {
            console.log('Disagree clicked');
          }
        },
        {
          text: 'De acuerdo',
          handler: () => {
            this.logOut();
            console.log('Agree clicked');
          }
        }
      ]
    });
    confirm.present();
  }
  }
