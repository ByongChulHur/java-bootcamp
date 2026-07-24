package com.academy.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ReportService {

    private final LibraryService libraryService;

    public ReportService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void displaySummaryReport() {
        int totalBooks = libraryService.getBooks().size();
        int borrowedBooks = libraryService.getBorrowRecords().size();
        int availableBooks = totalBooks - borrowedBooks;
        int totalMembers = libraryService.getMembers().size();
        String popularCategory = findMostPopularCategory();

        System.out.println("Reports");
        System.out.println("Books : " + totalBooks);
        System.out.println("Borrowed : " + borrowedBooks);
        System.out.println("Available : " + availableBooks);
        System.out.println("Members : " + totalMembers);
        System.out.println("Most Popular Category : " + popularCategory);
    }

    public Path exportReportToFile(String fileName) throws IOException {
        int totalBooks = libraryService.getBooks().size();
        int borrowedBooks = libraryService.getBorrowRecords().size();
        int availableBooks = totalBooks - borrowedBooks;
        int totalMembers = libraryService.getMembers().size();
        String popularCategory = findMostPopularCategory();
        StringBuilder content = new StringBuilder();
        content.append("Reports\n");
        content.append("Books : ").append(totalBooks).append("\n");
        content.append("Borrowed : ").append(borrowedBooks).append("\n");
        content.append("Available : ").append(availableBooks).append("\n");
        content.append("Members : ").append(totalMembers).append("\n");
        content.append("Most Popular Category : ").append(popularCategory).append("\n");
        content.append("\nBooks per Category:\n");
        for (Map.Entry<String, Integer> entry : libraryService.getCategoryBookCount().entrySet()) {
            content.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        Path outputPath = Path.of(fileName);
        Files.writeString(outputPath, content.toString());
        return outputPath;
    }

    private String findMostPopularCategory() {
        Map<String, Integer> categoryCounts = libraryService.getCategoryBookCount();
        if (categoryCounts.isEmpty()) {
            return "N/A";
        }
        String mostPopular = null;
        int highestCount = 0;
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            if (entry.getValue() > highestCount) {
                highestCount = entry.getValue();
                mostPopular = entry.getKey();
            }
        }
        return mostPopular;
    }
}
