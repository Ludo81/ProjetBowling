package com.mycompany.multiplayergame;

import bowling.Frame;
import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.HashMap;

/**
 *
 * @author pedago
 */
public class MultiGame implements MultiPlayerGame
{   
    int nombreTotalJoueurs, numJoueur;
    boolean gameStarted;
    
    String[] nomJoueur;    
    HashMap <String,SinglePlayerGame> joueurs;
    
    public MultiGame()                              //Constructeur
    {
        this.nombreTotalJoueurs=0;                  //Pour la vérification du nombre
        this.numJoueur=0;                           //Numero du joueur jouant actuellement
        this.gameStarted=false;                     //Détermine si la partie est démarré
        this.nomJoueur= new String[0];              //Liste des joueurs
        this.joueurs= new HashMap<>();              //Table clé : joueur, valeur : son SinglePlayerGame
    }
    
    /**
	 * Démarre une nouvelle partie pour un groupe de joueurs
	 * @param playerName un tableau des noms de joueurs (il faut au moins un joueur)
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 1"
	 * @throws java.lang.Exception si le tableau est vide ou null
	 */
    @Override
    public String startNewGame(String[] playerName) throws Exception 
    {
        this.nombreTotalJoueurs=playerName.length;              
        this.gameStarted=true;
        this.nomJoueur=playerName;
        
        if (nombreTotalJoueurs==0)
        {
            throw new java.lang.Exception("Il n'y a aucun joueur.");        //Si aucun joueur présent.
        }
        
        for (int i=0; i<playerName.length; i++)
        {
            this.joueurs.put(playerName[i], new SinglePlayerGame());        //Ajout dans table
        }
        
        return "Prochain tir : joueur " + playerName[numJoueur] +                                               
                ", tour n° " + this.joueurs.get(playerName[numJoueur]).getCurrentFrame().getFrameNumber() + 
                ", boule n° " + this.joueurs.get(playerName[numJoueur]).getCurrentFrame().getBallsThrown()+1;
    }
    
    /**
	 * Enregistre le nombre de quilles abattues pour le joueur courant, dans le frame courant, pour la boule courante
	 * @param nombreDeQuillesAbattues : nombre de quilles abattue à ce lancer
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 1",
	 * ou bien "Partie terminée" si la partie est terminée.
	 * @throws java.lang.Exception si la partie n'est pas démarrée, ou si elle est terminée.
	 */
    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception 
    {
        if (!this.gameStarted)
        {
            throw new java.lang.Exception("La partie n'est pas démarrée ou elle est terminée.");        
        }
        
        this.joueurs.get(nomJoueur[numJoueur]).lancer(nombreDeQuillesAbattues);                         //Lancé pour le joueur courant.
        
        if (this.joueurs.get(nomJoueur[numJoueur]).getCurrentFrame().isFinished())
        {
            this.numJoueur++;                                                                           
        }                                                                                               
        
        if (this.numJoueur==this.nomJoueur.length)                                                      
        {                                                                                               
            if (this.joueurs.get(this.nomJoueur[this.numJoueur-1]).getCurrentFrame()==null)             //Si le tour du dernier joueur est terminé.
            {
                this.gameStarted=false;                                                                 //Arrêt de la partie
                return "Partie terminée";
            }
            this.numJoueur=0;                                                                           //Retour au premier joueur si c'est pas terminé
        }
        
        return "Prochain tir : joueur " + this.nomJoueur[this.numJoueur] + 
                ", tour n° " + this.joueurs.get(this.nomJoueur[this.numJoueur]).getCurrentFrame().getFrameNumber() + 
                ", boule n° " + this.joueurs.get(this.nomJoueur[this.numJoueur]).getCurrentFrame().getBallsThrown()+1;     
    }

    /**
	 * Donne le score pour le joueur playerName
	 * @param playerName le nom du joueur recherché
	 * @return le score pour ce joueur
	 * @throws Exception si le playerName ne joue pas dans cette partie
	 */
    @Override
    public int scoreFor(String playerName) throws Exception
    {
        if (!this.joueurs.containsKey(playerName))      
        {
            throw new java.lang.Exception("Ce joueur ne joue pas dans cette partie.");
        }
        return this.joueurs.get(playerName).score();         
    }  
}