package majortaskactual;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MajorTaskActual 
{
    static Scanner Input = new Scanner (System.in);
    
    //Global Variables
    public static String strPlayerElement;
    public static String strBossElement;
    public static String strPlayerAttack;
    public static String strBossAttack;
    public static int intPlayerWTR;
    public static int intPlayerAIR;
    public static int intPlayerETH;
    public static int intPlayerFIR;
    public static double dblBossHealth;
    public static int intBossATK;
    public static int intBossDEF;
    public static double dblPlayerHealth = 40;
    public static int intPlayerATK = 5;
    public static int intPlayerDEF = 5;
    public static int intBossWTR;
    public static int intBossAIR;
    public static int intBossETH;
    public static int intBossFIR;
    public static int intUpgradePoint = 0;
    public static String strCrit;
    public static String strSuperEffective;
    public static boolean boolCriticalHit = false;
    public static boolean boolMiss = false;
    public static int intSECount = 0;
    public static int intWTRCount = 0;
    public static int intAIRCount = 0;
    public static int intFIRCount = 0;
    public static int intETHCount = 0;
    
    //Main to only run opening and define array for counting attacks used
    public static void main(String[] args) throws Exception 
    {
        String strElement[] = new String[4];
        int intAttackCount[] = new int[4];
        
        strElement[0] = "earth";
        strElement[1] = "water";
        strElement[2] = "fire";
        strElement[3] = "air";
        
        Opening(intAttackCount, strElement);
        
    }
    
    //Tutorial level to introduce battle system
    public static void Tutorial (int intAttackCount[], String strElement[]) throws Exception
    {
        //If statements to make sure users preferred element is super effective against boss
        if (strPlayerElement.equals("Water"))
        {
            strBossElement = "Fire";
            strSuperEffective = "Water";
        } 
        
        if (strPlayerElement.equals("Air"))
        {
            strBossElement = "Earth";
            strSuperEffective = "Air";
        } 
        
        if (strPlayerElement.equals("Fire"))
        {
            strBossElement = "Air";
            strSuperEffective = "Fire";
        } 
        
        if (strPlayerElement.equals("Earth"))
        {
            strBossElement = "Water";
            strSuperEffective = "Earth";
        } 
        
        //Sets boss attributes
        intBossATK = 1;
        intBossDEF = 1;
        dblBossHealth = 2;
        
        //When HP reaches 0 for either battler, end code
        while (dblBossHealth > 0 && dblPlayerHealth > 0)
        {    
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("The boss has " + dblBossHealth + " health remaining!");
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("You have " + dblPlayerHealth + " health remaining!");
            //Informs the user which move is best to win
            System.out.println("You are a " + strPlayerElement + " bender. The boss is a " + strBossElement + " bender. Using a " + strSuperEffective + " move would be the most effective!");
            PlayerAttackSelect(intAttackCount, strElement);
            PlayerDamage();
            
            if (dblBossHealth <= 0)
            {
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Congratulations, you beat the tutorial");
                //Informs user on what is super effective against each element
                System.out.println((char)27 + "[31m Remember - Water beats Fire, Air beats Earth, Fire beats Air and Earth beats Water! Have fun!" + (char)27 + "[0m");
                Stage1(intAttackCount, strElement);
            }
        }
    }        
    
    //Backstory and introduction of game - also allows user to choose element
    public static void Opening (int intAttackCount[], String strElement[]) throws Exception
    {
        System.out.println("Water. ");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Earth. ");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Fire. ");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Air. ");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("You now how the story goes, basically beat some people with your crazy bending skills and you'll win");
        TimeUnit.MILLISECONDS.sleep(5000);
        System.out.println("You will be given options on screen. Respond with one of the options. Don't worry, if you misspell a word, you will be asked to input again, so don't stress!");
        TimeUnit.MILLISECONDS.sleep(5000);
        System.out.println("Lets do a tutorial to get you started");
        TimeUnit.MILLISECONDS.sleep(3000);
        PlayerElementChoice();
        PlayerAttributeLevel();
        Tutorial(intAttackCount, strElement);
    }   
    
    //Stage 1 boss - works same as tutorial - all Stage modules work the same, however boss attributes are altered
    public static void Stage1(int intAttackCount[], String strElement[]) throws Exception
    {
        BossElementRNG();
        intBossATK = 1;
        intBossDEF = 1;
        dblBossHealth = 15;
        intSECount = 0;
        
        while (dblBossHealth > 0 && dblPlayerHealth > 0)
        {    
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("The boss has " + dblBossHealth + " health remaining!");
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print("You have " + dblPlayerHealth + " health remaining!");
            System.out.println("");
            PlayerAttackSelect(intAttackCount, strElement);
            PlayerDamage();
            
            if (dblBossHealth <= 0)
            {
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("You win");
                Stage2(intAttackCount, strElement);
            }
            else
            {    
                BossAttackSelect();
                BossDamage();
            }    
        }
        
        if (dblPlayerHealth <= 0)
        {
            Stage3(intAttackCount, strElement);
        }  
    }  
    
    //Stage 2 boss - works same as tutorial - all Stage modules work the same, however boss attributes are altered
    public static void Stage2(int intAttackCount[], String strElement[]) throws Exception
    {
        Upgrade();
        dblPlayerHealth = 40;
        BossElementRNG();
        intBossATK = 2;
        intBossDEF = 2;
        dblBossHealth = 20;
        intSECount = 0;
        
        while (dblBossHealth > 0 && dblPlayerHealth > 0)
        {    
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("The boss has " + dblBossHealth + " health remaining!");
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print("You have " + dblPlayerHealth + " health remaining!");
            System.out.println("");
            PlayerAttackSelect(intAttackCount, strElement);
            PlayerDamage();
            
            if (dblBossHealth <= 0)
            {
                System.out.println("You win");
                Stage3(intAttackCount, strElement);
            }
            else
            {    
                BossAttackSelect();
                BossDamage();
            }   
        }
        
    }  
    
    //Stage 3 boss - works same as tutorial - all Stage modules work the same, however boss attributes are altered
    public static void Stage3(int intAttackCount[], String strElement[]) throws Exception
    {
        Upgrade();
        dblPlayerHealth = 40;
        BossElementRNG();
        intBossATK = 3;
        intBossDEF = 3;
        dblBossHealth = 25;
        intSECount = 0;
        
        while (dblBossHealth > 0 && dblPlayerHealth > 0)
        {    
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("The boss has " + dblBossHealth + " health remaining!");
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print("You have " + dblPlayerHealth + " health remaining!");
            System.out.println("");
            PlayerAttackSelect(intAttackCount, strElement);
            PlayerDamage();
            
            if (dblBossHealth <= 0)
            {
                System.out.println("You win");
                Stage4(intAttackCount, strElement);
            }
            else
            {    
                BossAttackSelect();
                BossDamage();
            } 
        }
        
    }  
    
    //Stage 4 boss - works same as tutorial - all Stage modules work the same, however boss attributes are altered
    public static void Stage4(int intAttackCount[], String strElement[]) throws Exception
    {
        Upgrade();
        dblPlayerHealth = 40;
        BossElementRNG();
        intBossATK = 4;
        intBossDEF = 4;
        dblBossHealth = 30;
        intSECount = 0;
        
        while (dblBossHealth > 0 && dblPlayerHealth > 0)
        {    
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("The boss has " + dblBossHealth + " health remaining!");
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print("You have " + dblPlayerHealth + " health remaining!");
            System.out.println("");
            PlayerAttackSelect(intAttackCount, strElement);
            PlayerDamage();
            
            if (dblBossHealth <= 0)
            {
                System.out.println("You win");
                Stage5(intAttackCount, strElement);
            }
            else
            {    
                BossAttackSelect();
                BossDamage();
            } 
        }
  
    }  
    
    //Stage 5 boss - works same as tutorial - all Stage modules work the same, however boss attributes are altered
    public static void Stage5(int intAttackCount[], String strElement[]) throws Exception
    {
        Upgrade();
        dblPlayerHealth = 40;
        BossElementRNG();
        intBossATK = 7;
        intBossDEF = 7;
        dblBossHealth = 40;
        intSECount = 0;
        
        while (dblBossHealth > 0 && dblPlayerHealth > 0)
        {    
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("The boss has " + dblBossHealth + " health remaining!");
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.print("You have " + dblPlayerHealth + " health remaining!");
            System.out.println("");
            PlayerAttackSelect(intAttackCount, strElement);
            PlayerDamage();
            intSECount = 0;
            
            if (dblBossHealth <= 0)
            {
                WinEnding(intAttackCount, strElement);
            }
            else
            {
                BossAttackSelect();
                BossDamage();
            }    
            
        }
       
    }  
    
    //Ending module to inform user they lost
    public static void Ending(int intAttackCount[], String strElement[]) throws Exception
    {
        System.out.println("You lost. The Fire Nation will rein supreme forever. Try harder next time");
        MostUsedMove(intAttackCount, strElement);
    }  
    
    //Ending module to inform user they won
    public static void WinEnding(int intAttackCount[], String strElement[]) throws Exception
    {
        System.out.println("You won. The Fire Nation is no more and you are the new ruler. Well done!");
        MostUsedMove(intAttackCount, strElement);
    } 
    
    //Upgrade system to allow users to upgrade attack or defense
    public static void Upgrade() throws Exception
    {
        //Awards user one upgrade point
        intUpgradePoint ++;
        
        //Rewards user for better performance
        if (intSECount > 2)
        {
            intUpgradePoint ++;
        }   
        
        //Allows upgrade of element attributes
        if (intUpgradePoint == 2)
        {
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("You performed exceptionally - you have been rewarded with the ability to upgrade an element attribute!");
            System.out.println((char)27 + "[31m Respond with Earth, Water, Fire or Air" + (char)27 + "[0m");
            String strUpgradeChoice = Input.nextLine();
            
            while (!strUpgradeChoice.equalsIgnoreCase("Earth") && !strUpgradeChoice.equalsIgnoreCase("Water") && !strUpgradeChoice.equalsIgnoreCase("Fire") && !strUpgradeChoice.equalsIgnoreCase("Air"))
            {
                System.out.println((char)27 + "[31m Respond with Earth, Water, Fire or Air" + (char)27 + "[0m");
                strUpgradeChoice = Input.nextLine();
            }
            
            if (strUpgradeChoice.equalsIgnoreCase("Earth"))
            {
                intPlayerETH ++;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Your earth bending ability is now " + intPlayerETH + "!");
                intUpgradePoint --;
            } 
          
            if (strUpgradeChoice.equalsIgnoreCase("Water"))
            {
                intPlayerWTR ++;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Your water bending ability is now " + intPlayerWTR + "!");
                intUpgradePoint --;
            } 
          
            if (strUpgradeChoice.equalsIgnoreCase("Fire"))
            {
                intPlayerFIR ++;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Your fire bending ability is now " + intPlayerFIR + "!");
                intUpgradePoint --;
            } 
            
            if (strUpgradeChoice.equalsIgnoreCase("Air"))
            {
                intPlayerAIR ++;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Your air bending ability is now " + intPlayerAIR + "!");
                intUpgradePoint --;
            }
        }    
        
        if (intUpgradePoint == 1)
        {    
            TimeUnit.MILLISECONDS.sleep(250);
            System.out.println("Do you want to upgrade Attack or Defense?");
            System.out.println((char)27 + "[31m Respond with Attack or Defense" + (char)27 + "[0m");
            String strUpgradeChoice = Input.nextLine();

            //Ensure users input attack or defense
            while (!strUpgradeChoice.equalsIgnoreCase("Attack") && !strUpgradeChoice.equalsIgnoreCase("Defense"))
            {
                System.out.println((char)27 + "[31m Respond with Attack or Defense" + (char)27 + "[0m");
                strUpgradeChoice = Input.nextLine();
            }
        
            if (strUpgradeChoice.equalsIgnoreCase("Attack"))
            {
                intPlayerATK ++;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Your attack is now " + intPlayerATK + "!");
            }    
        
            if (strUpgradeChoice.equalsIgnoreCase("Defense"))
            {
                intPlayerDEF ++;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Your defense is now " + intPlayerDEF + "!"); 
            }   
            
            intUpgradePoint = 0;
        }  
    }        
    
    //Randomly generates boss element for each stage
    public static void BossElementRNG() throws Exception
    {
        String[] aryBossElement = new String[4];
        
        aryBossElement[0] = "Water";
        aryBossElement[1] = "Fire";
        aryBossElement[2] = "Earth";
        aryBossElement[3] = "Air";
        
        int intLength = aryBossElement.length;
        int intRNG = (int) (Math.random() * intLength);
        strBossElement = aryBossElement[intRNG];
    } 
    
    //Sets value for boss attribites based on randomly generated element
    public static void BossAttributeLevel() throws Exception
    {
        if (strBossElement.equals("Earth"))
        {
            intBossWTR = 3;
            intBossAIR = 1;
            intBossETH = 5;
            intBossFIR = 3;
        } 
        
        if (strBossElement.equals("Water"))
        {
            intBossWTR = 5;
            intBossAIR = 3;
            intBossETH = 3;
            intBossFIR = 1;
        }   
        
        if (strBossElement.equals("Fire"))
        {
            intBossWTR = 3;
            intBossAIR = 3;
            intBossETH = 1;
            intBossFIR = 5;
        } 
        
        if (strBossElement.equals("Air"))
        {
            intBossWTR = 3;
            intBossAIR = 5;
            intBossETH = 3;
            intBossFIR = 1;
        }   
    }
    
    //Allows player to choose element
    public static void PlayerElementChoice() throws Exception
    {
        TimeUnit.MILLISECONDS.sleep(250);
        System.out.println("What is your element of choice?");
        System.out.println((char)27 + "[31m Respond with Fire, Earth, Water or Air" + (char)27 + "[0m");
        
        strPlayerElement = Input.nextLine();
        
        //Ensures input is water, earth, fire or air
        while (!strPlayerElement.equalsIgnoreCase("Water") && !strPlayerElement.equalsIgnoreCase("Fire") && !strPlayerElement.equalsIgnoreCase("Air") && !strPlayerElement.equalsIgnoreCase("Earth"))
        {
            System.out.println((char)27 + "[31m Respond with Fire, Earth, Water or Air" + (char)27 + "[0m");
            strPlayerElement = Input.nextLine();
        }
        
        //Ensures input is capitlised, even if not inputted that way
        if (strPlayerElement.equals("earth"))
        {
            strPlayerElement = "Earth";
        } 
        
        if (strPlayerElement.equals("water"))
        {
            strPlayerElement = "Water";
        }   
        
        if (strPlayerElement.equals("fire"))
        {
            strPlayerElement = "Fire";
        } 
        
        if (strPlayerElement.equals("air"))
        {
            strPlayerElement = "Air";
        }    
              
    }
    
    //Assigns values to attributes based on player chosen element
    public static void PlayerAttributeLevel() throws Exception
    {
        if (strPlayerElement.equals("Earth"))
        {
            intPlayerWTR = 3;
            intPlayerAIR = 1;
            intPlayerETH = 5;
            intPlayerFIR = 3;
        } 
        
        if (strPlayerElement.equals("Water"))
        {
            intPlayerWTR = 5;
            intPlayerAIR = 3;
            intPlayerETH = 3;
            intPlayerFIR = 1;
        }   
        
        if (strPlayerElement.equals("Fire"))
        {
            intPlayerWTR = 3;
            intPlayerAIR = 3;
            intPlayerETH = 1;
            intPlayerFIR = 5;
        } 
        
        if (strPlayerElement.equals("Air"))
        {
            intPlayerWTR = 3;
            intPlayerAIR = 5;
            intPlayerETH = 3;
            intPlayerFIR = 1;
        }   
    }        
    
    //Allows player to choose attack during battle
    public static void PlayerAttackSelect(int intAttackCount[], String strElement[]) throws Exception
    {
        TimeUnit.MILLISECONDS.sleep(250);
        System.out.println("What attack will you use?");
        System.out.println((char)27 + "[31m Respond with Fire, Earth, Water or Air" + (char)27 + "[0m");
        
        strPlayerAttack = Input.nextLine();
        
        //Ensures input is water, fire, earth or air
        while (!strPlayerAttack.equalsIgnoreCase("Water") && !strPlayerAttack.equalsIgnoreCase("Fire") && !strPlayerAttack.equalsIgnoreCase("Air") && !strPlayerAttack.equalsIgnoreCase("Earth"))
        {
            System.out.println((char)27 + "[31m Respond with Fire, Earth, Water or Air" + (char)27 + "[0m");
            strPlayerAttack = Input.nextLine();
        }
        
        //Ensures input is capitalised, even if not inputted that way
        if (strPlayerAttack.equals("earth"))
        {
            strPlayerAttack = "Earth";
            intAttackCount[0] ++;
        } 
        if (strPlayerAttack.equals("water"))
        {
            strPlayerAttack = "Water";
            intAttackCount[1] ++;
        }    
        if (strPlayerAttack.equals("fire"))
        {
            strPlayerAttack = "Fire";
            intAttackCount[2] ++;
        }    
        if (strPlayerAttack.equals("air"))
        {
            strPlayerAttack = "Air";
            intAttackCount[3] ++;
        } 
    }        
    
    //Calculates player damage
    public static void PlayerDamage() throws Exception
    {
        double dblATKEffectiveness = 0.8;
        int intAttributeLevel = 0;
        String strEffectiveness = "effective";
        
        //If statements to check effectiveness of move
        if (strBossElement.equals("Fire") && strPlayerAttack.equals("Water"))
        {
            dblATKEffectiveness = 1.75;
            intAttributeLevel = intPlayerWTR;
            strEffectiveness = "super effective";
            intSECount ++;
        }
        
        if (strBossElement.equals("Water") && strPlayerAttack.equals("Earth"))
        {
            dblATKEffectiveness = 1.75;
            intAttributeLevel = intPlayerETH;
            strEffectiveness = "super effective";
            intSECount ++;
        } 
        
        if (strBossElement.equals("Air") && strPlayerAttack.equals("Fire"))
        {
            dblATKEffectiveness = 1.75;
            intAttributeLevel = intPlayerFIR;
            strEffectiveness = "super effective";
            intSECount ++;
        } 
        
        if (strBossElement.equals("Earth") && strPlayerAttack.equals("Air"))
        {
            dblATKEffectiveness = 1.75;
            intAttributeLevel = intPlayerAIR;
            strEffectiveness = "super effective";
            intSECount ++;
        } 
        
        if (strBossElement.equals("Fire") && strPlayerAttack.equals("Air"))
        {
            dblATKEffectiveness = 0.25;
            intAttributeLevel = intPlayerAIR;
            strEffectiveness = "not very effective";
        } 
        
        if (strBossElement.equals("Air") && strPlayerAttack.equals("Earth"))
        {
            dblATKEffectiveness = 0.25;
            intAttributeLevel = intPlayerETH;
            strEffectiveness = "not very effective";
        } 
        
        if (strBossElement.equals("Earth") && strPlayerAttack.equals("Water"))
        {
            dblATKEffectiveness = 0.25;
            intAttributeLevel = intPlayerWTR;
            strEffectiveness = "not very effective";
        } 
        
        if (strBossElement.equals("Water") && strPlayerAttack.equals("Fire"))
        {
            dblATKEffectiveness = 0.25;
            intAttributeLevel = intPlayerFIR;
            strEffectiveness = "not very effective";
        } 
        
        //Ifs to find attribute level of user attack
        if (strPlayerAttack.equals("Fire"))
        {
            intAttributeLevel = intPlayerFIR;
        }    
        
        if (strPlayerAttack.equals("Water"))
        {
            intAttributeLevel = intPlayerWTR;
        } 
        
        if (strPlayerAttack.equals("Earth"))
        {
            intAttributeLevel = intPlayerETH;
        } 
        
        if (strPlayerAttack.equals("Air"))
        {
            intAttributeLevel = intPlayerAIR;
        } 
        
        //Checks for miss
        Accuracy();
        //Checks for critical hit
        CriticalHit();
        
        if (boolMiss == false)
        {    
            if (boolCriticalHit == true)
            {
                //Damage calculation for critical hits
                double dblDamageDone = (((((4 * (intAttributeLevel / 2) * (intPlayerATK/intBossDEF)) / 50) + 2) * dblATKEffectiveness) * 2.5) + 2.5;
                //Rounding as to not have damage such as 1.472873278
                double dblDamageDoneRounded = (double) Math.round(dblDamageDone * 1000) / 1000;
                dblBossHealth = dblBossHealth - dblDamageDoneRounded;
                dblBossHealth = (double) Math.round(dblBossHealth * 1000) / 1000;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("You used " + strPlayerAttack + " attack and it did " + dblDamageDone + " damage!" + " It's " + strEffectiveness + "!" + " It was a critical hit!");
            }  
            else
            {
                //Damage calculation without critical hit
                double dblDamageDone = ((((4 * intAttributeLevel * (intPlayerATK/intBossDEF)) / 50) + 2) * dblATKEffectiveness) + 2.5;
                double dblDamageDoneRounded = (double) Math.round(dblDamageDone * 1000) / 1000;
                dblBossHealth = dblBossHealth - dblDamageDoneRounded;
                dblBossHealth = (double) Math.round(dblBossHealth * 1000) / 1000;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("You used " + strPlayerAttack + " attack and it did " + dblDamageDone + " damage!" + " It's " + strEffectiveness + "!");
            }
        }
        else
        {
            double dblDamageDone = 0;
            dblBossHealth = dblBossHealth - dblDamageDone;
            System.out.println("You missed and did 0 damage!");
        }    
    }   
    
    //Randomly selects attack for boss
    public static void BossAttackSelect() throws Exception
    {
        String[] aryBossAttack = new String[4];
        
        aryBossAttack[0] = "Water";
        aryBossAttack[1] = "Fire";
        aryBossAttack[2] = "Earth";
        aryBossAttack[3] = "Air";
        
        int intLength = aryBossAttack.length;
        int intRNG = (int) (Math.random() * intLength);
        strBossAttack = aryBossAttack[intRNG];
    } 
    
    //Calculates boss damage
    public static void BossDamage() throws Exception
    {
        BossAttackSelect();
        double dblATKEffectiveness = 0.8;
        int intAttributeLevel = 0;
        String strEffectiveness = "effective";
        
        //Ifs to check effectiveness
        if (strPlayerElement.equals("Fire") && strBossAttack.equals("Water"))
        {
            dblATKEffectiveness = 1.75;
            intAttributeLevel = intBossWTR;
            strEffectiveness = "super effective";
        }
        
        if (strPlayerElement.equals("Water") && strBossAttack.equals("Earth"))
        {
            dblATKEffectiveness = 1.75;
            intAttributeLevel = intBossETH;
            strEffectiveness = "super effective";
        } 
        
        if (strPlayerElement.equals("Air") && strBossAttack.equals("Fire"))
        {
            dblATKEffectiveness = 1.75;
            intAttributeLevel = intBossFIR;
            strEffectiveness = "super effective";
        } 
        
        if (strPlayerElement.equals("Earth") && strBossAttack.equals("Air"))
        {
            dblATKEffectiveness = 1.75;
            intAttributeLevel = intBossAIR;
            strEffectiveness = "super effective";
        } 
        
        if (strPlayerElement.equals("Fire") && strBossAttack.equals("Air"))
        {
            dblATKEffectiveness = 0.25;
            intAttributeLevel = intBossAIR;
            strEffectiveness = "not very effective";
        } 
        
        if (strPlayerElement.equals("Air") && strBossAttack.equals("Earth"))
        {
            dblATKEffectiveness = 0.25;
            intAttributeLevel = intBossETH;
            strEffectiveness = "not very effective";
        } 
        
        if (strPlayerElement.equals("Earth") && strBossAttack.equals("Water"))
        {
            dblATKEffectiveness = 0.25;
            intAttributeLevel = intBossWTR;
            strEffectiveness = "not very effective";
        } 
        
        if (strPlayerElement.equals("Water") && strBossAttack.equals("Fire"))
        {
            dblATKEffectiveness = 0.25;
            intAttributeLevel = intBossFIR;
            strEffectiveness = "not very effective";
        } 
        
        //Ifs to find attribute level of boss attack
        if (strBossAttack.equals("Fire"))
        {
            intAttributeLevel = intBossFIR;
        }    
        
        if (strBossAttack.equals("Water"))
        {
            intAttributeLevel = intBossWTR;
        } 
        
        if (strBossAttack.equals("Earth"))
        {
            intAttributeLevel = intBossETH;
        } 
        
        if (strBossAttack.equals("Air"))
        {
            intAttributeLevel = intBossAIR;
        } 
        
        //Check for accuracy
        Accuracy();
        //Check for critical hit
        CriticalHit();
        
        if (boolMiss == false)
        {    
            if (boolCriticalHit == true)
            {
                //Damage calculation for critical hit
                double dblDamageDone = (((((4 * intAttributeLevel * ((intBossATK * 2.5)/(intPlayerDEF * 2.5))) / 50) + 2) * dblATKEffectiveness) * 2.5 + 2.5);
                double dblDamageDoneRounded = (double) Math.round(dblDamageDone * 1000) / 1000;
                dblPlayerHealth = dblPlayerHealth - dblDamageDoneRounded;
                dblPlayerHealth = (double) Math.round(dblPlayerHealth * 1000) / 1000;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Boss used " + strBossAttack + " attack and it did " + dblDamageDone + " damage!" + " It's " + strEffectiveness + "!" + " It was a critical hit!");
            }  
            else
            {
                //Damage calculation without critical hit
                double dblDamageDone = ((((4 * intAttributeLevel * ((intBossATK * 2.5)/(intPlayerDEF * 2.5))) / 50) + 2) * dblATKEffectiveness) + 2.5;
                double dblDamageDoneRounded = (double) Math.round(dblDamageDone * 1000) / 1000;
                dblPlayerHealth = dblPlayerHealth - dblDamageDoneRounded;
                dblPlayerHealth = (double) Math.round(dblPlayerHealth * 1000) / 1000;
                TimeUnit.MILLISECONDS.sleep(250);
                System.out.println("Boss used " + strBossAttack + " attack and it did " + dblDamageDone + " damage!" + " It's " + strEffectiveness + "!");
            }
        }
        else
        {
            double dblDamageDone = 0;
            dblPlayerHealth = dblPlayerHealth - dblDamageDone;
            System.out.println("The boss missed and did 0 damage!");
        }    
    }
    
    //Gives a 1 in 20 chance to get a critical hit, doing more damage
    public static void CriticalHit() throws Exception
    {
        int intRNG = (int) (Math.random() * 20);
        
        if (intRNG == 1)
        {
            boolCriticalHit = true;
        }    
        else
        {
            boolCriticalHit = false;
        }    
    }
    
    //Gives a chance to miss attack
    public static void Accuracy() throws Exception
    {
        int intRNG = (int) (Math.random() * 100);
        
        if (intRNG == 1 || intRNG == 2 || intRNG == 3 || intRNG == 4 || intRNG == 5)
        {
            boolMiss = true;
        }    
        else
        {
            boolMiss = false;
        }    
    }
    
    //Sorts what move was used the most
    public static void MostUsedMove(int intAttackCount[], String strElement[]) throws Exception
    {
        for (int intCounter = 0; intCounter < 3; intCounter ++)         
        {
            
            for (int intSecondCounter = 0; intSecondCounter < 4 - intCounter - 1; intSecondCounter++) 
            {
                
                if (intAttackCount[intSecondCounter] > intAttackCount[intSecondCounter + 1])
                {
                    int intSwap = intAttackCount[intSecondCounter];
                    intAttackCount[intSecondCounter] = intAttackCount[intSecondCounter + 1];
                    intAttackCount[intSecondCounter + 1] = intSwap;
                    String strSwap = strElement[intSecondCounter];
                    strElement[intSecondCounter] = strElement[intSecondCounter + 1];
                    strElement[intSecondCounter + 1] = strSwap;
                }
            
            }
            
        }
        
        int intPrintCounter = 3;
        
        //Prints how many times a move was used from highest to lowest
        while (intPrintCounter != -1)
        {
            System.out.println("A " + strElement[intPrintCounter] + " attack was used " + intAttackCount[intPrintCounter] + " times");
            intPrintCounter --;
        }
    }        
}
