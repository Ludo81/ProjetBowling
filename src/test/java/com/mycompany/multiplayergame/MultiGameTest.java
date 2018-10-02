/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.multiplayergame;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedago
 */
public class MultiGameTest
{    
    private MultiGame jeuMulti;
    String[] vide;
    String[] parties;
    
    @Before
    public void setUp() 
    {
        jeuMulti=new MultiGame();
        vide=new String[0];
        parties=new String[] {"Marcel", "Patrice", "Emile", "Marco"};
    }
    
    @Test (expected=java.lang.Exception.class)
    public void testAucunJoueur() throws Exception 
    {
        jeuMulti.startNewGame(vide);
    }
    
    @Test (expected=java.lang.Exception.class)
    public void testPartieNonCommencé() throws Exception
    {
        jeuMulti.lancer(5);
    }
    
    @Test
    public void testScore() throws Exception
    {
        jeuMulti.startNewGame(parties);
        assertEquals(0, jeuMulti.scoreFor("Marcel"));
    }
    
    @Test (expected=java.lang.Exception.class)
    public void testScoreJoueurInnexistant() throws Exception
    {
        jeuMulti.startNewGame(parties);
        assertEquals(0, jeuMulti.scoreFor("Patrice"));
    }
    
    @Test
    public void testCreationPartie() throws Exception
    {
        assertEquals("Prochain tir : joueur Jacke, tour n° 1, boule n° 01", jeuMulti.startNewGame(parties));
    }
    
    @Test
    public void testRandomGame() throws Exception
    {
        jeuMulti.startNewGame(parties);
        jeuMulti.lancer(5);
        assertEquals(5, jeuMulti.scoreFor("Marcel"));
    }
    
}