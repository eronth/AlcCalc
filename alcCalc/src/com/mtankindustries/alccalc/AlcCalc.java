package com.mtankindustries.alccalc;// solomid comment best comment

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

 
public class AlcCalc extends Activity implements AddDrinkFragment.Communicator {
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
    	ListView drinkList = (ListView)findViewById(R.id.listIngredients);
    	drinkList.setAdapter(adapter);
        
    	drinkList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Toasty("position: "+position+" id: "+id);
			}
        });
        
    	drinkList.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Toasty("long position: "+position+" id: "+id);
				new AlertDialog.Builder(AlcCalc.this)
			    .setTitle("Delete ingredient")
			    .setMessage("Are you sure you want to delete this ingredient?")
			    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // continue with delete
			        	Toasty("Delete me!");
			        }
			     })
			    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        	Toasty("Don't delete me!");
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
				return false;
			}
        });
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
    	DialogFragment df = new AddDrinkFragment();
    	df.show(getFragmentManager(), "testing");

    	//if (true) {
    		
    	//} else {
	    	//Ingredient i = new Ingredient( ingredientText.getText().toString(), Double.parseDouble(percentText.getText().toString()) );    	
	    	//drink.addIngredient( i, Integer.parseInt(partText.getText().toString()) );
	    	//adapter.add(i.toString());
	    	//drink.addIngredient(new Ingredient((findViewById(R.id.ingredientEditText).getContext().toString()), (isProof ? Double.parseDouble((findViewById(R.id.percentEditText).getContext().toString()))/2:Double.parseDouble((findViewById(R.id.percentEditText).getContext().toString()))), Integer.parseInt((findViewById(R.id.partsEditText).getContext().toString()))));
    	//}
    }
    
    public void togglePercent() {
    	isProof = !isProof;
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

	@Override
	public void onDialogMessage(String name, Double percent, int volume) {
		// Add ingredient to the drink.
		drink.addIngredient(new Ingredient(name, percent), volume);
		
		TextView tv = (TextView)findViewById(R.id.DrinkPercent);
		tv.setText(drink.getPercent().toString()+"%");
		tv = (TextView)findViewById(R.id.DrinkVolume);
		tv.setText(drink.getVolume().toString()+" ml");
		
		// Change list items to be based on the current list of ingredients.
		listItems.add(volume + " ml of " + name + " (" + percent + "%)");
		//adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
		ListView ingredientList = (ListView) findViewById(R.id.listIngredients);
		ingredientList.setAdapter(adapter);
		
		Toasty( name+" "+percent+"% "+volume );
	}
	
	public void Toasty(String message) {
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}
}