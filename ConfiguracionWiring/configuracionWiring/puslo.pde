 /* Sensor de temperatura Directo
 * ------------------ 

 */
 #include <string.h>
 // Selecciona el pin de entrada analoga a leer.
 int pulso = 28;
 // variable para guardar el valor del sensor de temperatura.
 int raw = 0;

 // variable temporal 
 bool inicio=false;

 int p1 = 0;
 int t1 = 0;
 int p2 = 0;
 int t2 = 0;
 int p3 = 0;
 int t3 = 0;
 int centinela=0;
 int tCentinela=0;
 bool encontroP1=false;
 bool encontroP2=false;
 bool encontroP3=false;
 // preparacion del proceso
 void setupPulso() { 
   // Abre puerto serial y lo configura a 9600 bps
    // Serial.begin(9600);
     // se fija la unidad de trabajo del sensor de temperatura.
 }
 // ejecuta el procesamiento del sensor
 void loopPulso() {
   // lee el valor del sensor de temperatura en Volts
   raw = analogRead(pulso);
   if(raw==0){
 //  Serial.print("Iniciando");
   medicionArray[2]="0";
   inicio=true;
         p1=0;
         p2=0;
         p3=0;
         t3=0;
         t1=0;
         t2=0;
         tiempo=0;
         tCentinela=0;
         encontroP1=false;
         encontroP2=false;
         encontroP3=false; 
        centinela=0;  
   }
   else if(raw<=200||raw>600){
     
     medicionArray[2]="-1";
     tiempo=0;
  //  Serial.print("No Contacto");
  //  Serial.println("");
   
   }
   else{
     if (raw>200 && raw<600&&inicio){     
       if(raw>=centinela){
         centinela =raw;
         tCentinela=tiempo;
      //   Serial.print(String(raw));
       }
       else if(raw+2<centinela&&encontroP1==false&&inicio){
         p1=centinela;
         t1=tCentinela;
         centinela=raw;
         encontroP1=true; 
       }
       else if(raw+2<centinela&&encontroP1==true&&encontroP2==false&&inicio){
         if(tiempo-t1>=300){
         p2=centinela;
         t2= tCentinela;
         centinela=raw;
         encontroP2=true;
         }
       } else if(raw+2<centinela&&encontroP1==true&&encontroP2==true&&encontroP3==false&&inicio){
         if(tiempo-t1>=500&&centinela>=p2-1){
         p3=centinela;
         t3= tCentinela;
         centinela=raw;
         tCentinela=tiempo;
         encontroP3=true;
         }
       } 
     }
     if(encontroP1==true&&encontroP2==true&&encontroP3==true&&inicio){
   
       int resultado = 60000/(t3-t1);
       if(resultado > 40 &&resultado <190){
       // se transforma el dato int de temperatura a un char
       String bpm = String(resultado);
       medicionArray[2]=bpm;
       // Envia los datos uno por uno del arreglo del sensor por puerto serial
     //   Serial.print(bpm);     
       // final de la trama de datos
   //    Serial.println("");
       t1=0;
       p1=p3;
       tiempo=0;
       encontroP2=false;
       encontroP3=false;
       }
       else{
         p1=0;
         p2=0;
         p3=0;
         t3=0;
         t1=0;
         t2=0;
         tiempo=0;
         tCentinela=0;
         centinela=0;
         encontroP1=false;
         encontroP2=false;
         encontroP3=false;        
       }
     }
   }
 }
