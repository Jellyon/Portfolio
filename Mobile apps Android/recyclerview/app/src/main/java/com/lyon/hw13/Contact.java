package com.lyon.hw13;

import java.util.ArrayList;

public class Contact {
    String name;
    boolean online;

    public Contact(String name, boolean online){
        this.name = name;
        this.online = online;
    }
    public String getName(){
        return this.name;
    }
    public boolean isOnline(){
        return this.online;
    }
    private static int lasContactId =0;

    public static ArrayList<Contact> createContactsList(int num){
            ArrayList<Contact> contacts = new ArrayList<Contact>();
            for(int i=1; i<num; i++){
                contacts.add(new Contact("Jeff"+ ++lasContactId, i<=num /2));
            }
            return contacts;
    }

}
