/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybank.server.rest.util;

import java.util.ArrayList;

/**
 *
 * @author pelt815
 */
public class SolveurBalance {
    int[][] tableau;
    int[][] tableauComplement;
    int nbLigne =0;
    int nbColonne = 0;
        
       public ArrayList<ArrayList<String>> getTableComplement()
       {
            ArrayList<ArrayList<String>> tableCorrigee = new ArrayList<ArrayList<String>>();
            for(int lig=0;lig<nbLigne;lig++)
            {
                int sumCol=0;
                ArrayList<String> ligne1 = new ArrayList<String>();
                for(int col=0;col<nbColonne;col++)
                {
                    ligne1.add(" ("+tableauComplement[lig][col]+")");
                    sumCol+=tableauComplement[lig][col];
                }
                ligne1.add(" ("+sumCol+")");
                tableCorrigee.add(ligne1);
            }
            int sumtotal = 0;
             ArrayList<String> ligne1 = new ArrayList<String>();
            for(int col=0;col<nbColonne;col++)
            {
                int sumcol=0;
                for(int lig=0;lig<nbLigne;lig++)
                    sumcol+=tableauComplement[lig][col];
                ligne1.add(" ("+sumcol+")");
                sumtotal+=sumcol;
            }
             ligne1.add(" ("+sumtotal+")");
             tableCorrigee.add(ligne1);
            // totale
            return tableCorrigee;
       }
    public SolveurBalance(ArrayList<ArrayList<String>> tab) {
        int x=0;
        int y=0;
         nbLigne = tab.size();
         nbColonne = tab.get(0).size()-1;
        tableau = new int[nbLigne][nbColonne];
        tableauComplement = new int[nbLigne][nbColonne];
        for(ArrayList<String> ligne : tab)
        {
            for(String val : ligne)
            {
                if(val.isEmpty())
                    val="0";
                if(x<nbColonne)
                    tableau[y][x++]=Integer.parseInt(val);
            }
            y++;
            x=0;
        }
    }
    
    public int getSommeLigne(int ligne)
    {
        int res = 0;
        for(int i=0;i<nbColonne;i++)
            res+=tableau[ligne][i]+tableauComplement[ligne][i];
        return res;
    }
    public int getSommeColonne(int colonne)
    {
        int res = 0;
        for(int i=0;i<nbLigne;i++)
            res+=tableau[i][colonne]+tableauComplement[i][colonne];
        return res;
    }
    public void print()
    {
        for(int lig=0;lig<nbLigne;lig++)
        {
              for(int col=0;col<nbColonne;col++)
            {
                System.out.print(tableau[lig][col]+"("+tableauComplement[lig][col]+")   ");
            }
            System.out.println();
        }
    }
    public void solve()
    {
        // Recherche du max par ligne
        int maxLigne = 0;
        for(int i=0;i<nbLigne;i++)
        {
            int sommeLigne = getSommeLigne(i);
            if(sommeLigne>maxLigne)
                maxLigne = sommeLigne;
        }
        
        int maxColonne = 0;
        for(int i=0;i<nbColonne;i++)
        {
            int sommeColonne = getSommeColonne(i);
            if(sommeColonne>maxColonne)
                maxColonne = sommeColonne;
        }

        int maxColonneCible = maxLigne * nbLigne / nbColonne;
        // cas ou ce max par ligne est trop petit
        int montantTotalMaxLigne = maxLigne*nbLigne;
        int montantTotalMaxCol = maxColonne*nbColonne;
        if(montantTotalMaxCol>montantTotalMaxLigne) {
            maxColonneCible = montantTotalMaxCol/ nbColonne;
            maxLigne = montantTotalMaxCol/nbLigne;
        }
        
        for(int col=0;col<nbColonne;col++)
            for(int lig=0;lig<nbLigne;lig++)
            {
                int val1 = maxColonneCible - getSommeColonne(col);
                int val2 = maxLigne - getSommeLigne(lig);
                int val=val1;
                if(val>val2)
                    val=val2;

                tableauComplement[lig][col]=val;
            }
    }
}
