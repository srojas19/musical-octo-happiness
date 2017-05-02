import { Component } from '@angular/core';

import { NavController, NavParams } from 'ionic-angular';

import { ListPage } from '../list/list';

@Component({
  selector: 'page-historia-clinica',
  templateUrl: 'historia-clinica.html'
})
export class HistoriaClinicaPage {
  icons: string[];
  items: Array<{title: string, icon: string}>;
  titles: string[];

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.icons = ['heart', 'flask', 'body', 'paper'];
    this.titles =['Mediciones', 'Tratamientos','Examenes','Diagnosticos']

    this.items = [];
    for(let i = 0; i < 4; i++) {
      this.items.push({
        title: this.titles[i],
        icon: this.icons[i]
      });
    }
  }

  itemTapped(event, item) {
    this.navCtrl.push(ListPage, {
      item: item
    });
  }
}
