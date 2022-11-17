package com.example.lyonhw3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup main = (ViewGroup) findViewById(R.id.fragment2);
        ViewGroup [] keyboard = {
                (ViewGroup) main.getChildAt(0),
                (ViewGroup) main.getChildAt(1),
                (ViewGroup) main.getChildAt(2),
                (ViewGroup) main.getChildAt(3),
                (ViewGroup) main.getChildAt(4)
        };
        ViewGroup specialKeys = (ViewGroup)keyboard[4].getChildAt(0);
        for(int i=0; i<specialKeys.getChildCount(); i++){
            View b = specialKeys.getChildAt(i);
            String but = new String(String.valueOf(b));
            but = but.substring(but.indexOf("/b")+2, but.length()-1);
            String val = but;
            //Log.i("buttonval:", val);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonClick(val);
                }
            });
        }
        //System.out.println(specialKeys.getClass());
        for(int i=0; i<keyboard.length-1; i++){
            for(int j=0; j<keyboard[i].getChildCount(); j++){
                View b = keyboard[i].getChildAt(j);
                String but = new String(String.valueOf(b));
                but = but.substring(but.indexOf("/b")+2, but.length()-1);
                String val = but;
                b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                          buttonClick(val);
                        }
                    });
                //System.out.println(b.getClass());
            }
        }

//        View frag2 = findViewById(R.id.fragment2);
//        int i = frag2.toString().indexOf("app:id")+7;
//        Log.i("frag2", frag2.toString().substring(i));
//        frag2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("click", "click");
//            }
//        });
//        FragmentManager fragManager= getFragmentManager();
//        FragmentTransaction fragTransaction = fragManager.beginTransaction();
//        DisplayMetrics display = this.getResources().getDisplayMetrics();
//        int width = display.widthPixels;
//        int height = display.heightPixels;
        //Fragment1 frag1 = new Fragment1();
//        Fragment2 frag2 = new Fragment2();
        //fragTransaction.replace(R.id.fragment1, frag1);
        //fragTransaction.replace(R.id.fragment2, frag2);
//        fragTransaction.commit();

//        View keyboard = (View)findViewById(R.id.fragment2);
//        keyboard.setOnClickListener(v -> {
//                System.out.println(v.getId());
//                Context con = v.getContext();
//                String msg = new String("TEST");
//                Toast tst = Toast.makeText(con, msg, Toast.LENGTH_SHORT);
//                tst.show();
//        });

    }

    public void buttonClick(String val){
        TextView typer = findViewById(R.id.lblFragment1);
        if(val.length()>1){

            String in = new String();
            switch (val) {
                case "ret":
                    in = "\n";
                    break;
                case "qmark":
                    in = "?";
                    break;
                case "dot":
                    in = ".";
                    break;
                case "comma":
                    in = ",";
                    break;
                case "clear":
                    in="";
                    typer.setText("");
                    break;
                case "space":
                    in = " ";
                    break;
                case "del":
                    in="";
                    String buffer = new String(String.valueOf(typer.getText()));
                    buffer = buffer.substring(0, buffer.length()-1);
                    typer.setText(buffer);
                    break;
            }
            typer.setText(typer.getText() + in);
        }
        else {
            typer.setText(typer.getText() + val);
        }
    }

}