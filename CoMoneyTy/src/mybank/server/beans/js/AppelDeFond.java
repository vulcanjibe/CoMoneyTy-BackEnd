package mybank.server.beans.js;

import java.util.ArrayList;
import java.util.Date;
import mybank.server.beans.*;

public class AppelDeFond {

    Compte compte;
    Communaute communaute;
    double montant;
    String description;
    Date dateecheance;

    TypeObjet typeMouvement;

   
    ArrayList<ContactRetenu> contactsRetenu;
    String urlDocument;

    public Communaute getCommunaute() {
        return communaute;
    }

    public void setCommunaute(Communaute communaute) {
        this.communaute = communaute;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateecheance() {
        return dateecheance;
    }

    public void setDateecheance(Date dateecheance) {
        this.dateecheance = dateecheance;
    }

    public ArrayList<ContactRetenu> getContactsRetenu() {
        return contactsRetenu;
    }

    public void setContactsRetenu(ArrayList<ContactRetenu> contactsRetenu) {
        this.contactsRetenu = contactsRetenu;
    }

    public String getUrlDocument() {
        return urlDocument;
    }

    public void setUrlDocument(String urlDocument) {
        this.urlDocument = urlDocument;
    }
 public TypeObjet getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(TypeObjet typeMouvement) {
        this.typeMouvement = typeMouvement;
    }
  
}
