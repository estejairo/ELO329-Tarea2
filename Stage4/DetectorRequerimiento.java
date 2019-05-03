/**
   @version 1.0 2019-05-03
   @author Paula Amigo
   @author Luis Bahamondes
   @author Jairo Gonzalez
*/

/**
 * Class to manage requirements
 * (from requeriments detector buttons)
 */
public class DetectorRequerimiento{
    boolean state;

    /**
     * DetectorRequerimiento constructor
     */
    public DetectorRequerimiento(){
        state=false;
    }

    /**
     * Gets the state of the requirement detector
     * returns <code> boolean </code> representing the state of the requirement
     */

    public boolean isOn(){
        return state;
    }

    /**
     * Changes the state of the requirement to on when one is detected
     */
    public void setOn(){
        state = true;
        //System.out.println("Se detecto un requerimiento!");

    }

    /**
     * Changes the state of the requirement to off when one is detected
     */
    public void setOff(){
        state = false;
        //System.out.println("El requerimiento esta siendo atendido!");
    }

    /**
     * Convert state to string
     * @return a <code> string </code> representing the state of the requirement detector
     */
    public String toString(){
    	if(state){
		return ("1");
	    }
	    else{
		    return("0");
	    }
    }
}
