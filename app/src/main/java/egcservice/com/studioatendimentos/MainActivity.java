package egcservice.com.studioatendimentos;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import egcservice.com.studioatendimentos.basic.Atendimento;
import egcservice.com.studioatendimentos.basic.Cliente;
import egcservice.com.studioatendimentos.utils.ClickListener;

public class MainActivity extends ActionBarActivity implements ClickListener {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private ListView mDrawerList;
    private String[] mOpcoes = {"Atendimentos", "Clientes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mOpcoes));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getSupportFragmentManager();
                MainFragment home = new MainFragment();
                Bundle args = new Bundle();
                args.putString("codTela", String.valueOf(position));
                home.setArguments(args);
                home.newInstance(String.valueOf(position));
                fm.beginTransaction().replace(R.id.content_frame_principal, home)
                        .commit();
                mDrawer.closeDrawer(mDrawerList);
            }
        });

        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open,
                R.string.app_name) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.drawer_open);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu();
            }
        };
        mToggle.setDrawerIndicatorEnabled(true);
        mDrawer.setDrawerListener(mToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        // int id = item.getItemId();

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void cliClick(Cliente cliente) {
        Intent it = new Intent(this, DetailClienteActivity.class);
        it.putExtra("cliente", cliente);
        startActivity(it);
    }

    @Override
    public void atdClick(Atendimento atendimento) {

    }


//    @Override
//    public void cli(Empresa empresa) {
//        validarIntent();
//        Intent it2 = new Intent(this, DetailClienteActivity.class);
//        it2.putExtra("empresa", empresa);
//        startActivity(it2);
//    }

}