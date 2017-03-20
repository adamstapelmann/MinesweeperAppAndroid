package hu.ait.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tvFlagText;
    TextView tvToggleText;
    //TextView tvFlagText = (TextView) findViewById(R.id.tvFlagMessage);
    //TextView tvToggleText = (TextView) findViewById((R.id.tvMode));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MinesweeperView gameView = (MinesweeperView) findViewById(R.id.gameView);

        tvFlagText = (TextView) findViewById(R.id.tvFlagMessage);
        tvToggleText = (TextView) findViewById(R.id.tvMode);

        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.resetGame();
            }
        });

        Button btnToggle = (Button) findViewById(R.id.btnToggle);
        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.toggleMode();
            }
        });


        ShimmerFrameLayout shimmerFrameLayoutMode = (ShimmerFrameLayout) findViewById(
                R.id.shimmer_view_container_mode);
        shimmerFrameLayoutMode.startShimmerAnimation();

        ShimmerFrameLayout shimmerFrameLayoutMessage = (ShimmerFrameLayout) findViewById(
                R.id.shimmer_view_container_message);
        shimmerFrameLayoutMessage.startShimmerAnimation();
    }

    public void setFlagText(String s) {
        tvFlagText.setText(s);
    }

    public void setToggleText(String s) {
        tvToggleText.setText(s);
    }
}
