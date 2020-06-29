package com.udemymasterclass;

import java.sql.SQLOutput;
import java.util.*;

public class PlayList {
    private ArrayList<Album> albums;
    private LinkedList<Song> songList;

    public PlayList(ArrayList<Album> albums, LinkedList<Song> songList) {
        this.albums = albums;
        this.songList = songList;
    }

    public PlayList() {
        this(new ArrayList<Album>(), new LinkedList<Song>());
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        if (findAlbum(album.getTitle())) {
            System.out.println(album.getTitle() + " already exists");
        } else {
            this.albums.add(album);
        }
    }

    public boolean findAlbum(String title) {
        Iterator<Album> i = getAlbums().iterator();
        while (i.hasNext()) {
            Album a = i.next();
            if (a.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void addSong(Song song) {

        songList.add(song);
    }

    public LinkedList<Song> getSongList() {
        return songList;
    }

    public void play() {
        ListIterator<Song> i = getSongList().listIterator();
        Song s = new Song("No Song Selected", "0:00");
        boolean quit = false;
        boolean forward = true;
        if (i.hasNext()) {
            s = i.next();
            System.out.println(s.getTitle());
        } else {
            System.out.println("Playlist is empty");
            quit = true;
        }
        Scanner sc = new Scanner(System.in);
        while (!quit) {
            System.out.println("Current Song: " + s.getTitle());
            System.out.println("Choose an option: ");
            System.out.println("1: Play\n2: Next Song\n3: Previous Song\n4: Replay Current\n5: List Songs\n6: Remove Current\n7: Quit");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                case 4:
                    System.out.println(s.getTitle() + " is playing for " + s.getDuration());
                    break;
                case 2:
                    if (!forward) {
                        if (i.hasNext()) {
                            i.next();
                            forward = true;
                        } else {
                            System.out.println("Error can't move forward");
                            break;
                        }
                    }
                    if (i.hasNext()) {
                        s = i.next();
                        System.out.println(s.getTitle() + " is playing for " + s.getDuration());
                    } else {
                        System.out.println("At end of playlist");
                    }
                    break;
                case 3:
                    if (forward) {
                        if (i.hasPrevious()) {
                            forward = false;
                            i.previous();
                        } else {
                            System.out.println("Error can't move backwards");
                            break;
                        }
                    }
                    if (i.hasPrevious()) {
                        s = i.previous();
                        System.out.println(s.getTitle() + " is playing for " + s.getDuration());
                    } else {
                        System.out.println("At beginning of playlist");
                    }
                    break;
                case 5:
                    printPlayList();
                    break;
                case 6:
                    i.remove();
                    break;
                case 7:
                    System.out.println("Quitting...");
                    quit = true;
                    break;
            }
        }
    }

    private void printPlayList() {
        Iterator<Song> i = getSongList().iterator();
        while (i.hasNext()) {
            Song s = i.next();
            System.out.println("Song Title: " + s.getTitle() + " Duration: " + s.getDuration());
        }
    }

}
// Songs from different albums can be added to the playlist and will appear in the list in the order
// they are added.
// Once the songs have been added to the playlist, create a menu of options to:-
// Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
// List the songs in the playlist
// A song must exist in an album before it can be added to the playlist (so you can only play songs that
// you own).
// Hint:  To replay a song, consider what happened when we went back and forth from a city before we
// started tracking the direction we were going.
// As an optional extra, provide an option to remove the current song from the playlist
// (hint: listiterator.remove()