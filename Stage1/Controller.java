public class Controller {
   public Controller(TrafficLight m, TrafficLight p, TrafficLight plac) {
      matta = m;
      pedestrian = p;
      placeres = plac;
   }
   public void manageTraffic(){
      try {
          while (true) {
            //Rutina Semaforo Mata y peatonal placeres
            matta.turnFollow();
            pedestrian.turnFollow();
            Thread.sleep(matta.getFollowTime()*1000);
            matta.turnTransition();
            pedestrian.turnTransition();
            Thread.sleep(matta.getTransitionTime()*1000);
            matta.turnStop();
            pedestrian.turnStop();
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
   private TrafficLight pedestrian, matta,placeres;
}
