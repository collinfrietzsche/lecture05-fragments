package edu.uw.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class SearchFragment extends Fragment {

    private OnSearchListener callback; //context that we use for event callbacks


    public SearchFragment() {
        // Required empty public constructor
    }

    public interface OnSearchListener {
        public void onSearchSubmitted(String searchTerm);
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnSearchListener)context;
        }catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        Button searchButton = (Button)rootView.findViewById(R.id.btnSearch);
        final EditText editText = (EditText)rootView.findViewById(R.id.txtSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = editText.getText().toString();
                callback.onSearchSubmitted(searchText);
            }
        });

        return rootView;
    }


}
