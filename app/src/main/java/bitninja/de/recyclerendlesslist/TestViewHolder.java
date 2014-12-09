package bitninja.de.recyclerendlesslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TestViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public TestViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }
}
