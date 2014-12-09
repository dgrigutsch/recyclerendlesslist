package bitninja.de.recyclerendlesslistlibrary;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public abstract class EndlessAdapter<VH
        extends android.support.v7.widget.RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>  {

    private RecyclerView recyclerView;
    private boolean loading = false;
    private RecyclerEndlessList recyclerEndlessList;

//    public abstract void clear();
//    public abstract void addAll();
//    public abstract void add();
//    public abstract void getItem();

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {

        recyclerView = (RecyclerView) viewHolder.itemView.getRootView().findViewWithTag(RecyclerEndlessList.RECYCLERVIEW_ID);

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (position == getItemCount() - 1 && linearLayoutManager.findLastVisibleItemPosition() < getItemCount()
                && linearLayoutManager.findLastVisibleItemPosition() != RecyclerView.NO_POSITION) {

            loading = true;

            registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();

                    unregisterAdapterDataObserver(this);
                    recyclerView.setOnTouchListener(null);
                    loading = false;

                }
            });
            recyclerEndlessList.getRecyclerViewEndlessListener().onRequestLastItem();
        }
        if (position == getItemCount() && loading == true) {

            recyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

    }

    public void setRecyclerEndlessList(RecyclerEndlessList recyclerEndlessList){
        this.recyclerEndlessList = recyclerEndlessList;
    }
}
