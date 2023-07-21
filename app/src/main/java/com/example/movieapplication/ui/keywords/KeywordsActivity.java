package com.example.movieapplication.ui.keywords;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieapplication.R;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeywordsActivity extends AppCompatActivity {

    private EditText keywordsEditText;
    private Button btnSaveKeywords;

    private static final String KEY_KEYWORDS = "keywords";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_keywords);

        keywordsEditText = findViewById(R.id.keywordsEditText);
        btnSaveKeywords = findViewById(R.id.btnSaveKeywords);

        keywordsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String keywords = charSequence.toString().trim();
                btnSaveKeywords.setEnabled(!keywords.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnSaveKeywords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveKeywords();
            }
        });
    }

    private void showPreferencesSavedPopup() {
        Toast.makeText(this, "Your preferences are saved!", Toast.LENGTH_SHORT).show();}

    private void saveKeywords() {
        // Get the keywords entered by the user
        String keywordsText = keywordsEditText.getText().toString();

        // Split the keywords by semicolon to get individual keywords
        String[] keywordsArray = keywordsText.split(";");

        // Create a Set to store the keywords
        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywordsArray));

        // Save the keywords in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("selectedKeywords", keywordsSet);
        editor.apply();

        Toast.makeText(this, "Keywords saved successfully!", Toast.LENGTH_SHORT).show();

        // Finish the activity and return to the previous screen
        finish();
    }
}


