package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import es.iessaladillo.pedrojoya.pr01.R;
import es.iessaladillo.pedrojoya.pr01.bmi.BmiCalculator;

import static es.iessaladillo.pedrojoya.pr01.utils.SoftInputUtils.hideKeyboard;

public class MainActivity extends AppCompatActivity {

    EditText txtWeight;

    EditText txtHeight;

    TextView lblRes;

    ImageView imgImage;

    Button btnReset;

    Button btnCalculate;

    BmiCalculator bmiCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        txtWeight = ActivityCompat.requireViewById(this, R.id.txtWeight);
        txtHeight = ActivityCompat.requireViewById(this, R.id.txtHeight);
        lblRes = ActivityCompat.requireViewById(this, R.id.lblResult);
        imgImage = ActivityCompat.requireViewById(this, R.id.imgBmi);
        btnReset = ActivityCompat.requireViewById(this, R.id.btnReset);
        btnCalculate = ActivityCompat.requireViewById(this, R.id.btnCalculate);
        bmiCalculator = new BmiCalculator();


        btnReset.setOnClickListener(x -> resetValues());

        btnCalculate.setOnClickListener(x -> giveIBM());

    }

    private void resetValues() {

        txtWeight.setText("");

        txtHeight.setText("");

        txtWeight.setError(null);

        txtHeight.setError(null);

        lblRes.setText("");

        imgImage.setImageResource(R.drawable.bmi);

        hideKeyboard(btnReset);

    }

    private void giveIBM() {

        float bmi;

        BmiCalculator.BmiClasification bmi_class;

        boolean no_error = validateWeight() && validateHeight();


        if (no_error) {

            bmi = bmiCalculator.calculateBmi(Float.parseFloat(txtWeight.getText().toString()), Float.parseFloat(txtHeight.getText().toString()));

            bmi_class = bmiCalculator.getBmiClasification(bmi);

            txtWeight.setError(null);

            txtHeight.setError(null);

            setIBM(bmi_class, bmi);

        }

        hideKeyboard(btnCalculate);

    }

    private boolean validateHeight() {



        if (!isFloat(txtHeight.getText().toString())) {

            txtHeight.setError(getString(R.string.main_invalid_height));

            return false;

        }

        else if (txtHeight.getText().toString().isEmpty() || Float.parseFloat(txtHeight.getText().toString()) <= 0) {

            txtHeight.setError(getString(R.string.main_invalid_height));

            return false;

        }

            return true;


    }

    private boolean validateWeight() {

        if (!isFloat(txtWeight.getText().toString())) {

            txtWeight.setError(getString(R.string.main_invalid_weight));

            return false;

        }

        else if (txtWeight.getText().toString().isEmpty() || Float.parseFloat(txtWeight.getText().toString()) <= 0) {

            txtWeight.setError(getString(R.string.main_invalid_weight));

            return false;

        }

            return true;


    }

    private void setIBM(BmiCalculator.BmiClasification bmi_class, float bmi) {

        switch (bmi_class) {

            case LOW_WEIGHT:
                lblRes.setText(getString(R.string.main_bmi, bmi, getString(R.string.underweight)));
                imgImage.setImageResource(R.drawable.underweight);
                break;

            case NORMAL_WEIGHT:
                lblRes.setText(getString(R.string.main_bmi, bmi, getString(R.string.normal)));
                imgImage.setImageResource(R.drawable.normal_weight);
                break;

            case OVERWWEIGHT:
                lblRes.setText(getString(R.string.main_bmi, bmi, getString(R.string.main_overweight)));
                imgImage.setImageResource(R.drawable.overweight);
                break;

            case OBESITY_GRADE_1:
                lblRes.setText(getString(R.string.main_bmi, bmi, getString(R.string.grade1)));
                imgImage.setImageResource(R.drawable.obesity1);
                break;

            case OBESITY_GRADE_2:
                lblRes.setText(getString(R.string.main_bmi, bmi, getString(R.string.grade2)));
                imgImage.setImageResource(R.drawable.obesity2);
                break;

            case OBESITY_GRADE_3:
                lblRes.setText(getString(R.string.main_bmi, bmi, getString(R.string.grade3)));
                imgImage.setImageResource(R.drawable.obesity3);
                break;


        }

    }

    public boolean isFloat(String number) {
        try {
            Float.parseFloat(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
