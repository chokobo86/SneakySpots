package com.example.jypark.gazua.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.jypark.gazua.R;

/*
<탭 간 터치슬라이드용>
첫번째 탭 화면(COLLECTION)
*/

public class PageOneFragment extends Fragment {
    Context context;

    private OnFragmentInteractionListener mListener;

    public PageOneFragment() {
    }


    public static PageOneFragment newInstance() {
        PageOneFragment fragment = new PageOneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page_one, container, false);
        int size = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        final GridView gAdapter = view.findViewById(R.id.gridView1);
        gAdapter.setAdapter(new ImageAdapter(getActivity(),size));

        return view;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
       /* void onFragmentInteraction(Uri uri);*/
    }

    private class ImageAdapter extends BaseAdapter{
        Context context;
        private int mIconSize;

        public ImageAdapter(Context context, int iconsize) {
            super();
            this.context = context;
           this.mIconSize = iconsize;
        }


        /*화면에 출력할 내용(반복할 부분)*/
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(context);

                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(posterID[position]);
            return imageView;
        }
        // references to our images
        Integer[] posterID = { R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection };



        @Override
        public int getCount() {
            return posterID.length;
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



    }

}




