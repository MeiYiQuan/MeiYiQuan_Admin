package com.neiquan.meiyiquan.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
// 生成图片验证码的工具类
public class ImageCodeUtil {
	// 设置宽
	private static final int WIDTH=65;
	// 设置高
	private static final int HEIGHT=30;
	// 设置验证内容的选择集(36个)
	private static final String SRC="0123456789QWERTYUIOPLKJHGFDSAZXCVBNM";
	// 用于生产随机数，在从选择集中选择元素的时候需要随机出若干下标
	private static final Random random=new Random();
	
	// 此方法生产出成品的图片，外界就是通过调用这个方法得到一个验证码图片
	public static BufferedImage createImageCode(String code){
		// 获取图片对象
		BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		// 获取画笔
		Graphics gra=image.getGraphics();
		// 画背景和干扰线
		drawBackground(gra);
		// 将验证码画入背景中
		drawFont(gra,code);
		// 释放资源
		gra.dispose();
		// 返回图片对象
		return image;
	}
	// 由于外界还要得到一个验证码的字符串来进行验证是否输入正确，所以这里有个获得字符串验证码的方法
	public static String getStringCode(){
		// 获得用于存储字符验证码的对象
		StringBuffer strcode=new StringBuffer();
		// 用for循环产生SRC中的单个字符，并拼接在strcode中,这里验证码是5位
		for(int i=1;i<=5;i++){
			strcode.append(SRC.charAt(random.nextInt(36)));
		}
		return strcode.toString();
	}
	
	
	// 画背景和干扰线
	private static void drawBackground(Graphics gra){
		// 设置画笔颜色，这里为灰色
		gra.setColor(new Color(0xDDDDDD));
		// 用这个颜色填充矩形
		gra.fillRect(0, 0, WIDTH, HEIGHT);
		// 以上对于灰色的背景已经画完，现在来画干扰线和点
		// 画干扰线
		drawLi(gra);
		// 画点
		drawOv(gra);
	}
	// 画点
	private static void drawOv(Graphics gra) {
		// 这里设置出现20个干扰点
		for(int i=1;i<=20;i++){
			// 随机产生顶点
			int x=random.nextInt(WIDTH);	// 顶点横坐标
			int y=random.nextInt(HEIGHT);	// 顶点纵坐标
			// 随机产生颜色
			int red=random.nextInt(255);	// 每种颜色的最大值255，固定的
			int green=random.nextInt(255);
			int blue=random.nextInt(255);
			// 用随机生成的颜色来设置画笔的颜色
			gra.setColor(new Color(red,green,blue));
			// 画点，前两个参数是起始坐标，后两个参数是粗细和高低，后两个参数随机产生
			int r=random.nextInt(5);	// 设置点的直径在5以内
			gra.drawOval(x, y, r, r);
		}
	}
	// 画干扰线
	private static void drawLi(Graphics gra) {
		// 这里设置5条干扰线
		for(int i=1;i<=5;i++){
			// 随机产生顶点和结束点
			int beginX=random.nextInt(WIDTH);
			int beginY=random.nextInt(HEIGHT);
			int endX=random.nextInt(WIDTH);
			int endY=random.nextInt(HEIGHT);
			// 随机产生颜色
			int red=random.nextInt(255);
			int green=random.nextInt(255);
			int blue=random.nextInt(255);
			// 设置画笔颜色
			gra.setColor(new Color(red,green,blue));
			// 画出干扰线（忽略粗细）
			gra.drawLine(beginX, beginY, endX, endY);
		}
	}
	// 将验证码画入背景中
	private static void drawFont(Graphics gra,String code){
		// 设置画笔颜色
		gra.setColor(Color.BLUE);
		// 设置字体(参数含义依次为：字体，字的粗细，字号)
		gra.setFont(new Font("宋体",Font.BOLD,18));
		// 将字符串的验证码画到背景中,单个单个的画入，并且有一定距离，不再一条直线上
		gra.drawString(code.charAt(0)+"", 2, 15);
		gra.drawString(code.charAt(1)+"", 15, 25);
		gra.drawString(code.charAt(2)+"", 27, 20);
		gra.drawString(code.charAt(3)+"", 40, 25);
		gra.drawString(code.charAt(4)+"", 50, 15);
	}
}