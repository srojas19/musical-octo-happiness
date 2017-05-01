import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { Chart } from 'chart.js';

/**
* Generated class for the DetalleMediciones page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/
@Component({
  selector: 'page-detalle-mediciones',
  templateUrl: 'detalle-mediciones.html',
})
export class DetalleMediciones {

  ps: any;
  ne: any;
  fc: any;
  dictamen: any;
  mediciones:any;


  constructor(public navCtrl: NavController, public navParams: NavParams) {
    console.log(navParams);
    this.mediciones=navParams.get('medicionesLista')
  }

  ionViewDidLoad() {
    console.log(this.navParams);
    this.mediciones=this.navParams.get('medicionesLista')

    var ctxPs = document.getElementById("ps");
    var ctxNe = document.getElementById("ne");
    var ctxFc = document.getElementById("fc");
    var ctxDictamen = document.getElementById("dictamen");
    var labels=[];
    var dataPs=[];
    var dataNe=[];
    var dataFc=[];
    var rojos=0;
    var amarillos=0;
    var verdes=0;
    for (var i = 0; i < this.mediciones.length; i++){
      labels.push(this.mediciones[i].fecha);
      dataPs.push(this.mediciones[i].presionSanguinea);
      dataNe.push(this.mediciones[i].nivelEstres);
      dataFc.push(this.mediciones[i].frecuenciaCardiaca);
      switch(this.mediciones[i].dictamen) {
        case "Verde":
        verdes+=1;
        break;
        case "Amarillo":
        amarillos+=1;
        break;
        case "Rojo":
        rojos+=1;
        break;
        default:
      }
    }

    this.ps = new Chart(ctxPs, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: "Presión Sanguinea",
            fill: false,
            lineTension: 0.1,
            backgroundColor: "rgba(75,192,192,0.4)",
            borderColor: "rgba(75,192,192,1)",
            borderCapStyle: 'butt',
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: 'miter',
            pointBorderColor: "rgba(75,192,192,1)",
            pointBackgroundColor: "#fff",
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: "rgba(75,192,192,1)",
            pointHoverBorderColor: "rgba(220,220,220,1)",
            pointHoverBorderWidth: 2,
            pointRadius: 5,
            pointHitRadius: 10,
            data: dataPs,
            spanGaps: false,
          }
        ]
      }

    });

    this.ne = new Chart(ctxNe, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: "Nivel estrés",
            fill: false,
            lineTension: 0.1,
            backgroundColor: "rgba(111,120,63,0.4)",
            borderColor: "rgba(111,120,63,1)",
            borderCapStyle: 'butt',
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: 'miter',
            pointBorderColor: "rgba(111,120,63,1)",
            pointBackgroundColor: "#fff",
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: "rgba(111,120,63,1)",
            pointHoverBorderColor: "rgba(220,220,220,1)",
            pointHoverBorderWidth: 2,
            pointRadius: 5,
            pointHitRadius: 10,
            data: dataNe,
            spanGaps: false,
          }
        ]
      },

    });

    this.fc = new Chart(ctxFc, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: "Frecuencia cardiaca",
            fill: false,
            lineTension: 0.1,
            backgroundColor: "rgba(255,100,120,0.4)",
            borderColor: "rgba(255,100,120,1)",
            borderCapStyle: 'butt',
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: 'miter',
            pointBorderColor: "rgba(255,100,120,1)",
            pointBackgroundColor: "#fff",
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: "rgba(255,100,120,1)",
            pointHoverBorderColor: "rgba(220,220,220,1)",
            pointHoverBorderWidth: 2,
            pointRadius: 5,
            pointHitRadius: 10,
            data: dataFc,
            spanGaps: false,
          }
        ]
      }

    });
    this.dictamen = new Chart(ctxDictamen, {
      type: 'doughnut',
      data: {
        labels:["Rojo", "Amarillo", "Verde"],
        datasets: [{
          label: 'Distribución dictamenes',
          data: [rojos,amarillos, verdes],
          backgroundColor: [
            'rgba(255, 98, 51, 1)',
            'rgba(255, 249, 51, 1)',
            'rgba(51, 255, 79, 1)'
          ]
        }]
      }

    });
    console.log('ionViewDidLoad DetalleMediciones');
  }

}
