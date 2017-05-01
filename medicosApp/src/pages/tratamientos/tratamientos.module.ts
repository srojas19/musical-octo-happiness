import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { Tratamientos } from './tratamientos';

@NgModule({
  declarations: [
    Tratamientos,
  ],
  imports: [
    IonicPageModule.forChild(Tratamientos),
  ],
  exports: [
    Tratamientos
  ]
})
export class TratamientosModule {}
