package com.wouterv.quantifiedstudents.entities.ui;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ivo on 23-6-2016.
 */
public class EmotionText {
    private final TextView textView;
    private final MessageEntity[] messages;

    public EmotionText(TextView textView, MessageEntity... messages) {
        this.textView = textView;
        this.messages = messages;
    }

    public void setText(String prefix, double ratio) {
        MessageEntity message = null;

        for (MessageEntity m : this.messages)
            if (m.isInBounds(ratio)) {
                message = m;
                break;
            }

        this.textView.setText(prefix + message.getText());
    }
}
