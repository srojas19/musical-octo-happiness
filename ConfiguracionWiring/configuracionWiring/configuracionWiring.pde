 /* Sensor de temperatura Directo
 * ------------------ 
 * Laboratorio IoT para Arquitectura de Software 2016-20
 * Universidad de los Andes
 * 
 * el ejemplo lee una entrada analoga del sensor de temperatura y
 * la transmite por el puerto serial como un string cada segundo.
 * 
 * creado el 18 de julio de 2016
 */
 #include <string.h>
 // Selecciona el pin de entrada analoga a leer.
 int tempPin = 24;
 int movPin = 26;
 int tiempo=0;
 int segundos=0;
 // variable para guardar el valor del sensor de temperatura.
 int tempC = 0;
 int movimiento = 0;
 // variable para la unidad de temperatura C-Celsius, K- Kelvin, F-Fahrenheit.
 // arreglo de chars para envio final del dato del sensor.
 String medicionArray[3] = {"0","0","0"};
 // variable temporal de conteo
 int i = 0;
 // preparacion del proceso
 void setup() { 
   // Abre puerto serial y lo configura a 9600 bps
     Serial.begin(9600);  
     // se fija la unidad de trabajo del sensor de temperatura.
 }
 // ejecuta el procesamiento del sensor
 void loop() {
   loopPulso();
   // lee el valor del sensor de temperatura en Volts
  tempC = analogRead(tempPin);
   // Convierte el valor a temperatura a grados centigrados y de analogo a digital
   // DEPENDE DEL SENSOR, REVISAR DATASHEET
  tempC = (4.9 * tempC * 100.0)/1024.0;
   // se transforma el dato int de temperatura a un char
  medicionArray[0] = String(tempC);
  
  movimiento=analogRead(movPin);
  medicionArray[1]= String(movimiento);
  
   // Envia los datos uno por uno del arreglo del sensor por puerto serial
  if(tiempo%1000==0){
  for (i=0; i<3; i++){
     // imprime el elemento del arreglo por el puerto serial
     Serial.print(medicionArray[i]);
     // espacio para diferenciar elementos en el arreglo
     if (i < 2){
       Serial.print("\t");      
     }
   }
    // final de la trama de datos
   Serial.println("");
  }
  
   // espera 1 segundo para repetir el procedimiento
     
   tiempo+=25;
   delay(25);
   // Serial.println(tempArray[0]);
 }
