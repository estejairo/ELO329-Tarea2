public class Controller {
   private TrafficLight pedestrianPlaceres, matta,placeresvalpo;
   int currentGreenTime =1;
   int currentYellowTime = 1;
   DetectorRequerimiento botonplaceres;
   boolean serving_botonplaceres = false;

   public Controller(TrafficLight matta, TrafficLight pedestrianPlaceres, TrafficLight placeresvalpo, DetectorRequerimiento botonplaceres) {
      this.matta = matta;
      this.pedestrianPlaceres = pedestrianPlaceres;
      this.placeresvalpo = placeresvalpo;
      this.botonplaceres = botonplaceres;
      matta.turnFollow();
   }
   public void manageTraffic(){
      while(true){
         if ( (currentGreenTime < placeresvalpo.getFollowTime()) && (placeresvalpo.getState()==TrafficLightState.FOLLOW) ){
            if(botonplaceres.isOn()){
               currentGreenTime = placeresvalpo.getFollowTime();
            }
            else{
               currentGreenTime += 1; //se mantiene sin cambio de estado, avanzando en el tiempo
            }

         }
         else if ((currentGreenTime == placeresvalpo.getFollowTime())&&(placeresvalpo.getState()==TrafficLightState.FOLLOW)){
            placeresvalpo.turnTransition();
            currentGreenTime = 1;
         }
         else if ((currentYellowTime < placeresvalpo.getTransitionTime())&&(placeresvalpo.getState()==TrafficLightState.TRANSITION)){
            currentYellowTime += 1;
         }
         else if ((currentYellowTime == placeresvalpo.getTransitionTime())&&(placeresvalpo.getState()==TrafficLightState.TRANSITION)){
            if ((botonplaceres.isOn())){
               botonplaceres.setOff();
               serving_botonplaceres = true;
               pedestrianPlaceres.turnFollow();
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
            currentYellowTime += 1;
         }
         else if ((currentYellowTime == matta.getTransitionTime())&&(matta.getState()==TrafficLightState.TRANSITION)){
            pedestrianPlaceres.turnStop();
            serving_botonplaceres = false;
            matta.turnStop();
            placeresvalpo.turnFollow();
            currentYellowTime = 1;
         }
         else{
            System.out.println("Caimos en un estado fantasma del controlador de semáforos!");
         }
         //System.out.println(counter+"\t"+pedestrianPlaceres+"\t"+pedestrianMatta+"\t"+giro+"\t"+placeresvalpo+"\t"+placeresvalpo+"\t"+matta+"\t");      
         try{
            Thread.sleep(1000); //Si no hay requerimiento, se espera un segundo
            //counter = counter + 1;  //Se agrega el tiempo transcurrido al contador
         } catch(InterruptedException e){
            System.out.println(e);
        }    
      }
   }
}
