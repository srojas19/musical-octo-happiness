import { Component } from '@angular/core';

import { NavController, NavParams } from 'ionic-angular';

// import { IonicStorageModule } from '@ionic/storage';

import { Storage } from '@ionic/storage';

import { AboutPage } from '../about/about';
import { ContactPage } from '../contact/contact';
import { HomePage } from '../home/home';
import { HistoriaClinicaPage } from '../historia-clinica/historia-clinica';
import { Autenticacion } from '../autenticacion/autenticacion';




@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = HomePage;
  tab2Root = HistoriaClinicaPage;
  tab3Root = ContactPage;

//   constructor(public navCtrl: NavController, public storage: Storage) {
//     this.storage.set('test','test');
//     let user = this.storage.get('test');
//     console.log(user);
//     if (!user) {
//         navCtrl.setRoot(HistoriaClinicaPage);
//       };
//   }
// }
  constructor() {

  }
}
