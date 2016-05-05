package com.serveza.lepet.serveza.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.R;
import com.serveza.lepet.serveza.Utils.ImageDownloader;
import com.serveza.lepet.serveza.Utils.TextViewUtils;

import java.util.concurrent.Callable;

public class AddCommentActivity extends AppCompatActivity {

    private Core core;

    private String link;

    private int note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        Intent i = getIntent();

        core = (Core)i.getSerializableExtra("Core");
        link = (String)i.getSerializableExtra("link");
        note = 1;
        TextViewUtils.SetText((TextView)findViewById(R.id.ContentName), (String)i.getSerializableExtra("name"));
        ImageDownloader.SetImage((String)i.getSerializableExtra("image"), (ImageView)findViewById(R.id.ContentImage));
    }


    public void CancelClick(View v)
    {
        onBackPressed();
    }

    public void SendClick(View v)
    {
        core.network.SendComment(this, core, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return goback();
            }
        }, link, TextViewUtils.GetText((TextView) findViewById(R.id.CommentContent)), note);
    }

    private int goback() {
        onBackPressed();
        return 0;
    }

    public void selectyournoteClick(View view) {
        CharSequence colors[] = new CharSequence[] {"1", "2", "3", "4", "5"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your note");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                note = which + 1;
                Button button = ( Button)findViewById(R.id.noteButton);
                button.setText(String.valueOf(note));
            }
        });
        builder.show();
    }
}
