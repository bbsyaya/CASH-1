package com.android.cash1;

import android.os.Bundle;

import com.android.cash1.model.Cash1Activity;

public class FindOfficeResultActivity extends Cash1Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_office_result);

        setupActionBar();
        setupFooter();
    }
}
