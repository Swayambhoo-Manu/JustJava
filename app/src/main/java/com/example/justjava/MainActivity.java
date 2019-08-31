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

import android.content.Intent;
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
    private String createOrderSummary(String name,int price,boolean hasWhippedCream,boolean hasChccolate) {
        String priceMessage="Name:"+name+"\nWant Whipped Cream?: "+hasWhippedCream+"\nWant Chocolate?"+hasChccolate+"\nTotal: $"+price;
        priceMessage+="\nThank You!";
        return priceMessage;
    }
    public void increment(View view){
        if(quantity<100)
            quantity++;
        displayQuantity(quantity);
        CheckBox whippedCreamCheckbox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox =(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream=whippedCreamCheckbox.isChecked(),hasChocolate=chocolateCheckBox .isChecked();
    }
    public void decrement(View view){
        if(quantity>1)
            quantity--;
        displayQuantity(quantity);
        CheckBox whippedCreamCheckbox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox =(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream=whippedCreamCheckbox.isChecked(),hasChocolate=chocolateCheckBox .isChecked();

    }
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


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//
        String subjectOfEmail=name+"'s order";
        String bodyOfEmail=createOrderSummary(name,price,hasWhippedCream,hasChocolate);
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectOfEmail);
        intent.putExtra(Intent.EXTRA_TEXT, bodyOfEmail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        Intent intent=new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6,-122.3"));
//        if(intent.resolveActivity(getPackageManager())!=null){
//            startActivity(intent);
//        }
    }

}