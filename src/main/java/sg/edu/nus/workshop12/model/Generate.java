package sg.edu.nus.workshop12.model;

import java.io.Serializable;

public class Generate implements Serializable{
        private int numberVal;
        
        public void setNumberVal(int numberVal){
                this.numberVal = numberVal;
        }

        public int getNumberVal(){
                return this.numberVal;
        }
}
