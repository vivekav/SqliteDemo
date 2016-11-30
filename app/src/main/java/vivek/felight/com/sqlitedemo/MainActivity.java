package vivek.felight.com.sqlitedemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email, etid;
    Button btnadddata, btndisplay, btnupdate, btndelete;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.etname);
        email = (EditText) findViewById(R.id.etmail);
        btnadddata = (Button) findViewById(R.id.btnadddata);
        btndisplay=(Button)findViewById(R.id.btndisplay);
        etid=(EditText) findViewById(R.id.etid);
        btnupdate=(Button)findViewById(R.id.btnupdate);
        btndelete=(Button)findViewById(R.id.btndelete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }


    public void DeleteData(){
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows=mydb.deleteData(etid.getText().toString());
                if(deleteRows>0)
                    Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void UpdateData(){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated=mydb.updateData(etid.getText().toString(),name.getText().toString(),email.getText().toString());
                if(isUpdated==true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                    else
                    Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void AddData(){
        btnadddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            boolean isInserted=mydb.insertData(name.getText().toString(),email.getText().toString());
                if(isInserted==true)
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"No Data present",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewAll(){
        btndisplay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = mydb.getAllData();
                        if (res.getCount() == 0) {
                            // for showing messaage is no data present
                            showMessage("Error", "No data present");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("Name :"+res.getString(1)+"\n");
                            buffer.append("Email :"+res.getString(2)+"\n\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                });
    }


    public void showMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.icon);
        builder.show();
    }


}
