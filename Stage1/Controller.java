public class Controller {
   public Controller(TrafficLight m, TrafficLight p, TrafficLight plac) {
      matta = m;
      pedestrian = p;
      placeres = plac;
   }
   public void manageTraffic(){
      try {
          while (true) {
            //Rutina Semaforo Mata
            matta.turnFollow();
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
            //rutina Semaforo peatonal
            pedestrian.turnFollow();
            Thread.sleep(pedestrian.getFollowTime()*1000);
            pedestrian.turnTransition();
            Thread.sleep(pedestrian.getTransitionTime()*1000);
            pedestrian.turnStop();
         }   
     } catch (InterruptedException e){
        System.out.println(e);
     }
   }
   private TrafficLight pedestrian, matta,placeres;
}
