package devexchanges.info.roundimages;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.joooonho.SelectableRoundedImageView;

public class MainActivity extends AppCompatActivity {

    private SelectableRoundedImageView imageView;
    private View btnOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (SelectableRoundedImageView) findViewById(R.id.image);
        btnOption = findViewById(R.id.btn_set);

        btnOption.setOnClickListener(onClickListener());
    }

    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // custom dialog
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.layout_dialog);
                dialog.setTitle("Set Image Radius");

                // set the custom dialog components - text, image and button
                final EditText topLeftTxt = (EditText) dialog.findViewById(R.id.left_top_edt);
                final EditText topRightTxt = (EditText) dialog.findViewById(R.id.right_top_edt);
                final EditText bottomLeftTxt = (EditText) dialog.findViewById(R.id.left_bottom_edt);
                final EditText bottomRightTxt = (EditText) dialog.findViewById(R.id.right_bottom_edt);
                final CheckBox chkIsOval = (CheckBox) dialog.findViewById(R.id.checkbox);

                View btnCancel = dialog.findViewById(R.id.btn_cancel);
                View btnOK = dialog.findViewById(R.id.btn_ok);

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imageView.setImageResource(0);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView.setImageResource(R.mipmap.photo_sample);
                        imageView.setCornerRadiiDP(getInt(topLeftTxt), getInt(topRightTxt),
                                getInt(bottomLeftTxt), getInt(bottomRightTxt));
                        imageView.setBorderWidthDP(2);
                        imageView.setBorderColor(Color.BLUE);

                        if (chkIsOval.isChecked()) {
                            imageView.setOval(true);
                        } else imageView.setOval(false);

                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        };
    }

    private int getInt(EditText editText) {
        if (!editText.getText().toString().trim().equals("")) {
            return Integer.parseInt(editText.getText().toString().trim());
        } else {
            return 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
