package com.syfblp.sas.testfortab.directory;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.syfblp.sas.testfortab.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile extends AppCompatActivity {
    ArrayList<Person> tobedispayedList = new ArrayList<>();
    ArrayList<Person> input = new ArrayList<>();
    String nameToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        Intent incomingIntent = this.getIntent();
        nameToDisplay = (String) incomingIntent.getStringExtra("snails");
        input = (ArrayList<Person>) incomingIntent.getSerializableExtra("json");

        final Person person = getPeople(input);

        getProfImg();

        TextView firstLastNameTxt = (TextView) findViewById(R.id.txtFirstLastName);
        firstLastNameTxt.setText(person.getFirstName() + " " + person.getLastName());

        TextView FunctionLocationTxt = (TextView) findViewById(R.id.txtFunctionLocation);
        FunctionLocationTxt.setText(person.getFunction() + "  |  " + person.getLocation());

        TextView roleTxt = (TextView) findViewById(R.id.txtRole);
        roleTxt.setText(person.getRole());

        TextView alTxt = (TextView) findViewById(R.id.txtAL);
        alTxt.setText(person.getAl());

        TextView phoneTxt = (TextView) findViewById(R.id.txtPhone);
        phoneTxt.setText(person.getPhone());

        TextView emailTxt = (TextView) findViewById(R.id.txtEmail);
        emailTxt.setText(person.getEmail());

        TextView uniTxt = (TextView) findViewById(R.id.txtUniv);
        uniTxt.setText(person.getUniversity());

        View buttonView = findViewById(R.id.btnCall);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + person.getPhone()));
                startActivity(intent);
            }
        });
    }

    private Person getPeople(ArrayList<Person> input) {
        Person toBeReturned = null;
        for (int i = 0; i < input.size(); i++) {
            Person check = (Person) input.get(i);
            String check1 = check.getFirstName() + " " + check.getLastName() + "- " + check.getLocation();
            if (check1.equals(nameToDisplay)) {
                toBeReturned = check;
            }
        }
        return toBeReturned;
    }

    /*private void getProfImg() {
            final Person person = getPeople(input);
            String mDrawableName = person.getLastName().toLowerCase();
            CircleImageView profImage = (CircleImageView) findViewById(R.id.profile_image);
            int id = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            profImage.setImageResource(id);
        } */

    private void getProfImg() {
        final Person person = getPeople(input);
        CircleImageView profImage = (CircleImageView) findViewById(R.id.profile_image);
        String lastNm = person.getLastName();

        int id = 0;
        id = getResources().getIdentifier(lastNm.toLowerCase(), "drawable", Profile.this.getPackageName());

        if(id == 0) {
            profImage.setVisibility(View.GONE);
            return;
        }

        try {
            profImage.setImageDrawable(getResources().getDrawable(id));
        } catch(Exception ex) {
            Log.e("", "Resource not found!");
        }
    }
}




