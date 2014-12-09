package bitninja.de.recyclerendlesslistlibrary;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.ItemAnimator;
import static android.support.v7.widget.RecyclerView.LayoutManager;
import static android.support.v7.widget.RecyclerView.OnScrollListener;

public class RecyclerEndlessList extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LayoutManager layoutManager;
    public static final int RECYCLERVIEW_ID = 70004;
    private RecyclerViewEndlessListener recyclerViewEndlessListener;

    private OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            swipeRefreshLayout.setEnabled(layoutManager instanceof LinearLayoutManager ?
                    ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0 :
                    ((GridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0);
        }
    };

    public RecyclerEndlessList(Context context) {
        super(context);
        initView();
    }

    public RecyclerEndlessList(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RecyclerEndlessList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setRecyclerViewEndlessListener(RecyclerViewEndlessListener recyclerViewEndlessListener) {
        this.recyclerViewEndlessListener = recyclerViewEndlessListener;
    }

    public void initView() {

        recyclerView = new RecyclerView(getContext());
        swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setTag(RECYCLERVIEW_ID);

        swipeRefreshLayout.addView(recyclerView);
        addView(swipeRefreshLayout);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setOnScrollListener(onScrollListener);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (recyclerViewEndlessListener != null)
                    recyclerViewEndlessListener.onItemClick(view, position);
            }
        }));
    }

    public void setRefreshing(boolean refreshing){
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    public void setProgressBackgroundColor(int colorRes){
        swipeRefreshLayout.setProgressBackgroundColor(colorRes);
    }

    public void setSize(int size){
        swipeRefreshLayout.setSize(size);
    }

    public void setColorSchemeColors(int... colors){
        swipeRefreshLayout.setColorSchemeColors(colors);
    }

    public void setColorSchemeResources(int... colorResIds){
        swipeRefreshLayout.setColorSchemeResources(colorResIds);
    }

    public RecyclerViewEndlessListener getRecyclerViewEndlessListener() {
        return recyclerViewEndlessListener;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.recyclerView.setLayoutManager(layoutManager);
    }

    public LayoutManager getLayoutManager() {
        return this.layoutManager;
    }

    public void setItemAnimator(ItemAnimator itemAnimator) {
        recyclerView.setItemAnimator(itemAnimator);
    }

    public ItemAnimator getItemAnimator() {
        return recyclerView.getItemAnimator();
    }

    public void setAdapter(EndlessAdapter adapter) {
        recyclerView.setAdapter(adapter);
        adapter.setRecyclerEndlessList(this);
    }

    public void setHasFixedSize(boolean fixedSize) {
        recyclerView.setHasFixedSize(fixedSize);
    }

    public boolean hasFixedSize() {
        return recyclerView.hasFixedSize();
    }

    public RecyclerView.Adapter getAdapter() {
        return recyclerView.getAdapter();
    }

    public void setRecyclerListener(RecyclerView.RecyclerListener listener) {
        recyclerView.setRecyclerListener(listener);
    }

    public RecyclerView.RecycledViewPool getRecycledViewPool() {
        return recyclerView.getRecycledViewPool();
    }

    public void setRecycledViewPool(RecyclerView.RecycledViewPool pool) {
        recyclerView.setRecycledViewPool(pool);
    }

    public int getScrollState() {
        return recyclerView.getScrollState();
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor, int index) {
        recyclerView.addItemDecoration(decor,index);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        addItemDecoration(decor, -1);
    }

    public void removeItemDecoration(RecyclerView.ItemDecoration decor) {
        recyclerView.removeItemDecoration(decor);
    }

    public void scrollToPosition(int position) {
        recyclerView.scrollToPosition(position);
    }

    public void smoothScrollToPosition(int position) {
        recyclerView.smoothScrollToPosition(position);
    }

    public void scrollBy(int x, int y) {
        recyclerView.scrollBy(x,y);
    }

    public void smoothScrollBy(int dx, int dy) {
        recyclerView.smoothScrollBy(dx,dy);
    }

    public boolean fling(int velocityX, int velocityY) {
        return recyclerView.fling(velocityX,velocityY);
    }

    public void stopScroll() {
        recyclerView.stopScroll();
    }

    public View focusSearch(View focused, int direction) {
        return recyclerView.focusSearch(focused,direction);
    }

    public void requestChildFocus(View child, View focused) {
        recyclerView.requestChildFocus(child,focused);
    }

    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        return recyclerView.requestChildRectangleOnScreen(child,rect,immediate);
    }

    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        recyclerView.addFocusables(views,direction,focusableMode);
    }

    public void addOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        recyclerView.addOnItemTouchListener(listener);
    }

    public void removeOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        recyclerView.removeOnItemTouchListener(listener);
    }

    public boolean onTouchEvent(MotionEvent e) {
        return recyclerView.onTouchEvent(e);
    }

    public RecyclerView.ViewHolder getChildViewHolder(View child) {
        return recyclerView.getChildViewHolder(child);
    }

    public int getChildPosition(View child) {
        return getChildPosition(child);
    }

    public long getChildItemId(View child) {
        return recyclerView.getChildItemId(child);
    }

    public RecyclerView.ViewHolder findViewHolderForPosition(int position) {
        return recyclerView.findViewHolderForPosition(position);
    }

    public RecyclerView.ViewHolder findViewHolderForItemId(long id) {
        return recyclerView.findViewHolderForItemId(id);
    }

    public View findChildViewUnder(float x, float y) {
        return recyclerView.findChildViewUnder(x,y);
    }

    public void offsetChildrenVertical(int dy) {
        recyclerView.offsetChildrenVertical(dy);
    }

    public void offsetChildrenHorizontal(int dx) {
        recyclerView.offsetChildrenHorizontal(dx);
    }

    @Override
    public void onRefresh() {
        recyclerViewEndlessListener.onRefresh();
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        GestureDetector mGestureDetector;
        private OnItemClickListener mListener;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);
        }
    }

}
