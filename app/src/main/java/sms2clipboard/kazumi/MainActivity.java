package sms2clipboard.kazumi;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter readSMSIntentFilter = new IntentFilter();
        readSMSIntentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        readSMSIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        ReadSMSBroadcastReceiver readSMSBroadcastReceiver
                = new ReadSMSBroadcastReceiver();
        registerReceiver(readSMSBroadcastReceiver, readSMSIntentFilter);

        Toast.makeText(this, "SMS2Clipboard 已启动", Toast.LENGTH_SHORT).show();
    }
}
