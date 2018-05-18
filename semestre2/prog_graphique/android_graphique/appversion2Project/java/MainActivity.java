public class MainActivity extends Activity {

    private GLSurfaceView surfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        surfaceView = new MyGLSurfaceView(this);
        this.setContentView(surfaceView);
        ShaderUtilities.init(this);
    }
}