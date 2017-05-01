import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { ListPage } from '../pages/list/list';
import { Pacientes } from '../pages/pacientes/pacientes';
import { Autenticacion} from '../pages/autenticacion/autenticacion';
import { Recomendaciones} from '../pages/recomendaciones/recomendaciones';

import { DetallePaciente} from '../pages/detalle-paciente/detalle-paciente';
import {DetalleMediciones} from'../pages/detalle-mediciones/detalle-mediciones';
import {EnviarConsejo} from'../pages/enviar-consejo/enviar-consejo';

import { IonicStorageModule } from '@ionic/storage';


import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import {CorazonRest} from '../providers/corazon-rest'
import { HttpModule } from '@angular/http';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    ListPage,
    Pacientes,
    Autenticacion,
    DetallePaciente,
    DetalleMediciones,
    Recomendaciones,
    EnviarConsejo
  ],
  imports: [
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot()
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    ListPage,
    Pacientes,
    Autenticacion,
    DetallePaciente,
    DetalleMediciones,
    Recomendaciones,
    EnviarConsejo
  ],
  providers: [
    StatusBar,
    SplashScreen,
    CorazonRest,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
