package com.example.p2103174_assignment.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2103174_assignment.Adapter.CustomSpinner;
import com.example.p2103174_assignment.Adapter.TextSizeAdapter;
import com.example.p2103174_assignment.Model.TextSizeData;
import com.example.p2103174_assignment.R;

public class TextSize_Activity extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener{

    private TextView txtView_Done;

    private TextSizeAdapter textSizeAdapter;

    private CustomSpinner textSize_spinner;

    private LinearLayout linearLayout_textSize;

    private androidx.appcompat.widget.SwitchCompat defaultTextSize_switch, customizeTextSize_switch;

    private String mTextSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_size);

        initWidget();

        spinnerSetup();

        pageDirectories();
    }

    private void spinnerSetup() {
        textSizeAdapter = new TextSizeAdapter(getApplicationContext(), TextSizeData.getTextSizeList());
        textSize_spinner.setAdapter(textSizeAdapter);
        textSize_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                mTextSize = String.valueOf(parent.getItemAtPosition(i));
                if (mTextSize == "14")
                    Toast.makeText(TextSize_Activity.this, "Your text size for this app has been changed to 14", Toast.LENGTH_SHORT).show();
                else if (mTextSize == "16")
                    Toast.makeText(TextSize_Activity.this, "Your text size for this app has been changed to 16", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mTextSize = textSize_spinner.getSelectedItem().toString();
    }

    private void pageDirectories() {
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainSettings_Activity.class));
            }
        });

        defaultTextSize_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    customizeTextSize_switch.setChecked(false);
                }
                else
                {
                    customizeTextSize_switch.setChecked(true);
                }
            }
        });

        customizeTextSize_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                {
                    defaultTextSize_switch.setChecked(false);

                    //-->Spinner becomes visible
                    linearLayout_textSize.setVisibility(View.VISIBLE);
                }
                else
                {
                    defaultTextSize_switch.setChecked(true);

                    //-->Spinner becomes gone
                    linearLayout_textSize.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initWidget() {
        txtView_Done = findViewById(R.id.txtView_Done);

        defaultTextSize_switch = findViewById(R.id.defaultTextSize_switch);
        customizeTextSize_switch = findViewById(R.id.customizeTextSize_switch);

        textSize_spinner = findViewById(R.id.textSize_spinner);

        linearLayout_textSize = findViewById(R.id.linearLayout_textSize);
    }

    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        textSize_spinner.setBackground(getResources().getDrawable(R.drawable.bg_spinner_vocation_up));
        textSize_spinner.setBackground(getResources().getDrawable(R.drawable.bg_spinner_vocation_up));
    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        textSize_spinner.setBackground(getResources().getDrawable(R.drawable.bg_spinner_vocation_up));
        textSize_spinner.setBackground(getResources().getDrawable(R.drawable.bg_spinner_vocation_up));
    }
}