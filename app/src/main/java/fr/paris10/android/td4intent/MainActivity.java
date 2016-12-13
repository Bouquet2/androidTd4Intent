package fr.paris10.android.td4intent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static Intent intent;
    public static PendingIntent pendingIntent;
    public static String EXTRA_TITLE = "title";
    private AlarmManager alarmManager;
    private Button shortButton;
    private Button longButton;
    private Class destination = SecondActivity.class;
    private int timeShort = 1;
    private int timeLong = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        shortButton = (Button) findViewById(R.id.activity_main_short_button);
        shortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitBeforeSendingMessage("30 secondes", timeShort);
            }
        });

        longButton = (Button) findViewById(R.id.activity_main_long_button);
        longButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitBeforeSendingMessage("60 secondes", timeLong);
            }
        });
    }

    /**
     * Wait x seconds before sending message to destination's activity
     * @param msg
     * @param time
     */
    private void waitBeforeSendingMessage(String msg, int time) {
        intent = new Intent(this, destination);
        intent.putExtra(EXTRA_TITLE, msg);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + time * 1000,
                pendingIntent);
        Log.d("sendMessage", "called");
    }
    //TODO startActivityForResult
    //TODO onActivityResult
}
