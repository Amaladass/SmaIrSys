package com.thinkers.smart_irrigation_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import java.lang.reflect.Member;


public class MainActivity extends AppCompatActivity {

    TextView a,b,c,d,e,f,g;
    Button btn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (TextView)findViewById(R.id.batview);
        b = (TextView)findViewById(R.id.senview1);
        c = (TextView)findViewById(R.id.senview2);
        d = (TextView)findViewById(R.id.senview3);
        e = (TextView)findViewById(R.id.senview4);
        f = (TextView)findViewById(R.id.pumpview);
        g = (TextView)findViewById(R.id.sysview);

        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference();
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String bat = dataSnapshot.child("bat").getValue().toString();
                        String sen1 = dataSnapshot.child("sen1").getValue().toString();
                        String sen2 = dataSnapshot.child("sen2").getValue().toString();
                        String sen3 = dataSnapshot.child("sen3").getValue().toString();
                        String sen4 = dataSnapshot.child("sen4").getValue().toString();
                        String pump = dataSnapshot.child("pump").getValue().toString();
                        String sys = dataSnapshot.child("sys").getValue().toString();
                        a.setText(bat);
                        b.setText(sen1);
                        c.setText(sen2);
                        d.setText(sen3);
                        e.setText(sen4);
                        f.setText(pump);
                        g.setText(sys);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        Toast.makeText(MainActivity.this, "Firebase Connection Success", Toast.LENGTH_SHORT).show();

    }
}
