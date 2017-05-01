import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { Examenes } from './examenes';

@NgModule({
  declarations: [
    Examenes,
  ],
  imports: [
    IonicPageModule.forChild(Examenes),
  ],
  exports: [
    Examenes
  ]
})
export class ExamenesModule {}
