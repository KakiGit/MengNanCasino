import java.util.ArrayList;
import java.util.Random;

import javax.lang.model.util.ElementScanner6;



public class MengNanGame {
    public static void main(String[] args) {
        int gamePlayNumber = 100000, mengNan1WinCount=0, mengNan2WinCount=0,mengNanDrawCount=0;
        float money;
        int moneyTmp=0,maximumDeficit=0, maximumProfit=0;
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
            if(winner=="draw")
            mengNanDrawCount++;
            
            if(winner == "mengNan2")
            moneyTmp = moneyTmp + 150;
            else
            moneyTmp = moneyTmp - 100;
            
            if(moneyTmp<maximumDeficit)
            maximumDeficit=moneyTmp;
            if(moneyTmp>maximumProfit)
            maximumProfit=moneyTmp;
        }   
        money =(float) (mengNan2WinCount) * 150 /gamePlayNumber - (float) (mengNan1WinCount+mengNanDrawCount) * 100 /gamePlayNumber;
        System.out.println("MengNan2 won $"+money+" on average every time");
        System.out.println("MengNan2 lost maximum $"+maximumDeficit+" in the game");
        System.out.println("MengNan2 got maximum $"+maximumProfit+" in the game");
        System.out.println("MengNan1 won "+mengNan1WinCount+" times!");
        System.out.println("MengNan2 won "+mengNan2WinCount+" times!");
        System.out.println("Game drew "+mengNanDrawCount+" times!");
    
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
                   if(mengNan1.healthPoint==mengNan2.healthPoint)
                   winner = "draw";
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
            int ran = (int) (6 * Math.random());
            if(ran>=4)
            arrayList.add(diceCate[2]);         
            else if(ran>=2)
            arrayList.add(diceCate[3]);
            else if(ran>=1)
            arrayList.add(diceCate[0]);
            else
            arrayList.add(diceCate[1]);          
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