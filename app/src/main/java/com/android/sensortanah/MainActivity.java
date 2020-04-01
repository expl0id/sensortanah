package com.android.sensortanah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    DatabaseReference databaseTanaman;
    EditText nama;
    EditText indikatorNyala;
    EditText indikatorMati;
    Button submitProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nama=findViewById(R.id.nama);
        indikatorNyala=findViewById(R.id.indikatorNyala);
        indikatorMati=findViewById(R.id.indikatorMati);
        submitProfile=findViewById(R.id.submitProfile);
        KadarTanah= findViewById(R.id.KadarTanah);
        //database list tanaman
        databaseTanaman=FirebaseDatabase.getInstance().getReference("Tanaman");
        //buat database reference ke bagian presentase
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("persentase");
        readFromDatabase();

        submitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addList();
            }
        });
    }
    public void readFromDatabase() {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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
    private void addList(){
        String namaTanaman = nama.getText().toString().trim();
        Integer on = Integer.parseInt(indikatorNyala.getText().toString());
        Integer off = Integer.parseInt(indikatorMati.getText().toString());

        String id = databaseTanaman.push().getKey();
        Tanaman tanaman = new Tanaman(id, namaTanaman, on, off);
        databaseTanaman.child(id).setValue(tanaman);

    }
}
