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
 
   }
}
