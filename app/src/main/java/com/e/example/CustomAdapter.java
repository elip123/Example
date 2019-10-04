package com.e.example;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class CustomAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private List<Contact> arrContact;

    public CustomAdapter(Context context, int resource, ArrayList<Contact> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
        viewHolder = new ViewHolder();
        viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
        viewHolder.tvNumberPhone = (TextView) convertView.findViewById(R.id.tvPhoneNumber);
        viewHolder.tvOrder = (TextView) convertView.findViewById(R.id.tvorder);
        viewHolder.idNumber = (EditText) convertView.findViewById(R.id.idnumber);
        convertView.setTag(viewHolder);
        }
        else {viewHolder = (ViewHolder) convertView.getTag();}
        final Contact contact = arrContact.get(position);
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvNumberPhone.setText(contact.getPhoneNumber());
        viewHolder.tvOrder.setText(contact.getNo());
        viewHolder.tvName.setText(contact.getName());


        final int i = position;

        viewHolder.idNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//               p.setIdnumber( viewHolder.idNumber.getText().toString().trim() );
                arrContact.get(i).setIdnumber(viewHolder.idNumber.getText().toString().trim());

            }
        });

        return convertView;


    }

    public class ViewHolder {
        TextView tvName, tvNumberPhone, tvOrder;
        EditText idNumber;
    }
}