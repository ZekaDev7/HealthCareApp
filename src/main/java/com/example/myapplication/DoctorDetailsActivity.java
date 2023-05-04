package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctorDetails1 = {
            {"Doctor Name: Heyder Heyderov", "Hospital Address: Central", "Experience: 5years", "Mobile No: 0553636366", "630"},
            {"Doctor Name: Murad Muradov", "Hospital Address: Tree", "Experience: 6years", "Mobile No: 0506778890", "190"},
            {"Doctor Name: Esger Esgerov", "Hospital Address: Ankle", "Experience: 10years", "Mobile No: 0513768899", "330"},
            {"Doctor Name: Ferit Haciyev", "Hospital Address: Eye", "Experience: 20years", "Mobile No: 0773454678", "900"},
            {"Doctor Name: Ilkin Son", "Hospital Address: Lung", "Experience: 3years", "Mobile No: 0557889345", "500"}
    };

    private String[][] doctorDetails2 = {
            {"Doctor Name: Jorge Jay", "Hospital Address: Central2", "Experience: 15years", "Mobile No: 0553636366", "2630"},
            {"Doctor Name: Valentina Val", "Hospital Address: Tree2", "Experience: 16years", "Mobile No: 0506778890", "2190"},
            {"Doctor Name: Mario Mar", "Hospital Address: Ankle2", "Experience: 11years", "Mobile No: 0513768899", "2330"},
            {"Doctor Name: Edward Ed", "Hospital Address: Eye2", "Experience: 2years", "Mobile No: 0773454678", "2900"},
            {"Doctor Name: Frank Fr", "Hospital Address: Lung2", "Experience: 13years", "Mobile No: 0557889345", "2500"}
    };

    private String[][] doctorDetails3 = {
            {"Doctor Name: Amalia Amal", "Hospital Address: Central3", "Experience: 1years", "Mobile No: 0553636366", "3630"},
            {"Doctor Name: Nadya Nad", "Hospital Address: Tree3", "Experience: 12years", "Mobile No: 0506778890", "3190"},
            {"Doctor Name: Eric Er", "Hospital Address: Ankle3", "Experience: 14years", "Mobile No: 0513768899", "3330"},
            {"Doctor Name: Arnold Schw", "Hospital Address: Eye3", "Experience: 21years", "Mobile No: 0773454678", "3900"},
            {"Doctor Name: Jacob Jac", "Hospital Address: Lung3", "Experience: 23years", "Mobile No: 0557889345", "3500"}
    };

    private String[][] doctorDetails4 = {
            {"Doctor Name: Paul Pal", "Hospital Address: Central4", "Experience: 9years", "Mobile No: 0553636366", "4630"},
            {"Doctor Name: Masha Grey", "Hospital Address: Tree4", "Experience: 8years", "Mobile No: 0506778890", "4190"},
            {"Doctor Name: Melim Mel", "Hospital Address: Ankle4", "Experience: 18years", "Mobile No: 0513768899", "4330"},
            {"Doctor Name: Senorita Sen", "Hospital Address: Eye4", "Experience: 22years", "Mobile No: 0773454678", "4900"},
            {"Doctor Name: Bianca Bean", "Hospital Address: Lung4", "Experience: 17years", "Mobile No: 0557889345", "4500"}
    };

    private String[][] doctorDetails5 = {
            {"Doctor Name: Zarina Zar", "Hospital Address: Central5", "Experience: 4years", "Mobile No: 0553636366", "5630"},
            {"Doctor Name: Pico Pic", "Hospital Address: Tree5", "Experience: 7years", "Mobile No: 0506778890", "5190"},
            {"Doctor Name: Wait Asec", "Hospital Address: Ankle5", "Experience: 19years", "Mobile No: 0513768899", "5330"},
            {"Doctor Name: Kim Who", "Hospital Address: Eye5", "Experience: 24years", "Mobile No: 0773454678", "5900"},
            {"Doctor Name: Haun Hu", "Hospital Address: Lung5", "Experience: 2years", "Mobile No: 0557889345", "5500"}
    };
    TextView tv;
    Button btn;
    String[][] doctorDetails = {};
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textView_logo6);
        btn = findViewById(R.id.buttonCardGoBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians") == 0) {
            doctorDetails = doctorDetails1;
        } else if (title.compareTo("Dietician") == 0) {
            doctorDetails = doctorDetails2;
        } else if (title.compareTo("Dentist") == 0) {
            doctorDetails = doctorDetails3;
        } else if (title.compareTo("Surgeon") == 0) {
            doctorDetails = doctorDetails4;
        } else if (title.compareTo("Cardiologists") == 0) {
            doctorDetails = doctorDetails5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctor.class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i < doctorDetails.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctorDetails[i][0]);
            item.put("line2", doctorDetails[i][1]);
            item.put("line3", doctorDetails[i][2]);
            item.put("line4", doctorDetails[i][3]);
            item.put("line5", "Cons Fees" + doctorDetails[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_i}
        );
        ListView lst = findViewById(R.id.ListViewLT);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctorDetails[i][0]);
                it.putExtra("text3",doctorDetails[i][1]);
                it.putExtra("text4",doctorDetails[i][3]);
                it.putExtra("text5",doctorDetails[i][4]);
                startActivity(it);
            }
        });
    }
}