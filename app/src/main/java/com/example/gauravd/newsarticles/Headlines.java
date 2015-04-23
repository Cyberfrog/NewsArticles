package com.example.gauravd.newsarticles;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link com.example.gauravd.newsarticles.Headlines.OnHeadlineSelectedListener}
 * interface.
 */
public class Headlines extends ListFragment {
    String TAG ="LifeCycle";
    OnHeadlineSelectedListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        public void onArticleSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"headline oncreate");
        super.onCreate(savedInstanceState);
        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // Create an array adapter for the list view, using the Ipsum headlines array
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Ipsum.Headlines));
    }

    @Override
    public void onStart() {
        Log.i(TAG,"headline onStart");

        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG,"headline onAttach");

        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onResume(){
        Log.i(TAG,"headline onResume");

        super.onResume();
    }
    @Override
    public void onPause(){
        Log.i(TAG,"headline onPause");
        super.onPause();
    }
    @Override
    public  void onStop(){
        Log.i(TAG,"headline onStop");
        super.onStop();
    }
    @Override
    public  void  onDestroy(){
        Log.i(TAG,"headline onDestroy");
        super.onDestroy();
    }
    @Override
    public  void onDetach(){
        Log.i(TAG,"headline onDetach");
        super.onDetach();
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onArticleSelected(position);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
}
