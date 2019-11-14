
public interface Common {
    // 画面の大きさ定数
    public static final int HEIGHT_MARGIN = 50;
    public static final int MY_WIDTH = 640;
    public static final int MY_HEIGHT = MY_WIDTH + HEIGHT_MARGIN;
    // メニュー画面の定数
    public static final int TITLE_X = MY_WIDTH/2 - MY_WIDTH/2/2;
    public static final int TITLE_Y = 125;
    public static final int SELECT_MODE_Y = TITLE_Y + 110;
    public static final int SELECT_MODE_X = TITLE_X - 35;
    public static final int BEST_SCORES_Y = SELECT_MODE_Y;
    public static final int BEST_SCORES_X = SELECT_MODE_X + 250;
    // ボタン表示位置に関する定数
    public static final int BUTTON_MARGIN = 115;
    public static final int BUTTON_X = 100;
    public static final int BUTTON_9_Y = TITLE_Y + 135;
    public static final int BUTTON_16_Y = BUTTON_9_Y + BUTTON_MARGIN;
    public static final int BUTTON_32_Y = BUTTON_16_Y + BUTTON_MARGIN;
    // スコア表示に関する定数
    public static final int SCORE_X = BUTTON_X + 275;
    public static final int SCORE_9_Y = BUTTON_9_Y + 20;
    public static final int SCORE_16_Y = BUTTON_16_Y + 20;
    public static final int SCORE_32_Y = BUTTON_32_Y + 20;
    // ゲームの状態定数
    public static final int GAMEOVER = 3;
    public static final int CLEAR = 2;
    public static final int GAME = 1;
    public static final int MENU = 0;
    // ゲームのモード定数
    public static final int MODE_9 = 9;
    public static final int MODE_16 = 16;
    public static final int MODE_32 = 32;
    // クリック入力定数
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
}
