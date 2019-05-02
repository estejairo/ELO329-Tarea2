/**
 * Class to manage all the traffic lights
 */

public class Controller {
   private TrafficLight pedestrianPlaceres, pedestrianMatta, matta, placeresvalpo, placeresvina, giro;
   int currentGreenTime = 1;
   int currentYellowTime = 1;
   DetectorRequerimiento botonplaceres;
   DetectorRequerimiento botonmatta;
   DetectorRequerimiento sensorInductivo;
   boolean serving_botonplaceres = false;
   boolean serving_botonmatta = false;
   boolean serving_sensorInductivo = false;

   /**
    * Controller constructor
    * @param matta is the Matta Av. traffic light
    * @param pedestrianPlaceres is the Placeres Av. pedestrian traffic light
    * @param pedestrianMatta is the Matta Av. pedestrian traffic light
    * @param placeresvalpo is the Placeres Av. traffic light for cars going to valparaiso
    * @param placeresvina is the Placeres Av. traffic light for cars going to vina
    * @param giro is the traffic light for turning from Placeres Av. to Matta Av.
    * @param bottonplaceres is the button for pedestrians to press if they want to cross Placeres Av.
    * @param botonmatta is the button for pedestrians to press if they want to cross Matta Av.
    * @param sensorInductivo is the inductive sensor that detects cars wanting to turn from Placeres Av to Matta Av
    
    */
   public Controller(TrafficLight matta, TrafficLight pedestrianPlaceres, TrafficLight pedestrianMatta, TrafficLight placeresvalpo, TrafficLight placeresvina, TrafficLight giro, DetectorRequerimiento botonplaceres, DetectorRequerimiento botonmatta, DetectorRequerimiento sensorInductivo) {
      this.matta = matta;
      this.pedestrianPlaceres = pedestrianPlaceres;
      this.pedestrianMatta = pedestrianMatta;
      this.placeresvalpo = placeresvalpo;
      this.placeresvina = placeresvina;
      this.giro = giro;
      this.botonplaceres = botonplaceres;
      this.botonmatta = botonmatta;
      this.sensorInductivo = sensorInductivo;

      //Estado inicial
      pedestrianMatta.turnStop();
      pedestrianPlaceres.turnStop();
      matta.turnStop();
      placeresvalpo.turnFollow();
      placeresvina.turnFollow();
      giro.turnStop();
      sensorInductivo.setOff();
      botonmatta.setOff();
      botonplaceres.setOff();
   }  

  /**
   * Manages traffic lights according to requiremnts 
   * (if non, just regular timing)
   */ 
      public void manageTraffic(){
         while(true){
            if ( (currentGreenTime < placeresvina.getFollowTime()) && (placeresvina.getState()==TrafficLightState.FOLLOW) ){
               if (botonmatta.isOn()){
                  botonmatta.setOff();
                  serving_botonmatta = true;
                  pedestrianMatta.turnFollow();
                  currentGreenTime +=1;
               }
               else if(botonplaceres.isOn()){
                  currentGreenTime = placeresvina.getFollowTime();
               }
               else{
                  currentGreenTime += 1; //se mantiene sin cambio de estado, avanzando en el tiempo
               }
            }
            else if ((currentGreenTime == placeresvina.getFollowTime())&&(placeresvina.getState()==TrafficLightState.FOLLOW)){
               placeresvina.turnTransition();
               if(sensorInductivo.isOn()){
                  serving_sensorInductivo = true;
                  currentGreenTime = 1;
               }
               else{
                  placeresvalpo.turnTransition();
                  currentGreenTime = 1;
               }
            }
            else if ((currentYellowTime < placeresvina.getTransitionTime())&&(placeresvina.getState()==TrafficLightState.TRANSITION)){
               if (serving_botonmatta){
                  pedestrianMatta.turnTransition();
               }
               if (botonmatta.isOn()){
                  botonmatta.setOff();
                  currentYellowTime += 1;
               }
               else{
                  currentYellowTime += 1;
               }
            }
            else if ((currentYellowTime == placeresvina.getTransitionTime())&&(placeresvina.getState()==TrafficLightState.TRANSITION)){
               if ((botonplaceres.isOn())&&(!serving_sensorInductivo)){
                  botonplaceres.setOff();
                  serving_botonplaceres = true;
                  pedestrianPlaceres.turnFollow();
               }
               if (serving_botonmatta){
                  pedestrianMatta.turnStop();
                  serving_botonmatta = false;
                  }               
               if (botonmatta.isOn()){
                  botonmatta.setOff();
                  currentYellowTime = 1;
               }
               if(serving_sensorInductivo){
                  giro.turnFollow();
                  currentYellowTime = 1;
               }
               else{
                  matta.turnFollow();
                  placeresvalpo.turnStop();
                  currentYellowTime = 1;
               }
               placeresvina.turnStop();
            }

            ////Parte del semaforo de giro, ojo que se sincroniza con sem placeres a valpo
            else if ((currentGreenTime < giro.getFollowTime())&&(giro.getState()==TrafficLightState.FOLLOW)){
               currentGreenTime +=1;
            }
            else if ((currentGreenTime == giro.getFollowTime())&&(giro.getState()==TrafficLightState.FOLLOW)){
               giro.turnTransition();
               placeresvalpo.turnTransition();
               currentGreenTime = giro.getFollowTime()+1;
            }
            else if ((currentYellowTime < placeresvalpo.getTransitionTime())&&(placeresvalpo.getState()==TrafficLightState.TRANSITION)&&(serving_sensorInductivo)){
               currentYellowTime += 1;
            }
            else if ((currentYellowTime == placeresvalpo.getTransitionTime())&&(placeresvalpo.getState()==TrafficLightState.TRANSITION)&&(serving_sensorInductivo)){
               if (botonplaceres.isOn()){
                  botonplaceres.setOff();
                  serving_botonplaceres = true;
                  pedestrianPlaceres.turnFollow();
               }
               matta.turnFollow();
               placeresvalpo.turnStop();
               giro.turnStop();
               sensorInductivo.setOff();
               serving_sensorInductivo = false;
               currentGreenTime = 1;
               currentYellowTime = 1;
            }

            ///el de matta
            else if ((currentGreenTime < matta.getFollowTime()) && (matta.getState()==TrafficLightState.FOLLOW) ){
               if (botonplaceres.isOn()){
                  botonplaceres.setOff();
                  serving_botonplaceres = true;
                  pedestrianPlaceres.turnFollow();
                  currentGreenTime += 1;
               }
               else if (botonmatta.isOn()){
                  currentGreenTime = matta.getFollowTime();
               }
               else{
                  currentGreenTime += 1;
               }
            }
            else if ((currentGreenTime == matta.getFollowTime())&&(matta.getState()==TrafficLightState.FOLLOW)){
               matta.turnTransition();
               if (serving_botonplaceres){
                  pedestrianPlaceres.turnTransition();
               }
               currentGreenTime = 1;
            }
            else if ((currentYellowTime < matta.getTransitionTime())&&(matta.getState()==TrafficLightState.TRANSITION)){
               if (botonplaceres.isOn()){
                  botonplaceres.setOff();
                  currentYellowTime += 1;
               }
               else{
                  currentYellowTime += 1;
               }
            }
            else if ((currentYellowTime == matta.getTransitionTime())&&(matta.getState()==TrafficLightState.TRANSITION)){
               if(botonmatta.isOn()){
                  botonmatta.setOff();
                  serving_botonmatta = true;
                  pedestrianMatta.turnFollow();
               }
               if (serving_botonplaceres){
                  pedestrianPlaceres.turnStop();
                  serving_botonplaceres = false;
               }
               if (botonplaceres.isOn()){
                  botonplaceres.setOff();
                  currentYellowTime += 1;
               }
               matta.turnStop();
               placeresvina.turnFollow();
               placeresvalpo.turnFollow();
               currentYellowTime = 1;
            }
            else{
               System.out.println("Caimos en un estado fantasma del controlador de semáforos!");
            }
            //System.out.println(counter+"\t"+pedestrianPlaceres+"\t"+pedestrianMatta+"\t"+giro+"\t"+placeresvalpo+"\t"+placeresvina+"\t"+matta+"\t");      
            try{
               Thread.sleep(1000); //Si no hay requerimiento, se espera un segundo
               //counter = counter + 1;  //Se agrega el tiempo transcurrido al contador
            } catch(InterruptedException e){
               System.out.println(e);
         }    
      }

   }
}
