package com.cavdardevelopment.morsecode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import net.mabboud.android_tone_player.OneTimeBuzzer;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<Character, Boolean[]> morseTable;

    private MyTextWatcher myTextWatcher;

    private TextView textViewMorse;
    private EditText editTextTextMultiLine2;
    private Button buttonCopy, buttonSound;

    private Intent morseIntent;

    private OneTimeBuzzer buzzer;
    private double dotDuration = 0.3, dashDuration = 0.4  , waitDuration = 0.3;

    private AdView adViewBanner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAd();

        morseIntent = new Intent(this, MorseAlphabetActivity.class);

        init();

        setMorseTable();
    }

    private void initAd() {
        adViewBanner1 = new AdView(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        adViewBanner1 = findViewById(R.id.adViewBanner1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewBanner1.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemMorseAlphabet:
                startActivity(morseIntent);
                break;
            case R.id.menuItemOtherApps:
                launchPlayStore(this);
                break;
        }

        return true;
    }

    private void setMorseTable() {
        morseTable.put('A', new Boolean[]{false, true});
        morseTable.put('a', new Boolean[]{false, true});

        morseTable.put('B', new Boolean[]{true, false, false, false});
        morseTable.put('b', new Boolean[]{true, false, false, false});

        morseTable.put('C', new Boolean[]{true, false, true, false});
        morseTable.put('c', new Boolean[]{true, false, true, false});

        morseTable.put('D', new Boolean[]{true, false, false});
        morseTable.put('d', new Boolean[]{true, false, false});

        morseTable.put('E', new Boolean[]{false});
        morseTable.put('e', new Boolean[]{false});

        morseTable.put('F', new Boolean[]{false, false, true, false});
        morseTable.put('f', new Boolean[]{false, false, true, false});

        morseTable.put('G', new Boolean[]{true, true, false});
        morseTable.put('g', new Boolean[]{true, true, false});

        morseTable.put('H', new Boolean[]{false, false, false, false});
        morseTable.put('h', new Boolean[]{false, false, false, false});

        morseTable.put('I', new Boolean[]{false, false});
        morseTable.put('i', new Boolean[]{false, false});

        morseTable.put('J', new Boolean[]{false, true, true, true});
        morseTable.put('j', new Boolean[]{false, true, true, true});

        morseTable.put('K', new Boolean[]{true, false, true});
        morseTable.put('k', new Boolean[]{true, false, true});

        morseTable.put('L', new Boolean[]{false, true, false, false});
        morseTable.put('l', new Boolean[]{false, true, false, false});

        morseTable.put('M', new Boolean[]{true, true});
        morseTable.put('m', new Boolean[]{true, true});

        morseTable.put('N', new Boolean[]{true, false});
        morseTable.put('n', new Boolean[]{true, false});

        morseTable.put('O', new Boolean[]{true, true, true});
        morseTable.put('o', new Boolean[]{true, true, true});

        morseTable.put('P', new Boolean[]{false, true, true, false});
        morseTable.put('p', new Boolean[]{false, true, true, false});

        morseTable.put('Q', new Boolean[]{true, true, false, true});
        morseTable.put('q', new Boolean[]{true, true, false, true});

        morseTable.put('R', new Boolean[]{false, true, false});
        morseTable.put('r', new Boolean[]{false, true, false});

        morseTable.put('S', new Boolean[]{false, false, false});
        morseTable.put('s', new Boolean[]{false, false, false});

        morseTable.put('T', new Boolean[]{true});
        morseTable.put('t', new Boolean[]{true});

        morseTable.put('U', new Boolean[]{false, false, true});
        morseTable.put('u', new Boolean[]{false, false, true});

        morseTable.put('V', new Boolean[]{false, false, false, true});
        morseTable.put('v', new Boolean[]{false, false, false, true});

        morseTable.put('W', new Boolean[]{false, true, true});
        morseTable.put('w', new Boolean[]{false, true, true});

        morseTable.put('X', new Boolean[]{true, false, false, true});
        morseTable.put('x', new Boolean[]{true, false, false, true});

        morseTable.put('Y', new Boolean[]{true, false, true, true});
        morseTable.put('y', new Boolean[]{true, false, true, true});

        morseTable.put('Z', new Boolean[]{true, true, false, false});
        morseTable.put('z', new Boolean[]{true, true, false, false});

        morseTable.put('1', new Boolean[]{false, true, true, true, true});
        morseTable.put('2', new Boolean[]{false, false, true, true, true});
        morseTable.put('3', new Boolean[]{false, false, false, true, true});
        morseTable.put('4', new Boolean[]{false, false, false, false, true});
        morseTable.put('5', new Boolean[]{false, false, false, false, false});
        morseTable.put('6', new Boolean[]{true, false, false, false, false});
        morseTable.put('7', new Boolean[]{true, true, false, false, false});
        morseTable.put('8', new Boolean[]{true, true, true, false, false});
        morseTable.put('9', new Boolean[]{true, true, true, true, false});
        morseTable.put('0', new Boolean[]{true, true, true, true, true});
    }

    private void init() {
        morseTable = new HashMap<>();

        myTextWatcher = new MyTextWatcher(this);

        textViewMorse = findViewById(R.id.textViewMorse);
            textViewMorse.setEnabled(false);

        editTextTextMultiLine2 = findViewById(R.id.editTextTextMultiLine2);
            editTextTextMultiLine2.addTextChangedListener(myTextWatcher);

            buzzer = new OneTimeBuzzer();
    }

    public void translateMorseToLatin(char[] chars){
        textViewMorse.setText("");

        for(char c : chars){
            if(morseTable.containsKey(c)){
                for(boolean b : morseTable.get(c)){
                    if(b)
                        textViewMorse.append("_");
                    else
                        textViewMorse.append(".");
                }
            }
            else if(c == ' '){
                textViewMorse.append(" ");
            }
            else if(c == '\n'){
                textViewMorse.append("\n");
            }

            textViewMorse.append(" ");
        }
    }

    public void translateLatinToMorse(char[] chars){

    }

    // buttonCopy onClick
    public void buttonCopyOnClick(View view){
        System.out.println(textViewMorse.getText().toString());
        if(!textViewMorse.getText().toString().isEmpty()){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Morse Code", textViewMorse.getText());
            assert clipboard != null;
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "Morse Code copied to clipboard", Toast.LENGTH_LONG).show();
        }
    }

    // buttonSound onClick
    public void buttonSoundOnClick(View view) throws InterruptedException {
        Thread soundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                buzzer.setVolume(100);
                int i = 0;
                int leng = textViewMorse.getText().length();

                if(leng > 0) {
                    try {
                        fun1(i, textViewMorse.getText().toString(), leng);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        soundThread.start();
    }

    private void fun1(int i, String e, int leng) throws InterruptedException {
        if(e.charAt(i) == '.'){
            playSound(dotDuration,false);
        } else if(e.charAt(i) == '_'){
            playSound(dashDuration, false);
        }
        else if(e.charAt(i) == ' '){
            playSound(waitDuration, true);
        }

        i++;
        if(i != leng-1)
            fun2(i, e, leng);
    }

    private void fun2(int i, String e, int leng) throws InterruptedException {
        if(e.charAt(i) == '.'){
            playSound(dotDuration, false);
        } else if(e.charAt(i) == '_'){
            playSound(dashDuration, false);
        }
        else if(e.charAt(i) == ' '){
            playSound(waitDuration, true);
        }

        i++;
        if(i != leng - 1)
            fun1(i, e, leng);
    }

    private void playSound(double duration, boolean isSpace) throws InterruptedException {
        if (!isSpace) {
            buzzer.setDuration(duration);
            buzzer.play();
        }
        Thread.sleep((long) (duration * 1000));
    }

    public void launchPlayStore(Context context) {
        Intent intent = null;
        try {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Cavdar"));
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Cavdar")));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        buzzer.stop();
    }
}