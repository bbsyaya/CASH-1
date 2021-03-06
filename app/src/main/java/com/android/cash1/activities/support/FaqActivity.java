package com.android.cash1.activities.support;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cash1.R;
import com.android.cash1.model.Cash1Activity;
import com.android.cash1.model.FaqItem;
import com.android.cash1.rest.Cash1ApiService;
import com.android.cash1.rest.Cash1Client;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FaqActivity extends Cash1Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        setupActionBar();
        setupFooter();

        loadQuestionsAndAnswers();
        // TODO: Close answer on tap of question (when opened)
    }

    private void loadQuestionsAndAnswers() {
        Cash1ApiService service = new Cash1Client().getApiService();
        service.listQuestionsAndAnswers(new Callback<List<FaqItem>>() {
            @Override
            public void success(List<FaqItem> itemList, Response response) {
                final ViewGroup container = (ViewGroup) findViewById(R.id.container);

                for (final FaqItem item : itemList) {
                    LinearLayout itemLayout = (LinearLayout) getLayoutInflater().inflate(
                            R.layout.list_item_faq, null);

                    final TextView answerView = (TextView) itemLayout.findViewById(R.id.answer_textview);
                    answerView.setText(item.getAnswer());
                    answerView.setId(item.getId());

                    final TextView questionView = (TextView) itemLayout.findViewById(R.id.question_textview);
                    questionView.setText(item.getQuestion());
                    questionView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < 10; i++) {
                                TextView answerView = (TextView) container.findViewById(i);
                                if (answerView != null) {
                                    answerView.setVisibility(View.GONE);
                                }
                            }

                            if (answerView.getVisibility() == View.GONE) {
                                answerView.setVisibility(View.VISIBLE);
                            } else {
                                answerView.setVisibility(View.GONE);
                            }
                        }
                    });

                    container.addView(itemLayout);
                }

                findViewById(R.id.main_menu).setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(FaqActivity.this, getString(R.string.faq_load_error),
                        Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
    }

    @Override
    public void showFaq(View view) {
        closeFooter();
    }
}
