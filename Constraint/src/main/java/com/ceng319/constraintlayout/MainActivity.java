package com.ceng319.constraintlayout;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    WebView myWebView;
    RadioGroup myRadioGroup;
    EditText myEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.webview);
        myRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        myEditText = (EditText) findViewById(R.id.editText3);

        gotoWebsite("www.yahoo.ca");
        closeKeyboard();
        // Method 1 to add ClickEvent Listener to a View.
        // How to do it in program??
        RadioButton mRadioButton = (RadioButton) findViewById(R.id.radioButton1);
        mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoWebsite("www.google.ca");
                myRadioGroup.check(R.id.radioButton1);
            }
        });

        myEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    gotoWebsite(myEditText.getText().toString());
                    return true;
                }
                return false;
            }
        });
   }

    // Method 2 to add ClickEvent Listener to a View. Add it from the layout design.
    public void Radio2Click(View v){
        gotoWebsite("www.humber.ca");
        myRadioGroup.check(R.id.radioButton2);
    }

    public void gotoWebsite(String url){
        myRadioGroup.clearCheck();
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://" +url);
        myEditText.setText(url);
        closeKeyboard();

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
