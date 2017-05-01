import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import {CorazonRest} from'../../providers/corazon-rest'


/**
* Generated class for the Recomendaciones page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/

@Component({
  selector: 'page-recomendaciones',
  templateUrl: 'recomendaciones.html',
})
export class Recomendaciones {

  recomendaciones:any;

  constructor(public navCtrl: NavController, public navParams: NavParams,
    public rest:CorazonRest) {
    }

    ionViewDidLoad() {
      this.recomendaciones=this.navParams.get('recomencionesLista')
      console.log(this.recomendaciones);
      console.log('ionViewDidLoad Recomendaciones');
    }

  }
