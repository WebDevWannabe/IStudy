package com.app.jl.istudy.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;

import com.app.jl.istudy.R;
import com.app.jl.istudy.fragment.PdfDrawerFragment;
import com.app.jl.istudy.fragment.PrivacyDrawerFragment;
import com.app.jl.istudy.fragment.ProfileDrawerFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView = (NavigationView)findViewById(R.id.navigationView);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name);
        //find the docu of actionbardrawertoggle
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, new ProfileDrawerFragment()).commit();

        //setup click events on the navigation view items using setNavigationItemSelectedListener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();

                if(item.getItemId() == R.id.drawerProfile) {
                    switchFragment(new ProfileDrawerFragment(), R.id.drawerProfile);
                }
                else if(item.getItemId() == R.id.drawerPdf) {
                    switchFragment(new PdfDrawerFragment(), R.id.drawerPdf);
                }
                //continue this until every drawer item is initialized
                else if(item.getItemId() == R.id.drawerPrivacy) {
                    switchFragment(new PrivacyDrawerFragment(), R.id.drawerPrivacy);
                }
                else if(item.getItemId() == R.id.drawerLogout) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                return false;
            }
        });
    }

    private void switchFragment(Fragment fragment, int itemId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, fragment).commit();
        navigationView.setCheckedItem(itemId);
        toolbar.setTitle(navigationView.getMenu().findItem(itemId).getTitle().toString());
    }

    @Override
    public void onBackPressed() {

    }
}