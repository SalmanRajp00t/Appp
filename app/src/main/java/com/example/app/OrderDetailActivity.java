package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.database.DBHelper;

public class OrderDetailActivity extends AppCompatActivity {
    ImageView order_image,plus,minus;
    TextView quantity,food_name,order_description,order_price;
    EditText name_box,phone_box;
    Button order_now;
    int quant=0;
    int total_price=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        order_image = (ImageView) findViewById(R.id.orderDetailImage);
        minus = (ImageView) findViewById(R.id.minus);
        plus = (ImageView) findViewById(R.id.plus);

        food_name = (TextView) findViewById(R.id.foodName);
        order_description = (TextView) findViewById(R.id.orderDetailDes);
        order_price = (TextView) findViewById(R.id.orderPrice);

        quantity = (TextView) findViewById(R.id.quantity);

        name_box = (EditText) findViewById(R.id.nameBox);
        phone_box = (EditText) findViewById(R.id.phoneBox);

        order_now = (Button) findViewById(R.id.btn_OrderNow);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quant += 1;
                quantity.setText(String.format("%d",quant));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quant>1){
                    quant -= 1;
                    quantity.setText(String.format("%d",quant));
                }
            }
        });

        final DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 1) {
            int image = getIntent().getIntExtra("image", 0);
            String food = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("description");
            int price = Integer.parseInt(getIntent().getStringExtra("price"));

            int qua = Integer.parseInt(quantity.getText().toString());
            int total=Integer.parseInt(String.format("%d", price));

            total_price+=total*qua;

            order_image.setImageResource(image);
            food_name.setText(food);
            order_description.setText(description);
            order_price.setText(String.format("%d", total_price));

            order_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = name_box.getText().toString();
                    String phone = phone_box.getText().toString();

                    Boolean isInserted = helper.insertOrder(image, quant, food, description, name, phone, total_price);
                    if (isInserted) {
                        Toast.makeText(OrderDetailActivity.this, "Order is successful.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OrderDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(getIntent().getIntExtra("type", 0) == 2){
            int id=getIntent().getIntExtra("id",0);
            Cursor cursor=helper.getOrderById(id);

            int updateImage=cursor.getInt(1);
            order_image.setImageResource(updateImage);
            quantity.setText(String.format("%d", cursor.getInt(2)));
            food_name.setText(cursor.getString(3));
            order_description.setText(cursor.getString(4));
            name_box.setText(cursor.getString(5));
            phone_box.setText(cursor.getString(6));
            order_price.setText(String.format("%d", cursor.getInt(7)));

            order_now.setText("Update Now");
            order_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qua = Integer.parseInt(quantity.getText().toString());
                    String updateFoodName=food_name.getText().toString();
                    String updateDes=order_description.getText().toString();
                    String name = name_box.getText().toString();
                    String phone = phone_box.getText().toString();
                    int updatePrice=Integer.parseInt(order_price.getText().toString());
                    Boolean isUpdated = helper.updateOrder(updateImage, qua, updateFoodName, updateDes, name, phone, updatePrice,id);
                    if (isUpdated) {
                        Toast.makeText(OrderDetailActivity.this, "Update is successful.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OrderDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(getIntent().getIntExtra("type", 0) == 3){
            int image = getIntent().getIntExtra("image", 0);
            String food = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("description");
            int price = Integer.parseInt(getIntent().getStringExtra("price"));

            order_image.setImageResource(image);
            food_name.setText(food);
            order_description.setText(description);
            order_price.setText(String.format("%d", price));

            order_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   int qua = Integer.parseInt(quantity.getText().toString());
                    String name = name_box.getText().toString();
                    String phone = phone_box.getText().toString();

                    Boolean isInserted = helper.insertOrder(image, quant, food, description, name, phone, price);
                    if (isInserted) {
                        Toast.makeText(OrderDetailActivity.this, "Order is successful.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OrderDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}