package com.e.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;
    CustomAdapter CustomAdapter;
    public static ArrayList<Contact> listcontact;

    private ListView lvContact;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = (ListView) findViewById(R.id.lv_Contact);
        save=(Button)findViewById( R.id.btnsave ) ;
        final ArrayList<Contact> arrContact = new ArrayList<>();

        Contact contact1 = new Contact("Guru","000988 933 xxx", "1","");
        Contact contact2 = new Contact("Kindle","0001667 585 545", "2","");
        Contact contact3 = new Contact("Warrent Buffet","000918 033 033", "3","");
        Contact contact4 = new Contact("Steve Dan","000978 102 102", "4","");
        Contact contact5 = new Contact("Howard","0001667 333 000", "5","");
        Contact contact6 = new Contact("Murphy","0008 999 321 371", "6","");
        Contact contact7 = new Contact("Richard","0001222 331 331","7","");
        Contact contact8 = new Contact("John Nerry","0001229 231 371","8","");
        Contact contact9 = new Contact("Marry Hill","0001822 331 831","9","");

        arrContact.add(contact1);
        arrContact.add(contact2);
        arrContact.add(contact3);
        arrContact.add(contact4);
        arrContact.add(contact5);
        arrContact.add(contact6);
        arrContact.add(contact7);
        arrContact.add(contact8);
        arrContact.add(contact9);
        db = new DatabaseHelper(this);
        if(!db.isTableExists( Contact.TABLE_NAME  )||db.isEmpty(Contact.TABLE_NAME )) {
            lvContact.setAdapter(new CustomAdapter( this, R.layout.activity_main, arrContact ) );
        }
        // neu da ton tai co du lieu trong database thilay du lieu trong database load len
        else
        {   listcontact=new ArrayList<Contact>(  );
            listcontact.addAll( db.getAllContacts() );
            Collections.sort(listcontact, new Comparator<Contact>() {
                @Override
                public int compare(Contact data1, Contact data2) {
                    return (int) data1.getNo().charAt(0) - (int) data2.getNo().charAt(0);
                }
            });
            lvContact.setAdapter(new CustomAdapter( this, R.layout.activity_main, listcontact ) );
        }
        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deletetable( Contact.TABLE_NAME );
            for(Contact contact:arrContact)
            {System.out.println(contact.getNo()+ " "+contact.getIdnumber() );
                db.insertContact( contact );
            }
                                        }
        } );
    }
}
