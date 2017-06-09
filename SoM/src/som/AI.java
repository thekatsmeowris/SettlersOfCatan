/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import som.assets.Settlement;

/**
 *
 * @author David Kapanga
 */
public class AI implements Runnable {

    @Override
    public void run() {
        Lock lock = new ReentrantLock();
        Condition startPlay = lock.newCondition();

    }

    public static void buildCity(HexBoard board) {
        ArrayList<HexVertex> listOfPlayerVertexToUpgrade = new ArrayList<>();
        //for each hex on the bord check each vertex to know if a player has a city already build
        //if not we put the available vertex in a list of available vertex
        for (Hex hex : board.hexList) {
            for (int i = 0; i < 6; i++) {

                Player thisPlayer = null;

                if (hex.getVerticies().get(i).getAsset().getPlayer() == thisPlayer) {
                    listOfPlayerVertexToUpgrade.add(hex.getVerticies().get(i));
                }
            }
        }
        //generate a random number between 0 and the number of vertex that the player has a settlement 
        //this number will be use as index where the AI will built the city
        int indexForBuildInVertex = (int) (Math.random() * (listOfPlayerVertexToUpgrade.size() - 0 + 1) + 0);

        /*
        code for upgradind the settlement to a city here
         */
    }

    public HexVertex verTexChoiceTobuildSettlement(HexBoard board) {
        ArrayList<HexVertex> listOfVertexAvailable = new ArrayList<>();
        //for each hex on the bord check each vertex to know if a player has a Settlement already build
        //if not we put the available vertex in a list of available vertex
        for (Hex hex : board.hexList) {
            for (int i = 0; i < 5; i++) {
                if (!hex.getVerticies().get(i).isOwned()) {
                    listOfVertexAvailable.add(hex.getVerticies().get(i));
                }
            }
        }
        //generate a random number between 0 and the number of vertex available 
        //this index is where the AI will built the city
        int n = listOfVertexAvailable.size();
        n = n - 1;
        int indexForBuildInVertex = (int) (Math.random() * (n - 0 + 1) + 0);
        HexVertex verTex = listOfVertexAvailable.get(indexForBuildInVertex);

        return verTex;

    }

    public HexEdge EdgeChoiceForBuildRoad(HexBoard board, HexVertex currentVertex, Cpu currentPlayer,int i) {

//        //board=(HexBoard)currentPlayer.getBoard();
//        int n = board.edgeList.size();
//        n = n - 1;
//        int indexForBuildInVertex = (int) (Math.random() * (50- 0 + 1) + 0);
        HexEdge edge2 = board.edgeList.get(i);
        //edge2 = (HexEdge)currentVertex.getAsset().getHexEdge();
        System.out.println("the vert@@@@@ belong to : " + edge2);
//            a:
//            for (int i=1;i> board.edgeList.size()-1;i++) {
//                 if(board.edgeList.get(i).getAsset().getPlayer().getNickname().equals(currentPlayer.getNickname())) {
//                    edge2 = board.edgeList.get(i);                    
//                    System.out.println("found : " + edge2);
//                    break;
//            }else
//                 {
//                     break a;
//                 }
//                      
//            }
//        if((edge2.getAsset() == null) || (edge2.isOwned())) {
//            //indexForBuildInEdge = (int) (Math.random() * ((1 - 0) + 1) + 0);
//            edge2 = currentVertex.getAdjacentEdge().get(1);
//       }

        

        return edge2;
    }

    public HexEdge EdgeChoiceForBuildRoad(HexBoard board, Cpu currentPlayer, HexVertex currentVertex) {
        String className = currentPlayer.getClass().getSimpleName();
        //if(className.equals("Player"))
        HexEdge edge2 = null;
        ArrayList<HexEdge> listOfEdgeAvailable = new ArrayList<>();
        ArrayList<HexVertex> listOfVertexAvailable = new ArrayList<>();
        for (HexEdge edge : board.edgeList) {

            if (edge.getStartVertex().getAsset() != null) {
                if (edge.getStartVertex().getAsset().getPlayer().equals(currentVertex.getAsset().getPlayer())) {
                    if (!edge.isOwned()) {
                        listOfEdgeAvailable.add(edge);
                        edge2 = edge;
                        return edge2;
                    }
                }
            }

            if (edge.getEndVertex().getAsset() != null) {
                if (edge.getEndVertex().getAsset().getPlayer().equals(currentVertex.getAsset().getPlayer())) {
                    if (!edge.isOwned()) {
                        listOfEdgeAvailable.add(edge);
                        edge2 = edge;
                        return edge2;
                    }
                }
            }

        }
        //int indexForBuildInedge = (int) (Math.random() * ((listOfEdgeAvailable.size() - 1) - 0 + 1) + 0);
        ///HexEdge edge = listOfEdgeAvailable.get(indexForBuildInedge);
        return edge2;
    }

    public HexEdge EdgeChoiceForBuildRoad(HexBoard board, Cpu currentPlayer) {
        String className = currentPlayer.getClass().getSimpleName();
        //if(className.equals("Player"))
        ArrayList<HexEdge> listOfEdgeAvailable = new ArrayList<>();
        ArrayList<HexVertex> listOfVertexAvailable = new ArrayList<>();
        for (HexEdge edge : board.edgeList) {
            try {
                if (edge.getStartVertex().getAsset() != null) {
                    if (edge.getStartVertex().getAsset().getPlayer().equals(currentPlayer)) {
                        if (!edge.isOwned()) {
                            listOfEdgeAvailable.add(edge);
                        }
                    }
                }
            } catch (NullPointerException nullPointer) {
                System.out.println("FAIL ON START");
            }
            try {
                if (edge.getEndVertex().getAsset() != null) {
                    if (edge.getEndVertex().getAsset().getPlayer().equals(currentPlayer)) {
                        if (!edge.isOwned()) {
                            listOfEdgeAvailable.add(edge);
                        }
                    }
                }
            } catch (NullPointerException nullPointer) {
                System.out.println("FAIL ON START");
            }

        }
        int indexForBuildInedge = (int) (Math.random() * ((listOfEdgeAvailable.size() - 1) - 0 + 1) + 0);
        HexEdge edge = listOfEdgeAvailable.get(indexForBuildInedge);
        return edge;
    }

    public static void tradeResspurce() {
    }

}
