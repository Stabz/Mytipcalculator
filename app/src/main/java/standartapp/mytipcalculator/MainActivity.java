package standartapp.mytipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {





    private double billAmount = 0.0; // tallet som er sat i første widget
    private double percent = 0.15; // start tip procenten
    private TextView totalAmount; // område hvor man indtaster beløbet
    private TextView tipText; // området hvor man ser tip i %
    private TextView tipDisplay; // området hvor man ser tip-beløbet
    private TextView totalDisplay; // viser beløbet med tip.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantier mine textview med deres id.
        totalAmount = (TextView) findViewById(R.id.totalAmount);
        tipText = (TextView) findViewById(R.id.tipText);
        tipDisplay = (TextView) findViewById(R.id.tipDisplay);
        totalDisplay = (TextView) findViewById(R.id.totalDisplay);

        //instantier min seekbare og tilføjer en listener
        SeekBar percentSeekBar =
                (SeekBar) findViewById(R.id.seekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

        EditText amountEditText =
                (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

    }

    //viser hvor stor en % tippet er
    private void tipDisplayer(){

        String txt = (percent * 100)+"%";

        tipText.setText(txt);

    }

    private void calculate(){


        //udregner tip og beløbet+tip
        double tip = billAmount + percent;
        double total = billAmount + tip;

        //sætter de nye værdier ind
        tipDisplay.setText((int) tip);
        totalDisplay.setText((int) total);
        tipDisplayer();
    }



    private final SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                // opdaterer min procent og calculater når der er ændringer i seekbar
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    percent = progress / 100.0;
                    calculate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            };


    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        // en listener til når der er ændringer i totalamount
        @Override
        //når der er ændringer
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { //hvis vi får et tal
                billAmount = Double.parseDouble(s.toString()) / 100.0;
                totalAmount.setText((int) billAmount);
            }
            catch (NumberFormatException e) {
                //hvis der ikke er et tal.
                totalAmount.setText("");
                billAmount = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
}
