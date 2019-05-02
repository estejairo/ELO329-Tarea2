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

   public Controller(TrafficLight matta, TrafficLight pedestrianPlaceres, TrafficLight pedestrianMatta, TrafficLight placeresvalpo, TrafficLight placeresvina, TrafficLight giro, DetectorRequerimiento botonplaceres, DetectorRequerimiento botonmatta, DetectorRequerimiento sensorInductivo) {
      this.matta = matta;
      this.pedestrianPlaceres = pedestrianPlaceres;
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
      giro.turnFollow();
      sensorInductivo.setOff();
      botonmatta.setOff();
      botonplaceres.setOff();
   }   
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
               pedestrianMatta.turnTransition();
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
               currentYellowTime += 1;
            }
            else if ((currentYellowTime == placeresvina.getTransitionTime())&&(placeresvina.getState()==TrafficLightState.TRANSITION)){
               if ((botonplaceres.isOn())&&(!serving_sensorInductivo)){
                  botonplaceres.setOff();
                  serving_botonplaceres = true;
                  pedestrianPlaceres.turnFollow();
               }
               pedestrianMatta.turnStop();
               serving_botonmatta = false;
               placeresvina.turnStop();
               
               if(serving_sensorInductivo){
                  giro.turnFollow();
                  currentYellowTime = 1;
               }
               else{
                  matta.turnFollow();
                  placeresvalpo.turnStop();
                  currentYellowTime = 1;
               }
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
               if (botonplaceres.isOn()&&(!serving_sensorInductivo)){
                  botonplaceres.setOff();
                  serving_botonplaceres = true;
                  pedestrianPlaceres.turnFollow();
               }
               matta.turnFollow();
               placeresvalpo.turnStop();
               giro.turnStop();
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
               pedestrianPlaceres.turnTransition();
               currentGreenTime = 1;
            }
            else if ((currentYellowTime < matta.getTransitionTime())&&(matta.getState()==TrafficLightState.TRANSITION)){
               currentYellowTime += 1;
            }
            else if ((currentYellowTime == matta.getTransitionTime())&&(matta.getState()==TrafficLightState.TRANSITION)){
               if(botonmatta.isOn()){
                  botonmatta.setOff();
                  serving_botonmatta = true;
                  pedestrianMatta.turnFollow();
               }
               pedestrianPlaceres.turnStop();
               serving_botonplaceres = false;
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
