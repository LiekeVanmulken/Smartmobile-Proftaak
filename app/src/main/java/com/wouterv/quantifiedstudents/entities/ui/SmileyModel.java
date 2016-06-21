package com.wouterv.quantifiedstudents.entities.ui;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ivo on 21-6-2016.
 */
public class SmileyModel {
    private final ImageView imageView;
    private final TextView textView;
    private final MessageEntity[] messages;

    public SmileyModel(ImageView imageView, TextView textView, MessageEntity... messages) {
        this.imageView = imageView;
        this.textView = textView;
        this.messages = messages;
    }

    public void setSmiley(String prefix, double ratio) {
        MessageEntity message = null;

        for (MessageEntity m : this.messages)
            if (m.isInBounds(ratio)) {
                message = m;
                break;
            }

        this.imageView.setImageResource(message.getImageResource());
        this.textView.setText(prefix + message.getText());
    }
}
