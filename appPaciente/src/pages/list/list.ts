import { Component } from '@angular/core';

import { NavController, NavParams } from 'ionic-angular';

import * as data from '../../providers/data';
import {AppServices} from '../../providers/data.services';

import { ItemDetailsPage } from '../item-details/item-details';

@Component({
  selector: 'page-list',
  templateUrl: 'list.html'
})
export class ListPage {
  icons: string[];
  items = [];
  selectedItem: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, public services: AppServices) {
    this.selectedItem = navParams.get('item');
    // this.icons = ['flask', 'wifi', 'beer', 'football', 'basketball', 'paper-plane',
    // 'american-football', 'boat', 'bluetooth', 'build'];

    if(this.selectedItem.title=='Mediciones'){
      this.services.getMediciones().then(items => this.items = items);
    }
    else if(this.selectedItem.title=='Tratamientos'){
      this.services.getTratamientos().then(items => this.items = items);
    }
    else if(this.selectedItem.title=='Examenes'){
      this.services.getExamenes().then(items => this.items = items);
    }
    else if(this.selectedItem.title=='Diagnosticos'){
      this.services.getDiagnosticos().then(items => this.items = items);
    }
  }

  itemTapped(event, item) {
    this.navCtrl.push(ItemDetailsPage, {
      tipo: this.selectedItem.title,
      item: item
    });
  }
}
