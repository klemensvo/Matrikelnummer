package com.example.matrikelnummer;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText matrikelnummerInput;
    TextView showResultFromServer;
    TextView showResultFromCalculation;
    Button sendToServerButton;
    Button calculateButton;

    String matrikelnummer;
    String resultFromServer;
    String resultFromCalculation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelnummerInput = (EditText) findViewById(R.id.matrikelnummerInput);
        showResultFromServer = (TextView) findViewById(R.id.resultFromServer);
        showResultFromCalculation = (TextView) findViewById(R.id.resultFromCalculation);

        sendToServerButton = (Button) findViewById(R.id.sendToServerButton);
        sendToServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matrikelnummer = matrikelnummerInput.getText().toString();
                startTransmission();
                showResultFromServer.setText(resultFromServer);
            }
        });

        calculateButton = (Button) findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matrikelnummer = matrikelnummerInput.getText().toString();
                resultFromCalculation = findCommonDenominatorsGreaterThanOne(matrikelnummer);
                showResultFromCalculation.setText(resultFromCalculation);
            }
        });
    }

    public void startTransmission() {
        TCPCLient tcpClient = new TCPCLient();
        Thread thread = new Thread(tcpClient);
        tcpClient.setMatrikelnummer(matrikelnummer);
        thread.start();

        try {
            thread.join(); //todo set milliseconds?
        } catch (InterruptedException ie) {
            System.out.println("Der Thread wurde unterbrochen. Exception-Trace:\n");
            ie.printStackTrace();
        }

        resultFromServer = tcpClient.getResult();
    }

    public String findCommonDenominatorsGreaterThanOne(String matrikelnummer) {
        /*
        Anmerkung: Teiler von 2 Ziffern > 1 genau dann, wenn entweder beide zu vergleichende Ziffern mod 2 == 0 oder
        beide zu vergleichende Ziffern mod 3 == 0 und beide Ziffern != 0 (vereinfachter Test auf
        Gemeinsamen Teiler größer 1)
        */

        int[] ziffer = new int[8];
        for (int i = 0; i < matrikelnummer.length(); i++) {
            ziffer[i] = Integer.parseInt(matrikelnummer.substring(i, i + 1));
        }
        boolean commonDenominator = false;
        String result = "Gemeinsamer Teiler an folgenden Indices,\n" +
                "alle Teiler größer als 1, Index beginnt bei 0:\n";
        String buildingBlock = "";
        for (int i = 0; i < matrikelnummer.length() - 1; i++) {
            for (int j = i + 1; j < matrikelnummer.length(); j++) {
                if ((ziffer[i] != 0 && ziffer[j] != 0) &&
                        ((ziffer[i] % 2 == 0 && ziffer[j] % 2 == 0) ||
                                (ziffer[i] % 3 == 0 && ziffer[j] % 3 == 0))) {
                    buildingBlock = "\n" + "Index " + i + " und " + j;
                    result += buildingBlock;
                    commonDenominator = true;
                }
            }
        }
        if (commonDenominator) {
            result += "\n";
        } else {
            result += "\n -- keine gemeinsamen Teiler gefunden --\n";
        }

        return result;
    }

}




/*
public class checkForGCD {

    public static void main(String[] args) {
        String matrikelnummer = "09126243";
        checkForGCD.divideAndCheck(matrikelnummer);
    }

    public static void divideAndCheck(String matrikelnummer) {
        int[] ziffer = new int[8];
        for (int i = 0; i < matrikelnummer.length(); i++) {
            ziffer[i] = Integer.parseInt(matrikelnummer.substring(i, i + 1));
            //System.out.println(ziffer[i]);
        }

        for (int i = 0; i < matrikelnummer.length() - 1; i++) {
            for (int j = i + 1; j < matrikelnummer.length(); j++) { */
                /*ggT > 1 genau dann, wenn entweder beide zu vergleichende Ziffern mod 2 == 0 oder
                beide zu vergleichende Ziffern mod 3 == 0 und beide Ziffern != 0 (vereinfachter GGT-Test)
                 */
                /*
                if ((ziffer[i] != 0 && ziffer[j] != 0) &&
                        ((ziffer[i] % 2 == 0 && ziffer[j] % 2 == 0) ||
                        (ziffer[i] % 3 == 0 && ziffer[j] % 3 == 0))) {
                        System.out.println("Index i = " + i + ", Index j = " + j);
                        }
                        }
                        }


                        }


                        }


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