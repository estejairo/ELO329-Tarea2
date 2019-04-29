public class Controller {
   private TrafficLight pedestrian, matta,placeres;
   int currentGreenTime =1;
   int currentYellowTime = 1;
   DetectorRequerimiento botonplaceres;
   boolean serving_botonplaceres = false;

   public Controller(TrafficLight m, TrafficLight p, TrafficLight plac, DetectorRequerimiento btnplac) {
      matta = m;
      pedestrian = p;
      placeres = plac;
      botonplaceres = btnplac;
      matta.turnFollow();
   }
   public void manageTraffic(){
      int counter = 0;
      while (true) {
         //Semaforo Matta
         //Verde
         if((matta.getState()==TrafficLightState.FOLLOW)&&(currentGreenTime<matta.getFollowTime())){
            if(botonplaceres.isOn()){
               botonplaceres.setOff();
               serving_botonplaceres = true;
               currentGreenTime=matta.getFollowTime();
               matta.turnTransition();
            }
            else{
               placeres.turnStop();
               currentGreenTime+=1;
            }
         }
         else if((matta.getState()==TrafficLightState.FOLLOW)&& (currentGreenTime>=matta.getFollowTime())){
            matta.turnTransition();
            currentGreenTime = 1;
         }
         //Amarillo
         else if((matta.getState()==TrafficLightState.TRANSITION)&& currentYellowTime<matta.getTransitionTime()){
            placeres.turnStop();
            currentYellowTime+=1;
         }
         //Amarillo a Rojo
         else if((matta.getState()==TrafficLightState.TRANSITION)&& currentYellowTime>=matta.getTransitionTime()){
            if(serving_botonplaceres){
               matta.turnStop();
               pedestrian.turnFollow();
               currentYellowTime=1;
            }
            else{
               currentYellowTime=1;
               matta.turnStop();
               placeres.turnFollow();
            }
         }
         
         //Peatonal
         else if((pedestrian.getState()==TrafficLightState.FOLLOW)&&currentGreenTime<pedestrian.getFollowTime()){
            placeres.turnStop();
            matta.turnStop();
            currentGreenTime+=1;
         }
         else if((pedestrian.getState()==TrafficLightState.FOLLOW)&&currentGreenTime>=pedestrian.getFollowTime()){
            pedestrian.turnTransition();
            currentGreenTime=1;
         }
         else if((pedestrian.getState()==TrafficLightState.TRANSITION)&&currentYellowTime<pedestrian.getTransitionTime()){
            currentYellowTime+=1;
         }
         else if((pedestrian.getState()==TrafficLightState.TRANSITION)&&currentYellowTime>=pedestrian.getTransitionTime()){
            pedestrian.turnStop();
            currentYellowTime=1;
            placeres.turnFollow();
            serving_botonplaceres=false;
         }
         //Semaforo Placeres
         else if((placeres.getState()==TrafficLightState.FOLLOW)&&(currentGreenTime<placeres.getFollowTime())&&(!serving_botonplaceres)){
            if(botonplaceres.isOn()){
               botonplaceres.setOff();
               serving_botonplaceres = true;
               currentGreenTime=placeres.getFollowTime();
               placeres.turnTransition();
            }
            else{
               matta.turnStop();
               currentGreenTime+=1;
            }
         }
         else if((placeres.getState()==TrafficLightState.FOLLOW)&&(currentGreenTime>=placeres.getFollowTime())){
            placeres.turnTransition();
            currentGreenTime = 1;
         }          
         //Amarillo
         else if((placeres.getState()==TrafficLightState.TRANSITION)&& (currentYellowTime<placeres.getTransitionTime())){
            matta.turnStop();
            currentYellowTime+=1;
         }
         //Amarillo a Rojo
         else if((placeres.getState()==TrafficLightState.TRANSITION)&& currentYellowTime>=placeres.getTransitionTime()){
            placeres.turnStop();
            matta.turnFollow();
         }
         else{
            System.out.println("Caimos en un estado fantasma :C");
         }
         try{
            Thread.sleep(1000); //Si no hay requerimiento, se espera un segundo
            counter = counter + 1;  //Se agrega el tiempo transcurrido al contador
         }
         catch(InterruptedException e){
            System.out.println(e);
         }            
      }
   }
}
