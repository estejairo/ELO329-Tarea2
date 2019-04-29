public class Controller {
   private TrafficLight pedestrian, matta,placeres;
   int currentGreenTime,currentYellowTime={1,1};
   DetectorRequerimiento botonplaceres;
   boolean serving_botonplaceres = false;

   public Controller(TrafficLight m, TrafficLight p, TrafficLight plac,DetectorRequerimientob btnplac) {
      matta = m;
      pedestrian = p;
      placeres = plac;
      botonplaceres = btnplac;
   }
   public void manageTraffic(){
      int counter = 0;
      try {
         while (true) {
            if((matta.getState()==TrafficLightState.FOLLOW)&&(currentGreenTime<matta.getFollowTime()){
               if(botonplaceres.isOn()){
                  botonplaceres.setOff();
                  serving_botonplaceres = true;
                  currentGreenTime=matta.getFollowTime();
                  matta.turnTransition();
               }
               else{
                  currentGreenTime+=1;
               }
            else if((matta.getState()==TrafficLightState.TRANSITION)&& currentYellowTime<matta.getTransitionTime()){

            }
            }






            //rutina Semaforo peatonal
            pedestrian.turnFollow();
            Thread.sleep(pedestrian.getFollowTime()*1000);
            pedestrian.turnTransition();
            Thread.sleep(pedestrian.getTransitionTime()*1000);
            pedestrian.turnStop();
            //Rutina Semaforo Mata
            if((currentGreenTime<matta.getFollowTime())&&(matta.getState()==TrafficLightState.FOLLOW)){
               if((botonplaceres.isOn()){
                  botonplaceres.setOff();
                  serving_botonplaceres = true;
                  placeres.turnFollow();
                  currentGreenTime += 1;
               }
               else if((botonmata.isOn())&&(currentGreenTime>(mata.getFollowTime()*0.5))){
                  currentGreenTime=mata.getFollowTime();
               }
               else{
                  currentGreenTime += 1;
               }
            }
            else if((currentGreenTime == mata.getFollowTime)&&(mata.getState()==TrafficLightState.FOLLOW)){

            }
            Thread.sleep(matta.getFollowTime()*1000);
            matta.turnTransition();
            Thread.sleep(matta.getTransitionTime()*1000);
            matta.turnStop();
            //Rutina Semaforo Placeres
            placeres.turnFollow();
            Thread.sleep(placeres.getFollowTime()*1000);
            placeres.turnTransition();
            Thread.sleep(placeres.getTransitionTime()*1000);
            placeres.turnStop();

         }   
     } catch (InterruptedException e){
        System.out.println(e);
     }
   }
}
