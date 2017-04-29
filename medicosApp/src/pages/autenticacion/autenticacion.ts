import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ViewController } from 'ionic-angular';

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
  constructor(public navCtrl: NavController, public navParams: NavParams,fb: FormBuilder,public viewCtrl: ViewController) {
    this.authForm = fb.group({
      'username' : [null, Validators.compose([Validators.required])],
      'password' : [null, Validators.compose([Validators.required])],
      'levelAccess':"user"
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad Autenticacion');
  }



  submitForm():void{
    console.log("inicio de sesion");
    this.viewCtrl.dismiss(this.authForm.value);
  }

}
