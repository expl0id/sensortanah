package com.android.sensortanah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView KadarTanah;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KadarTanah= findViewById(R.id.KadarTanah);
        database=FirebaseDatabase.getInstance();
        //referensi (nama si databasenya)
        myRef=database.getReference("persentase");
        readFromDatabase();
    }

    public void readFromDatabase() {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                Log.v("value", "Value is: " + value);
                KadarTanah.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.v("value", "Failed to read value.", error.toException());
            }
        });
    }
}
