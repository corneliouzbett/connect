package com.lavacreators.corneliouzbett.ufarm.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lavacreators.corneliouzbett.ufarm.Adapters.MenuRecyclerAdapter;
import com.lavacreators.corneliouzbett.ufarm.R;
import com.lavacreators.corneliouzbett.ufarm.activities.constants.Menus;
import com.lavacreators.corneliouzbett.ufarm.auth.LoginActivity;
import com.lavacreators.corneliouzbett.ufarm.model.Menu;
import com.lavacreators.corneliouzbett.ufarm.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private MenuRecyclerAdapter recyclerAdapter;
    private RecyclerView menuRecyclerView;
    private ArrayList<Menu> menuArrayList;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;

    private TextView nameTextView;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_navigation);

        Toolbar toolbar = findViewById( R.id. toolbar) ;
        setSupportActionBar( toolbar) ;

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference("Ufarm").child("users");

        nameTextView = findViewById(R.id.name_tv);
        titleTextView = findViewById(R.id.title_tv);
        if (firebaseAuth.getCurrentUser() != null){
            nameTextView.setText(firebaseAuth.getCurrentUser().getEmail());
        }

        DrawerLayout drawer = ( DrawerLayout) findViewById( R.id. drawer_layout) ;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) ;
        drawer.setDrawerListener( toggle) ;
        toggle.syncState( ) ;
        NavigationView navigationView = findViewById( R.id. nav_view) ;
        navigationView.setNavigationItemSelectedListener( this) ;

        menuArrayList = new ArrayList<>();
        menuRecyclerView = findViewById(R.id.menu_recyclerview);
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        menuRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        Menu  menu1 = new Menu(getResources().getDrawable(R.drawable.cerclebackgroundpink),
                getResources().getDrawable(R.drawable.ic_report), Menus.REQUEST);
        Menu  menu2 = new Menu(getResources().getDrawable(R.drawable.cerclebackgrounddodge),
                getResources().getDrawable(R.drawable.ic_cast),Menus.HISTORY);
        Menu  menu3 = new Menu(getResources().getDrawable(R.drawable.cerclebackgroundgreen),
                getResources().getDrawable(R.drawable.ic_report),Menus.TRAININGS);
        Menu  menu4 = new Menu(getResources().getDrawable(R.drawable.cerclebackgroundbrown),
                getResources().getDrawable(R.drawable.ic_account_circle),Menus.NEWS);
        Menu  menu5 = new Menu(getResources().getDrawable(R.drawable.cerclebackgroundred),
                getResources().getDrawable(R.drawable.ic_account_circle),Menus.ACCOUNT);
        Menu  menu6 = new Menu(getResources().getDrawable(R.drawable.cerclebackgroundorange),
                getResources().getDrawable(R.drawable.ic_error_black_24dp),Menus.FAQS);
        menuArrayList.add(menu1);
        menuArrayList.add(menu2);
        menuArrayList.add(menu3);
        menuArrayList.add(menu4);
        menuArrayList.add(menu5);
        menuArrayList.add(menu6);

        recyclerAdapter =new MenuRecyclerAdapter(menuArrayList,this);
        menuRecyclerView.setAdapter(recyclerAdapter);
    }
    @Override
    public void onBackPressed( ) {
        DrawerLayout drawer = ( DrawerLayout) findViewById( R.id. drawer_layout) ;
        if ( drawer.isDrawerOpen( GravityCompat.START) ) {
            drawer.closeDrawer( GravityCompat.START) ;
        } else {
            super. onBackPressed( ) ;
        }
    }
    @SuppressWarnings( "StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        // Handle navigation view item clicks here.
        switch( item.getItemId( ) ) {
            case R.id.nav_logout:
            firebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            break;
            default:
                Toast.makeText(MainActivity.this,"INVALID MENU",Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = ( DrawerLayout) findViewById( R.id. drawer_layout) ;
        drawer.closeDrawer( GravityCompat.START) ;
        return true;
    }
}
