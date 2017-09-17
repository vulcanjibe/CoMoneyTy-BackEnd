/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybank.server.beans.js;

import java.util.Date;

import mybank.server.beans.ObjetId;

/**
 *
 * @author pelt815
 */
public class FiltreBalance {
    ObjetId communaute;
    Date debut;
    Date fin;

    public FiltreBalance() {
    }

    public ObjetId getCommunaute() {
        return communaute;
    }

    public void setCommunaute(ObjetId communaute) {
        this.communaute = communaute;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
    
}
