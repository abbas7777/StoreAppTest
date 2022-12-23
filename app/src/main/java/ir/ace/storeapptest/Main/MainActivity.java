package ir.ace.storeapptest.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import ir.ace.storeapptest.Cart.CartFragment;
import ir.ace.storeapptest.Daste.DasteFragment;
import ir.ace.storeapptest.FrHome.HomeFragment;
import ir.ace.storeapptest.Profile.ProfileFragment;
import ir.ace.storeapptest.R;
import ir.ace.storeapptest.Search.SearchActivity;

public class MainActivity extends AppCompatActivity {

    FrameLayout hostLayout;
    BottomNavigationView btmMain;
    LinearLayout linlayBoxSearch;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        checkCartIntent();
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }


    private void setUpViews() {
        hostLayout = findViewById(R.id.frlay_MainActivity_host);
        linlayBoxSearch = findViewById(R.id.linlay_MainActivity_boxsearch);
        btmMain = findViewById(R.id.btm_MainActivity_menu);
        toolbar = findViewById(R.id.tb_MainActivity_toolbar);

        loadFragment(new HomeFragment());
        btmMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home_menubnav:
                        toolbar.setVisibility(View.VISIBLE);
                        loadFragment(new HomeFragment());

                        return true;
                    case R.id.daste_menubnav:
                        toolbar.setVisibility(View.VISIBLE);
                        loadFragment(new DasteFragment());

                        return true;

                    case R.id.cart_menubnav:

                        toolbar.setVisibility(View.GONE);
                        loadFragment(new CartFragment());

                        return true;

                    case R.id.profile_menubnav:
                        toolbar.setVisibility(View.GONE);
                        loadFragment(new ProfileFragment());

                        return true;

                }
                return false;
            }
        });
        linlayBoxSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frlay_MainActivity_host, fragment);
        transaction.addToBackStack("f");
        transaction.commit();
    }

    private void checkCartIntent() {
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getInt("flag_cart") == 10001) {

                btmMain.setSelectedItemId(R.id.cart_menubnav);
            }
        }
    }
}