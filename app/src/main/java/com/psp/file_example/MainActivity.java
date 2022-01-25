package com.psp.file_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    final String FILE_NAME = "Example.txt";

    Button btnRead,btnWrite;
    TextView txtRead;
    EditText edtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        txtRead = findViewById(R.id.txtRead);
        edtMessage = findViewById(R.id.edtMessage);


        btnRead.setOnClickListener(v-> {
            readFile();
        });

        btnWrite.setOnClickListener(v-> {
            writeFile();
        });


    }

    private void readFile() {
       StringBuilder sb = new StringBuilder();

        try(FileInputStream in = openFileInput(FILE_NAME)) {
            int read = 0;
            while((read = in.read()) != -1) {
                sb.append((char) read);
            }

            txtRead.setText(sb.toString());
        }
        catch (Exception e) {
            Toast.makeText(this, "Read Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void writeFile() {
        String msg = edtMessage.getText().toString();
        if(msg.isEmpty()) {
            return;
        }

        try (FileOutputStream out = openFileOutput(FILE_NAME,MODE_PRIVATE)) {
            out.write(msg.getBytes());
            Toast.makeText(this, "Write success", Toast.LENGTH_SHORT).show();
            edtMessage.setText("");
        }
        catch (Exception e) {
            Toast.makeText(this, "Write Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}