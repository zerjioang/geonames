/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sherpa.challenge.model.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author .local
 */
@Entity
@Table(name="DETALLE")
public class Detalle implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String zipcode, city;
    @OneToMany(cascade=ALL, mappedBy="detalle")
    private Collection<Master> masters;

    public Detalle() {
        this.masters = new ArrayList<Master>();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Collection<Master> getMasters() {
        return masters;
    }

    public void setMasters(Collection<Master> masters) {
        this.masters = masters;
    }

    public void addMaster(Master m) {
        this.masters.add(m);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Detalle{" + "id=" + id + ", zipcode=" + zipcode + ", city=" + city + ", masters=" + Arrays.asList(masters) + '}';
    }
    
    
    
}
