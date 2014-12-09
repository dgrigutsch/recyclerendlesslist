package bitninja.de.recyclerendlesslistlibrary;

import android.view.View;

public interface RecyclerViewEndlessListener {

    public void onRequestLastItem();
    public void onRefresh();
    public void onItemClick(View view, int position);

}
