//package com.example.justjava;

//import androidx.appcompat.app.AppCompatActivity;

//import android.os.Bundle;
////
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

package com.example.justjava;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;
    int price=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }
    private void displayPrice(int quantity,boolean hasChocolate,boolean hasWhippedCream) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        int price=calculatePrice(hasChocolate,hasWhippedCream);
        priceTextView.setText(price);
    }
    private String createOrderSummary(String name,int price,boolean hasWhippedCream,boolean hasChccolate) {
        String priceMessage="Name:"+name+"\nWant Whipped Cream?: "+hasWhippedCream+"\nWant Chocolate?"+hasChccolate+"\nTotal: $"+price;
        priceMessage+="\nThank You!";
        return priceMessage;

    }
    public void displayOrderSummary(String priceMessage){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText( priceMessage );
    }
    public void increment(View view){
        if(quantity<100)
            quantity++;
        displayQuantity(quantity);
        CheckBox whippedCreamCheckbox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox =(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream=whippedCreamCheckbox.isChecked(),hasChocolate=chocolateCheckBox .isChecked();
        displayPrice(quantity*price,hasChocolate,hasWhippedCream);
    }
    public void decrement(View view){
        if(quantity>1)
            quantity--;
        displayQuantity(quantity);
        CheckBox whippedCreamCheckbox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox =(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream=whippedCreamCheckbox.isChecked(),hasChocolate=chocolateCheckBox .isChecked();
        displayPrice(quantity*price,hasChocolate,hasWhippedCream);

    }
    /*public boolean itemClicked(View view){
        CheckBox checkbox=(CheckBox)view;
        if(checkbox.isChecked()){
           return true;
        }
        return false;
    }*/
    private int calculatePrice(boolean haaChocolate,boolean hasWhippedCream){
        if(hasWhippedCream){
            price++;
        }
        if(haaChocolate){
            price+=2;
        }
        return price*quantity;
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox =(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream=whippedCreamCheckbox.isChecked(),hasChocolate=chocolateCheckBox .isChecked();
        int price=calculatePrice(hasChocolate,hasWhippedCream);
        EditText nameFireld=(EditText)findViewById(R.id.name);
        String name=nameFireld.getText().toString();
        String priceMessage=createOrderSummary(name,price,hasWhippedCream,hasChocolate);
        displayQuantity(quantity);
        displayOrderSummary(priceMessage);
    }

}