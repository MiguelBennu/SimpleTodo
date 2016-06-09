package com.iryarte.simpletodo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EditItemActivity extends AppCompatActivity {

    private int pos;
    private long id;
    private ArrayList<String> items;
    private EditText etEditItem;
    private Button btnEditItem;
    static String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        //Extras received
        pos = getIntent().getIntExtra("LIST_ITEM_POSITION",0);
        id = getIntent().getLongExtra("LIST_ITEM_ID", 0);

        //Delcare views and find resources
        etEditItem = (EditText) findViewById(R.id.etEditItem);
        btnEditItem = (Button) findViewById(R.id.btnEditItem);

        //read the contents of item in list
        String item = readItemsByPos();
        etEditItem.setText(item);
        //set selection
        etEditItem.setSelection(etEditItem.getText().length());
        //set focus
        etEditItem.requestFocus();

/*                  //This block checks if Focus has been set.

                    boolean checkFocus=etEditItem.requestFocus();
                    Log.i("CheckFocus", ""+checkFocus);
*/

        btnEditItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //retreive item from text field
                String item = etEditItem.getText().toString();

                //New intent
                Intent data = new Intent();
                // Pass relevant data back as a result
                data.putExtra("ITEM", item);
                data.putExtra("CODE", 20); // ints work too

                // Activity finished ok, return the data
                setResult(RESULT_OK, data); // set result code and bundle data for response

                //return to main activity
                finish(); // closes the activity, pass data to parent

            }
        });

    }

    private String readItemsByPos() {
        //Declare and Initialize String
        String item="";

        //retreive list form file
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));

            //assign selected content to String "item"
            item = items.get(pos);

        } catch (IOException e) {
            Log.i("UNABLE TO IMPORT: ","items file");
        }
        return item;
    }



}
