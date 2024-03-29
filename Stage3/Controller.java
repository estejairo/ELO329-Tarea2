public class Controller {
   private TrafficLight pedestrianPlaceres, matta, placeresvalpo, giro;
   int currentGreenTime = 1;
   int currentYellowTime = 1;
   DetectorRequerimiento botonplaceres;
   DetectorRequerimiento sensorInductivo;
   boolean serving_botonplaceres = false;
   boolean serving_sensorInductivo = false;

   public Controller(TrafficLight matta, TrafficLight pedestrianPlaceres, TrafficLight placeresvalpo, TrafficLight giro, DetectorRequerimiento botonplaceres, DetectorRequerimiento sensorInductivo) {
      this.matta = matta;
      this.pedestrianPlaceres = pedestrianPlaceres;
      this.placeresvalpo = placeresvalpo;
      this.giro = giro;
      this.botonplaceres = botonplaceres;
      this.sensorInductivo = sensorInductivo;

      //Estado inicial
      pedestrianPlaceres.turnStop();
      matta.turnStop();
      placeresvalpo.turnFollow();
      giro.turnStop();
      sensorInductivo.setOff();   
      botonplaceres.setOff();
   }
   public void manageTraffic(){
      while(true){
         if ( (currentGreenTime < placeresvalpo.getFollowTime()) && (placeresvalpo.getState()==TrafficLightState.FOLLOW) ){
            if(botonplaceres.isOn()){
               currentGreenTime = placeresvalpo.getFollowTime();
            }
            else if(sensorInductivo.isOn()){
               sensorInductivo.setOff();
               serving_sensorInductivo = true;
               giro.turnFollow();
               currentGreenTime += 1;
            }
            else{
               currentGreenTime += 1; //se mantiene sin cambio de estado, avanzando en el tiempo
            }

         }
         else if ((currentGreenTime == placeresvalpo.getFollowTime())&&(placeresvalpo.getState()==TrafficLightState.FOLLOW)){
            if(serving_sensorInductivo){
               giro.turnTransition();
               placeresvalpo.turnTransition();
               currentGreenTime = 1;
            }
            else{
               placeresvalpo.turnTransition();
               currentGreenTime = 1;
            }
         }
         else if ((currentYellowTime < placeresvalpo.getTransitionTime())&&(placeresvalpo.getState()==TrafficLightState.TRANSITION)){
               if(sensorInductivo.isOn()){
               sensorInductivo.setOff();
               currentYellowTime += 1;
            }
            else{
               currentYellowTime += 1;
            }
         }
         else if ((currentYellowTime == placeresvalpo.getTransitionTime())&&(placeresvalpo.getState()==TrafficLightState.TRANSITION)){
            if ((botonplaceres.isOn())){
               botonplaceres.setOff();
               serving_botonplaceres = true;
               pedestrianPlaceres.turnFollow();
            }
            if(serving_sensorInductivo){
               giro.turnStop();
               serving_sensorInductivo=false;
            }
            placeresvalpo.turnStop();
            matta.turnFollow();
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
            pedestrianPlaceres.turnStop();
            serving_botonplaceres = false;
            matta.turnStop();
            placeresvalpo.turnFollow();
            currentYellowTime = 1;
            if(sensorInductivo.isOn()){
               sensorInductivo.setOff();
               serving_sensorInductivo = true;
               giro.turnFollow();
            }
         }
         else{
            System.out.println("Caimos en un estado fantasma del controlador de semáforos!");
         }
         try{
            Thread.sleep(1000); //Si no hay requerimiento, se espera un segundo
         } catch(InterruptedException e){
            System.out.println(e);
        }    
      }

   }
}
