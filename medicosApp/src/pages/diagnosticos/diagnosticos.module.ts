import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { Diagnosticos } from './diagnosticos';

@NgModule({
  declarations: [
    Diagnosticos,
  ],
  imports: [
    IonicPageModule.forChild(Diagnosticos),
  ],
  exports: [
    Diagnosticos
  ]
})
export class DiagnosticosModule {}
