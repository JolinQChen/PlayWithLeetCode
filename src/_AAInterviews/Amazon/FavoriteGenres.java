package _AAInterviews.Amazon;

import java.util.*;
public class FavoriteGenres {
    public static Map<String, List<String>> favoriteGenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        Map<String, List<String>> resMap = new HashMap<>();

        Map<String, String> songGenreMap = new HashMap<>();
        for(String genre:genreMap.keySet()) {
            List<String> songs = genreMap.get(genre);
            for(String song:songs) songGenreMap.put(song, genre);
        }


        for(String user:userMap.keySet()) {
            Map<String, Integer> countMap = new HashMap<>(); // song-count
            int max = 0;
            resMap.put(user,new ArrayList<>());
            List<String> songs = userMap.get(user);
            for(String song:songs) {
                String genre = songGenreMap.get(song);
                int c = countMap.getOrDefault(genre,0)+1;
                countMap.put(genre,c);
                max = Math.max(max, c);
            }
            for(String genre:countMap.keySet()) {
                if(countMap.get(genre)==max) resMap.get(user).add(genre);
            }
        }

        return resMap;
    }

}
