package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue
    Integer id;
    String text;

   // @ManyToOne
   //User user;

    public String getText() {

        if(text == null){
            return "";
        }
        return text;
    }
}
