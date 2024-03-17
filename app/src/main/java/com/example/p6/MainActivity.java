package com.example.p6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.p6.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private ContactDAO contactDAO;

    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private ContactsAdapter contactsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<Contact>();
        contactsAdapter = new ContactsAdapter(contactList);
        binding.rvContacts.setAdapter(contactsAdapter);

        contactList.add(new Contact("Nguyen Van A","0912345678","a@xyz.net"));
        contactList.add(new Contact("Nguyen Van B","0912345689","b@xyz.net"));
        contactList.add(new Contact("Nguyen Van C","0912345690","c@xyz.net"));

        contactsAdapter.notifyDataSetChanged();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDAO = appDatabase.contactDAO();

                contactDAO.insert(new Contact("Nguyen Van A","0912345678","a@xyz.net"));
            }
        });



    }
}