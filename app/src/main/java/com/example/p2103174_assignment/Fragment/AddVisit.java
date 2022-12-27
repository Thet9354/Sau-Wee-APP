package com.example.p2103174_assignment.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2103174_assignment.AddActivity;
import com.example.p2103174_assignment.Addhelper;
import com.example.p2103174_assignment.HistoryActivity;
import com.example.p2103174_assignment.MainActivity;
import com.example.p2103174_assignment.MapActivity2;
import com.example.p2103174_assignment.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddVisit extends Fragment {

    private static final int RESULT_OK = -1;
    private Context mContext;

    private Calendar myCalendar;

    private DatePickerDialog.OnDateSetListener date, date2;

    Addhelper addhelper;
    Addhelper db;

    public int PICK_IMAGE = 1;
    private Uri selectedImageUri = null;
    private Bitmap bitmap = null;

    @BindView(R.id.ll_pic)
    LinearLayout ll_pic;

    @BindView(R.id.imgView_countryImg)
    ImageView imgView_countryImg;

    @BindView(R.id.editTxt_country)
    EditText editTxt_country;

    @BindView(R.id.ll_FlyOut)
    LinearLayout ll_FlyOut;

    @BindView(R.id.txtView_Date)
    TextView txtView_Date;

    @BindView(R.id.ll_liveLocation)
    LinearLayout ll_liveLocation;

    @BindView(R.id.editTxt_latView)
    EditText editTxt_latView;

    @BindView(R.id.editTxt_lonView)
    EditText editTxt_lonView;

    @BindView(R.id.editTxt_note)
    EditText editTxt_note;

    @BindView(R.id.btn_SearchFlight)
    androidx.appcompat.widget.AppCompatButton btn_SearchFlight;

    String userDate, userCountry, userlat, userlong, userNote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_visit, container, false);

        mContext = getActivity();

        ButterKnife.bind(this, rootView);

        initUi(rootView);

        pageDirectories(rootView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        return rootView;
    }

    private void initUi(View rootView) {

        myCalendar = Calendar.getInstance();

        addhelper = new Addhelper(mContext);
        db = new Addhelper(mContext);

    }

    private void pageDirectories(View rootView) {

        ll_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose Picture"), PICK_IMAGE);
            }
        });

        ll_FlyOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ll_liveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 =new Intent(mContext, MapActivity2.class);
                startActivity(intent3);
            }
        });

        final String[] userlatitude = new String[1];
        final String[] userlongitude = new String[1];

        Bundle bundle = getArguments();

        try {
            userlatitude[0] = bundle.getString("latitude");
            userlongitude[0] = bundle.getString("longitude");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        editTxt_latView.setText(userlatitude[0]);
        editTxt_lonView.setText(userlongitude[0]);

        btn_SearchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userDate = txtView_Date.getText().toString();
                userCountry = editTxt_country.getText().toString();
                userlat = editTxt_latView.getText().toString();
                userlong = editTxt_lonView.getText().toString();
                userNote = editTxt_note.getText().toString();

                dateValidation();
                countryValidation();
                latValidation();
                lonValidation();
                noteValidation();
                addData();



            }
        });

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

    }

    private void addData() {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        if (bitmap != null) {

            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
            byte[] bArray = bos.toByteArray();

            if(bArray != null) {

                if (!dateValidation() | !latValidation() | !lonValidation() | !noteValidation() | !countryValidation())
                    Toast.makeText(mContext, "Entry was not saved due to empty inputs", Toast.LENGTH_SHORT).show();
                else
                {
                    addhelper.insert(userDate, userCountry, userlat, userlong, userNote, bArray);
                    Toast.makeText(mContext, "Visit Entry saved successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                }


            }else{
                Toast.makeText(mContext, "Entry not saved, image too large", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(mContext, "Entry not saved, Please put in an image", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean noteValidation() {

        if (userNote.isEmpty())
        {
            editTxt_note.setError("Field cannot be empty");
            return false;
        }
        else
            return true;
    }

    private boolean lonValidation() {

        if (userlong.isEmpty())
        {
            editTxt_lonView.setError("Field cannot be empty");
            return false;
        }
        else
            return true;
    }

    private boolean latValidation() {

        if (userlat.isEmpty())
        {
            editTxt_latView.setError("Field cannot be empty");
            return false;
        }
        else
            return true;
    }

    private boolean countryValidation() {

        if (userCountry.isEmpty())
        {
            editTxt_country.setError("Field cannot be empty");
            return false;
        }
        else
            return true;
    }

    private boolean dateValidation() {

        if (userDate.isEmpty())
        {
            txtView_Date.setError("Field cannot be empty");
            return false;
        }
        else
            return true;
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtView_Date.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                selectedImageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    bitmap = Bitmap.createScaledBitmap(bitmap,  600,392,true);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (selectedImageUri != null) {

                    ll_pic.setVisibility(View.GONE);

                    imgView_countryImg.setImageURI(selectedImageUri);
                    imgView_countryImg.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(mContext, "photo is too large", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}