package com.demo;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employe_ins {

    private String nom;
    private String prenom;
    private String cin;
    private String tel;
    private String password;
    private String status; // "en_attente" ou "accepte"
    private String competence;
    private boolean disponibilite;
    public Employe_ins() {}

    public Employe_ins(String nom, String prenom, String cin, String tel, String password,
               String status, String competence, boolean disponibilite) {
    this.nom = nom;
    this.prenom = prenom;
    this.cin = cin;
    this.tel = tel;
    this.password = password;
    this.status = status;
    this.competence = competence;
    this.disponibilite = disponibilite;
}
    

    // Getters et Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setstatus(String status) { this.status = status; }

    public String getCompetence() {return competence; }
    public void setCompetence(String competence) { this.competence = competence; }

    public boolean isDisponibilite() { return disponibilite; }
    public void setDisponibilite(boolean disponibilite) {this.disponibilite = disponibilite; }

        @Override
    public String toString() {
        return "Employe{nom=" + nom +", prenom=" + prenom +"tel="+tel+ 
               "status= "+status+", competence=" + competence + ", disponibilite=" + disponibilite + "}";
    }
}