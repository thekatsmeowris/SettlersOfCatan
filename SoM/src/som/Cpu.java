/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author david
 */
public class Cpu extends Player {

    private int victoryPoints;
    private TradePack tradePack = new TradePack(this);
    private int priority;
    private int preference;
    private int action;
    HexBoard board;

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public HexBoard getBoard() {
        return board;
    }

    public void setBoard(HexBoard board) {
        this.board = board;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public TradePack getTradePack() {
        return tradePack;
    }

    public void setTradePack(TradePack tradePack) {
        this.tradePack = tradePack;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    Cpu() {
        super();
        assets = new PlayerAssets();
        resources = new int[]{0, 0, 0, 0, 0};
        victoryPoints = 0;
        setBoard(board);
        int pref = (int) (Math.random() * (7 - 0 + 1) + 0);
        this.setPreference(pref);

    }

    Cpu(String name, Color playerColor, HexBoard board) {
        super(name, playerColor);
        assets = new PlayerAssets();
        nickname = name;
        this.playerColor = playerColor;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        setBoard(board);
        int pref = (int) (Math.random() * (7 - 0 + 1) + 0);
        this.setPreference(pref);
    }

    Cpu(String name, int[] resources, Color playerColor, HexBoard board) {
        super(name, resources, playerColor);
        this.nickname = name;
        this.resources = resources;
        this.victoryPoints = 4;
        assets = new PlayerAssets();
        this.playerColor = playerColor;
        setBoard(board);
        int pref = (int) (Math.random() * (5 - 0 + 1) + 1);
        this.setPreference(pref);
        this.setPriority(1);
    }

    Cpu(String name, int[] resources, Color playerColor, HexBoard board, int priority) {
        super(name, resources, playerColor);
        this.nickname = name;
        this.resources = resources;
        this.victoryPoints = 4;
        assets = new PlayerAssets();
        this.playerColor = playerColor;
        setBoard(board);
        int pref = (int) (Math.random() * (5 - 0 + 1) + 1);
        this.setPreference(pref);
        this.setPriority(priority);
    }

    public synchronized void play() throws IOException {
        /*resource numbers:
            0 = steel = ore
            1 = glass = brick
            2 = hemp = sheep
            3 = soy = wheat
            4 = plastic = lumber
         */
        int steel = resources[0], glass = resources[1], hemp = resources[2], water = resources[3], plastic = resources[4];

        if (preference == 1) //preference 1 the cpu prefferences is on building settlement 
        {
            if ((hemp >= 1) && (glass >= 1) && (water >= 1) && (plastic >= 1)) {
                this.setAction(1);//buildSettlement(this.board);
            } else {
                int actionOther = (int) (Math.random() * (6 - 0 + 1) + 1);
                if (actionOther == 1) {
                    if ((glass >= 1)&&(plastic >= 1)) {
                        this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >= 3) && (water >= 2)) {
                            this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                                this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;
                            }
                        }

                    }

                }
                if (actionOther == 2) {
                    if ((glass >= 1)&&(plastic >= 1)) {
                        this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >= 3) && (water >= 2)) {
                            this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >= 3) && (water >= 2)) {
                                this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 3) {
                    if ((steel >= 3) && (water >= 2)) {
                        this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {
                            this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((hemp >= 1) && (glass >= 1) && (water >= 1) && (plastic >= 1)) {
                                this.setAction(1);//build settlement, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 4) {
                    if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                        this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((hemp >= 1) && (glass >= 1) && (water >= 1) && (plastic >= 1)) {
                            this.setAction(1);//build settlement, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >=1) && (hemp >=1) && (water >=1)) {
                                this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 5) {
                    //progress card, if not enough resources incriment action +1 so the next if can be executed
                    if ((steel >=1) && (hemp >=1) && (water >=1)) {
                        this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {
                            this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                                this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 6) {
                    //pass without doing anything
                }
            }
        }
        
        if (preference == 2) //preference 1 the cpu prefferences is on building settlement 
        {
            if ((glass >= 1)&&(plastic >= 1)) {
                this.setAction(2);//build road(this.board);
            } else {
                int actionOther = (int) (Math.random() * (6 - 0 + 1) + 1);
                if (actionOther == 1) {
                    if ((steel >=1) && (hemp >=1) && (water >=1)) {
                        this.setAction(5);//progress card, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                                this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed                            
                        } else {
                            actionOther++;
                            if ((steel >= 3) && (water >= 2)) {
                                this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }

                }
                if (actionOther == 2) {
                    if ((steel >= 3) && (water >= 2)) {
                            this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {                            
                        this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((hemp >= 1) && (glass >= 1) && (water >= 1) && (plastic >= 1)) {
                                this.setAction(1);//build settlement, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 3) {
                    if ((steel >=1) && (hemp >=1) && (water >=1)) {
                        this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                            this.setAction(4);//trade road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((hemp >= 1) && (glass >= 1) && (water >= 1) && (plastic >= 1)) {
                                this.setAction(1);//build settlement, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 4) {
                    if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                        this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {
                            this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >=1) && (hemp >=1) && (water >=1)) {
                                this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 5) {
                    //progress card, if not enough resources incriment action +1 so the next if can be executed
                    if ((steel >=1) && (hemp >=1) && (water >=1)) {
                        this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                            this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                                this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 6) {
                    //pass without doing anything
                }
            }
        }
        
        if (preference == 4) //preference 1 the cpu prefferences is on trade 
        {
            if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                this.setAction(4);//trade;
            } else {
                int actionOther = (int) (Math.random() * (6 - 0 + 1) + 1);
                if (actionOther == 1) {
                    if ((glass >= 1)&&(plastic >= 1)) {
                        this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >= 3) && (water >= 2)) {
                            this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                                this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }

                }
                if (actionOther == 2) {
                    if ((glass >= 1)&&(plastic >= 1)) {
                        this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >=1) && (hemp >=1) && (water >=1)) {
                            this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >= 3) && (water >= 2)) {
                                this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 3) {
                    if ((steel >=1) && (hemp >=1) && (water >=1)) {
                        this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {
                            this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((hemp >= 1) && (glass >= 1) && (water >= 1) && (plastic >= 1)) {
                                this.setAction(1);//build settlement, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 4) {
                    if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                        this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {
                            this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >=1) && (hemp >=1) && (water >=1)) {
                                this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 5) {
                    //progress card, if not enough resources incriment action +1 so the next if can be executed
                    if ((steel >=1) && (hemp >=1) && (water >=1)) {
                        this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {
                            this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >= 3) && (water >= 2)) {
                                this.setAction(3);//city, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 6) {
                    //pass without doing anything
                }
            }
        }
        
        if (preference == 4) //preference 1 the cpu prefferences is on building dev card 
        {
            if ((steel >=1) && (hemp >=1) && (water >=1)) {
                this.setAction(5);//dev card;
            } else {
                int actionOther = (int) (Math.random() * (6 - 0 + 1) + 1);
                if (actionOther == 1) {
                    if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                        this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {
                                this.setAction(2);//road, if not enough resources incriment action +1 so the next if can be executed                            
                        } else {
                            actionOther++;
                            if ((hemp >= 1) && (glass >= 1) && (water >= 1) && (plastic >= 1)) {
                                this.setAction(1);//build settlement, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }

                }
                if (actionOther == 2) {
                    if ((steel >= 3) && (water >= 2)) {
                            this.setAction(3);//build city, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {                            
                        this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                                this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 3) {
                    if ((glass >= 1)&&(plastic >= 1)) {
                        this.setAction(2);//road, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                            this.setAction(4);//trade road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((hemp >= 1) && (glass >= 1) && (water >= 1) && (plastic >= 1)) {
                                this.setAction(1);//build settlement, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 4) {
                    if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                        this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((glass >= 1)&&(plastic >= 1)) {
                            this.setAction(2);//build road, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >=1) && (hemp >=1) && (water >=1)) {
                                this.setAction(5);//dev card, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 5) {
                    //progress card, if not enough resources incriment action +1 so the next if can be executed
                    if ((glass >= 1)&&(plastic >= 1)) {
                        this.setAction(2);//road, if not enough resources incriment action +1 so the next if can be executed
                    } else {
                        actionOther++;
                        if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                            this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                        } else {
                            actionOther++;
                            if ((steel >0) || (glass>0) || (water>0) || (plastic>0) || (hemp >0)) {
                                this.setAction(4);//trade, if not enough resources incriment action +1 so the next if can be executed
                            } else {
                                actionOther = 6;

                            }
                        }

                    }
                }
                if (actionOther == 6) {
                    //pass without doing anything
                }
            }
        }
    }
    /*
    public static void buildSettlement(HexBoard board) throws IOException
    {
        ArrayList<HexVertex>listOfVertexAvailable= new ArrayList<>();
        //for each hex on the bord check each vertex to know if a player has a Settlement already build
        //if not we put the available vertex in a list of available vertex
        for(Hex  hex: board.hexList)
        {
            for(int i=0;i<6;i++)
            {
                if(hex.getVerticies().get(i).getAsset().getPlayer()!=null)
                {
                    listOfVertexAvailable.add(hex.getVerticies().get(i));
                }
            }
        }
        //generate a random number between 0 and the number of vertex available 
        //this index is where the AI will built the settlement
        int indexForBuildInVertex = (int)(Math.random()*(listOfVertexAvailable.size()-0+1)+0);
        HexVertex selectedItem = listOfVertexAvailable.get(indexForBuildInVertex);
        //gsc.buildSettlement(selectedItem);
              
   }
    
    public static void BuildRoad(HexBoard board) throws IOException
    {
        ArrayList<HexEdge>listOfEgdeAvailable= new ArrayList<>();
         for(HexEdge  edge: board.edgeList)
        {
            if(edge.getOwner()!=null)
            {
                listOfEgdeAvailable.add(edge);
            }
        }
         int indexForBuildInEdge = (int)(Math.random()*(listOfEgdeAvailable.size()-0+1)+0);
         HexEdge selectedItem = listOfEgdeAvailable.get(indexForBuildInEdge);
        //gsc.buildRoad(selectedItem);
    }
     public static void tradeRessource()
    {
    }
     
     public static void buildCity(HexBoard board)
    {
        ArrayList<HexVertex>listOfPlayerVertexToUpgrade= new ArrayList<>();
        //for each hex on the bord check each vertex to know if a player has a city already build
        //if not we put the available vertex in a list of available vertex
        for(Hex  hex: board.hexList)
        {
            for(int i=0;i<6;i++)
            {
                Player thisPlayer = null;

                if(hex.getVerticies().get(i).getAsset().getPlayer()==thisPlayer)
                {
                    listOfPlayerVertexToUpgrade.add(hex.getVerticies().get(i));
                }
            }
        }
        //generate a random number between 0 and the number of vertex that the player has a settlement 
        //this number will be use as index where the AI will built the city
        int indexForBuildInVertex = (int)(Math.random()*(listOfPlayerVertexToUpgrade.size()-0+1)+0);
        
        
        code for upgradind the settlement to a city here       
        
   }
     */
}
