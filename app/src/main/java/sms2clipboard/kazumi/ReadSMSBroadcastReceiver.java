package sms2clipboard.kazumi;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Nene Sakura on 2018-01-26.
 * ====
 * 接收来自短信的广播.
 */

public class ReadSMSBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // 获取短信内容
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[pdus != null ? pdus.length : 0];
        for (int i = 0; i < messages.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) (pdus != null ? pdus[i] : null));
        }
        String fullMessage = "";
        for (SmsMessage message : messages) {
            fullMessage += message.getMessageBody();
        }
        abortBroadcast();

        // 将短信内容复制到剪切板
        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", fullMessage);
        myClipboard.setPrimaryClip(myClip);
    }
}
