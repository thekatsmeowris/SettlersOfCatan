package som;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class AnimatedMedia {

	public final static int PLAYER1 = 1;
	public final static int PLAYER2 = 2;
	public final static int PLAYER3 = 3;
	public final static int PLAYER4 = 4;
	public final static int ROBBER = 5;
	public final static int KNIGHT = 6;
	public final static int MONOPOLY = 7;
	public final static int YEAR_OF_PLENTY = 8;
	public final static int VICTORY_POINT = 9;
	public final static int ROAD_BUILDING = 10;
	public final static int PLAYER1WIN = 11;
	public final static int PLAYER2WIN = 12;
	public final static int PLAYER3WIN = 13;
	public final static int PLAYER4WIN = 14;
	public final static int GAME_OVER = 15;

	private final static int NUMBER_OF_FILES = 15;

	private ArrayList<MediaView> mediaViews;
	private ArrayList<MediaPlayer> mediaPlayers;
	private ArrayList<Media> media;

	public AnimatedMedia() {
		mediaViews = new ArrayList<MediaView>();
		mediaPlayers = new ArrayList<MediaPlayer>();
		media = new ArrayList<Media>();

		for (int i = 1; i <= NUMBER_OF_FILES; i++) {
			System.out.println(getClass().getResource("animated/file" + i + ".flv").toExternalForm());
			media.add(new Media(getClass().getResource("animated/file" + i + ".flv").toExternalForm()));
			mediaPlayers.add(new MediaPlayer(media.get(i - 1)));
			mediaViews.add(new MediaView(mediaPlayers.get(i - 1)));
		}
	}

	public ArrayList<Media> getMedia() {
		return media;
	}

	public ArrayList<MediaPlayer> getMediaPlayers() {
		return mediaPlayers;
	}

	public ArrayList<MediaView> getMediaViews() {
		return mediaViews;
	}

	public void play(int fileNum) {
		mediaPlayers.get(fileNum - 1).play();
	}
}
