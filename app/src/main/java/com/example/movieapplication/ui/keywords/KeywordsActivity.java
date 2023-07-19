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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieapplication.R;

import java.util.Arrays;
import java.util.List;

public class KeywordsActivity extends AppCompatActivity {

    private EditText editTextKeywords;
    private Button btnSaveKeywords;

    private static final String KEY_KEYWORDS = "keywords";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_keywords);

        editTextKeywords = findViewById(R.id.editTextKeywords);
        btnSaveKeywords = findViewById(R.id.btnSaveKeywords);
        btnSaveKeywords.setEnabled(false);

        // Check if there are saved keywords and populate the EditText with them
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String savedKeywords = sharedPreferences.getString(KEY_KEYWORDS, "");
        editTextKeywords.setText(savedKeywords);

        // Enable the Save button only when there is text in the EditText
        editTextKeywords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSaveKeywords.setEnabled(!TextUtils.isEmpty(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        btnSaveKeywords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveKeywords();
            }
        });
    }

    private void saveKeywords() {
        String keywords = editTextKeywords.getText().toString().trim();

        // Save the keywords in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_KEYWORDS, keywords);
        editor.apply();

        // Finish the activity and return to the previous screen
        finish();
    }
}


