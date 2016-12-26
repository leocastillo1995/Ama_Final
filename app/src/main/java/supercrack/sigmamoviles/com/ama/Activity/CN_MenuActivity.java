package supercrack.sigmamoviles.com.ama.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import supercrack.sigmamoviles.com.ama.Conexion.ServicioAma;
import supercrack.sigmamoviles.com.ama.Fragment.CN_AcercaDeFragment;
import supercrack.sigmamoviles.com.ama.Fragment.CN_PerfilUsuarioFragment;
import supercrack.sigmamoviles.com.ama.Modelo.Token;
import supercrack.sigmamoviles.com.ama.Preferencia.Preferen;
import supercrack.sigmamoviles.com.ama.R;
import supercrack.sigmamoviles.com.ama.Utils.ElementosWebservis;

public class CN_MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Preferen preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn__menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        preference = new Preferen(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cn__menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.menu_inicio) {

        } else if (id == R.id.menu_foro) {

        } else if (id == R.id.menu_perfil) {

            fragmentManager.beginTransaction().replace(R.id.content_cn__menu , new CN_PerfilUsuarioFragment()).commit();
            getSupportActionBar().setTitle(item.getTitle());
            item.setCheckable(true);

        } else if (id == R.id.menu_acercade) {

            fragmentManager.beginTransaction().replace(R.id.content_cn__menu , new CN_AcercaDeFragment()).commit();
            getSupportActionBar().setTitle(item.getTitle());
            item.setCheckable(true);

        } else if (id == R.id.menu_salir) {

            Retrofit retrofit = new Retrofit.Builder().baseUrl(ServicioAma.URL)
                                    .addConverterFactory(GsonConverterFactory.create()).build();

            ServicioAma ama = retrofit.create(ServicioAma.class);

            ama.Idendificador(ElementosWebservis.client_id , ElementosWebservis.client_secret ,
                              ElementosWebservis.username , ElementosWebservis.password ,
                              ElementosWebservis.grant_type).enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {

                    Intent intent = new Intent(CN_MenuActivity.this  , CN_InicioActivity.class);

                    preference.modificarUsuario(CN_MenuActivity.this , null);
                    preference.modificartoken(CN_MenuActivity.this  , response.body().getAccess_token());

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {

                    Intent intent = new Intent(CN_MenuActivity.this  , CN_InicioActivity.class);

                    preference.modificarUsuario(CN_MenuActivity.this , null);
                    preference.modificartoken(CN_MenuActivity.this  , null);

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);

                }
            });

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
