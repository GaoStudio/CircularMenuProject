package com.jaye.circularmenuproject;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyCenterView extends View {
	private float ScreenWidth;
	private float ScreenHeight;
	private final float SPACE = 30f;
	private float blank_x = 25.98f;
	private float blank_y = 15f;
	private Paint paintBorderStrock;
	private Paint paintBackGround;
	private float CenterX;
	private float CenterY;
	private Paint paintCenter;
	private Paint paintText;
	private Paint paintTop;
	private Paint paintLeftTop;
	private Paint paintRightTop;
	private Paint paintLeftDown;
	private Paint paintDown;
	private Paint paintRightDown;
	private Context context;
	private Integer mBitmap[];
	private float bitmap_wigth;
	private float bitmap_height;
	private float BigRadius;
	private float CenterRadius;
	private float LittleRadius;
	public static MyCenterView myView ;
	onViewClick onclick;
	float minScreen ;
	public MyCenterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		myView = this;
		this.context = context;
		onclick = (onViewClick) context;
		setLongClickable(true);
		initPaint();
		initResource();
		Bitmap bitmap = BitmapFactory
				.decodeResource(getResources(), mBitmap[0]);
		bitmap_wigth = bitmap.getWidth();
		bitmap_height = bitmap.getHeight();

	}
	public interface onViewClick{
		void setOnClick(int type);
	}
	private void initPaint() {
		paintCenter = new Paint();
		paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintTop = new Paint();
		paintCenter.setColor(Color.WHITE);
		paintText.setColor(0xff787878);

		paintText.setTextSize(35);
		paintTop.setColor(Color.WHITE);
		paintLeftTop = new Paint();
		paintLeftTop.setColor(Color.WHITE);
		paintRightTop = new Paint();
		paintRightTop.setColor(Color.WHITE);
		paintLeftDown = new Paint();
		paintLeftDown.setColor(Color.WHITE);
		paintDown = new Paint();
		paintDown.setColor(Color.WHITE);
		paintRightDown = new Paint();
		paintRightDown.setColor(Color.WHITE);
	}

	private void initResource() {
		mBitmap = new Integer[] { R.drawable.moni, R.drawable.shunxu,
				R.drawable.zhangjie, R.drawable.qianghua, R.drawable.suiji,
				R.drawable.tongji, R.drawable.weizuo, };
	}
	/**
	 * 设置控件为正方形
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int resWidth = 0;
		int resHeight = 0;

		/**
		 * 根据传入的参数，分别获取测量模式和测量值
		 */
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		int height = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		resWidth = resHeight = Math.min(width, height);
		resHeight = resWidth-resWidth / 11;
		setMeasuredDimension(resWidth, resHeight);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		ScreenWidth = this.getWidth();
		ScreenHeight =  this.getHeight();
		minScreen = ScreenWidth;
		if(ScreenWidth>ScreenHeight)
		{
			minScreen = ScreenWidth;
		}
		// 外层大圆半径
		BigRadius = minScreen / 2 - minScreen / 11;
		// 中间圆半径
		CenterRadius = minScreen / 5;
		// 模拟考试圆半径
		LittleRadius = minScreen / 6+10;
		CenterX = ScreenWidth / 2;
		CenterY = ScreenHeight / 2;
		// 设置背景画笔
		paintBackGround = new Paint();
		paintBackGround.setColor(0x00F8F8F8);
		canvas.drawRect(0, 0, ScreenWidth, ScreenHeight, paintBackGround);
		// 设置边框画笔
		paintBorderStrock = new Paint();
		paintBorderStrock.setColor(0xffe5e6e6);
		paintBorderStrock.setStyle(Paint.Style.STROKE);
		paintBorderStrock.setStrokeWidth(3);
		// 设置顺序练习画笔
		float TopCenterX = CenterX;
		float TopCenterY = CenterY - SPACE + 4;
		MyDrawArc(canvas, TopCenterX, TopCenterY, paintTop, 240, 60);
		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), mBitmap[1]),
				CenterX - bitmap_wigth / 2, CenterY - CenterRadius
						- (BigRadius - CenterRadius) / 2 - bitmap_height,
				paintTop);
		drawTextCenter(canvas,"顺序练习",(int)CenterX,(int)(CenterY - CenterRadius
				- (BigRadius - CenterRadius) / 2) - 10,paintText);
		// 设置左上的画笔
		float LeftTopCenterX = (float) (CenterX - blank_x);
		float LeftTopCenterY = (float) (CenterY - blank_y);
		MyDrawArc(canvas, LeftTopCenterX, LeftTopCenterY, paintLeftTop, 180, 60);
		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), mBitmap[6]),
				CenterX - CenterRadius -(BigRadius - CenterRadius) / 2 , CenterY - CenterRadius-10
				,paintLeftTop);
		drawTextCenter(canvas,"未做题练习",(int)(CenterX - CenterRadius - (BigRadius - CenterRadius) / 2 + bitmap_wigth/2) ,(int)(CenterY - CenterRadius + bitmap_height/2)+10,paintText);
		// 设置右上的画笔

		float RightTopCenterX = (float) (CenterX + blank_x);
		float RightTopCenterY = (float) (CenterY - blank_y);
		MyDrawArc(canvas, RightTopCenterX, RightTopCenterY, paintRightTop, 300,
				60);
		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), mBitmap[2]),
				CenterX + CenterRadius + (BigRadius - CenterRadius) / 2 - bitmap_wigth , CenterY - CenterRadius-10
				,paintRightTop);
		drawTextCenter(canvas,"章节练习",(int)(CenterX + CenterRadius + (BigRadius - CenterRadius) / 2 - bitmap_wigth/2) ,(int)(CenterY - CenterRadius + bitmap_height/2)+10,paintText);
		// 设置左下的画笔

		float LeftDownCenterX = (float) (CenterX - blank_x);
		float LeftDownCenterY = (float) (CenterY + blank_y);
		MyDrawArc(canvas, LeftDownCenterX, LeftDownCenterY, paintLeftDown, 120,60);
		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), mBitmap[5]),
				CenterX - CenterRadius -(BigRadius - CenterRadius) / 2 , CenterY + CenterRadius-bitmap_height-40
				,paintLeftTop);
		drawTextCenter(canvas,"统计",(int)(CenterX - CenterRadius - (BigRadius - CenterRadius) / 2 + bitmap_wigth/2) ,(int)(CenterY + CenterRadius-bitmap_height/2)-20,paintText);
		// 设置中下画笔

		float DownCenterX = CenterX;
		float DownCenterY = CenterY + SPACE;
		MyDrawArc(canvas, DownCenterX, DownCenterY, paintDown, 60, 60);
		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), mBitmap[4]),
				CenterX - bitmap_wigth / 2, CenterY + CenterRadius
						+ (BigRadius - CenterRadius) / 2 - bitmap_height/2-20,
				paintDown);
		drawTextCenter(canvas,"乱序练习",(int)CenterX,(int)(CenterY + CenterRadius
				+ (BigRadius - CenterRadius) / 2)+10,paintText);
		// 设置右下的画笔

		float RightDownCenterX = (float) (CenterX + blank_x);
		float RightDownCenterY = (float) (CenterY + blank_y);
		MyDrawArc(canvas, RightDownCenterX, RightDownCenterY, paintRightDown,
				0, 60);
		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), mBitmap[3]),
				CenterX + CenterRadius + (BigRadius - CenterRadius) / 2 - bitmap_wigth , CenterY + CenterRadius-bitmap_height-40
				,paintRightTop);
		drawTextCenter(canvas,"强化练习",(int)(CenterX + CenterRadius + (BigRadius - CenterRadius) / 2 - bitmap_wigth/2) ,(int)(CenterY + CenterRadius-bitmap_height/2)-20,paintText);
		// 画出第二层圆
		Paint paintCenter1 = new Paint();
		paintCenter1.setColor(0xFFf8f8f8);
		// 画出中心模拟考试圆形
		canvas.drawCircle(CenterX, CenterY, CenterRadius + 2, paintBorderStrock);
		canvas.drawCircle(CenterX, CenterY, CenterRadius, paintCenter1);

		Paint paintline = new Paint();
		paintline.setColor(0XFFF8F8F8);
		canvas.drawArc(new RectF(CenterX - CenterRadius - 4, CenterY
				- CenterRadius - 4, CenterX + CenterRadius + 2, CenterY
				+ CenterRadius + 2), 175, 10, true, paintline);
		canvas.drawArc(new RectF(CenterX - CenterRadius - 4, CenterY
				- CenterRadius - 4, CenterX + CenterRadius + 4, CenterY
				+ CenterRadius + 4), 355, 10, true, paintline);
		canvas.drawArc(new RectF(CenterX - CenterRadius - 4, CenterY
				- CenterRadius - 4, CenterX + CenterRadius + 4, CenterY
				+ CenterRadius + 4), 55, 10, true, paintline);
		canvas.drawArc(new RectF(CenterX - CenterRadius - 4, CenterY
				- CenterRadius - 4, CenterX + CenterRadius + 4, CenterY
				+ CenterRadius + 4), 115, 10, true, paintline);
		canvas.drawArc(new RectF(CenterX - CenterRadius - 4, CenterY
				- CenterRadius - 4, CenterX + CenterRadius + 4, CenterY
				+ CenterRadius + 4), 235, 10, true, paintline);
		canvas.drawArc(new RectF(CenterX - CenterRadius - 4, CenterY
				- CenterRadius - 4, CenterX + CenterRadius + 4, CenterY
				+ CenterRadius + 4), 295, 10, true, paintline);
		canvas.drawCircle(CenterX, CenterY, LittleRadius + 2, paintBorderStrock);
		canvas.drawCircle(CenterX, CenterY, LittleRadius, paintCenter);
		canvas.drawBitmap(
				BitmapFactory.decodeResource(getResources(), mBitmap[0]),
				CenterX - bitmap_wigth / 2, CenterY - bitmap_height / 2-40,
				paintCenter);
		drawTextCenter(canvas,"模拟考试",(int)CenterX,(int)CenterY-10,paintText);
		//canvas.drawText("模拟考试", CenterX - bitmap_wigth / 2 - 20, CenterY + bitmap_height / 2+20, paintText);
	}
	public void MyDrawArc(Canvas canvas, float CenterX, float CenterY,
						  Paint paint, int StartAngle, int HowAngle) {
		canvas.drawArc(new RectF(CenterX - BigRadius, CenterY - BigRadius,
						CenterX + BigRadius, CenterY + BigRadius), StartAngle,
				HowAngle, true, paint);
		canvas.drawArc(new RectF(CenterX - BigRadius, CenterY - BigRadius,
						CenterX + BigRadius, CenterY + BigRadius), StartAngle,
				HowAngle, true, paintBorderStrock);

	}
	public void drawTextCenter(Canvas canvas,String text,int centerX,int centerY,Paint paint)
	{
		Rect targetRect = new Rect((int)centerX-20, (int)centerY + (int)bitmap_height / 2-10, (int)centerX+20, (int)centerY + (int)bitmap_height / 2+30);
		FontMetricsInt fontMetrics = paint.getFontMetricsInt();
		int baseline = targetRect.top + (targetRect.bottom - targetRect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
		// 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(text, targetRect.centerX(), baseline, paint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		float event_X = e.getX();
		float event_Y = e.getY();
		float x = Math.abs(event_X - CenterX);
		float y = Math.abs(event_Y - CenterY);
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			ViewonDown(event_X, event_Y, x, y);
			break;
		case MotionEvent.ACTION_UP:
			ViewonUp(event_X, event_Y, x, y);

		default:
		}
		return true;
	}

	public void ViewonUp(float event_X,float event_Y,float x,float y)
	{
		if (x <= minScreen / 6 && y <= minScreen / 6) {
			onclick.setOnClick(0);
		} else if (x <= (minScreen / 2 - minScreen / 12)
				&& y <= (minScreen / 2 - minScreen / 12)) {
			if (event_Y < CenterY) {
				if (y / x > Math.sqrt(3)
						&& event_Y < (CenterY - CenterRadius)) {
						onclick.setOnClick(2);
				} else if (event_X < CenterX) {
						onclick.setOnClick(1);
				} else if (event_X > CenterX) {
					onclick.setOnClick(3);
					//ExerciseChapter.startActivity(context);
				}
			} else if (event_Y > CenterY) {
				if (y / x > Math.sqrt(3)
						&& event_Y > (CenterY + CenterRadius)) {
						onclick.setOnClick(5);
				} else if (event_X < CenterX) {
					onclick.setOnClick(7);
					/*Intent intent = new Intent(context,Activity_Tongji.class);
					context.startActivity(intent);*/
				} else if (event_X > CenterX) {
						onclick.setOnClick(4);
				}
			}
		}
		reSet();
		invalidate();
	}
	public void ViewonDown(float event_X,float event_Y,float x,float y)
	{
		if (x <= minScreen / 6 && y <= minScreen / 6) {
			paintCenter.setColor(0xFF43c2ed);
			mBitmap[0]=R.drawable.moni_s;
		} else if (x <= (minScreen / 2 - minScreen / 12)
				&& y <= (minScreen / 2 - minScreen / 12)) {
			if (event_Y < CenterY) {
				if (y / x > Math.sqrt(3)
						&& event_Y < (CenterY - CenterRadius)) {
					paintTop.setColor(0xFF43c2ed);
					mBitmap[1] = R.drawable.shunxu_s;
				} else if (event_X < CenterX) {
					paintLeftTop.setColor(0xFF43c2ed);
					mBitmap[6] = R.drawable.weizuo_s;
				} else if (event_X > CenterX) {
					paintRightTop.setColor(0xFF43c2ed);
					mBitmap[2] = R.drawable.zhangjie_s;
				}
			} else if (event_Y > CenterY) {
				if (y / x > Math.sqrt(3)
						&& event_Y > (CenterY + CenterRadius)) {
					paintDown.setColor(0xFF43c2ed);
					mBitmap[4] = R.drawable.suiji_s;

				} else if (event_X < CenterX) {
					paintLeftDown.setColor(0xFF43c2ed);
					mBitmap[5] = R.drawable.tongji_s;
				} else if (event_X > CenterX) {
					paintRightDown.setColor(0xFF43c2ed);
					mBitmap[3] = R.drawable.qianghua_s;
				}
			}
		}
		invalidate();

	}
	public void reSet()
	{
		paintCenter.setColor(Color.WHITE);
		mBitmap[0] = R.drawable.moni;
		paintTop.setColor(Color.WHITE);
		mBitmap[1] = R.drawable.shunxu;
		paintLeftTop.setColor(Color.WHITE);
		mBitmap[6] = R.drawable.weizuo;
		paintRightTop.setColor(Color.WHITE);
		mBitmap[2] = R.drawable.zhangjie;
		paintDown.setColor(Color.WHITE);
		mBitmap[4] = R.drawable.suiji;
		paintLeftDown.setColor(Color.WHITE);
		mBitmap[5] = R.drawable.tongji;
		paintRightDown.setColor(Color.WHITE);
		mBitmap[3] = R.drawable.qianghua;
		invalidate();
	}
}
