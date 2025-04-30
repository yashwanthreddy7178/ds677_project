package songsDAC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.*;

public class Album {
	public String title, recordLabel, id, releaseYear;
	Band band;
	List<Song> songs;
	
	public Album(String albumID) {
		getInfoFromDB(albumID);
	}
	
	public Album(String title, String bandID) {
		getInfoFromDB(title, bandID);
	}
	
	public void getInfoFromDB(String albumID) {
		String query = "SELECT * FROM Albums WHERE album_id = " + albumID + ";";
		try {
			SQLiteConnection conn = new SQLiteConnection(DBInfo.DBFILEPATH, DBInfo.DB_NAME);
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(query);
			if(results.next()) {
				title = results.getString("title");
				recordLabel = results.getString("record_label");
				id = results.getString("album_id");
				releaseYear = results.getString("release_year");
				band = new Band(results.getString("band_id"));
				
				String songsQuery = "SELECT * FROM Songs WHERE album_id = '" + id + "';";
				Statement songsStatement = conn.createStatement();
				ResultSet songsResults = songsStatement.executeQuery(songsQuery);
				songs = new ArrayList<Song>();
				ArrayList<Integer> songIDs = new ArrayList<Integer>();
				while(songsResults.next()) {
					songIDs.add(songsResults.getInt("song_id"));
				}		
				conn.close();
				for(Integer songID: songIDs) {
					songs.add(new Song(songID.toString()));
				}
			}
			else {
				title = "";
				recordLabel = "";
				id = "";
				releaseYear = "";
				band = null;
				songs = new ArrayList<Song>();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			title = "";
			recordLabel = "";
			id = "";
			releaseYear = "";
			band = null;
			songs = new ArrayList<Song>();
		}
	}

	public void getInfoFromDB(String title, String bandID) {
		String query = "SELECT * FROM Albums WHERE title = '" + title + "' AND band_id = '" + bandID + "';";
		try {
			SQLiteConnection conn = new SQLiteConnection(DBInfo.DBFILEPATH, DBInfo.DB_NAME);
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(query);
			if(results.next()) {
				title = results.getString("title");
				recordLabel = results.getString("record_label");
				id = results.getString("album_id");
				releaseYear = results.getString("release_year");
				band = new Band(results.getString("band_id"));
				
				String songsQuery = "SELECT * FROM Songs WHERE album_id = '" + id + "';";
				Statement songsStatement = conn.createStatement();
				ResultSet songsResults = songsStatement.executeQuery(songsQuery);
				songs = new ArrayList<Song>();
				ArrayList<Integer> songIDs = new ArrayList<Integer>();
				while(songsResults.next()) {
					songIDs.add(songsResults.getInt("song_id"));
				}		
				conn.close();
				for(Integer songID: songIDs) {
					songs.add(new Song(songID.toString()));
				}	
			}
			else {
				title = "";
				recordLabel = "";
				id = "";
				releaseYear = "";
				band = null;
				songs = new ArrayList<Song>();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			title = "";
			recordLabel = "";
			id = "";
			releaseYear = "";
			band = null;
			songs = new ArrayList<Song>();
		}
	}
}
