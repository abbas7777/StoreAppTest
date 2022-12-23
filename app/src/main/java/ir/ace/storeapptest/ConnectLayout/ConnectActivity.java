package ir.ace.storeapptest.ConnectLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ir.ace.storeapptest.Login.LoginActivity;
import ir.ace.storeapptest.R;
import ir.ace.storeapptest.Regster.RegesterActivity;

public class ConnectActivity extends AppCompatActivity {

    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        setUpviews();
    }

    private void setUpviews() {

        btn1=(Button)findViewById(R.id.btn_reoen_regester);
        btn2=(Button)findViewById(R.id.btn_reoen_enter);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ConnectActivity.this, RegesterActivity.class);
                startActivity(intent);
                finish();

            }
        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  intent=new Intent(ConnectActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }

}