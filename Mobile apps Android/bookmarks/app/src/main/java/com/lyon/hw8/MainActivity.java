package com.lyon.hw8;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String url = "";
    String[] itemArray = new String[30];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submit = (Button) findViewById(R.id.btnSubmit);
        itemArray[0] = "https://www.google.com/";
        WebView viewer = (WebView) findViewById(R.id.webView);
        WebSettings webset = viewer.getSettings();
        webset.setBuiltInZoomControls(true);
        submit.setOnClickListener(view -> {
            EditText urlInput = (EditText) findViewById(R.id.urlText);
            viewer.loadUrl(urlInput.getText().toString());
        });

        Button bookmark = (Button) findViewById(R.id.btnBookmark);
        bookmark.setOnClickListener(view -> {
           WebView wv = (WebView) findViewById(R.id.webView);
           url = wv.getUrl().toString();
           Log.i("user url", url);
           invalidateOptionsMenu();
        });

        viewer.loadUrl(itemArray[0]);

    }


    private void createMenu(Menu menu) {
        for (int i = 0; i < itemArray.length; i++) {
            if (itemArray[i] == null) break;
            else menu.add(itemArray[i]);
        }
//        mi1.setAlphabeticShortcut('a');
//
//        MenuItem mi2 = menu.add(0,1,1, "item 2");
//        mi1.setAlphabeticShortcut('b');
//
//        MenuItem mi3 = menu.add(0,2,2, "item 3");
//        mi1.setAlphabeticShortcut('c');
//
//        MenuItem mi4 = menu.add(0,3,3, "item 4");
//        mi1.setAlphabeticShortcut('d');
//
//        menu.add(0,4,4, "item 5");
//        menu.add(0, 5, 5, "item 6");
//        menu.add(0, 6, 6, "item 7");
    }



    private boolean menuChoice(MenuItem mitem){
        WebView viewer = (WebView) findViewById(R.id.webView);
        viewer.loadUrl(mitem.getTitle().toString());
        Toast.makeText(this, mitem.getTitleCondensed(),Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){

        for(int i=0; i< menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            itemArray[i] = menu.getItem(i).toString();
            if (item.getTitle().toString() == url) {
                return false;
            }
        }
        if(url.contains("http"))
            menu.add( url);

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem mitem){
        return menuChoice(mitem);
    }

}