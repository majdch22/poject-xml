package com.demo;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "employes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employes_ins {

    private List<Employe_ins> employe = new ArrayList<>();

    public List<Employe_ins> getEmploye() {
        return employe;
    }

    public void addEmploye(Employe_ins e) {
        employe.add(e);
    }
    public boolean removeEmploye(Employe_ins employe) {
        return this.employe.remove(employe);
    }
        public void setEmploye(List<Employe_ins> employe) { // or setEmployes()
        this.employe = employe;
    }
}

