package com.mtankindustries.alccalc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddDrinkFragment extends DialogFragment {
	Communicator communicator;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		communicator = (Communicator)activity;
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	LayoutInflater inflater = getActivity().getLayoutInflater();
    	final View view = inflater.inflate(R.layout.dialog_add_ingredient, null);
        // Use the Builder class for convenient dialog construction
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Enter Ingredient");
        builder.setView(view)
				.setPositiveButton("Add", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						String name = ((EditText)view.findViewById(R.id.ingredientName)).getText().toString();
						Double percent = Double.parseDouble(((EditText)view.findViewById(R.id.alcoholicPercent)).getText().toString());
						int volume = Integer.parseInt(((EditText)view.findViewById(R.id.volume)).getText().toString());
						communicator.onDialogMessage(name, percent, volume);
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User cancelled the dialog
					}
				});
        // Create the AlertDialog object and return it
        return builder.create();
    }
    interface Communicator {
    	public void onDialogMessage(String message, Double percent, int volume);
    }
}