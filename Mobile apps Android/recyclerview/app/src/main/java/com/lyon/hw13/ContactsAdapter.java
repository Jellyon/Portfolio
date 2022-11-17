package com.lyon.hw13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<Contact> contacts;

    public ContactsAdapter(List<Contact> contacts){
        this.contacts=contacts;
    }
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int type){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(context, contactView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int position){
        Contact contact = this.contacts.get(position);
        TextView textView = viewHolder.nameView;
        textView.setText(contact.getName());
        Button button = viewHolder.messageButton;
        button.setText(contact.isOnline()?"Message":"Offline");
        button.setEnabled(contact.isOnline());

    }
    @Override
    public int getItemCount(){
        return this.contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameView;
        public Button messageButton;
        public Context context;

        public ViewHolder(Context context, View item){
            super(item);
            this.context=context;
            nameView = (TextView) item.findViewById(R.id.contact_name);
            messageButton = (Button) item.findViewById(R.id.message_button);
            item.setOnClickListener(this);
        }
        public int getAdapterPostion(){
            for (int i=0; i<getItemCount(); i++){
                if(contacts.get(i).getName() == nameView.getText()){
                    return i;
                }
            }
            return -1;
        }

        @Override
        public void onClick(View view){
            int pos = getAdapterPostion();
            if(pos != RecyclerView.NO_POSITION){
                String txt = contacts.get(pos).getName();
                Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
