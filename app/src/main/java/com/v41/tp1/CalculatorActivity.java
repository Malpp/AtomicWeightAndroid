package com.v41.tp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.v41.tp1.modele.portal.IPortalModel;
import com.v41.tp1.vuecontroleur.Controller;
import com.v41.tp1.vuecontroleur.portal.IPortalView;

public class CalculatorActivity extends AppCompatActivity implements IPortalView
{

	private EditText formulaEditText;
	private TextView resultsView;

	//https://stackoverflow.com/a/28625726
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);

		Controller.INSTANCE.init(this, "periodicTableEnglish.txt");
		resultsView = (TextView) findViewById(R.id.resultView);
		formulaEditText = (EditText) findViewById(R.id.formulaText);

		formulaEditText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
			{
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
			{
			}

			@Override
			public void afterTextChanged(final Editable s)
			{
				if (s.length() > 0)
				{
					String result = Controller.INSTANCE.parseUserInput(formulaEditText.getText().toString());
					if (!result.equals(""))
					{
						resultsView.setText(result);
					}
				} else
				{
					resultsView.setText("");
				}
			}
		});
	}

	@Override
	public void notify(IPortalModel model)
	{
		//formulaEditText.clearFocus();
		String result = model.getFormulaInformation();
		resultsView.setText(result);
		String[] resultsTable = result.split("\\r?\\n");
		//TableLayout layout = (TableLayout)findViewById(R.id.elementsTable);
		//layout.
	}
}
