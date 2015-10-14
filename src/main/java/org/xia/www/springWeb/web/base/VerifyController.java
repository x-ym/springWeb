package org.xia.www.springWeb.web.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证码(大写字母、小写字母、数字、中文)
 * @date 2013-8-24 下午5:00:43
 * @version 1.0
 */
@Controller
public class VerifyController {
	/** 定义存放在Session中的验证码 */
	public static final String VERIFY_CODE = "verify_code";
	
	// 定义生成图片的宽度
	private static final int IMG_WIDTH = 55;
	// 定义生成图片的高度
	private static final int IMG_HEIGHT = 22;
	// 定义字体
	private static Font font = new Font("方正", Font.PLAIN, 18);
	// 定义随机对象
	private static Random random  = new Random();

	@RequestMapping
	public static String getVerifyCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//HttpServletResponse response = ServletActionContext.getResponse();
		// 设置响应的类型
		response.setContentType("image/jpeg; charset=utf-8");
		// 创建图片缓冲流对象
		BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 获取绘图对象(画笔)
		Graphics g = image.getGraphics();
		// 填充一个矩形框
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
		// 设置画笔的颜色
		g.setColor(Color.BLACK);
		// 绘制一个矩形框
		g.drawRect(0, 0, IMG_WIDTH - 1, IMG_HEIGHT - 1);
		
		
		// 绘制干扰线
		for (int i = 0; i < 100; i++){
			// 设置颜色
			g.setColor(new Color(200 + random.nextInt(55),
							     200 + random.nextInt(55),
					             200 + random.nextInt(55)));
			int x1 = 2 + random.nextInt(IMG_WIDTH - 4);
			int y1 = 2 +random.nextInt(IMG_HEIGHT - 4);
			int x2 = 2 + random.nextInt(IMG_WIDTH - 4);
			int y2 = 2 +random.nextInt(IMG_HEIGHT - 4);
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 设置字体大小
		g.setFont(font);
		
		// 绘制验证码(4位)
		String res = "";
		for (int i = 0; i < 4; i++){
			String str = generatorVerify();
			// 设置颜色
			g.setColor(new Color(20 + random.nextInt(110),
							     20 + random.nextInt(110),
					             20 + random.nextInt(110)));
			g.drawString(str, i * 13 + 3, 17);
			res += str;
		}
		System.out.println("最终的验证码：" + res);
		
		request.getSession().setAttribute(VERIFY_CODE, res);
		// 消毁绘图对象
		g.dispose();
		// 输出
		ImageIO.write(image, "jpeg", response.getOutputStream());
		
		return null;
	}
	
	/**
	 * 随机生成(大写字母、小写字母、数字、中文)
	 * @return
	 */
	public static String generatorVerify(){
		// 随机生成0-3之间的数字
//		int witch = (int)Math.round(Math.random() * 2);
//		switch (witch){
//			case 0: // 大写字母 (A-Z: 65-90)
//				long temp = Math.round(Math.random() * 25 + 65);
//				return String.valueOf((char)temp);
//			case 1: // 小写字母(a-z: 97-122)
//				temp = Math.round(Math.random() * 25 + 97);
//				return String.valueOf((char)temp);
//			case 2: // 数字(0-9: 48-57)
//				
//			default: // 中文(0x4E00-0x9FBF)
//				temp = Math.round(Math.random() * 500 + 0x4E00);
//				return String.valueOf((char)temp);
//		}
		return String.valueOf(Math.round(Math.random() * 9));
		
	}
	
	public void setRandom(String random){}
}