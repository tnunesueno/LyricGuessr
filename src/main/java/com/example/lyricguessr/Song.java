package com.example.lyricguessr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import java.io.Serial;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Song {
    String lyrics;

    static ArrayList<Song> allSongs = new ArrayList<Song>();
    @JsonProperty("song")
    String songName;

    @JsonProperty("artist")
    String artist;

    @JsonProperty("this_week")
    Integer thisWeek;

    @JsonProperty("last_week")
    Integer lastWeek;

    @JsonProperty("peak_position")
    Integer peakPos;

    @JsonProperty("weeks_on_chart")
    Integer weeksOnChart;

    @JsonProperty("link")
    URL imageLink;

    // public Lyric lyrics;

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

    public Integer getThisWeek() {
        return thisWeek;
    }

    public Integer getLastWeek() {
        return lastWeek;
    }

    public Integer getPeakPos() {
        return peakPos;
    }

    public Integer getWeeksOnChart() {
        return weeksOnChart;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setThisWeek(Integer thisWeek) {
        this.thisWeek = thisWeek;
    }

    public void setLastWeek(Integer lastWeek) {
        this.lastWeek = lastWeek;
    }

    public void setPeakPos(Integer peakPos) {
        this.peakPos = peakPos;
    }

    public void setWeeksOnChart(Integer weeksOnChart) {
        this.weeksOnChart = weeksOnChart;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public static ArrayList<Song> getAllSongs() {
        return allSongs;
    }

    public static void setAllSongs(ArrayList<Song> allSongs) {
        Song.allSongs = allSongs;
    }

    public URL getImageLink() {
        return imageLink;
    }

    public void setImageLink(URL imageLink) {
        this.imageLink = imageLink;
    }

    static String getJSONfromURL(String urlString, boolean configureConnection) throws Exception {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (configureConnection) {
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
            }
            InputStreamReader inStream = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inStream);
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    static public void getTop100() throws Exception {
        String JSONSongs = getJSONfromURL("https://raw.githubusercontent.com/mhollingshead/billboard-hot-100/main/recent.json", false);
        // System.out.println(JSONSongs);

        // Read JSON objects using JsonNode after readTree()
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(JSONSongs);
        // By reading the JSON tree, the code can now get individual "key":"value" pairs
        // The value of the "result" key is an ARRAY of JSON objects
        JsonNode arrayOfSongs = jsonNode.get("data");
        for (JsonNode eachSong : arrayOfSongs) {
            // read 1 JSON object (its "key":"value" pairs) into the fields of a ChuckNorrisJoke object.
            Song newSong = new Song();
            newSong.setSongName(eachSong.get("song").asText());
            newSong.setArtist(eachSong.get("artist").asText());
            newSong.setThisWeek(eachSong.get("this_week").asInt());
            newSong.setLastWeek(eachSong.get("last_week").asInt());
            newSong.setPeakPos(eachSong.get("peak_position").asInt());
            newSong.setWeeksOnChart(eachSong.get("weeks_on_chart").asInt());
            allSongs.add(newSong);
            System.out.println(newSong.getSongName() + " by " + newSong.getArtist());
        }
    }

    // https://private-anon-2c323ffa75-lyricsovh.apiary-proxy.com/v1/Coldplay/Adventure%20of%20a%20Lifetime

    static public Lyric getLyricsFromSong(Song song) throws Exception {
        String artistFormat = song.getArtist();
        int andSign = artistFormat.indexOf("&");
        if (andSign != -1) {
            artistFormat = artistFormat.substring(0, andSign - 1);
            System.out.println("snipped name: " + artistFormat);
        }
        int featuring = artistFormat.indexOf("Featuring");
        if (featuring != -1) {
            artistFormat = artistFormat.substring(0, featuring - 1);
            System.out.println("snipped name: " + artistFormat);
        }

        artistFormat=artistFormat.replace(" ", "%20");
        String songFormat = song.getSongName().replace(" ", "%20");


        String JSONLyrics = getJSONfromURL("https://private-anon-cb9a4ba16c-lyricsovh.apiary-proxy.com/v1/" + artistFormat + "/" + songFormat, true);
        System.out.println("URL THAT WE ARE TRYING: " + "https://private-anon-cb9a4ba16c-lyricsovh.apiary-proxy.com/v1/" + artistFormat + "/" + songFormat);
        if (JSONLyrics == null) {
            return null;
        }
        System.out.println("JSON Lyrics: " + JSONLyrics);

        ObjectMapper objectMapper = new ObjectMapper();
        Lyric lyrics = objectMapper.readValue(JSONLyrics, Lyric.class);
        System.out.println("song: " + song.getSongName() + " real lyrics: " + lyrics);
        String lyricWords = lyrics.getLyrics();

        song.setLyrics(lyricWords);
        return lyrics;
    }

    static public Lyric randomLyrics() throws Exception {
        Random random = new Random();
        int songNum = random.nextInt(99);
        Song chosenSong = allSongs.get(songNum);
        if(chosenSong.getArtist().equals("Morgan Wallen")){
            songNum= random.nextInt(99);
            chosenSong = allSongs.get(songNum);

        }
        Lyric lyrics = getLyricsFromSong(chosenSong);
        if (lyrics == null) {
            System.out.println("MY ERROR:" + allSongs.get(songNum).getSongName() + " Gave null");
            if (songNum < 37) {
                songNum = songNum + 1;
                lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
                if (lyrics == null) {
                    System.out.println("MY ERROR 2:" + allSongs.get(songNum).getSongName() + " Gave null");
                }
            } else {
                songNum = songNum - 1;
                lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
            }

            if (lyrics == null) {
                System.out.println("MY ERROR 2:" + allSongs.get(songNum).getSongName() + " Gave null");
                songNum = random.nextInt(49);
                lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
            }
            if (lyrics == null) {
                System.out.println("MY ERROR 3:" + allSongs.get(songNum).getSongName() + " Gave null");
                songNum = random.nextInt(49);
                lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
            }
            if (lyrics == null) {
                System.out.println("MY ERROR 4:" + allSongs.get(songNum).getSongName() + " Gave null");
                songNum = random.nextInt(49);
                lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
            }
            if (lyrics == null) {
                System.out.println("MY ERROR 5:" + allSongs.get(songNum).getSongName() + " Gave null");
                songNum = random.nextInt(49);
                lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
            }
        }

            lyrics.fillArray();
            System.out.println("SIZE FROM RANDOM LYRICS: " + lyrics.getLyricArray().size());
            lyrics.setSong(Song.allSongs.get(songNum));
            return lyrics;
        }
   // https://customsearch.googleapis.com/customsearch/v1?key=AIzaSyBixnwAc3dICuzHev9klwJgzLn0Vf5VkMQ&q=too%20sweet%20by%20hozier%20album%20cover&cx=4119d58e961e7488f&searchType=image
      static public URL getAlbumArt(Song song) throws Exception {
        String q = song.getSongName().replaceAll(" ","%20")+"%20"+song.getArtist().replaceAll(" ","%20");
        String JSON = getJSONfromURL("https://customsearch.googleapis.com/customsearch/v1?key=AIzaSyBixnwAc3dICuzHev9klwJgzLn0Vf5VkMQ&q="+ q +"&cx=4119d58e961e7488f&searchType=image", false);
        ObjectMapper objectMapper = new ObjectMapper();
        // for this to work get the link section from the items thing
          // Read JSON objects using JsonNode after readTree()
          JsonNode jsonNode = objectMapper.readTree(JSON);
          // By reading the JSON tree, the code can now get individual "key":"value" pairs
          // The value of the "result" key is an ARRAY of JSON objects
          JsonNode arrayOfItems = jsonNode.get("items");
          JsonNode firstItem = arrayOfItems.get(0);
          URL imageURL = new URL(firstItem.get("link").asText());
          System.out.println(imageURL);
          song.setImageLink(imageURL);
          return imageURL;
        }
    }

