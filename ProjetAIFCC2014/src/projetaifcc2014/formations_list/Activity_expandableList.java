package projetaifcc2014.formations_list;

import java.util.ArrayList;

import projetaifcc2014.drawer.Activity_drawer;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

public class Activity_expandableList extends ExpandableListActivity
{
    // Create ArrayList to hold parent Items and Child Items
    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {

        super.onCreate(savedInstanceState);

        
        // Create Expandable List and set it's properties
        ExpandableListView expandableList = getExpandableListView(); 
        expandableList.setDividerHeight(2);
        expandableList.setGroupIndicator(null);
        expandableList.setClickable(true);

        // Set the Items of Parent
        setGroupParents();
        // Set The Child Data
        setChildData();

        // Create the Adapter
        MyExpandableAdapter adapter = new MyExpandableAdapter(parentItems, childItems);

        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        
        // Set the Adapter to expandableList
        expandableList.setAdapter(adapter);
        expandableList.setOnChildClickListener(this);
    }

    // method to add parent Items
    public void setGroupParents() 
    {
        parentItems.add("Fruits");
        parentItems.add("Flowers");
        parentItems.add("Animals");
        parentItems.add("Birds");
    }

    // method to set child data of each parent
    public void setChildData() 
    {

        // Add Child Items for Fruits
        ArrayList<String> child = new ArrayList<String>();
        child.add("Apple");
        child.add("Mango");
        child.add("Banana");
        child.add("Orange");
        
        childItems.add(child);

        // Add Child Items for Flowers
        child = new ArrayList<String>();
        child.add("Rose");
        child.add("Lotus");
        child.add("Jasmine");
        child.add("Lily");
        
        childItems.add(child);

        // Add Child Items for Animals
        child = new ArrayList<String>();
        child.add("Lion");
        child.add("Tiger");
        child.add("Horse");
        child.add("Elephant");
        
        childItems.add(child);

        // Add Child Items for Birds
        child = new ArrayList<String>();
        child.add("Parrot");
        child.add("Sparrow");
        child.add("Peacock");
        child.add("Pigeon");
        
        childItems.add(child);
    }

}