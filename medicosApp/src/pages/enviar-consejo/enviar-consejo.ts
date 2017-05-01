import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ViewController } from 'ionic-angular';

/**
 * Generated class for the EnviarConsejo page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@Component({
  selector: 'page-enviar-consejo',
  templateUrl: 'enviar-consejo.html',
})
export class EnviarConsejo {

  authForm : FormGroup;
  date:any;
  constructor(public navCtrl: NavController, public navParams: NavParams,public fb: FormBuilder
  ,public viewCtrl: ViewController) {
   this.date = new Date().toISOString();

    this.authForm = fb.group({
      'descripcion' : [null, Validators.compose([Validators.required])],
      'tipo': [null, Validators.compose([Validators.required])],
      'fecha':this.date.split("T")[0]
    })
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad EnviarConsejo');
  }

  submitForm(){
    console.log('EnviarConsejo');
    console.log(this.authForm.value);
    this.viewCtrl.dismiss(this.authForm.value);
  }

  dismiss() {
    let data = { };
    this.viewCtrl.dismiss(data);
  }
}
