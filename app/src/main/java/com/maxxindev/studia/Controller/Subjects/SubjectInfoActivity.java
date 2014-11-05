package com.maxxindev.studia.Controller.Subjects;

import com.maxxindev.studia.Controller.MainActivity;
import com.maxxindev.studia.Controller.NavigationDrawer.Item_objct;
import com.maxxindev.studia.Controller.NavigationDrawer.NavigationAdapter;
import com.maxxindev.studia.Controller.Professor.AddProfessorActivity;
import com.maxxindev.studia.Controller.ViewPagerAdapter;

import android.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.maxxindev.studia.R;

import java.util.ArrayList;


public class SubjectInfoActivity extends FragmentActivity implements
        ActionBar.TabListener, SubjectsFragment.OnFragmentInteractionListener {

    //NAVIGATION DRAWER
    private String[] titles;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Item_objct> NavItms;
    private TypedArray NavIcons;
    NavigationAdapter NavAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private ViewPager viewPager;
    private ViewPagerAdapter mAdapter;
    private ActionBar actionBar;

    // Tab titles
    private String[] tabs = { "Test" , "Professor" };
    private String tag;

    private Long idSubject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_info);

        Bundle bundle = getIntent().getExtras();

        idSubject = bundle.getLong("id");

        //Log.i(tag, "A LA SUBJECTINFOACTIVITY LLEGO ESTE ID " + idProfessor);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        SubjectInfoFragment subjectInfoFragment = new SubjectInfoFragment();

        subjectInfoFragment.setArguments(bundle);

        ft.replace(R.id.content_frame, subjectInfoFragment);
        ft.commit();

        //---------------------------------NAVIGATION DRAWER------------------------------------

        //Drawer Layout
        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout1);
        //List
        NavList = (ListView) findViewById(R.id.lista);

        //Declaramos el header el caul sera el layout de header.xml
        View header = getLayoutInflater().inflate(R.layout.header, null);
        //Establecemos header
        NavList.addHeaderView(header);
        //Tomamos listado  de imgs desde drawable
        NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
        //Tomamos listado  de titles desde el string-array de los recursos @string/nav_options
        titles = getResources().getStringArray(R.array.nav_options);
        //Listado de titles de barra de navegacion
        NavItms = new ArrayList<Item_objct>();
        //Agregamos objetos Item_objct al array
        //Perfil
        NavItms.add(new Item_objct(titles[0], NavIcons.getResourceId(0, -1)));
        //Favoritos
        NavItms.add(new Item_objct(titles[1], NavIcons.getResourceId(1, -1)));
        //Eventos
        NavItms.add(new Item_objct(titles[2], NavIcons.getResourceId(2, -1)));
        //Lugares
        NavItms.add(new Item_objct(titles[3], NavIcons.getResourceId(3, -1)));
        //Etiquetas
        NavItms.add(new Item_objct(titles[4], NavIcons.getResourceId(4, -1)));
        //Configuracion
        NavItms.add(new Item_objct(titles[5], NavIcons.getResourceId(5, -1)));
        //Share
        NavItms.add(new Item_objct(titles[6], NavIcons.getResourceId(6, -1)));

        //Declaramos y seteamos nuestrp adaptador al cual le pasamos el array con los titles
        NavAdapter = new NavigationAdapter(this,NavItms);
        NavList.setAdapter(NavAdapter);
        //Siempre vamos a mostrar el mismo titulo
        mTitle = mDrawerTitle = getTitle();

        //Declaramos el mDrawerToggle y las imgs a utilizar
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                NavDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* Icono de navegacion*/
                R.string.app_name,  /* "open drawer" description */
                R.string.hello_world  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                super.onDrawerOpened(drawerView);
            }
        };

        // Establecemos que mDrawerToggle declarado anteriormente sea el DrawerListener
        NavDrawerLayout.setDrawerListener(mDrawerToggle);

        //Establecemos que el ActionBar muestre el Boton Home
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Establecemos la accion al clickear sobre cualquier item del menu.
        //De la misma forma que hariamos en una app comun con un listview.
        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                MostrarFragment(position);
            }
        });

        //----------------------------------------------------------------------------------------
    }

    private void MostrarFragment(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransac = fragmentManager.beginTransaction();

        Fragment fragment = null;
        Intent intent = null;

        switch (position) {
            case 1:
                fragment = new SubjectsFragment();
                /*intent = new Intent(this, SubjectsActivity.class);
                startActivity(intent);*/
                break;
            case 2:
                intent = new Intent(this, MainActivity.class);
                //fragment = new AddSubjectFragment();
                break;
            case 3:
                intent = new Intent(this, AddProfessorActivity.class);
                startActivity(intent);
                break;


            default:
                //si no esta la opcion mostrara un toast y nos mandara a Home
                Toast.makeText(getApplicationContext(), "Opcion " + titles[position - 1] + "no disponible!", Toast.LENGTH_SHORT).show();
                //fragment = new AddSubjectFragment();
                position=1;
                break;
        }
        //Validamos si el fragment no es nulo
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            fragmentTransac.commit();

        } else {
            //Si el fragment es nulo mostramos un mensaje de error.
            Log.i("Activity ", " " + position);
        }
        // Actualizamos el contenido segun la opcion elegida
        NavList.setItemChecked(position, true);
        NavList.setSelection(position);
        //Cambiamos el titulo en donde decia "
        setTitle(titles[position-1]);
        //Cerramos el menu deslizable
        NavDrawerLayout.closeDrawer(NavList);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavDrawerLayout.isDrawerOpen(NavList)) {
                    NavDrawerLayout.closeDrawer(NavList);
                } else {
                    NavDrawerLayout.openDrawer(NavList);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        android.view.MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.subject_info, menu);
        return true; //super.onCreateOptionsMenu(menu);
    }

    public void onFragmentInteraction(Uri uri){

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onBackPressed() {

        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            // If there are back-stack entries, leave the FragmentActivity
            // implementation take care of them.
            manager.popBackStack();

        } else {
            // Otherwise, ask user if he wants to leave :)
            super.onBackPressed();
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }


}