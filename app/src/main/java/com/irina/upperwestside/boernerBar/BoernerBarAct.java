package com.irina.upperwestside.boernerBar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.irina.upperwestside.R;

import java.util.Arrays;

public class BoernerBarAct extends AppCompatActivity {

    private static String superPassword = "1";

    private BarMenuDatabase barMenuDatabase = new BarMenuDatabase();

    private Context context = this;

    private TextView announcementTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boerner_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initList();

        this.announcementTxtView = findViewById(R.id.announcementTxt);
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String currAnnouncementText = announcementTxtView.getText().toString();
        editor.putString("BoernerBarAnnouncement", currAnnouncementText);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        String announcementTxt = prefs.getString("BoernerBarAnnouncement", "Open 20 PM - 2 AM");
        announcementTxtView.setText(announcementTxt);
    }

    private void createPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText inputPw = new EditText(this);
        inputPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        builder.setMessage("You have to enter a password before you can change the text ")
                .setTitle("Password required")
                .setView(inputPw)

                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        String m_Text = inputPw.getText().toString();
                        if(superPassword.equals(m_Text)){
                            createChangeAnnouncementDialog();
                        } else {
                            Toast toast = Toast.makeText(context, "Wrong Password", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }

    private void createChangeAnnouncementDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        input.setLines(4);

        builder.setMessage("Enter new announcement: ")
                .setTitle("Announcement")
                .setView(input)

                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        String m_Text = input.getText().toString();
                        TextView announcementTxt = findViewById(R.id.announcementTxt);
                        announcementTxt.setText(m_Text);
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.boerner_bar_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.UpdateAnnouncement:
                this.createPasswordDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initList(){
        ListView currencyListView = findViewById(R.id.cartList);
        BarMenuAdapter myAdapter = new BarMenuAdapter(Arrays.asList(this.barMenuDatabase.getBarMenuItems()));
        currencyListView.setAdapter(myAdapter);
    }

}
