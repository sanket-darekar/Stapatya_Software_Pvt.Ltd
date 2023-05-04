public class DatabaseHelper {

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
        private EditText firstName, lastName, email, mobileNo, panNo, adharNo, dob;
        private Button saveBtn, updateBtn, deleteBtn, displayBtn;
        private DatabaseHelper dbHelper;
        private ListView listView;
        private CustomAdapter adapter;
        private ArrayList<User> userList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            dbHelper = new DatabaseHelper(this);

            firstName = findViewById(R.id.etFirstName);
            lastName = findViewById(R.id.etLastName);
            email = findViewById(R.id.etEmail);
            mobileNo = findViewById(R.id.etMobileNo);
            panNo = findViewById(R.id.etPanNo);
            adharNo = findViewById(R.id.etAdharNo);
            dob = findViewById(R.id.etDob);

            saveBtn = findViewById(R.id.btnSave);
            updateBtn = findViewById(R.id.btnUpdate);
            deleteBtn = findViewById(R.id.btnDelete);
            displayBtn = findViewById(R.id.btnDisplay);

            listView = findViewById(R.id.listView);
            userList = dbHelper.getAllUsers();
            adapter = new CustomAdapter(userList, this);
            listView.setAdapter(adapter);

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = new User(
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            email.getText().toString(),
                            mobileNo.getText().toString(),
                            panNo.getText().toString(),
                            adharNo.getText().toString(),
                            dob.getText().toString()
                    );

                    long rowId = dbHelper.insertUser(user);

                    if (rowId != -1) {
                        user.setId(rowId);
                        userList.add(user);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = new User(
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            email.getText().toString(),
                            mobileNo.getText().toString(),
                            panNo.getText().toString(),
                            adharNo.getText().toString(),
                            dob.getText().toString()
                    );

                    int rowsAffected = dbHelper.updateUser(user);

                    if (rowsAffected > 0) {
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to update data", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int rowsAffected = dbHelper.deleteUser(userList.get(listView.getCheckedItemPosition()).getId());

                    if (rowsAffected > 0) {
                        userList.remove(listView.getCheckedItemPosition());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to delete data", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            displayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userList.clear();
                    userList.addAll(dbHelper.getAllUsers());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Data displayed successfully", Toast

