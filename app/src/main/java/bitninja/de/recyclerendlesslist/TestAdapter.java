package bitninja.de.recyclerendlesslist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bitninja.de.recyclerendlesslistlibrary.EndlessAdapter;

public class TestAdapter extends EndlessAdapter<TestViewHolder> {

    private List<String> items = new ArrayList<>();

    public void add(String item){
        this.items.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<String> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear(){
        this.items.clear();
        notifyDataSetChanged();
    }

    public String getItem(int position){
        return items.get(position);
    }


    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new TestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {

        String text = getItem(position);
        holder.textView.setText(text);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
