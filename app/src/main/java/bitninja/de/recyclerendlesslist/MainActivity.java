package bitninja.de.recyclerendlesslist;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import bitninja.de.recyclerendlesslistlibrary.RecyclerEndlessList;
import bitninja.de.recyclerendlesslistlibrary.RecyclerViewEndlessListener;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements RecyclerViewEndlessListener {

        private RecyclerEndlessList endlessList;
        private TestAdapter testAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            endlessList = (RecyclerEndlessList) rootView.findViewById(R.id.list);

            return rootView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            List<String> strings = new ArrayList<>();
            strings.add("A");
            strings.add("B");
            strings.add("C");
            strings.add("D");
            strings.add("E");


            testAdapter = new TestAdapter();
            testAdapter.addAll(strings);
            endlessList.setAdapter(testAdapter);
            endlessList.setRecyclerViewEndlessListener(this);

        }

        @Override
        public void onRequestLastItem() {
            Log.e("onRequestLastItem","refreshing");
        }

        @Override
        public void onRefresh() {
            Log.e("onRefresh","refreshing");

            List<String> strings = new ArrayList<>();
            strings.add("F");
            strings.add("G");
            strings.add("H");
            strings.add("I");
            strings.add("J");
            strings.add("K");
            strings.add("L");
            strings.add("M");
            strings.add("N");
            strings.add("O");
            strings.add("P");
            strings.add("Q");
            testAdapter.addAll(strings);

            endlessList.setRefreshing(false);

        }

        @Override
        public void onItemClick(View view, int position) {
            Log.e("onItemClick","click");
        }
    }
}
