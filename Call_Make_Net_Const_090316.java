
import java.io.IOException;

/**
 *
 * @author pra
 */
public class Call_Make_Net_Const_090316 {

    public static void main(String pra[]) throws IOException
    {
      N = 100 //input the number of samples here
      for(int i = 0; i <= N; i = i+5)
      {
           boolean Is1Alive = true;
           boolean Is2Alive = true;
           boolean Is3Alive = true;
           boolean Is4Alive = true;
           boolean Is5Alive = true;

           Three_Make_Net_Const_090316 tmn1 = new Three_Make_Net_Const_090316(i+0);
           Three_Make_Net_Const_090316 tmn2 = new Three_Make_Net_Const_090316(i+1);
           Three_Make_Net_Const_090316 tmn3 = new Three_Make_Net_Const_090316(i+2);
           Three_Make_Net_Const_090316 tmn4 = new Three_Make_Net_Const_090316(i+3);
           Three_Make_Net_Const_090316 tmn5 = new Three_Make_Net_Const_090316(i+4);

           tmn1.start();
           tmn2.start();
           tmn3.start();
           tmn4.start();
           tmn5.start();

           do{

               if(Is1Alive && !tmn1.isAlive())
               {
                   Is1Alive = false;
                   //System.out.println("T" + i + " is dead");
               }
               if(Is2Alive && !tmn2.isAlive())
               {

                   Is2Alive = false;
                  // System.out.println("T" + (i+1) + " is dead");
               }
                if(Is3Alive && !tmn3.isAlive())
               {

                   Is3Alive = false;
                  // System.out.println("T" + (i+2) + " is dead");
               }
               if(Is4Alive && !tmn4.isAlive())
               {

                   Is4Alive = false;
                   // System.out.println("T" + (i+3) + " is dead");
               }
               if(Is5Alive && !tmn5.isAlive())
               {

                   Is5Alive = false;
                    //System.out.println("T" + (i+4) + " is dead");
               }

           }while(Is1Alive || Is2Alive || Is3Alive || Is4Alive || Is5Alive);

           System.out.println("\n");

       }

    }

}
