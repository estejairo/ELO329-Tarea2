public class DetectorRequerimiento{
    boolean state;

    public DetectorRequerimiento(){
        state=false;
    }
    public boolean isOn(){
        return state;
    }
    public void setOn(){
        state = true;
        //System.out.println("Se detecto un requerimiento!");

    }
    public void setOff(){
        state = false;
        //System.out.println("El requerimiento esta siendo atendido!");
    }
    public String toString(){
    	if(state){
		return ("1");
	    }
	    else{
		    return("0");
	    }
    }

    /** 
    Every method that changes an attribute that affects the
    object view must call repaint.
    This is an indirect and only way to call paintComponent 
    on this panel.
    */
}