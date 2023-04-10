package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MetadataProcess {
    public static void metadataAndRecordsMenu(MediaStreamingService mediaStreamingService, Scanner scanner) {
        int choice;
        do {
            System.out.println("Metadata and Records Menu");
            System.out.println("1. Enter play count for songs");
            System.out.println("2. Update play count for songs");
            System.out.println("3. Enter the count of monthly listeners for artists");
            System.out.println("4. Update the count of monthly listeners for artists");
            System.out.println("5. Enter the total count of subscribers and ratings for podcasts");
            System.out.println("6. Update the total count of subscribers and ratings for podcasts");
            System.out.println("7. Enter the listening count for podcast episodes");
            System.out.println("8. Update the listening count for podcast episodes");
            System.out.println("9. Find songs given artist");
            System.out.println("10. Find songs given album");
            System.out.println("11. Find podcast episodes given podcast");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            MetadataService metadataService = new MetadataService(mediaStreamingService);
            switch (choice) {
                case 1:
                    enterSongPlayCount(metadataService, scanner);
                    break;
                  case 2:
                    updateSongPlayCount(metadataService, scanner);
                    break;
                case 3:
                    enterArtistMonthlyListeners(metadataService, scanner);
                    break;
                case 4:
                    updateArtistMonthlyListeners(metadataService, scanner);
                    break;
                case 5:
                    enterPodcastSubscribersAndRatings(metadataService, scanner);
                    break;
                case 6:
                    updatePodcastSubscribersAndRatings(metadataService, scanner);
                    break;
                case 7:
                    enterPodcastEpisodeListeningCount(metadataService, scanner);
                    break;
                case 8:
                    updatePodcastEpisodeListeningCount(metadataService, scanner);
                    break;
                case 9:
                    findSongsGivenArtist(metadataService, scanner);
                    break;
                case 10:
                    findSongsGivenAlbum(metadataService, scanner);
                    break;
                case 11:
                    findPodcastEpisodesGivenPodcast(metadataService, scanner);
                    break;
            }
        } while (choice != 0);
    }
    private static void enterArtistMonthlyListeners(MetadataService metadataService, Scanner scanner){

    }
    private static void updateArtistMonthlyListeners(MetadataService metadataService, Scanner scanner) {
        metadataService.updateMonthlyListenerForArtists();
    }
    private static void enterPodcastSubscribersAndRatings(MetadataService metadataService, Scanner scanner){

    }
    private static void updatePodcastSubscribersAndRatings(MetadataService metadataService, Scanner scanner){
        try {
            metadataService.connection.setAutoCommit(false);
            metadataService.updateTheAvgRatingOfPodcast();
            metadataService.updateTotalCountOfSubscribers();
            metadataService.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                metadataService.connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    private static void enterPodcastEpisodeListeningCount(MetadataService metadataService, Scanner scanner){

    }
    private static void updatePodcastEpisodeListeningCount(MetadataService metadataService, Scanner scanner){

    }
    private static void findPodcastEpisodesGivenPodcast(MediaStreamingService mediaStreamingService, Scanner scanner) {
        System.out.print("Enter the podcast name: ");
        scanner.nextLine();
        String podcastName = scanner.nextLine();

        List<String> episodeTitles = mediaStreamingService.findEpisodesByPodcast(podcastName);

        System.out.println("Episodes in the podcast " + podcastName + ":");
        for (String episodeTitle : episodeTitles) {
            System.out.println(episodeTitle);
        }
    }

    private static void findSongsGivenAlbum(MediaStreamingService mediaStreamingService, Scanner scanner) {
        System.out.print("Enter the album name: ");
        scanner.nextLine();
        String albumName = scanner.nextLine();

        List<String> songTitles = mediaStreamingService.findSongsByAlbum(albumName);

        System.out.println("Songs in the album " + albumName + ":");
        for (String songTitle : songTitles) {
            System.out.println(songTitle);
        }
    }

    private static void findSongsGivenArtist(MediaStreamingService mediaStreamingService, Scanner scanner) {
        System.out.print("Enter the artist name: ");
        scanner.nextLine();
        String artistName = scanner.nextLine();

        List<String> songTitles = mediaStreamingService.findSongsByArtist(artistName);

        System.out.println("Songs by " + artistName + ":");
        for (String songTitle : songTitles) {
            System.out.println(songTitle);
        }
    }

    private static void enterSongPlayCount(MetadataService metadataService,Scanner scanner){
        System.out.println("Enter the user ID:");
        int userId = scanner.nextInt();
        System.out.println("Enter the song ID:");
        int songId = scanner.nextInt();
        System.out.println("Enter month (YYYY-MM format):");
        String month = scanner.next();

        System.out.println("Enter the listened times:");
        int count = scanner.nextInt();
        metadataService.addUserListenedSong(userId, songId, month, count);
    }
    private static void updateSongPlayCount(MetadataService metadataService, Scanner scanner){
        metadataService.updateMonthlyListenerForSongs();
    }

}
