package com.v41.tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.v41.tp1.modele.portal.IPortalModel;
import com.v41.tp1.vuecontroleur.portal.IPortalView;

public class CalculatorActivity extends AppCompatActivity implements IPortalView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    @Override
    public void notify(IPortalModel model) {
        TextView results = (TextView)findViewById(R.id.resultView);
        //results.setText("asd");
    }
}
