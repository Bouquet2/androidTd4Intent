package fr.paris10.android.td4intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static Intent intent;
    //public static String EXTRA_RESULT = "result";
    private TextView textView;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("onCreate", "called");
        intent = getIntent();
        setTextView();
    }

    void getIntentText(String param) {
        Log.d("getText", "called");
        if(intent != null) {
            title = intent.getStringExtra(param);
        }
        if(title == null)
            title="No title found in intent";
    }

    public void setTextView() {
        textView = (TextView) findViewById(R.id.activity_second_title);
        getIntentText(MainActivity.EXTRA_TITLE);
        textView.setText(title);
        Log.d("setTextView", "called");
    }

    /*
    Never called
     */
    @Override
    public void startActivity(Intent intent) {
        Log.d("startActivity", "called");
        SecondActivity.intent = intent;
        if(intent == null)
            Log.d("startActivity", "intent is null");
        else
            Log.d("startActivity", "intent is ok");
    }

    //TODO startActivityForResult

    /*
    @Override
    public void finish() {
        intent = new Intent();
        intent.putExtra(EXTRA_RESULT, "résultât");
        setResult(RESULT_OK, intent);
        super.finish();
    }
    */
}
