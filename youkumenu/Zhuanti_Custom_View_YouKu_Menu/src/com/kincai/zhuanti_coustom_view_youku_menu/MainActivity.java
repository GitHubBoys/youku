package com.kincai.zhuanti_coustom_view_youku_menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
/**
 * 
 * @company KCS互联网有限公司
 * 
 * @author kincai
 * 
 * @description TODO
 *
 * @project Zhuanti_Coustom_View_youku_menu
 *
 * @package com.kincai.zhuanti_coustom_view_youku_menu
 *
 * @time 2015-7-24 下午5:56:17
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	private ImageView iv_home, iv_menu;
	private RelativeLayout level1, level2, level3;

	private boolean isShowLevel2 = true;// 是否显示2级菜单
	private boolean isShowLevel3 = true;// 是否显示3级菜单

	private boolean isShowMenu = true;// 是否显示整个菜单，包括1级，2级，3级的菜单

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setListener();
	}

	/**
	 * 初始化空间
	 */
	private void initView() {
		setContentView(R.layout.activity_main);
		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_menu = (ImageView) findViewById(R.id.iv_menu);
		level1 = (RelativeLayout) findViewById(R.id.level1);
		level2 = (RelativeLayout) findViewById(R.id.level2);
		level3 = (RelativeLayout) findViewById(R.id.level3);
	}

	/**
	 * 设置监听事件
	 */
	private void setListener() {
		iv_home.setOnClickListener(this);
		iv_menu.setOnClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {

			//当动画都结束的时候才可以操作
			if(AnimUtil.animCount != 0) {
				return true;
			}
			if (isShowMenu) {
				// 需要关闭所有菜单
				int startOffset = 0;
				if (isShowLevel3) {
					AnimUtil.showOrChoseMenu(level3, startOffset,false);
					isShowLevel3 = false;
					startOffset += 200;
				}
				if (isShowLevel2) {
					AnimUtil.showOrChoseMenu(level2, startOffset, false);
					isShowLevel2 = false;
					startOffset += 200;
				}

				AnimUtil.showOrChoseMenu(level1, startOffset, false);

			} else {
				// 需要显示所有菜单
				AnimUtil.showOrChoseMenu(level1, 0, true);
				AnimUtil.showOrChoseMenu(level2, 200, true);
				isShowLevel2 = true;
				AnimUtil.showOrChoseMenu(level3, 400, true);
				isShowLevel3 = true;

			}
			isShowMenu = !isShowMenu;

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_home:
			//当动画都结束的时候才可以操作
			if (AnimUtil.animCount != 0) {
				// 说明有动画在执行
				return;
			}
			if (isShowLevel2) {
				// 需要隐藏
				int startOffset = 0;
				if (isShowLevel3) {
					AnimUtil.showOrChoseMenu(level3, startOffset, false);
					startOffset += 200;//因为菜单3先隐藏 所以给菜单200秒的延时
					isShowLevel3 = false;
				}

				AnimUtil.showOrChoseMenu(level2, startOffset, false);
			} else {
				// 需要显示
				// Log.e(tag, "执行显示操作");
				AnimUtil.showOrChoseMenu(level2, 0, true);
			}
			isShowLevel2 = !isShowLevel2;
			break;
		case R.id.iv_menu:
			//当动画都结束的时候才可以操作
			if (AnimUtil.animCount != 0) {
				// 说明有动画在执行
				return;
			}
			if (isShowLevel3) {
				// 隐藏3级菜单
				AnimUtil.showOrChoseMenu(level3, 0, false);
			} else {
				// 显示3级菜单
				AnimUtil.showOrChoseMenu(level3, 0, true);
			}
			isShowLevel3 = !isShowLevel3;
			break;
		default:
			break;
		}
	}

}
