package com.example.matrikelnummer;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
// import com.example.matrikelnummer; //_09126243_vospernik_klemens_einzelbeispiel.R;

public class MainActivity extends AppCompatActivity {
    EditText matrikelnummerInput;
    TextView showResponseFromServer;
    Button sendToServerButton;

    String matrikelnummer;
    String resultFromServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelnummerInput = (EditText) findViewById(R.id.matrikelnummerInput);
        showResponseFromServer = (TextView) findViewById(R.id.resultFromServer);
        sendToServerButton = (Button) findViewById(R.id.sendToServerButton);

        //Button sendToServerButton = (Button) findViewById(R.id.sendToServerButton);
        sendToServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matrikelnummer = matrikelnummerInput.getText().toString();
                startTransmission();
                System.out.println(resultFromServer);
                showResponseFromServer.setText(resultFromServer);
            }
        });
    }

    public void startTransmission() {
        TCPCLient tcpClient = new TCPCLient();
        Thread thread = new Thread(tcpClient);
        tcpClient.setMatrikelnummer(matrikelnummer);
        thread.start();

        try  {
            thread.join(); //todo set milliseconds?
        } catch (InterruptedException ie) {
            System.out.println("Der Thread wurde unterbrochen. Exception-Trace:\n");
            ie.printStackTrace();
        }

        resultFromServer = tcpClient.getResult();
    }

}

/*

Bsp AddNumbers
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
                EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);
                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                int num1 = Integer.parseInt(firstNumEditText.getText().toString());
                int num2 = Integer.parseInt(secondNumEditText.getText().toString());
                int result = num1 + num2;
                resultTextView.setText(result + "");

            }
        });
    }
}
 */