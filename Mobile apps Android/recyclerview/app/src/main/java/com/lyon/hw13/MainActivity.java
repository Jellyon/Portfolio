package com.lyon.hw13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvContacts.addItemDecoration(itemDecoration);

        contacts = Contact.createContactsList(20);
        ContactsAdapter adapter = new ContactsAdapter(contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        Button add = (Button) findViewById(R.id.btnAdd);
        add.setOnClickListener(view -> {
            int currentSize = adapter.getItemCount();
            Contact newContact = new Contact("Jeff" + ++currentSize, true);
            contacts.add(0, newContact);
            adapter.notifyItemInserted(0);
        });
    }
}