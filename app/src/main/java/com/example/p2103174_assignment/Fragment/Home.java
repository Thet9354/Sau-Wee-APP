package com.example.p2103174_assignment.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import com.example.p2103174_assignment.Adapter.CityGuidesAdapter;
import com.example.p2103174_assignment.Adapter.DealsAdapter;
import com.example.p2103174_assignment.Adapter.LongWeekendAdapter;
import com.example.p2103174_assignment.Adapter.RecommendationAdapter;
import com.example.p2103174_assignment.Model.Travel;
import com.example.p2103174_assignment.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Home extends Fragment {

    private RecommendationAdapter mRecAdapter;
    private ArrayList<Travel> mArrayListDestination = new ArrayList<>();

    private CityGuidesAdapter mCityGuideAdapter;
    private ArrayList<Travel> mArraymCityGuide = new ArrayList<>();

    private DealsAdapter mDealsAdapter;
    private ArrayList<Travel> mArrayListDeals = new ArrayList<>();

    private LongWeekendAdapter mLongWeekendAdapter;
    private ArrayList<Travel> mArrayListLongWeekend = new ArrayList<>();

    private Context mContext;

    @BindView(R.id.recView_popularDestination)
    RecyclerView rvPopulardestination;

    @BindView(R.id.PB_popularDestination)
    ProgressBar pgbPopulardestination;

    @BindView(R.id.recView_longWeekend)
    RecyclerView rvlongWeekend;

    @BindView(R.id.PB_longWeekend)
    ProgressBar pgbLongWeekend;

    @BindView(R.id.recView_deals)
    RecyclerView rvDeals;

    @BindView(R.id.pgb_Deals)
    ProgressBar pgbDeals;

    @BindView(R.id.recView_cityGuide)
    RecyclerView rvCityGuides;

    @BindView(R.id.pgb_CityGuide)
    ProgressBar pgbCityGuide;

    @BindView(R.id.txtView_acLocation)
    AutoCompleteTextView acLocation;

    int[] destinationPic = {R.drawable.austria, R.drawable.finland, R.drawable.netherlands, R.drawable.ireland, R.drawable.sweden,
            R.drawable.germany, R.drawable.denmark, R.drawable.switzerland, R.drawable.norway, R.drawable.france, R.drawable.spain, R.drawable.canada,
            R.drawable.bulgaria, R.drawable.belgium, R.drawable.estonia, R.drawable.uk, R.drawable.luxembourg, R.drawable.new_zealand, R.drawable.italy,
            R.drawable.australia, R.drawable.latvia, R.drawable.cyprus, R.drawable.singapore, R.drawable.japan, R.drawable.macedonia, R.drawable.south_korea,
            R.drawable.moldova, R.drawable.slovokia, R.drawable.romania, R.drawable.portugal, R.drawable.poland, R.drawable.czech_republic, R.drawable.slovenia,
            R.drawable.costa_rica, R.drawable.chile, R.drawable.iceland, R.drawable.lithuania, R.drawable.georgia, R.drawable.hungary, R.drawable.usa,
            R.drawable.russia, R.drawable.greece, R.drawable.bosnia, R.drawable.india, R.drawable.malaysia, R.drawable.armenia, R.drawable.south_africa,
            R.drawable.mauritius, R.drawable.uruguay, R.drawable.albania};

    int[] longWeekendPic = {R.drawable.bermuda, R.drawable.vancouver, R.drawable.baja, R.drawable.antigua, R.drawable.costa_rica, R.drawable.montreal,
            R.drawable.belize, R.drawable.cartagena};

    int[] dealsPic = {R.drawable.bali, R.drawable.japan, R.drawable.bangkok, R.drawable.goa, R.drawable.geneva, R.drawable.milan,
            R.drawable.paris, R.drawable.ibiza};

    int[] cityGuidePic = {R.drawable.bon_appetite, R.drawable.landmarks, R.drawable.airbnb, R.drawable.tips};


    private static final String[] COUNTRIES = new String[]{
            "Austria", "Finland", "Netherlands", "Ireland", "Sweden", "Germany", "Denmark", "Switzerland", "Norway",
            "France", "Spain", "Canada", "Bulgaria", "Belgium", "Estonia", "United Kingdom", "Luxembourg", "New Zealand",
            "Italy", "Australia", "Latvia", "Cyprus", "Singapore", "Japan", "North Macedonia", "South Korea", "Moldova",
            "Slovakia", "Romania", "Portugal", "Poland", "Czech Republic", "Slovenia", "Costa Rica", "Chile", "Iceland",
            "Lithuania", "Georgia", "Hungary", "United States", "Russia", "Greece", "Bosnia and Herzegovina", "India",
            "Malaysia", "Armenia", "South Africa", "Mauritius", "Uruguay", "Albania", "Bermuda", "Vancouver", "Baja",
            "Antigua", "Montreal", "Belize", "Cartagena", "Bali", "Japan", "Bangkok", "Goa", "Geneva", "Milan", "Paris", "Ibiza"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        System.out.println("Destinationpic" + destinationPic.length);

        mContext = getActivity();

        ButterKnife.bind(this, rootView);

        initUi(rootView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return rootView;
    }

    private void initUi(View rootView) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        acLocation.setAdapter(adapter);


        //------------rvpopular destination----------

        //for better performance of recyclerview.
        rvPopulardestination.setHasFixedSize(true);

        mRecAdapter = new RecommendationAdapter(getContext(), mArrayListDestination);
        rvPopulardestination.setAdapter(mRecAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvPopulardestination.setLayoutManager(llm);

        new LoadAllPopularDestination().execute();

        //------------rv long weekends----------

        //for better performance of recyclerview.
        rvlongWeekend.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llml = new LinearLayoutManager(mContext);
        llml.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llml.setOrientation(LinearLayoutManager.HORIZONTAL);
        llml.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvlongWeekend.setLayoutManager(llml);

        new LoadAllLongWeekend().execute();


        //------------rv deals----------

        //for better performance of recyclerview.
        rvDeals.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llmd = new LinearLayoutManager(mContext);
        llmd.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llmd.setOrientation(LinearLayoutManager.HORIZONTAL);
        llmd.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvDeals.setLayoutManager(llmd);

        new LoadAllDeals().execute();


        //------------rv cityguides----------

        //for better performance of recyclerview.
        rvCityGuides.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llmc = new LinearLayoutManager(mContext);
        llmc.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llmc.setOrientation(LinearLayoutManager.HORIZONTAL);
        llmc.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvCityGuides.setLayoutManager(llmc);

        new LoadAllCityGuide().execute();


        /*etLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_searchflight = new Intent(getContext(), SearchFlightActivity.class);
                startActivity(i_searchflight);
            }
        });*/
    }

    Travel travel;


    class LoadAllPopularDestination extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbPopulardestination.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                String[] destinationName = getResources().getStringArray(R.array.destination);
                String[] destinationID = getResources().getStringArray(R.array.destinationID);

                for (int i = 0 ; i < destinationName.length; i++)
                {
                    travel = new Travel();
                    travel.setId(i);
                    travel.setImages(destinationPic[i]);
                    travel.setID(destinationID[i]);
                    travel.setName(destinationName[i]);
                    mArrayListDestination.add(travel);
                    travel = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbPopulardestination.setVisibility(View.GONE);

            if (mArrayListDestination != null && mArrayListDestination.size() > 0) {
                mRecAdapter = new RecommendationAdapter(mContext, mArrayListDestination);
                rvPopulardestination.setAdapter(mRecAdapter);
                mRecAdapter.notifyDataSetChanged();
            }
        }
    }

    /*loading of long weekend item*/

    class LoadAllLongWeekend extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbLongWeekend.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                String[] longWeekend = getResources().getStringArray(R.array.allWeekend);
                String[] allWeekendID = getResources().getStringArray(R.array.allWeekendID);

                for (int i = 0 ; i < longWeekend.length; i++)
                {
                    travel = new Travel();
                    travel.setId(i);
                    travel.setImages(longWeekendPic[i]);
                    travel.setID(allWeekendID[i]);
                    travel.setName(longWeekend[i]);
                    mArrayListLongWeekend.add(travel);
                    travel = null;
//                    mArrayListDestination.add(new Destination(destinationName[i]));
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbLongWeekend.setVisibility(View.GONE);

            if (mArrayListLongWeekend != null && mArrayListLongWeekend.size() > 0) {
                mLongWeekendAdapter = new LongWeekendAdapter(mContext, mArrayListLongWeekend);
                rvlongWeekend.setAdapter(mLongWeekendAdapter);
                mLongWeekendAdapter.notifyDataSetChanged();
            }
        }
    }

    /*loading of deals item*/

    class LoadAllDeals extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbDeals.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                String[] flightDeals = getResources().getStringArray(R.array.flightDeals);
                String[] flightDealsCountry = getResources().getStringArray(R.array.flightDealsCountry);
                String[] flightDealsDesc = getResources().getStringArray(R.array.flightDealsDesc);
                String[] flightDealsExpDate = getResources().getStringArray(R.array.flightDealsExpDate);


                for (int i = 0 ; i < flightDeals.length; i++)
                {
                    travel = new Travel();
                    travel.setId(i);
                    travel.setImages(dealsPic[i]);
                    travel.setDesc(flightDealsDesc[i]);
                    travel.setExpDate(flightDealsExpDate[i]);
                    travel.setName(flightDealsCountry[i]);
                    mArrayListDeals.add(travel);
                    travel = null;
                }

            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbDeals.setVisibility(View.GONE);

            if (mArrayListDeals != null && mArrayListDeals.size() > 0) {
                mDealsAdapter = new DealsAdapter(mContext, mArrayListDeals);
                rvDeals.setAdapter(mDealsAdapter);
                mDealsAdapter.notifyDataSetChanged();
            }
        }
    }

    /*loading of cityguide item*/

    class LoadAllCityGuide extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbCityGuide.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                String[] cityGuideCat = getResources().getStringArray(R.array.cityGuideCat);
                String[] cityGuideDesc = getResources().getStringArray(R.array.cityGuideDesc);

                for (int i = 0; i < cityGuideCat.length; i++) {

                    travel = new Travel();
                    travel.setId(i);
                    travel.setImages(cityGuidePic[i]);
                    travel.setDesc(cityGuideDesc[i]);
                    travel.setName(cityGuideCat[i]);
                    mArraymCityGuide.add(travel);
                    travel = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbCityGuide.setVisibility(View.GONE);

            if (mArraymCityGuide != null && mArraymCityGuide.size() > 0) {
                mCityGuideAdapter = new CityGuidesAdapter(mContext, mArraymCityGuide);
                rvCityGuides.setAdapter(mCityGuideAdapter);
                mCityGuideAdapter.notifyDataSetChanged();
            }
        }
    }
}