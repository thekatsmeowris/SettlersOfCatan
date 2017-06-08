/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author dano2080
 */
public class Graphics {

	Image marsImage, spriteImage, spaceImage, hempImage, waterImage, glassImage, oreImage, image;
	static ImageView marsView, spriteImageView, spaceView, hempView, waterView, oreView, imageView;
	static StackPane graphicsPane;
	Canvas canvas;

	private static final int SPRITE_COLUMNS = 5;
	private static final int SPRITE_COUNT = 5;
	private static final int SPRITE_OFFSET_X = 0;
	private static final int SPRITE_OFFSET_Y = 2;
	private static final int SPRITE_WIDTH = 95;
	private static final int SPRITE_HEIGHT = 168;

	public Graphics() {
		// GraphicsContext gc = canvas.getGraphicsContext2D();
		// image = new Image("/mars.jpg");
		// view = new ImageView(image);
		// canvas.add(view);

		// GraphicsContext gc = canvas.getGraphicsContext2D();
		spaceImage = new Image(getClass().getResourceAsStream("graphics/space-background.png"));
		spaceView = new ImageView(spaceImage);

		spaceView.setRotate(270);
		spaceView.setScaleX(1.15);
		spaceView.setScaleY(1.15);

		spaceView.setTranslateY(70);
		spaceView.setTranslateX(-285);

		graphicsPane = new StackPane();
		graphicsPane.getChildren().addAll(spaceView);
		// graphicsPane.setBackground(Background.EMPTY);

	}

	public static StackPane getGraphicsPane() {
		return graphicsPane;
	}

	public ImageView getStormSprite() {
		spriteImageView.setTranslateX(-30.0);
		spriteImageView.setTranslateY(-50.0);
		spriteImage = new Image(getClass().getResourceAsStream("graphics/Tornado.png"));
		final ImageView spriteImageView = new ImageView(spriteImage);
		spriteImageView.setViewport(new Rectangle2D(SPRITE_OFFSET_X, SPRITE_OFFSET_Y, SPRITE_WIDTH, SPRITE_HEIGHT));
		spriteImageView.setRotate(270);
		spriteImageView.setFitHeight(50);
		spriteImageView.setFitWidth(50);
		final Animation animation = new SpriteAnimation(spriteImageView, Duration.millis(500), SPRITE_COUNT,
				SPRITE_COLUMNS, SPRITE_OFFSET_X, SPRITE_OFFSET_Y, SPRITE_WIDTH, SPRITE_HEIGHT);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		return spriteImageView;
	}

	// public ImageView get
}

// MarsCanvas = new Canvas(790, 790);
// final GraphicsContext graphicsContext = MarsCanvas.getGraphicsContext2D();
// marsImage = new Image(getClass().getResourceAsStream("graphics/MapAr.jpg"));
// + graphicsContext.drawImage(marsImage, 130, 110, 480, 480);
// +
// + spriteImageView.setTranslateX(-30.0);
// + spriteImageView.setTranslateY(-50.0);
// spriteImage = new
// Image(getClass().getResourceAsStream("graphics/Tornado.png"));
// + final ImageView spriteImageView = new ImageView(spriteImage);
// + spriteImageView.setViewport(new Rectangle2D(SPRITE_OFFSET_X,
// SPRITE_OFFSET_Y, SPRITE_WIDTH, SPRITE_HEIGHT));
// + spriteImageView.setRotate(270);
// + spriteImageView.setFitHeight(50);
// + spriteImageView.setFitWidth(50);
// + final Animation animation = new SpriteAnimation(
// + spriteImageView,
// + Duration.millis(500),
// + SPRITE_COUNT, SPRITE_COLUMNS,
// + SPRITE_OFFSET_X, SPRITE_OFFSET_Y,
// + SPRITE_WIDTH, SPRITE_HEIGHT
// + );
// + animation.setCycleCount(Animation.INDEFINITE);
// + animation.play();
// Image marsImage,spriteImage; //Image of Mars and sprite sheet of tornado
// + private static final int SPRITE_COLUMNS = 5;
// + private static final int SPRITE_COUNT = 5;
// + private static final int SPRITE_OFFSET_X = 0;
// + private static final int SPRITE_OFFSET_Y = 2;
// + private static final int SPRITE_WIDTH = 95;
// + private static final int SPRITE_HEIGHT = 168;
