package com.androidrion.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    Fragment fragment;
    FragmentTransaction transaction;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        firstFragmentDisplay(R.id.nav_home);

        logout=(Button)findViewById(R.id.nav_notification);
    }
    public void openAddModifyTask(View view) {
        startActivity(new Intent(this, CreateTask.class));
    }

    private void firstFragmentDisplay(int itemId) {

        fragment = null;

        switch (itemId) {
            case R.id.nav_home:
                fragment = new FragmentDashboard();
                break;
            case R.id.nav_account:
                fragment = new FragmentAccount();
                break;
            case R.id.nav_notification:
                Session session = new Session(MainActivity.this);
                session.removeSession();
                moveToLogin();
                break;
        }
        if (fragment != null) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fLayout, fragment);
            transaction.commit();
        }

        drawer.closeDrawers();
    }
    public void logout(View view) {
        //this method will remove session and open login screen
        Session session = new Session(MainActivity.this);
        session.removeSession();

        moveToLogin();
    }
    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        firstFragmentDisplay(item.getItemId());
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
