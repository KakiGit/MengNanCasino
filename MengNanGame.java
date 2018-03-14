import java.util.ArrayList;
import java.util.Random;

import javax.lang.model.util.ElementScanner6;



public class MengNanGame {
    public static void main(String[] args) {
        int gamePlayNumber = 100000, mengNan1WinCount=0, mengNan2WinCount=0;
        MengNan mengNan1;
        MengNan mengNan2;
        for(int i=0; i<gamePlayNumber; i++)
        {
            String winner;
            mengNan1 = new MengNan();
            mengNan2 = new MengNan();
            mengNan1.assignMengNan(mengNan2);
            mengNan2.assignMengNan(mengNan1);
            while(checkWinner(mengNan1, mengNan2)=="none")
            {
                if(mengNan1.diceNum>0)
                mengNan1.rollDices();
                if(mengNan2.diceNum>0)
                mengNan2.rollDices();
            }
            winner = checkWinner(mengNan1, mengNan2);            
            if(winner=="mengNan1")
            mengNan1WinCount++;
            if(winner=="mengNan2")
            mengNan2WinCount++;  
        }   
        System.out.println("MengNan1 win "+mengNan1WinCount+" times!");
        System.out.println("MengNan2 win "+mengNan2WinCount+" times!");
    
}

    static void showStatics(MengNan mengNan)
    {
        System.out.println("MengNan health "+mengNan.healthPoint);
        System.out.println("MengNan shield "+mengNan.shieldPoint);      
    }

    static String checkWinner(MengNan mengNan1, MengNan mengNan2){

        String winner = "none";

        if(mengNan1.state==true)
        winner = "mengNan2";
        if(mengNan2.state==true)
        winner = "mengNan1";

        if(mengNan1.diceNum==0&&mengNan2.diceNum==0)
               {
                   if(Math.max(mengNan1.healthPoint, mengNan2.healthPoint)==mengNan1.healthPoint)
                   winner = "mengNan1";
                   else
                   winner = "mengNan2";
               }
        return winner;
    }

}


class MengNan {
    int diceNum=15;
    int healthPoint = 10;
    int shieldPoint = 0;
    String [] diceCate = {"skull", "shield", "sword","null"};
    boolean state = false;
    MengNan opponent;


    void assignMengNan (MengNan mengNan) {
        opponent = mengNan;
    }

    void rollDices ()
    {
        ArrayList<String> arrayList = new ArrayList<String>();

        int rollDiceNum;
        if(diceNum>5)
        rollDiceNum = 5;
        else
        rollDiceNum = diceNum;
        

        for(int i = 0; i < rollDiceNum; i++)
        {
           arrayList.add(diceCate[ (int) (4 * Math.random())]);
        }
        while(arrayList.indexOf("null")!=-1)
        {
            arrayList.remove("null");
        }

        while(arrayList.indexOf("shield")!=-1)
        {
            shieldPoint++;
            arrayList.remove("shield");
            diceNum--;   
        }
        
        while(arrayList.indexOf("skull")!=-1)
        {
            if(shieldPoint==0)
            healthPoint--;
            else
            shieldPoint--;
            arrayList.remove("skull");
            diceNum--;
            checkHealth(this);
        }

        while(arrayList.indexOf("sword")!=-1)
        {
            if(opponent.shieldPoint==0)
            opponent.healthPoint--;
            else
            opponent.shieldPoint--;
            arrayList.remove("sword");
            diceNum--;
            checkHealth(opponent);
        }
    }

    private void checkHealth(MengNan mengNan) {
        if(mengNan.healthPoint<=0)
        mengNan.state = true;
        else
        mengNan.state = false;        
    }
}