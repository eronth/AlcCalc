package com.mtankindustries.alccalc;//comment solomid comment best comment

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

 
public class AlcCalc extends Activity {
	boolean isProof = false;
	// #SingleInstanceOfDrinkUsedToDisplayCurrentDrink
	private Drink drink = new Drink();
	// #Adapter
	final ArrayList<String> listItems = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alc_calc);

        // #IngredientListAdapterAndListArray
    	adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
    	ListView list = (ListView)findViewById(R.id.listView1);
    	list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alc_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addIngredient(View view) {
    	//Commented out as backup if super kool long line doesn't work.
    	EditText ingredientText = (EditText)findViewById(R.id.ingredientEditText);
    	EditText percentText = (EditText)findViewById(R.id.percentEditText);
    	EditText partText = (EditText)findViewById(R.id.partsEditText);
    	Ingredient i = new Ingredient( ingredientText.getText().toString(), Double.parseDouble(percentText.getText().toString()) );
    	drink.addIngredient( i, Integer.parseInt(partText.getText().toString()) );
    	//drink.addIngredient(new Ingredient((findViewById(R.id.ingredientEditText).getContext().toString()), (isProof ? Double.parseDouble((findViewById(R.id.percentEditText).getContext().toString()))/2:Double.parseDouble((findViewById(R.id.percentEditText).getContext().toString()))), Integer.parseInt((findViewById(R.id.partsEditText).getContext().toString()))));
    }
    
    public void togglePercent() {
    	isProof = !isProof;
    	if(isProof) {
    		((TextView)findViewById(R.id.percentSwitchText)).setText(R.string.proof);
    	} else {
    		((TextView)findViewById(R.id.percentSwitchText)).setText(R.string.percent);
    	}
    	
    	// #HandlesAddingToListViewListAndUpdate
    	// TODO 
    	//adapter.add(getString(R.string.VERYIMPORTANT)+ (findViewById(R.id.ingredientEditText).getContext().toString()));
    }
    //TODO: Save - name and percentage in title
    
    private void saveDrink() {
    	String saveName = drink.getName() + "_/" + drink.getPercent();
    	try {
        	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getPath() + saveName)));
        	oos.writeObject(this);
        	oos.flush(); 
        	oos.close();
    	} catch(Exception ex) {
    		// #DoNothingBecauseWeDon'tGiveShitsAboutErrors
           //Log.v("Serialization Save Error : ",ex.getMessage());
           //ex.printStackTrace();
        }
    }
    
    private void loadDrink(String loadDrinkName) {
    	try {
        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(Environment.getExternalStorageDirectory().getPath() + loadDrinkName)));
            // #PossiblyNotNecessary- Object o = ois.readObject();
            drink = (Drink)ois.readObject();//Questionable
            ois.close();
    	} catch(Exception ex) {
    		// #DoNothingBecauseWeDon'tGiveShitsAboutErrors
           //Log.v("Serialization Read Error : ",ex.getMessage());
           //ex.printStackTrace();
        }
    }
}
